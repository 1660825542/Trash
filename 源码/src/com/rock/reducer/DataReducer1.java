package com.rock.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.rock.witable.ReLongWritable;

public class DataReducer1 extends Reducer<ReLongWritable,Text,Text,ReLongWritable>{

	private static IntWritable num = new IntWritable(0);

	@Override
	protected void reduce(ReLongWritable key, Iterable<Text> values,
			Context count) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
			for(Text val:values){
				if(num.get() < 5){
					num.set(num.get()+1);;
					count.write(val, key);
				}
		}
		
	}
	
	
	

}
