package com.rock.master;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.rock.mapper.WordCountMapper2;
import com.rock.reducer.WordCountReducer2;
import com.rock.witable.ReDoubleWritable;

public class WordCountMaster2 {
	public  void test() throws IOException, ClassNotFoundException, InterruptedException {
        //输入路径文件
        Path inputPath = new Path("/input/51Job_python_5000.txt");
        //输出路径文件
        Path outputPath = new Path("/output/sal");



        //初始化配置
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);



        //初始化Job参数，指定Job名称
        Job job = Job.getInstance(conf,"wordcount");
        //设置执行的Job类
        job.setJarByClass(WordCountMaster2.class);
        //设置Mapper类
        job.setMapperClass(WordCountMapper2.class);
        //设置Reducer类
        job.setReducerClass(WordCountReducer2.class);
        //设置Map的输出类型ReDoubleWritable
        job.setMapOutputKeyClass(ReDoubleWritable.class);
        job.setMapOutputValueClass(Text.class);
        //设置Reducer输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ReDoubleWritable.class);



        //设置输入路径
        FileInputFormat.setInputPaths(job,inputPath);
        //设置输出路径
        FileOutputFormat.setOutputPath(job,outputPath);

        //提交Job
        if (fs.exists(outputPath)) {
            fs.delete(outputPath,true);
        }
        job.waitForCompletion(true);
    }
}
