package com.hvcc.Wangyi;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapFile.Reader;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.lib.output.MapFileOutputFormat;

import jxl.Workbook;
import jxl.write.WritableWorkbook;import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ReadResule {
	private static Configuration conf = new Configuration();
	private static Path path = new Path("hdfs://192.168.152.129:9000/wangyiCrawls_output/wangyiCrawls_output1/part-r-00000");
	private static FileSystem fs;
	private static Reader reader;
	
	public static void findKeyWork() throws Exception {
		fs=path.getFileSystem(conf);
		FSDataInputStream is = fs.open(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		WritableWorkbook wb = Workbook.createWorkbook(new File("D:/export123.xls"));
				// 准备Excel文件对象
				// 建立工作表Sheet
				WritableSheet baiduSheet = wb.createSheet("wangyi", 0);
				String line = null;
				// 每次循环应该往表里添加一行的数据,需要一个变量记录行数
				int rowCount = 0;
				while ((line = reader.readLine()) != null) {
					// 切分出词和频率
					String[] str = line.split("\t");
					// 建立两个单元格
					Label wordLb = new Label(0, rowCount, str[0]);
//					jxl.write.NumberFormat nf = new jxl.write.NumberFormat("#"); 
//					jxl.write.WritableCellFormat wcfN = new jxl.write.WritableCellFormat(nf);
//					int x = Integer.parseInt(str[1]);
//					jxl.write.Number labelNF = new jxl.write.Number(1, rowCount, x, wcfN); 
					Label countLb = new Label(1, rowCount, str[1]);
					
					// 把单元格加入到sheet表中
					baiduSheet.addCell(wordLb);
					baiduSheet.addCell(countLb);
					
					// 自增长行数
					rowCount++;
				}

				reader.close();

				// 保存工作表,并关闭
				wb.write();
				wb.close();
		
	}
}
		

