package com.hvcc.text;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hvcc.dbs.DataBaseConnection;
import com.hvcc.impl.ProductIPDAOimpl;
import com.hvcc.vo.MusicList;
import com.hvcc.vo.SongInfor;
import com.sun.xml.bind.v2.model.core.ID;

public class WYsongName {
	private static WebDriverWait wait = null;
	private static DataBaseConnection dbc = new DataBaseConnection();
	private static ProductIPDAOimpl dao= new ProductIPDAOimpl(dbc);
	public static void main(String[] args)throws Exception {
//		Jsoup.connect("http://music.163.com/playlist?id=2186364976")
//		.header("Referer","http://music.163.com/")
//		.header("Host", "music.163.com").timeout(5000).get().select("ul[class=f-hide] a")
//		.stream().map(w->w.text() +"-->"+w.attr("href"))
//		.forEach(System.out::println);
//		System.out.println(Jsoup.connect("http://music.163.com/#/discover/playlist").get());
//		songList("http://music.163.com/#/discover/playlist");
//		while (true) {
////			System.out.println(Thread.currentThread().getName());
//		}
			for (int i = 0; i <5; i++) {
			
			new WYsongName().new MyThread().start();
			}
//		System.setProperty("webdriver.chrome.driver","D:/chromedriver.exe");
//		String url = "http://music.163.com/playlist?id=2186364976";
//		String urn = "http://music.163.com/#/playlist?id=2134161438";
//		Demo(url);
//		Demo(urn);	
	}
	public static ChromeDriver INIT(ChromeDriver driver) {
		System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		return driver;
	}
	
	public static void songList(String url,ChromeDriver driver) throws Exception {
		driver.get(url);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("g-iframe")));
		Thread.sleep(2*500);
		driver.switchTo().frame("g_iframe");
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class=msk]")));
		Thread.sleep(2*500);
		List<WebElement> nextEl = driver.findElements(By.cssSelector("a[class=msk]"));	
		for (WebElement webElement : nextEl) {
			String b = webElement.getAttribute("href");
			String a = webElement.getAttribute("title");
			Pattern p = Pattern.compile(".*");
			if (p.matcher(b).find()) {
				System.out.println(b+"-->"+a);
				MusicList vo = new MusicList(0,b,a);
				System.out.println(vo.toString());
				dao.setUrl(vo);	
			}	
		}
		WebElement nextEll = driver.findElement(By.linkText("下一页"));
		String b = nextEll.getAttribute("href");
		System.out.println("下一页"+b);
		if (b!=null) {
			try {
				songList(b,driver);
				
			} catch (Exception e) {
			}
		}
	}
	
	public static void songName(ChromeDriver driver) throws Exception {
		MusicList musicList = dao.getUrl();
		String url = musicList.getUil();
		driver.get(url);
		Thread.sleep(2*500);
		driver.switchTo().frame("g_iframe");
		Thread.sleep(2*500);
		WebElement wElement =  driver.findElement(By.id("play-count"));
		String  c= wElement.getText();
		List<WebElement> label = driver.findElements(By.cssSelector("a[class=u-tag] i"));
		StringBuffer sBuffer = new StringBuffer();
		for (WebElement webElement : label) {
			webElement.getText();
			sBuffer.append(webElement.getText());
		}
		System.out.println("本歌单播放量："+"-->"+c+"   本歌单属于："+sBuffer.toString());
		List<WebElement> nextEl = driver.findElements(By.cssSelector("span[class=txt] a"));	
		for (WebElement webElement : nextEl) {
			String b = webElement.getAttribute("href");
			String a = webElement.getText();
			Pattern p = Pattern.compile(".*song.*");
			if (p.matcher(b).find()) {
				String bx = b.substring(b.indexOf("id=")+3);
				int bz = Integer.parseInt(bx);
				String[] ss = a.split("\n");
				StringBuffer stringBuffer = new StringBuffer();
				for (int i = 0; i < ss.length; i++) {
					if (i%2==0) {
						stringBuffer.append(ss[i]);
					}
				}
				SongInfor sInfor = new SongInfor(bz,b,stringBuffer.toString(),musicList.getIntro(),sBuffer.toString());
				dao.setMusic(sInfor);
			}		
		}
	}
	
	
	public static void CommentNumber(ChromeDriver driver) throws Exception {
		SongInfor songInfor = dao.getMusicURL();
		try {
			String url =songInfor.getUrl();
			driver.get(url);
			Thread.sleep(1*500);
			driver.switchTo().frame("g_iframe");
			Thread.sleep(1*500);
			WebElement wElementName = driver.findElement(By.cssSelector("span a[class=s-fc7]"));
			String WriterName = wElementName.getText();
			WebElement wElement =  driver.findElement(By.id("cnt_comment_count"));
			System.out.println(Thread.currentThread().getName()+"-->"+"-->"+wElement.getText());
			Integer a;
			try {
				a = Integer.parseInt(wElement.getText());
			} catch (Exception e) {
				a=0;
			}
			songInfor.setCommentNumber(a);
			songInfor.setWriter(WriterName);
//		System.out.println(songInfor.toString());
			dao.setComment(songInfor);
			CommentNumber(driver);
		} catch (Exception e) {
			e.printStackTrace();
			CommentNumber(driver);
//			 dao.getMusicURLTo(songInfor);
		}
	}
	class MyThread extends Thread{
		@Override
		public void run() {
			try {
				ChromeDriver driver = null;
				CommentNumber(INIT(driver));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			}
		}
	}
