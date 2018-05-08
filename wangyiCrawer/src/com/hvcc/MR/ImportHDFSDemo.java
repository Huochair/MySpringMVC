package com.hvcc.MR;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.hvcc.vo.infor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ImportHDFSDemo {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		Path path = new Path("hdfs://192.168.8.130:9000/wanyiyunyinyue/biaoqian.txt");
		FileSystem fs = path.getFileSystem(conf);

		DataBaseConnection dbc = new DataBaseConnection();
		ProductIPDAOimpl dao = new ProductIPDAOimpl(dbc);

		List<infor> allPro = dao.findAll();

		FSDataOutputStream os = fs.create(path);
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(os, "UTF-8"));

		int count = 1;
		for (infor vo : allPro) {
			if (vo!=null) {
				writer.println(vo.getClasses() + "\t" + vo.getCommentnumber());
			System.out.println("已经完成了:" + (count++) + " 条数据");
			}
			
			
		}

		writer.close();
		dbc.close();

	}

}
