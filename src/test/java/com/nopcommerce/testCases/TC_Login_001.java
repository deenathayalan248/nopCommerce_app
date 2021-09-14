package com.nopcommerce.testCases;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_Login_001 extends BaseClass
 
{
	public LoginPage loginpage;
	
	@Test(groups={"sanity","regression","master"})
	public void loginTest() throws IOException
	{	
		logger.info("*******loginTest starting******");
		driver.get(configprobObj.getProperty("url"));
		loginpage= new LoginPage(driver);
		
		logger.info("********Enterting login details******");
		loginpage.setUserName(configprobObj.getProperty("useremail"));
		loginpage.setPassword(configprobObj.getProperty("pswd"));
		
		logger.info("********Click on login button******");
		loginpage.clickLogin();
		
		String exp_title=configprobObj.getProperty("title");
		String act_title=driver.getTitle();
		
		if(exp_title.equals(act_title)) 
		{
			logger.info("********loginTest Passed*******");
			Assert.assertTrue(true);
		}else
		{
			logger.error("********loginTest Failed*******");
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
		}
		logger.info("**********loginTest finished********");
	}
	
	
	
}
