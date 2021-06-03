package com.jqueryui.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.jqueryui.utilities.DriverManager;


public class HomePage extends BasePage{
	
	@FindBy(xpath="//*[contains(text(),'New in jQuery UI')]")
	public WebElement txtHeading;
	
	@FindBy(xpath="//a[contains(text(),'Droppable')]")
	public WebElement lnkDroppable;
	
	@FindBy(xpath="//a[contains(text(),'Selectable')]")
	public WebElement lnkSelectable;
	
	@FindBy(xpath="//*[@id='sidebar']//a[contains(text(),'Controlgroup')]")
	public WebElement lnkControlgroup;
	
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(txtHeading);
	}
	
	public HomePage open(String url) {
		
		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (HomePage) openPage(HomePage.class);
	}
	//page is navigated to droppable
	public DroppablePage clickDroppable(String option) {
		click(lnkDroppable, option);
		return (DroppablePage) openPage(DroppablePage.class);
	}
	//page is navigated to selectable
	public SelectablePage clickSelectable(String option) {
		click(lnkSelectable, option);
		return (SelectablePage) openPage(SelectablePage.class);
	}
	//page is navigated to controlgroup
	public ControlgroupPage clickControlgroup(String Option) {
		click(lnkControlgroup, "Option");
		return (ControlgroupPage) openPage(ControlgroupPage.class);
	}
}
