package com.rock.mapper;

import java.io.IOException;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.rock.HDFS.Salary;
import com.rock.witable.ReDoubleWritable;

public class WordCountMapper2 extends Mapper<LongWritable,Text,ReDoubleWritable,Text>{
	@Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //value 代表读取到的当前行的数据
        String data = value.toString();
        //通过逗号分隔
        String[] temp = data.split(",");
        
        //将每个岗位标记为已经出现一次
        if(temp.length>1)
        	context.write(new ReDoubleWritable(Salary.converson(temp[3])),new Text(temp[0]));
        
    }
}
