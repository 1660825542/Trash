package com.rock.mapper;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.rock.witable.ReLongWritable;


public class DataMapper extends Mapper<LongWritable,Text,ReLongWritable,Text>{

	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		 String data = value.toString();
	    //通过逗号分隔
	     String[] temp = data.split("	&&");
	        
	    //将每个公司标记为已经出现一次
	     if(temp.length>1)
	        context.write(new ReLongWritable(Long.parseLong(temp[0])),new Text(temp[1]));
	}

}
