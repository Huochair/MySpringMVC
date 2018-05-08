package com.hvcc.MR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.sun.jersey.spi.StringReader;

public class reader88 {
	public static String line =null;
	public static String classes = null;
	public static int sum = 0;
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Path path = new Path("hdfs://192.168.8.130:9000/wanyiyunjieguo/part-r-00000");
		FileSystem fs = path.getFileSystem(conf);
		DataBaseConnection dbc = new DataBaseConnection();
		ProductIPDAOimpl dao = new ProductIPDAOimpl(dbc);
		FSDataInputStream  is = fs.open(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		//String line =null;
		//String classes = null;
		
		while ((line = reader.readLine()) != null) {
			String[] strs = line.split("\t");
			classes=strs[0];
			sum =Integer.parseInt(strs[1]);
			dao.adddata();
			System.out.println(classes+"---->"+sum);	
		}
	}

}
