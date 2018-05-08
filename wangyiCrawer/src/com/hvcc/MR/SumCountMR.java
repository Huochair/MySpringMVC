package com.hvcc.MR;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import jeasy.analysis.MMAnalyzer;

public class SumCountMR {

	// 自己编写的Map操作类必须继承Mapper类，同时根据算法需要，填入里面的泛型类型
	// 第一个参数：输入数据的key值类型，但是一般对于Map操作，输入时，没有key，所以该类型基本都设置为Object
	// 第二个参数：输入数据的value值类型，对于文本文件来说，就是这一行读取的文字内容，类型肯定是Text
	// 第三个参数：输出数据的key值类型
	// 第四个参数：输出数据的value值类型
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		//private Text word = new Text();
		private static MMAnalyzer mm = new MMAnalyzer();
		// 提前准备的输出所要用的变量。
		// 核心操作算法
		// value就是要处理的读入的内容，context是写出数据的操作对象
		public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
			
			 StringTokenizer itr = new StringTokenizer(value.toString());
		      String[] air = value.toString().split("\t");
		     if (air.length>=2) {
		    	 System.out.println(air[0]+air[1]);
			       if (!air[0].equals("null")) {
			    	   IntWritable num = new IntWritable(Integer.parseInt(air[1]));
				       String[] results = mm.segment(air[0], "|").split("\\|"); 
				      if (results.length>0) {
				      for (String result : results) {
				    	  System.out.println(result);
				    	   	context.write(new Text(result), num);
				         }
				      }
				}
			}
		}
	}

	// 每个自己编写的Reduce处理类必须继承Reducer类，也需要设置4个泛型参数
	// 第一个参数：输入数据的key值类型，必须与Map处理的输出类型一致
	// 第二个参数：输入数据的value值类型，必须与Map处理的输出类型一致
	// 第三个参数：输出数据的key值类型
	// 第四个参数：输出数据的value值类型
public static class IntSumReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable result = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable val : values) {
			sum += val.get();
		}
		result.set(sum);
		context.write(key, result);
	}
}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		// 建立一个MR任务，并提供一个任务的名称（随便起）
		Job job = Job.getInstance(conf, "click count sort");
		// 设置使用jar包模式运行时的主类的类名，实际上就是当前这个类的类名。
		job.setJarByClass(SumCountMR.class);
		// 设置Map处理的支持类。
		job.setMapperClass(TokenizerMapper.class);
		// 在各个电脑处理Map操作后，几个DataNode结点需要对结果进行合并，这里设置的是合并操作的支持类
		// 一般对于普通的MR程序来说，这个操作应该和最后的Reduce操作一样。
		// job.setCombinerClass(IntSumReducer.class);
		// 设置Reducer处理的支持类
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(IntSumReducer.class);
		// 设置处理后输出结果的Key和Value的数据类型，其中Text就表示String类型，IntWritable就表示int类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// for (int i = 0; i < otherArgs.length - 1; ++i) {
		// 修改这里的Path路径，改为要输入进行处理的文本所在的HDFS目录。
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.8.130:9000/wanyiyunyinyue/biaoqian.txt"));
		// }
		// 如果懒得每次手工删除输出目录,可以通过程序来进行目录的删除
		Path outputPath = new Path("hdfs://192.168.8.130:9000/wanyiyunjieguo");
		FileSystem fs = outputPath.getFileSystem(conf);
		// 判断如果输出的目录已经存在,可以先删除,再进行输出
		if (fs.exists(outputPath)) {
			fs.delete(outputPath, true);
		}

		// 设置结果所输出的Path路径，注意该目录必须是不存在的。
		FileOutputFormat.setOutputPath(job, outputPath);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
