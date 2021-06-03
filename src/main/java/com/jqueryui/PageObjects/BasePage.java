package com.jqueryui.PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jqueryui.ExtentListners.*;
import com.jqueryui.utilities.*;

public abstract class BasePage<T> {

	
	
	protected WebDriver driver;
	
	  private long LOAD_TIMEOUT = 30;
     private int AJAX_ELEMENT_TIMEOUT = 10;

	    public BasePage() {
	        this.driver = DriverManager.getDriver();
	    }

	  
	    
	   //OpenPage is called whenever the page is navigated to another page 
	    public T openPage(Class<T> clazz) {
	        T page = null;
	        try {
	            driver = DriverManager.getDriver();
	            AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, AJAX_ELEMENT_TIMEOUT);
	            page = PageFactory.initElements(driver, clazz);
	            PageFactory.initElements(ajaxElemFactory, page);
	            ExpectedCondition pageLoadCondition = ((BasePage) page).getPageLoadCondition();
	            waitForPageToLoad(pageLoadCondition);
	        } catch (NoSuchElementException e) {
	        /*    
	            
	     */       throw new IllegalStateException(String.format("This is not the %s page", clazz.getSimpleName()));
	        }
	        return page;
	    }

	    private void waitForPageToLoad(ExpectedCondition pageLoadCondition) {
	    	WebDriverWait wait = new WebDriverWait(driver,LOAD_TIMEOUT);
	        wait.until(pageLoadCondition);
	    }
	    //to be overridden on every page objects
	    protected abstract ExpectedCondition getPageLoadCondition();

		
		public void click(WebElement element, String elementName) {
			
			element.click();
			ExtentTestManager.testReport.get().info("Clicking on : "+elementName);
			
		}
		
		public void waitForElementVisible(WebElement element) {
	    	WebDriverWait wait = new WebDriverWait(driver,LOAD_TIMEOUT);
	        wait.until(ExpectedConditions.visibilityOf(element));
	    }
		
		
		public void type(WebElement element, String value, String elementName) {
			
			element.sendKeys(value);
			ExtentTestManager.testReport.get().info("Typing in : "+elementName+" entered the value as : "+value);
		
		}
		public void selectOption(WebElement element, String value, String elementName) {
			
			Select sel=new Select(element);
			sel.selectByVisibleText(value);
			ExtentTestManager.testReport.get().info("Selecting value : "+value+" from dropdown : "+elementName);
		
		}
	
}
