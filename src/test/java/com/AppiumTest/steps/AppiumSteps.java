package com.AppiumTest.steps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.jqueryui.ExtentListners.ExtentManager;
import com.jqueryui.ExtentListners.ExtentTestManager;
import com.jqueryui.utilities.AndroidManager;
import com.jqueryui.utilities.DriverManager;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;

public class AppiumSteps extends AppiumBaseSteps {

	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;
	protected AndroidDriver driver=AndroidManager.getDriver();
	 

	@Before
	public synchronized void  before(Scenario scenario) {
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x = x + 1;
		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
		
		setUpFramework();
		
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			//ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	
	@Given("^User open application in Android$")
	public void user_open_application_in_Android() throws Throwable {
		System.out.println("1");
	    openApplication();
	    ExtentTestManager.logInfo("Application Opened");
	   
	}

	@Then("^Title must be available \"([^\"]*)\"$")
	public void title_must_be_available(String expected) throws Throwable {
		String headingText= AndroidManager.getDriver().findElement(By.id("android:id/title")).getText();
		Assert.assertEquals(headingText, expected);
		ExtentTestManager.logInfo("Expected heading is :"+expected+"and heading coming is headingText");
	}

	@When("^User clicked on EN Button$")
	public void user_clicked_on_EN_Button() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/buttonTest")).click();
		ExtentTestManager.logInfo("EN button clicked");
	}

	@When("^select Option \"([^\"]*)\"$")
	public void select_Option(String arg1) throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/button2")));
		AndroidManager.getDriver().findElement(By.id("android:id/button2")).click();
	     ExtentTestManager.logInfo("No,No button clicked");
	}

	@Then("^Homepage must be displayed$")
	public void homepage_must_be_displayed() throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("android:id/title")));
	     List<WebElement> element=AndroidManager.getDriver().findElements(By.id("android:id/title"));
	     if(element.isEmpty()) {
	     	Assert.assertTrue(false,"Homepage is not displayed");
	     }else {
	    	 Assert.assertTrue(true,"Homepage is displayed");
	    	 ExtentTestManager.logInfo("Homepage is displayed");
	     }
	}

	@When("^User clicked on Chrome logo Button$")
	public void user_clicked_on_Chrome_logo_Button() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
		ExtentTestManager.logInfo("Clicked on Chrome logo button");
	}

	@Then("^the text must be available \"([^\"]*)\"$")
	public void the_text_must_be_available(String expectedText) throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='Hello, can you please tell me your name?']")));
	     String actualText=AndroidManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='Hello, can you please tell me your name?']")).getAttribute("content-desc");
	   Assert.assertEquals(actualText,expectedText);
	   ExtentTestManager.logInfo("Expected text is :"+expectedText+" and actual text is :"+actualText);
	}

	@When("^name is entered as \"([^\"]*)\"$")
	public void name_is_entered_as(String name) throws Throwable {
		AndroidManager.getDriver().findElement(By.xpath("//android.widget.EditText[@content-desc='Enter your name here!']")).sendKeys(name);
		ExtentTestManager.logInfo("Name is entered in the input field :"+name);
	}

	@When("^Preferred car is selected as \"([^\"]*)\"$")
	public void preferred_car_is_selected_as(String carType) throws Throwable {
		AndroidManager.getDriver().findElement(By.xpath("//android.widget.Spinner[@index='2']")).click();
	     System.out.println("2");
	     Thread.sleep(5000);
	     List<WebElement> li= AndroidManager.getDriver().findElements(By.id("android:id/text1"));
	     for(WebElement el:li) {
	     	if(el.getText().equalsIgnoreCase(carType)) {
	     		el.click();
	     		break;
	     	}
	     }
	    
	     ExtentTestManager.logInfo(carType+" is entered in the dropdown field :");
	}

	@When("^clicked on Send me Your Name$")
	public void clicked_on_Send_me_Your_Name() throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Button[@content-desc='Send me your name!']")));
		AndroidManager.getDriver().findElement(By.xpath("//android.widget.Button[@content-desc='Send me your name!']")).click();
		ExtentTestManager.logInfo("Send my name button is clicked");
	}

	@Then("^verify the text is available on the next screen \"([^\"]*)\"$")
	public void verify_the_text_is_available_on_the_next_screen(String heading) throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.view.View[@content-desc='This is my way of saying hello']")));
		
		String txtHeadingActual=AndroidManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='This is my way of saying hello']")).getAttribute("content-desc");
		Assert.assertEquals(txtHeadingActual,heading);
		  ExtentTestManager.logInfo("Expected text is :"+heading+" and actual text is :"+txtHeadingActual);
	}

	@Then("^name as \"([^\"]*)\" and preferred car as \"([^\"]*)\" is available in the next screen$")
	public void name_as_and_preferred_car_as_is_available_in_the_next_screen(String name, String carType) throws Throwable {
		
	     String txtName=AndroidManager.getDriver().findElement(By.xpath("//android.view.View[@index='3']")).getAttribute("content-desc");
	     String txtCarType=AndroidManager.getDriver().findElement(By.xpath("//android.view.View[@index='5']/android.view.View[@index='0']")).getAttribute("content-desc");
	     
	     Assert.assertEquals(txtName.substring(1, txtName.length()-1),name);
		  ExtentTestManager.logInfo("Expected text is :"+name+" and actual text is :"+txtName);
		  
		  Assert.assertEquals(txtCarType.substring(1, txtCarType.length()-1),carType);
		  ExtentTestManager.logInfo("Expected text is :"+carType+" and actual text is :"+txtCarType);
	}

	@When("^clicked on Here on the bottom of the page$")
	public void clicked_on_Here_on_the_bottom_of_the_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		AndroidManager.getDriver().findElement(By.xpath("//android.view.View[@content-desc='here']")).click();
		ExtentTestManager.logInfo("Here button is clicked");
	}

	@Then("^page is navigated to the previous page$")
	public void page_is_navigated_to_the_previous_page() throws Throwable {
		
	}

	@Then("^default Preferred car selected is \"([^\"]*)\"$")
	public void default_Preferred_car_selected_is(String expectedCarType) throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.Spinner[@index='2']")));
		
		
		String carTypeDefault= AndroidManager.getDriver().findElement(By.xpath("//android.widget.Spinner[@index='2']")).getAttribute("content-desc");
		Assert.assertEquals(carTypeDefault,expectedCarType);
		  ExtentTestManager.logInfo("Expected text is :"+expectedCarType+" and actual text is :"+carTypeDefault);
	}

	@When("^User clicked on File logo Button$")
	public void user_clicked_on_File_logo_Button() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/startUserRegistration")).click();
	}

	@Then("^Title must be visible on the page \"([^\"]*)\"$")
	public void title_must_be_visible_on_the_page(String expected) throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView")));
		//text tell the name
		 String actual=AndroidManager.getDriver().findElement(By.xpath("//android.widget.TextView")).getAttribute("text");
		Assert.assertEquals(actual,expected);
		  ExtentTestManager.logInfo("Expected text is :"+expected+" and actual text is :"+actual);
		
		AndroidManager.getDriver().hideKeyboard();
	}

	@Then("^Text must be visible on the register page \"([^\"]*)\"$")
	public void text_must_be_visible_on_the_register_page(String expected) throws Throwable {
	    
	}

	@Then("^Name field must be prepopulated as \"([^\"]*)\"$")
	public void name_field_must_be_prepopulated_as(String expected) throws Throwable {
		String actual=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputName")).getAttribute("text");
		Assert.assertEquals(actual,expected);
		  ExtentTestManager.logInfo("Expected text is :"+expected+" and actual text is :"+actual);
		
	}

	@Then("^\"([^\"]*)\" is selected as Programming Language by default$")
	public void is_selected_as_Programming_Language_by_default(String expected) throws Throwable {
		
		String actual=AndroidManager.getDriver().findElement(By.id("android:id/text1")).getAttribute("text");
		Assert.assertEquals(actual,expected);
		  ExtentTestManager.logInfo("Expected text is :"+expected+" and actual text is :"+actual);
	}

	@When("^Register Page is filled with details as \"([^\"]*)\" as Username, \"([^\"]*)\" as email, \"([^\"]*)\" as password,\"([^\"]*)\" as Name$")
	public void register_Page_is_filled_with_details_as_as_Username_as_email_as_password_as_Name(String username, String email, String password, String name) throws Throwable {
		//username
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputUsername")).sendKeys(username);
		//email
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputEmail")).sendKeys(email);
		//password
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputPassword")).sendKeys(password);

		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputName")).clear();
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputName")).sendKeys(name);
	}

	@When("^accept adds is checked$")
	public void accept_adds_is_checked() throws Throwable {
		//check accept adds
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/input_adds")).click();
	}

	@When("^Register Now button is clicked$")
	public void register_Now_button_is_clicked() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/btnRegisterUser")).click();
	}

	@Then("^Verify the details as \"([^\"]*)\" as Username, \"([^\"]*)\" as email, \"([^\"]*)\" as password,\"([^\"]*)\" as Name are available in the next page$")
	public void verify_the_details_as_as_Username_as_email_as_password_as_Name_are_available_in_the_next_page(String username, String email, String password, String name) throws Throwable {
		
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("io.selendroid.testapp:id/label_name_data")));
		String actualName=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/label_name_data")).getAttribute("text");
		Assert.assertEquals(actualName,name);
		  ExtentTestManager.logInfo("Expected text is :"+name+" and actual text is :"+name);
		  
		  String actualUsername=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/label_username_data")).getAttribute("text");
			Assert.assertEquals(actualUsername,username);
			  ExtentTestManager.logInfo("Expected text is :"+username+" and actual text is :"+actualUsername);
			  
			  String actualPassword=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/label_password_data")).getAttribute("text");
				Assert.assertEquals(actualPassword,password);
				  ExtentTestManager.logInfo("Expected text is :"+password+" and actual text is :"+actualPassword);
				  
				  String actualEmail=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/label_email_data")).getAttribute("text");
					Assert.assertEquals(actualEmail,email);
					  ExtentTestManager.logInfo("Expected text is :"+email+" and actual text is :"+actualEmail);
					  
					  String actualAccept=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/label_acceptAdds_data")).getAttribute("text");
						Assert.assertEquals(actualAccept,"true");
						  ExtentTestManager.logInfo("Expected text is :true and actual text is :"+actualAccept);

	}

	@When("^user clicks on Register now$")
	public void user_clicks_on_Register_now() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/buttonRegisterUser")).click();
	}

	@Then("^homepage is available$")
	public void homepage_is_available() throws Throwable {
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.widget.TextView")));
		
		String actualAccept=AndroidManager.getDriver().findElement(By.xpath("//android.widget.TextView")).getAttribute("text");
		Assert.assertEquals(actualAccept,"selendroid-test-app");
		  ExtentTestManager.logInfo("Expected text is :selendroid-test-app and actual text is :"+actualAccept);
		  
	}

	@When("^User clicked on Show Progress Bar for a while$")
	public void user_clicked_on_Show_Progress_Bar_for_a_while() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/waitingButtonTest")).click();
	}

	@Then("^User registration page is available after waiting for the progress bar to disappear$")
	public void user_registration_page_is_available_after_waiting_for_the_progress_bar_to_disappear() throws Throwable {
		WebDriverWait w=new WebDriverWait(AndroidManager.getDriver(), 20);
		w.until(ExpectedConditions.invisibilityOfElementLocated(By.id("android:id/message")));
		
		WebDriverWait wait=new WebDriverWait(AndroidManager.getDriver(), 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("io.selendroid.testapp:id/inputName")));
		 String actual=AndroidManager.getDriver().findElement(By.xpath("//android.widget.TextView")).getAttribute("text");
			Assert.assertEquals(actual,"selendroid-test-app");
			  ExtentTestManager.logInfo("Expected text is :selendroid-test-app and actual text is :"+actual);
			
			AndroidManager.getDriver().hideKeyboard();
			
			String actualType=AndroidManager.getDriver().findElement(By.id("android:id/text1")).getAttribute("text");
			Assert.assertEquals(actualType,"Ruby");
			  ExtentTestManager.logInfo("Expected text is :Ruby and actual text is :"+actualType);
			  
			  String actualName=AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/inputName")).getAttribute("text");
				Assert.assertEquals(actualName,"Mr. Burns");
				  ExtentTestManager.logInfo("Expected text is :Mr. Burns and actual text is :"+actualName);
				
	}

	@When("^User clicked on Press to throw unhandled exception$")
	public void user_clicked_on_Press_to_throw_unhandled_exception() throws Throwable {
		AndroidManager.getDriver().findElement(By.id("io.selendroid.testapp:id/exceptionTestButton")).click();
	    Thread.sleep(5000);
	    AndroidManager.getDriver().findElement(By.id("android:id/button1")).click();
	    ExtentTestManager.logInfo("Button is clicked");
	    Thread.sleep(8000);	
	}

	@Then("^User navigates to homepage$")
	public void user_navigates_to_homepage() throws Throwable {

	    List<WebElement> element=AndroidManager.getDriver().findElements(By.id("io.selendroid.testapp:id/exceptionTestButton"));
	  if(element.isEmpty()) {
		  ExtentTestManager.logInfo("Home page is not getting displayed");
	  	Assert.assertTrue(false,"Homepage is not availablw");
	  }else {
		  Assert.assertTrue(false,"Homepage is not availablw");
	  }
	}
}
