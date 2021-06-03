package com.jqueryui.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.jqueryui.ExtentListners.ExtentTestManager;

public class DroppablePage extends BasePage {

	@FindBy(xpath="//*[contains(text(),'Create targets for draggable elements')]")
	public WebElement txtDroppable;
	
	@FindBy(xpath="//*[@id='content']/iframe")
	public WebElement frameDrop;
	
	@FindBy(xpath="//*[@id='draggable']")
	public WebElement draggable;
	
	@FindBy(xpath="//*[@id='droppable']")
	public WebElement droppable;
	
	@FindBy(xpath="//*[@id='droppable']/p")
	public WebElement txtdroppable;
	
	
	
	
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(txtDroppable);
	}
	
	//This is used to drag and drop the object
	public DroppablePage dragAndDrop(String drag,String drop)  {
		
		driver.switchTo().frame(frameDrop);
		ExtentTestManager.testReport.get().info("Switching to the frame on : "+frameDrop);
		System.out.println("inside the frame");
		Actions action=new Actions(driver);
		action.dragAndDrop(draggable, droppable).perform();
		
		System.out.println("performing the drop and the value changed to : "+txtdroppable.getText());
		
		ExtentTestManager.testReport.get().info("Expected text was Dropped! , and text coming is: "+txtdroppable.getText());
		Assert.assertEquals(txtdroppable.getText(), "Dropped!");
				
		return this;
	}
}
