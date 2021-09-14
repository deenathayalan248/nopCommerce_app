package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.pageObject.SearchCustomerPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_SearchCustomerByName_005 extends BaseClass {

	@Test(groups= {"master"})
	public void searchCustomerByName() throws InterruptedException, IOException {
		logger.info("***************** Starting of TC_SearchCustomerByName_005***************");

		driver.get(configprobObj.getProperty("url"));
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(configprobObj.getProperty("useremail"));
		loginPage.setPassword(configprobObj.getProperty("pswd"));
		loginPage.clickLogin();
		Thread.sleep(3000);

		// Click Addcustomer
		AddcustomerPage addcustomerpage = new AddcustomerPage(driver);

		addcustomerpage.clickOnCustomersMenu();
		addcustomerpage.clickOnCustomersMenuItem();

		SearchCustomerPage searchcustomerpage = new SearchCustomerPage(driver);
		
		searchcustomerpage.setFirstName("Brenda");
		searchcustomerpage.clickSearch();
		Thread.sleep(3000);
		
		//validation
		
		boolean status = searchcustomerpage.searchCustomerByName("Brenda Lindgren");
		if(status==true) 
		{
			logger.info("*********searchCustomerByName passed****************");
			Assert.assertTrue(true);
			
		}else
		{
			logger.info("*********searchCustomerByName failed****************");
			captureScreen(driver,"searchCustomerByName");
			Assert.assertTrue(false);
		}
		
logger.info("**********************End of TC_SearchCustomerByName_005********************");
	}

}
