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
// 需要先在系统中设置使用的驱动的位置
				ReadResule readResule = new ReadResule();
				try {
					readResule.findKeyWork();
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
//				InIt();			//初始化
//				ChenYiXun();	//找到陈奕迅歌单
//				for (int i = 0; i < 20; i++) {
//					for (int j = 0; j < 50; j++) {
//						unfoldCange();		//展开歌曲
//						commentsCrawler();    //取得评论
//						nextPage();			//进入下一页评论
//					}
//					switchoverSong();		//进入下一首歌
//				}		
	}
	
	/**
	 * 初始化
	 */
	public static  void InIt(){
		// 建立等待对象,以及最大等待时间
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		// 建立浏览器驱动对象,并启动该对象
		driver = new ChromeDriver();
		// 建立等待对象,以及最大等待时间
		wait = new WebDriverWait(driver, 10);
		// 将页面的对象转换为javascript操作对象
		js = driver;
		driver.manage().window().maximize();
		driver.get("http://music.163.com/");
		//找到排行榜页面  c 
//		nextEl = driver.findElement(By.cssSelector("a[data-module=toplist]"));
		nextEl = driver.findElement(By.cssSelector("a[data-module=artist]"));
		nextEl.click();
		//点击到下方歌曲列表
		nextEl = driver.findElement(By.cssSelector("a[data-action=lock]"));
		nextEl.click();
		//进入frame页面
		driver.switchTo().frame("g_iframe");
		//退出frame页面：driver.switchTo().defaultContent();
	} 
	/**
	 * 切换页面，进入下一页
	 * @throws Exception 
	 */
	public static void nextPage() throws Exception{
		Thread.sleep(2*500);
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		nextEl =driver.findElement(By.linkText("下一页"));
		nextEl.click();
	}
//	System.out.println(driver.getPageSource());  
	/**
	 * 进入陈奕迅歌单并点击播放全部
	 * @throws Exception 
	 */
	public static void ChenYiXun() throws Exception{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		Thread.sleep(4*500);
		nextEl = driver.findElement(By.cssSelector("a[title=陈奕迅的音乐]"));
		nextEl.click();
		Thread.sleep(2*1000);
		Thread.sleep(4*500);
		nextEl = driver.findElement(By.className("u-btni-addply"));
		nextEl.click();
	}
	/**
	 * 展开歌曲
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
	 * 切换歌曲
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
	 * 爬取评论
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
	 * 上传到hadoop
	 * @param surl
	 * @throws Exception
	 */
	private static void upHDFS(String surl) throws Exception{
		
		if (surl.indexOf("：")!=-1) {
			String[] args=surl.split("：");
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
