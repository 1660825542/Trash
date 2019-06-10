package com.rock.reducer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.Text;
import java.io.IOException;


//Reducer  的输入就是Map阶段的输出
//Reducer  的输出就是前文内容的整合
public class WordCountReducer1 extends Reducer<Text,IntWritable,IntWritable,Text> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        //迭代计算，将元组中的值累加，得到该岗位总共出现的数量
        for(IntWritable value:values){
            count += value.get();
        }
        //结果输出，会生成一个数据文件
        key.set("&&"+key.toString());
        context.write(new IntWritable(count),key);
    }
}
