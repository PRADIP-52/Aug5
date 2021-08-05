package Demo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Demog{

    WebDriver driver;
	
    static Logger log=Logger.getLogger(Demog.class.getName());
    
    Properties pro=new Properties();
    
	@BeforeSuite
	public void browser() throws IOException
	{
		FileInputStream fis=new FileInputStream("F:\\CJC\\PradipEclipseWorkspace\\IntegrationLogGit\\demo1.properties");
		pro.load(fis);
		
		log.debug("Open Browser..");
		log.debug("URL="+pro.getProperty("url"));
		
		System.setProperty("webdriver.chrome.driver","D:\\Automation_Testing\\chromedriver.exe");
	    driver=new ChromeDriver();
	    log.info("Browser Open Successful..");
	}
	@BeforeClass
	public void maximizebrowser()
	{
		driver.manage().window().maximize();
		driver.get(pro.getProperty("url"));
	}	
	@BeforeMethod
	public void getCookies()
	{
		Set<Cookie> cookies=driver.manage().getCookies();
	    int count=cookies.size();
	    System.out.println("Total Cookies Are =" +count);
	
	    for(Cookie cookie : cookies)
	    {
	    	String name=cookie.getName();
	    	System.out.println("Name Of Cookie="+count);
	    	
	    	String domain=cookie.getDomain();
	    	System.out.println("Domain of Cookie+"+domain);
	    	
	    	String path = cookie.getPath();
			System.out.println("path of cookie:"+path);
			
			boolean secure = cookie.isSecure();
			System.out.println("Secure:"+secure);
			
			boolean htt = cookie.isHttpOnly();
			System.out.println("http only valid:"+htt);
			
			System.out.println("----------------------");
	    	
	    }	    
	}
	 @Test
	  public void m1() {
		 
		 driver.get("http://demowebshop.tricentis.com/");
		 String s=driver.getTitle();
		 log.info("Title of This Page="+s);
		 
		 Assert.assertEquals("Demo Web Shop", s);
		 
		 log.warn("Register Start..");
		 
		 driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")).click();
		 driver.findElement(By.xpath("//input[@id='gender-male']")).click();
	     driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(pro.getProperty("firstname"));
		 driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys(pro.getProperty("lastname"));
		 driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(pro.getProperty("email"));
		 driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pro.getProperty("pass"));
		 driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(pro.getProperty("cpass"));
		 driver.findElement(By.xpath("//input[@id='register-button']")).click();
		 
		 log.error("Register Successful..");
		 System.out.println("Hello git");
		
	  }
	
	 @AfterMethod
	  public void capturescreenshot() throws IOException
	  {
	    File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(src, new File("E:\\SS\\DemoWebShopAnn.jpg"));
		FileUtils.copyFileToDirectory(src, new File("D:\\SS"));
		
		log.fatal("Screenshot Taken Successful....");
	  }
	  @AfterClass
	  public void deletecookie()
	  {
		driver.manage().deleteCookieNamed("_ga");
		
		Set<Cookie> cookies1=driver.manage().getCookies();
		int count1=cookies1.size();
		System.out.println("New count="+count1);
	  }
	  @AfterTest
	  public void conclose()
	  {
		System.out.println("close...");
	  }

	  @AfterSuite
	  public void closedriver()
	  {
		System.out.println("Success..");
		driver.quit();
		  System.out.println("Pro successful");
	  }
}
