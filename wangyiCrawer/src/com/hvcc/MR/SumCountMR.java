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

	// �Լ���д��Map���������̳�Mapper�࣬ͬʱ�����㷨��Ҫ����������ķ�������
	// ��һ���������������ݵ�keyֵ���ͣ�����һ�����Map����������ʱ��û��key�����Ը����ͻ���������ΪObject
	// �ڶ����������������ݵ�valueֵ���ͣ������ı��ļ���˵��������һ�ж�ȡ���������ݣ����Ϳ϶���Text
	// ������������������ݵ�keyֵ����
	// ���ĸ�������������ݵ�valueֵ����
	public static class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {
		//private Text word = new Text();
		private static MMAnalyzer mm = new MMAnalyzer();
		// ��ǰ׼���������Ҫ�õı�����
		// ���Ĳ����㷨
		// value����Ҫ����Ķ�������ݣ�context��д�����ݵĲ�������
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

	// ÿ���Լ���д��Reduce���������̳�Reducer�࣬Ҳ��Ҫ����4�����Ͳ���
	// ��һ���������������ݵ�keyֵ���ͣ�������Map������������һ��
	// �ڶ����������������ݵ�valueֵ���ͣ�������Map������������һ��
	// ������������������ݵ�keyֵ����
	// ���ĸ�������������ݵ�valueֵ����
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
		// ����һ��MR���񣬲��ṩһ����������ƣ������
		Job job = Job.getInstance(conf, "click count sort");
		// ����ʹ��jar��ģʽ����ʱ�������������ʵ���Ͼ��ǵ�ǰ������������
		job.setJarByClass(SumCountMR.class);
		// ����Map�����֧���ࡣ
		job.setMapperClass(TokenizerMapper.class);
		// �ڸ������Դ���Map�����󣬼���DataNode�����Ҫ�Խ�����кϲ����������õ��Ǻϲ�������֧����
		// һ�������ͨ��MR������˵���������Ӧ�ú�����Reduce����һ����
		// job.setCombinerClass(IntSumReducer.class);
		// ����Reducer�����֧����
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setReducerClass(IntSumReducer.class);
		// ���ô������������Key��Value���������ͣ�����Text�ͱ�ʾString���ͣ�IntWritable�ͱ�ʾint����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		// for (int i = 0; i < otherArgs.length - 1; ++i) {
		// �޸������Path·������ΪҪ������д�����ı����ڵ�HDFSĿ¼��
		FileInputFormat.addInputPath(job, new Path("hdfs://192.168.8.130:9000/wanyiyunyinyue/biaoqian.txt"));
		// }
		// �������ÿ���ֹ�ɾ�����Ŀ¼,����ͨ������������Ŀ¼��ɾ��
		Path outputPath = new Path("hdfs://192.168.8.130:9000/wanyiyunjieguo");
		FileSystem fs = outputPath.getFileSystem(conf);
		// �ж���������Ŀ¼�Ѿ�����,������ɾ��,�ٽ������
		if (fs.exists(outputPath)) {
			fs.delete(outputPath, true);
		}

		// ���ý���������Path·����ע���Ŀ¼�����ǲ����ڵġ�
		FileOutputFormat.setOutputPath(job, outputPath);
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
