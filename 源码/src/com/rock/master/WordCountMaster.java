package com.rock.master;


import com.rock.mapper.DataMapper;
import com.rock.mapper.WordCountMapper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.rock.reducer.DataReducer;
import com.rock.reducer.WordCountReducer;
import com.rock.witable.ReLongWritable;

import java.io.IOException;

public class WordCountMaster {
    public  void test() throws IOException, ClassNotFoundException, InterruptedException {
        //输入路径文件
        Path inputPath = new Path("/input/51Job_python_5000.txt");
        //输出路径文件
        Path outputPath = new Path("/output/test");



        //初始化配置
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);



        //初始化Job参数，指定Job名称
        Job job = Job.getInstance(conf,"wordcount");
        //设置执行的Job类
        job.setJarByClass(WordCountMaster.class);
        //设置Mapper类
        job.setMapperClass(WordCountMapper.class);
        //设置Reducer类
        job.setReducerClass(WordCountReducer.class);
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
        job.waitForCompletion(true);
    }
    public void test1() throws IOException, ClassNotFoundException, InterruptedException{
    	//输入路径文件
        Path inputPath = new Path("/output/test/part-r-00000");
        //输出路径文件
        Path outputPath = new Path("/output/test1");



        //初始化配置
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);



        //初始化Job参数，指定Job名称
        Job job = Job.getInstance(conf,"wordcount");
        //设置执行的Job类
        job.setJarByClass(WordCountMaster.class);
        //设置Mapper类
        job.setMapperClass(DataMapper.class);
        //设置Reducer类
        job.setReducerClass(DataReducer.class);
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
        job.waitForCompletion(true);
    }
}
