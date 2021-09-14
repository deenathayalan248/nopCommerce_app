package com.nopcommerce.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchCustomerPage {

	public WebDriver driver;

	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Locators

	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(how = How.XPATH, using = "//input[@id='SearchFirstName']")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(how = How.XPATH, using = "//input[@id='SearchLastName']")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(how = How.XPATH, using = "//input[@id='SearchCompany']")
	@CacheLookup
	WebElement txtSearchCompany;

	@FindBy(how = How.XPATH, using = "//button[@id='search-customers']")
	@CacheLookup
	WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//table[@role='grid']")
	@CacheLookup
	WebElement tblSearchResults;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;

	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;

	// Action methods

	public void setEmail(String email) {
		txtEmail.clear();
		txtEmail.sendKeys(email);
	}

	public void setFirstName(String fname) {
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}

	public void setLastName(String lname) {
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}

	public void setCompany(String company) {
		txtSearchCompany.clear();
		txtSearchCompany.sendKeys(company);
	}

	public void clickSearch() throws InterruptedException {
		btnSearch.click();
		Thread.sleep(2000);
	}

	public int getNoOfRows() {
		return (tableRows.size());
	}

	public int getNoOfColumns() {
		return (tableColumns.size());
	}

	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String emailid = driver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[2]"))
					.getText();
			System.out.println(emailid);
			if (emailid.equals(email)) {
				flag = true;
				break;
			}else {
				flag = false;
			}
		}

		return flag;
	}

	public boolean searchCustomerByName(String Name) {
		boolean flag = false;

		for (int i = 1; i <= getNoOfRows(); i++) {
			String name = driver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr[" + i + "]//td[3]"))
					.getText();
			System.out.println(name);
			if (name.equals(Name)) {
				flag = true;
				break;
			}else
			{
				flag=false;
			}

		}
		return flag;
	}
}
