package com.rock.mapper;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//KEYIN, VALUEIN, KEYOUT, VALUEOUT；
// map阶段输入的key，map阶段输入的value
//map 阶段输出的key，map阶段输出的value
public class WordCountMapper1 extends Mapper<LongWritable,Text,Text,IntWritable> {
    //LongWritable 对应是长整形（行号）
    //Text 对应字符串
    //IntWritable 对应正常的整形（每个单词个数）

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //value 代表读取到的当前行的数据
        String data = value.toString();
        //通过逗号分隔
        String[] temp = data.split(",");
        
        //将每个岗位标记为已经出现一次
        if(temp.length>1)
        	context.write(new Text(temp[0]),new IntWritable(1));
        
    }
}
