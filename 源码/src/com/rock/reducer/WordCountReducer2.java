package com.rock.reducer;

import org.apache.hadoop.mapreduce.Reducer;

import com.rock.witable.ReDoubleWritable;

import org.apache.hadoop.io.Text;
import java.io.IOException;


//Reducer  的输入就是Map阶段的输出
//Reducer  的输出就是前文内容的整合
public class WordCountReducer2 extends Reducer<ReDoubleWritable,Text,Text,ReDoubleWritable> {
	
	private static int a = 0;
	@Override
	protected void reduce(ReDoubleWritable key, Iterable<Text> values,
			Context context)
			throws IOException, InterruptedException {
		
		for(Text val:values){
			if(a < 3){
				a += 1;
				context.write(val, key);
			}
		}
	}
    
}
