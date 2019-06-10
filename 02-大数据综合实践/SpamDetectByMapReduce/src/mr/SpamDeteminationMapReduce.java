package mr;

import bean.ServerContext;
import bean.WordInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import redis.clients.jedis.Jedis;
import service.SpamDeterminationService;
import util.HDFSOps;
import util.RedisPool;

import java.io.IOException;


public class SpamDeteminationMapReduce {

	public void beginMR() throws Exception {
		SpamDeterminationService biz = new SpamDeterminationService();
		biz.setGlobalCounterValue("CounterSpam", 0);
		biz.setGlobalCounterValue("CounterHam", 0);
		Configuration conf = new Configuration();
		String[] otherArgs = new GenericOptionsParser(conf, new String[] {
				ServerContext.HDFS_ROOT+"spamdetermination/SMSSpamCollection",
				ServerContext.HDFS_ROOT+"spamdetermination/output" })
				.getRemainingArgs();

		Job job = Job.getInstance(conf, "wc");

		// Job job = new Job(conf, "word count");
		job.setJarByClass(SpamDeteminationMapReduce.class);
		job.setMapperClass(SpamDeteminationLearningDataMapper.class);
		// job.setCombinerClass(SpamDeteminationLearningDataReducer.class);

		job.setReducerClass(SpamDeteminationLearningDataReducer.class);
		job.setOutputKeyClass(Text.class);

		job.setOutputValueClass(Text.class);
		FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		// job.setNumReduceTasks(2);
		// job.setPartitionerClass(SpamDeteminationLearningDataPartitioner.class);
		job.waitForCompletion(true);
		HDFSOps hdfs = new HDFSOps(ServerContext.HDFS_ROOT);
		hdfs.deleteFile("/spamdetermination/output");
	}

	// static Jedis jedis1 = new Jedis("192.168.1.119");
	public void beginMRJob() {

	}

	public static class SpamDeteminationLearningDataMapper extends
			Mapper<Object, Text, Text, Text> {
		SpamDeterminationService biz = new SpamDeterminationService();
		Text ham = new Text("ham");
		Text spam = new Text("spam");

		public void map(Object key, Text value, Context context)
				throws IOException, InterruptedException {

			// System.out.println(value);
			String data = value.toString();

			String[] datas = data.split("	");
			// 对消息体本身进行简单分词（本学习数据均为英文数据，因此可以利用空格进行自然分词，但是直接用空格分割还是有些简单粗暴，因为没有处理标点符号，大家可以对其进行扩展，先用正则表达式处理标点符号后再进行分词，也可以扩展加入中文的分词功能）
			String[] words = datas[1].split(" ");

			// 判定本条消息是否为有效消息
			if ("ham".equals(datas[0])) {
				// 内置的Counter无法在Map和Reduce中共享数据，只能在任务彻底完成后获取正确的数据
//				 context.getCounter(MsgConter.ConterHam).increment(1);
				biz.globalCounterValueIncrement("CounterHam", 1);

				// 遍历消息的分词结果
				for (String word : words) {
					// System.out.println("单词" + word + "出现在了有效信息中");
					context.write(new Text(word), ham);

				}
				// 如果该消息为垃圾消息
			} else {
				// 将其加入垃圾消息集合
//				context.getCounter(MsgConter.ConterSpam).increment(1);
				biz.globalCounterValueIncrement("CounterSpam", 1);
				// 循环遍历分词结果
				for (String word : words) {
					// System.out.println("单词" + word + "出现在了垃圾信息中");
					context.write(new Text(word), spam);
				}
			}

		}
	}



	public static class SpamDeteminationLearningDataReducer extends
			Reducer<Text, Text, Text, Text> {
		// Jedis jedis = new Jedis("192.168.1.119");

		long spamNum = -1;
		long hamNum = -1;
		SpamDeterminationService biz = new SpamDeterminationService();

		public void reduce(Text key, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {

			if (spamNum == -1 || hamNum == -1) {

				// System.out.println("------------------------------------"+biz.getGlobalCounterValue("CounterSpam"));
				// context.getCounter(MsgConter.ConterSpam).
				// context.getCounter(MsgConter.ConterSpam).increment(1);
				// System.out.println(context.getCounter(MsgConter.ConterSpam).getValue());
				// spamNum =
				// context.getCounter(MsgConter.ConterSpam).getValue();
				// hamNum = context.getCounter(MsgConter.ConterHam).getValue();
				spamNum = biz.getGlobalCounterValue("CounterSpam");
				hamNum = biz.getGlobalCounterValue("CounterHam");
				System.out.println("学习数据中有效消息的条目数是:" + hamNum + ",垃圾消息的条目数是:"
						+ spamNum);
			}
			Jedis jedis = RedisPool.getJedis(false);

			WordInfo word = new WordInfo();
			word.setWord(key.toString());
			for (Text value : values) {
				if ("ham".equals(value.toString())) {
					word.setHamNum(word.getHamNum() + 1);
				} else {
					word.setSpamNum(word.getSpamNum() + 1);
				}
			}
			word.setWordHamPossibility(computeWordHamPossibility(word
					.getHamNum()));
			word.setWordSpamPossibility(computeWordSpamPossibility(word
					.getSpamNum()));
			try {
				jedis.set(key.toString().getBytes(),
						word.saveInstanceToBytaArray());
				// System.out.println("单词"+key+"的统计数据信息被存入Redis");
				//
				// System.out.println("--------------------------------------");
				// System.out.println(WordInfo.getInstanceByByteArray(jedis.get(key.getBytes())).getWord());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			RedisPool.returnResource(jedis);
		}

		/**
		 * 利用贝叶斯分类计算出现了特定单词的消息为有效消息的概率比例，在计算中使用了拉普拉斯平滑处理（即将总体数目和有效信息存在的数目都加1，
		 * 防止出现0概率 ），贝叶斯概率表达式：：P(B|A) = P(A|B)*P(B)/P(A)
		 *
		 * @param wordHamNum
		 *            要计算的单词
		 * @return 出现了该单词的消息为有效消息的概率比例（经过了拉普拉斯平滑处理）
		 * */
		float computeWordHamPossibility(int wordHamNum) {

			// 计算贝叶斯分类概率，+1:拉普拉斯平滑处理
			float result = ((float) wordHamNum / (float) (hamNum + 1))
					* ((float) (hamNum + 1) / (float) (hamNum + spamNum + 1))
					/ (((float) wordHamNum + 1) / (float) (hamNum + spamNum + 1));
			// 返回计算结果
			return result;

		}

		/**
		 * 利用贝叶斯分类计算出现了特定单词的消息为垃圾消息的概率比例，在计算中使用了拉普拉斯平滑处理（即将总体数目和有效信息存在的数目都加1，
		 * 防止出现0概率 ），贝叶斯概率表达式：：P(B|A) = P(A|B)*P(B)/P(A)
		 *
		 * @param wordSpamNum
		 *            要计算的单词
		 * @return 出现了该单词的消息为垃圾消息的概率比例（经过了拉普拉斯平滑处理）
		 * */
		float computeWordSpamPossibility(int wordSpamNum) {

			// 计算贝叶斯分类概率，+1:拉普拉斯平滑处理
			float result = ((float) wordSpamNum / (float) (spamNum + 1))
					* ((float) (spamNum + 1) / (float) (hamNum + spamNum + 1))
					/ (((float) wordSpamNum + 1) / (float) (hamNum + spamNum + 1));
			// 返回计算结果
			return result;

		}

	}

	public static void main(String[] args) throws Exception {
		SpamDeteminationMapReduce mr = new SpamDeteminationMapReduce();
		mr.beginMR();

	}
}
