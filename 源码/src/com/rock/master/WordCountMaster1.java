package com.rock.master;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.rock.mapper.DataMapper;
import com.rock.mapper.WordCountMapper1;
import com.rock.reducer.DataReducer1;
import com.rock.reducer.WordCountReducer1;
import com.rock.witable.ReLongWritable;

public class WordCountMaster1 {
    public  void test() throws IOException, ClassNotFoundException, InterruptedException {
        //输入路径文件
        Path inputPath = new Path("/input/51Job_python_5000.txt");
        //输出路径文件
        Path outputPath = new Path("/output/outdata1");



        //初始化配置
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);



        //初始化Job参数，指定Job名称
        Job job = Job.getInstance(conf,"wordcount");
        //设置执行的Job类
        job.setJarByClass(WordCountMaster1.class);
        //设置Mapper类
        job.setMapperClass(WordCountMapper1.class);
        //设置Reducer类
        job.setReducerClass(WordCountReducer1.class);
        //设置Map的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置Reducer输出类型
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);



        //设置输入路径
        FileInputFormat.setInputPaths(job,inputPath);
        //设置输出路径
        FileOutputFormat.setOutputPath(job,outputPath);

        //提交Job
        if (fs.exists(outputPath)) {
            fs.delete(outputPath,true);
        }
        boolean result = job.waitForCompletion(true);
        if(result)
            System.out.println("程序成功执行");
    }
    public void test1() throws IOException, ClassNotFoundException, InterruptedException{
    	//输入路径文件
        Path inputPath = new Path("/output/outdata1/part-r-00000");
        //输出路径文件
        Path outputPath = new Path("/output/outdata2");



        //初始化配置
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);



        //初始化Job参数，指定Job名称
        Job job = Job.getInstance(conf,"wordcount");
        //设置执行的Job类
        job.setJarByClass(WordCountMaster1.class);
        //设置Mapper类
        job.setMapperClass(DataMapper.class);
        //设置Reducer类
        job.setReducerClass(DataReducer1.class);
        //设置Map的输出类型
        job.setMapOutputKeyClass(ReLongWritable.class);
        job.setMapOutputValueClass(Text.class);
        //设置Reducer输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ReLongWritable.class);



        //设置输入路径
        FileInputFormat.setInputPaths(job,inputPath);
        //设置输出路径
        FileOutputFormat.setOutputPath(job,outputPath);

        //提交Job
        if (fs.exists(outputPath)) {
            fs.delete(outputPath,true);
        }
        boolean result = job.waitForCompletion(true);
        if(result)
            System.out.println("程序成功执行");
    }
}
