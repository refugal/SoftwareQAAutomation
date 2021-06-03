package com.jqueryui.PageObjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.jqueryui.ExtentListners.ExtentTestManager;

public class SelectablePage extends BasePage {
	
	@FindBy(xpath="//*[contains(text(),'Use the mouse to select elements')]")
	public WebElement txtSelectable;
	
	@FindBy(xpath="//*[@id='selectable']/li")
	public List<WebElement> lstItems;
	
	@FindBy(xpath="//*[@id='content']/iframe")
	public WebElement frameDrop;
	
	
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(txtSelectable);
	}

	public SelectablePage selectMultipleItems(List<String> list)  {
		driver.switchTo().frame(frameDrop);
		
		Actions action=new Actions(driver);
		action.keyDown(Keys.CONTROL).build().perform();
			
		for(int i=0;i<lstItems.size();i++) {
			if(list.contains(lstItems.get(i).getText())) {
				lstItems.get(i).click();
			}
		}
		
		action.keyUp(Keys.CONTROL).build().perform();
		ExtentTestManager.testReport.get().info("Item1, Item3, Item7 selected succesfully");
		return this;
	}

}
