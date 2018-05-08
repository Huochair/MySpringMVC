package com.hvcc.Wangyi;

import static org.mockito.Matchers.anyString;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CrawlerDemo {
	private static ChromeDriver driver = null;
	private static WebDriverWait wait = null;
	private static WebElement nextEl=null;
	private static JavascriptExecutor js = null;
	private static String val = "hdfs://192.168.152.129:9000/wangyiCrawls/";
	private static Configuration conf = new Configuration();
	private static Path path = new Path(val);
	
	public static void main(String[] args) throws Exception {
// ��Ҫ����ϵͳ������ʹ�õ�������λ��
				ReadResule readResule = new ReadResule();
				try {
					readResule.findKeyWork();
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
//				InIt();			//��ʼ��
//				ChenYiXun();	//�ҵ�����Ѹ�赥
//				for (int i = 0; i < 20; i++) {
//					for (int j = 0; j < 50; j++) {
//						unfoldCange();		//չ������
//						commentsCrawler();    //ȡ������
//						nextPage();			//������һҳ����
//					}
//					switchoverSong();		//������һ�׸�
//				}		
	}
	
	/**
	 * ��ʼ��
	 */
	public static  void InIt(){
		// �����ȴ�����,�Լ����ȴ�ʱ��
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		// �����������������,�������ö���
		driver = new ChromeDriver();
		// �����ȴ�����,�Լ����ȴ�ʱ��
		wait = new WebDriverWait(driver, 10);
		// ��ҳ��Ķ���ת��Ϊjavascript��������
		js = driver;
		driver.manage().window().maximize();
		driver.get("http://music.163.com/");
		//�ҵ����а�ҳ��  c 
//		nextEl = driver.findElement(By.cssSelector("a[data-module=toplist]"));
		nextEl = driver.findElement(By.cssSelector("a[data-module=artist]"));
		nextEl.click();
		//������·������б�
		nextEl = driver.findElement(By.cssSelector("a[data-action=lock]"));
		nextEl.click();
		//����frameҳ��
		driver.switchTo().frame("g_iframe");
		//�˳�frameҳ�棺driver.switchTo().defaultContent();
	} 
	/**
	 * �л�ҳ�棬������һҳ
	 * @throws Exception 
	 */
	public static void nextPage() throws Exception{
		Thread.sleep(2*500);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		nextEl =driver.findElement(By.linkText("��һҳ"));
		nextEl.click();
	}
//	System.out.println(driver.getPageSource());  
	/**
	 * �������Ѹ�赥���������ȫ��
	 * @throws Exception 
	 */
	public static void ChenYiXun() throws Exception{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		Thread.sleep(4*500);
		nextEl = driver.findElement(By.cssSelector("a[title=����Ѹ������]"));
		nextEl.click();
		Thread.sleep(2*1000);
		Thread.sleep(4*500);
		nextEl = driver.findElement(By.className("u-btni-addply"));
		nextEl.click();
	}
	/**
	 * չ������
	 * @throws Exception 
	 */
	public static  void unfoldCange() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(2*1000);
		nextEl = driver.findElement(By.className("mask"));
		nextEl.click();
		driver.switchTo().frame("contentFrame");
		Thread.sleep(2*1000);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
	}
	/**
	 * �л�����
	 * @throws Exception 
	 */
	public static void switchoverSong() throws Exception {
		driver.switchTo().defaultContent();
		Thread.sleep(2*1000);
		//class="nxt"
		nextEl = driver.findElement(By.className("nxt"));
		nextEl.click();
		System.out.println(nextEl.getText());
		driver.switchTo().frame("contentFrame");
		Thread.sleep(2*1000);
		unfoldCange();
	}
	
	/**
	 * ��ȡ����
	 * @throws Exception 
	 */
	public static void commentsCrawler() throws Exception {
		Thread.sleep(2*1000);
		List<WebElement> allTitles = driver.findElements(By.className("f-brk"));
		StringBuffer stringBuffer = new StringBuffer();
		for (WebElement titleEl : allTitles) {
			String title = titleEl.getText();
			try {
//				System.out.println(title);
				upHDFS(title);
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}
	/**
	 * �ϴ���hadoop
	 * @param surl
	 * @throws Exception
	 */
	private static void upHDFS(String surl) throws Exception{
		
		if (surl.indexOf("��")!=-1) {
			String[] args=surl.split("��");
			for (int i=1;i<args.length;i++) {
				System.out.println(args[i]);
				FileSystem fs = path.getFileSystem(conf);
				String fileExtName = "product_"+System.currentTimeMillis()+".txt";
				FSDataOutputStream os = fs.create(new Path(val+fileExtName));
				PrintWriter writer = new PrintWriter(new OutputStreamWriter(os,"UTF-8"));
				writer.println(surl);
				writer.close();
			}
		}
	}
}
