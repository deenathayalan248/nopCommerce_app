package com.nopcommerce.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public Properties configprobObj=null;
	public Logger logger=LogManager.getLogger(this.getClass());
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br) throws Exception
	{
		//getting values from properties file
		configprobObj=new Properties();
		//Thread currentThread = Thread.currentThread();
		//ClassLoader contextClassLoader = currentThread.getContextClassLoader();
		//InputStream propertiesStream = contextClassLoader.getResourceAsStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		FileInputStream configfile=new FileInputStream(System.getProperty("user.dir")+"\\resources\\config.properties");
		configprobObj.load(configfile);
		
				
		if(br.equals("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			options.setHeadless(true);
			
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}else if(br.equals("edge"))
		{
			
			EdgeOptions options=new EdgeOptions();
			//options.setHeadless(true);
			
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			
		}else if(br.equals("firefox"))
		{
			FirefoxOptions options=new FirefoxOptions();
			options.setHeadless(true);
			
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		
	}
	
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\Screenshots\\" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return (generatedString1);
	}
	
	public int randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (Integer.parseInt(generatedString2));
	}
	
	
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
	}

}
