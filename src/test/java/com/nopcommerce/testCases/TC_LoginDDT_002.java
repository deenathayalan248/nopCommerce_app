package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;
import com.nopcommerce.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {
	
	public LoginPage loginpage;
	
	@Test(dataProvider="LoginData",groups= {"master"})
	public void loginTest(String user,String pwd,String exp) throws InterruptedException
	{
	logger.info("*******loginTest starting******");
	driver.get(configprobObj.getProperty("url"));
	loginpage= new LoginPage(driver);
	
	logger.info("********Enterting login details******");
	loginpage.setUserName(user);
	loginpage.setPassword(pwd);
	
	logger.info("********Click on login button******");
	loginpage.clickLogin();
	Thread.sleep(5000);
	
	String exp_title=configprobObj.getProperty("title");
	String act_title=driver.getTitle();
	
	if(exp_title.equals(act_title))
	{
		if(exp.equals("Pass"))
		{
			logger.info("**************** loginTest is Passed ************* ");
			loginpage.clickLogout();
			Thread.sleep(3000);
			Assert.assertTrue(true);
		}
		else if(exp.equals("Fail"))
		{
			logger.warn("**************** loginTest is Failed************* ");
			loginpage.clickLogout();
			Thread.sleep(3000);
			Assert.assertTrue(false);
		}
				
	}
	else if(!exp_title.equals(act_title))
	{
		if(exp.equals("Pass"))
		{
			logger.warn("**************** loginTest is Failed************* ");
			Assert.assertTrue(false);
		}
		else if(exp.equals("Fail"))
		{
			logger.info("**************** loginTest is Passed ************* ");
			Assert.assertTrue(true);
		}

	}
	logger.info("********* Finished  TC_LoginDDT_002 *************");
	
	}
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/testData/LoginData.xlsx";
		
		int totalrows=XLUtils.getRowCount(path, "Sheet1");
		int totalcols=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String[][] logindata=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		return logindata;
	
	}

}
