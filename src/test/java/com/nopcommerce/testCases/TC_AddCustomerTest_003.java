package com.nopcommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopcommerce.pageObject.AddcustomerPage;
import com.nopcommerce.pageObject.LoginPage;
import com.nopcommerce.testBase.BaseClass;

public class TC_AddCustomerTest_003 extends BaseClass 
{
	@Test(groups= {"sanity","regression","master"})
	public void addNewCustomer() throws InterruptedException, IOException
	{
		logger.info("********* starting TC_AddCustomerTest_003 *************");
		
		logger.info("********* starting addNewCustomer *************");
		
		driver.get(configprobObj.getProperty("url"));
		LoginPage loginPage=new LoginPage(driver);
		loginPage.setUserName(configprobObj.getProperty("useremail"));
		loginPage.setPassword(configprobObj.getProperty("pswd"));
		loginPage.clickLogin();
		Thread.sleep(3000);
		
		logger.info("*********Adding new customer *************");
		
		AddcustomerPage addcustomerpage=new AddcustomerPage(driver);
		
		addcustomerpage.clickOnCustomersMenu();
		addcustomerpage.clickOnCustomersMenuItem();
		addcustomerpage.clickOnAddnew();
		Thread.sleep(2000);

		logger.info("***************  Providing customer details  *********** ");

		String email=randomestring()+"@gmail.com";
		
		addcustomerpage.setEmail(email);
		addcustomerpage.setPassword("test123");
		addcustomerpage.setFirstName("Pavan");
		addcustomerpage.setLastName("Kumar");
		addcustomerpage.setGender("Male");
		addcustomerpage.setDob("7/05/1985"); // Format: MM/DD/YYY
		addcustomerpage.setCompanyName("busyQA");
		addcustomerpage.setCustomerRoles("Vendors");
		Thread.sleep(3000);
		addcustomerpage.setManagerOfVendor("Vendor 2");
		addcustomerpage.setAdminContent("This is for testing.........");
		addcustomerpage.clickOnSave();
		Thread.sleep(3000);

		// validation
				if (addcustomerpage.verifyConfirmationMsg()) {
					logger.info("***************  Customer added succesfully *********** ");
					Assert.assertTrue(true);

				} else {
					logger.error("*************** Customer Not added succesfully *********** ");
					captureScreen(driver,"addNewCustomer");
					Assert.assertTrue(false);
				}
				logger.info("***************   TC_AddCustomerTest_003 Finished  *********** ");
	}

}
