package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByEmail_004 extends BaseClass {
	
	@Test(groups={"regression","master"})
	public void searchCustomerbyEmail() throws InterruptedException, IOException
	{
		logger.info("********* TC_SearchCustomerByEmail_004 *************");
		
		driver.get(configprobObj.getProperty("url"));
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(configprobObj.getProperty("useremail"));
		loginPage.setPassword(configprobObj.getProperty("pswd"));
		loginPage.clickLogin();
		Thread.sleep(3000);
		
		//Click Addcustomer
		AddcustomerPage addcustomerpage=new AddcustomerPage(driver);
		
		addcustomerpage.clickOnCustomersMenu();
		addcustomerpage.clickOnCustomersMenuItem();
		
		SearchCustomerPage searchcustomerpage=new SearchCustomerPage(driver);
		
		searchcustomerpage.setEmail("victoria_victoria@nopCommerce.com");
		searchcustomerpage.clickSearch();
		Thread.sleep(3000);
		
		//Validation
		boolean status=searchcustomerpage.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		if(status==true)
		{
			logger.info("*****************Search customer by email is passed********************");
			Assert.assertTrue(true);
		}else if(status==false)
		{
			logger.info("*****************Search customer by email is failed********************");
			captureScreen(driver, "TC_SearchCustomerByEmail_004");
			Assert.assertTrue(false);
			
		}
		
		logger.info("********* End of TC_SearchCustomerByEmail_004 *************");	
		
	}

}
