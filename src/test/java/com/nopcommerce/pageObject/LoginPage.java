package com.nopcommerce.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//Locators
	
	@FindBy(xpath="//input[@id='Email']")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='Password']")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//button[contains(text(),'Log in')]")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	@CacheLookup
	WebElement lnkLogout;
	
		
	//Action methods
	
	public void setUserName(String uname) 
	{
		txtEmail.clear();
		txtEmail.sendKeys(uname);
	}
	
	public void setPassword(String pwd) 
	{
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
		
	}
	
	public void clickLogin() 
	{
		btnLogin.click();
		
	}
	public void clickLogout()
	{
		lnkLogout.click();
	}

}
