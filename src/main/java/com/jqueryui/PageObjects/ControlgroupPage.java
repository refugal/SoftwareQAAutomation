package com.jqueryui.PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.jqueryui.ExtentListners.ExtentTestManager;

public class ControlgroupPage extends BasePage {

	@FindBy(xpath="//*[contains(text(),'Groups multiple buttons and other widgets')]")
	public WebElement txtControlgroup;
	
	
	@FindBy(xpath="//*[@id='car-type-menu']/li")
	public List<WebElement> lstCarType;
	
	@FindBy(xpath="//*[@id='car-type-button']/span[1]")
	public WebElement btnCarType;
	
	@FindBy(xpath="//fieldset[1]/div/label[2]/span[1]")
	public WebElement chkAutomatic;
		
	@FindBy(xpath="//fieldset[1]/div/label[3]/span[1]")
	public WebElement chkInsurance;
	
	@FindBy(xpath="//*[@id='horizontal-spinner']")
	public WebElement txtNumberOfCars;
	
	@FindBy(xpath="//*[@id='ui-id-8-menu']/li")
	public List<WebElement> lstCarTypeRental;
	
	@FindBy(xpath="//*[@id='ui-id-8-button']/span[1]")
	public WebElement btnCarTypeRental;
	
	@FindBy(xpath="//fieldset[2]/div/select")
	public WebElement slctCarTypeRental;
	
	@FindBy(xpath="//fieldset[2]/div/label[1]/span[1]")
	public WebElement chkStandard;
	
	@FindBy(xpath="//fieldset[2]/div/label[3]/span[1]")
	public WebElement chkInsuranceRental;
	
	@FindBy(xpath="//*[@id='vertical-spinner']")
	public WebElement txtNumberOfCarsRental;
	
	@FindBy(xpath="//*[@id='vertical-spinner']")
	public WebElement btnBookNow;
	
	@FindBy(xpath="//*[@id='book']")
	public WebElement btnBookNowRental;
	
	@FindBy(xpath="//*[@id='content']/iframe")
	public WebElement frameDrop;
	
	
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(txtControlgroup);
	}
	
	//This will switch to the desired frame
	public ControlgroupPage switchToFrame() {
		driver.switchTo().frame(frameDrop);
		return this;
	}
	
	//car type will be selected here
	public ControlgroupPage selectCarType(String option)  {
		
//		selectOption(slctCarType, option, "Car Type");		
		click(btnCarType, "Car Type");
		for(WebElement el:lstCarType) {
			if(el.getText().equalsIgnoreCase(option)) {
				el.click();
			}
		}
		return this;
	}
	public ControlgroupPage clickAutomatic(String option)  {
		click(chkAutomatic, "Automatic checkbox");
		return this;
	}
	
	public ControlgroupPage chkInsurance(String option)  {
		click(chkInsurance, "Insurance checkbox");
		return this;
	}
	
	public ControlgroupPage inputCarsNo(String option)  {
		
		type(txtNumberOfCars, option, "Number of Cars");
		
		return this;
	}
	
	
	public ControlgroupPage selectCarTypeRental(String option)  {
			
	
			click(btnCarTypeRental, "Car Type");
			for(WebElement el:lstCarTypeRental) {
				if(el.getText().equalsIgnoreCase(option)) {
					el.click();
				}
			}
		return this;
	}
	
	public ControlgroupPage clickStandard(String option)  {
		click(chkStandard, "Standard checkbox");
		return this;
	}
	
	public ControlgroupPage chkInsuranceRental(String option)  {
		click(chkInsuranceRental, "Insurance checkbox");
		return this;
	}
	
	public ControlgroupPage inputCarsNoRental(String option)  {
		
		type(txtNumberOfCarsRental, option, "Number of Cars");
		
		return this;
	}
	public ControlgroupPage clickBookNow(String option)  {
		click(btnBookNowRental, "Book Now button");
		return this;
	}
	
}
