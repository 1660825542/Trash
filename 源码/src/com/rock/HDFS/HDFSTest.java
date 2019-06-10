package com.rock.HDFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSTest {
    private static Configuration conf=new Configuration();
    private  static final  String HADOOP_URL="hdfs://etc01:8020";
    private  static FileSystem fs;

    static {
        FileSystem.setDefaultUri(conf,HADOOP_URL);
        try {
            fs=FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static  Map<String,String> read(String s)
    {
    	Map<String,String> map= new HashMap<>();
        Path path=new Path(s);
        try {
            FSDataInputStream dataInputStream=fs.open(path);
            InputStreamReader inputStreamReader=new InputStreamReader(dataInputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String sum=" ";
            while ((sum=bufferedReader.readLine())!=null)
            {
             
                String []k=sum.split("	");
                
                map.put(k[0], k[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }

}
