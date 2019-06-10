package service;

import bean.ServerContext;
import bean.WordInfo;
import mr.GlobalCounters;
import mr.SpamDeteminationMapReduce;
import redis.clients.jedis.Jedis;
import util.HDFSOps;
import util.RedisPool;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class SpamDeterminationService {

    long FILE_LENGTH_128M = 1024 * 1024 * 128;
    // HDFSOps hdfs = new HDFSOps("hdfs://192.168.1.119:9000/");
    // Jedis jedis = new Jedis("192.168.1.119");
    Jedis jedis = null;
    SpamDeteminationMapReduce mr = new SpamDeteminationMapReduce();

    public void submitMsg(String msg, boolean isSpam) {
        File learningData = new File("leanrningData");
        if (learningData.exists() && learningData.length() > FILE_LENGTH_128M) {
            moveLearningDataToHDFS(learningData);
            reMR();
        }
        try {
            FileOutputStream fos = new FileOutputStream(learningData, true);
            String context = "";
            if (isSpam) {
                context += "spam	";
            } else {
                context += "ham	";
            }
            context += msg;
            System.out.println("新增学习数据：" + context);
            fos.write((context + "\n").getBytes());
            fos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public boolean isSpam(String msg) {

        jedis = RedisPool.getJedis(false);
        // 对目标字符串进行自然分词
        String[] words = msg.split(" ");
        // 计算有效数据的比率和垃圾消息的比率，如果垃圾消息的比例更大，说明其为垃圾消息，反之则为有效消息
        boolean result = computeStringSpamResult(words) > computeStringHamResult(words);
        RedisPool.returnResource(jedis);

        return result;
    }

    /**
     * 计算字符串是有效信息的比率结果
     *
     * @param words
     *            待计算字符串的分词结果
     * @return 字符串是有效信息的比率结果
     * */
    float computeStringHamResult(String[] words) {
        // 定义结果变量
        float result = 1.0f;
        // 循环遍历目标字符串分词结果
        for (String word : words) {

            try {
                // 如果单词存在于学习数据中
                if (jedis.get(word.getBytes()) != null) {
                    // 累计出现该单词后消息为有效消息的比率

                    WordInfo info = WordInfo.getInstanceByByteArray(jedis
                            .get(word.getBytes()));
                    result *= info.getWordHamPossibility();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        // 返回计算结果
        return result;

    }

    /**
     * 计算字符串垃圾信息的比率结果
     *
     * @param words
     *            待计算字符串的分词结果
     * @return 字符串是垃圾信息的比率结果
     * */
    float computeStringSpamResult(String[] words) {
        // 定义结果变量
        float result = 1.0f;
        // 循环遍历目标字符串分词结果
        for (String word : words) {
            try {
                // 如果单词存在于学习数据中
                if (jedis.get(word.getBytes()) != null) {
                    // 累计出现该单词后消息为垃圾消息的比率

                    WordInfo info = WordInfo.getInstanceByByteArray(jedis
                            .get(word.getBytes()));
                    result *= info.getWordSpamPossibility();

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // 返回计算结果
        return result;

    }

    public void reMR() {
        try {
            mr.beginMR();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void moveLearningDataToHDFS(File file) {
        String localPath = file.getAbsolutePath();
        String uuid = UUID.randomUUID().toString();

        try {
            HDFSOps hdfs = new HDFSOps(ServerContext.HDFS_ROOT);
            hdfs.uploadFile(localPath, "/spamdetermination/learningdata/"
                    + uuid + ".txt");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public long getGlobalCounterValue(String counterKey) {
        return GlobalCounters.getCounterValue(counterKey);
    }

    public void setGlobalCounterValue(String counterKey, long counterValue) {
        GlobalCounters.setCounterValue(counterKey, counterValue);

    }

    public void globalCounterValueIncrement(String counterKey, long counterValue) {
        GlobalCounters.increment(counterKey, counterValue);

    }
    public static void main(String[] args) throws Exception {
        SpamDeterminationService biz = new SpamDeterminationService();
        // System.out.println(biz.isSpam("Hello us"));
        biz.reMR();

    }

}
