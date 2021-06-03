package com.jqueryui.steps;

import com.jqueryui.ExtentListners.ExtentManager;
import com.jqueryui.ExtentListners.ExtentTestManager;
import com.jqueryui.PageObjects.ControlgroupPage;
import com.jqueryui.PageObjects.DroppablePage;
import com.jqueryui.PageObjects.HomePage;
import com.jqueryui.PageObjects.SelectablePage;

import java.util.List;

import com.aventstack.extentreports.Status;


import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class JquerySteps extends BaseSteps {
	
	public HomePage home;
	public DroppablePage droppable;
	public SelectablePage selectable;
	public ControlgroupPage controlG;

	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;

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
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}
	

@Given("^launch browser \"([^\"]*)\" in windows$")
public void launch_browser_in_windows(String browserName) throws Throwable {
	 openBrowser(browserName);
	 ExtentTestManager.logInfo("Browser Opened : "+browserName);
}

@When("^User navigate to the \"([^\"]*)\" url$")
public void user_navigate_to_the_url(String URL) throws Throwable {
	home = new HomePage().open(URL);
}

@When("^user selects \"([^\"]*)\" option available under Interactions$")
public void user_selects_option_available_under_Interactions(String Option) throws Throwable {
    droppable=home.clickDroppable(Option);
    
}

@Then("^use is able to drag the \"([^\"]*)\" component to \"([^\"]*)\" component$")
public void use_is_able_to_drag_the_component_to_component(String drag, String drop) throws Throwable {
    droppable.dragAndDrop(drag,drop);
}
@When("^user selects \"([^\"]*)\" option available under Interactions Tab$")
public void user_selects_option_available_under_Interactions_Tab(String option) throws Throwable {
	selectable=home.clickSelectable(option);
}

@Then("^user is able to select multiple Options$")
public void user_is_able_to_select(List<String> list) throws Throwable {
    selectable.selectMultipleItems(list);
}

@When("^user selects \"([^\"]*)\" option available under Widgets$")
public void user_selects_option_available_under_Widgets(String option) throws Throwable {
	controlG=home.clickControlgroup(option);
}

@When("^user selects \"([^\"]*)\" from the dropdown$")
public void user_selects_from_the_dropdown(String option) throws Throwable {
	controlG.switchToFrame();
    controlG.selectCarType(option);
}

@When("^selects type as \"([^\"]*)\"$")
public void selects_type_as(String arg1) throws Throwable {
    controlG.clickAutomatic(arg1);
}

@When("^checks \"([^\"]*)\"$")
public void checks(String arg1) throws Throwable {
    controlG.chkInsurance(arg1);
}

@When("^input number of cars as \"([^\"]*)\"$")
public void input_number_of_cars_as(String arg1) throws Throwable {
    controlG.inputCarsNo(arg1);
}

@When("^selects \"([^\"]*)\" from the dropdown$")
public void selects_from_the_dropdown(String arg1) throws Throwable {
    controlG.selectCarTypeRental(arg1);
}

@When("^car type as \"([^\"]*)\"$")
public void car_type_as(String arg1) throws Throwable {
	controlG.clickStandard(arg1);
}

@When("^checks \"([^\"]*)\" in the rental car option$")
public void checks_in_the_rental_car_option(String arg1) throws Throwable {
    controlG.chkInsuranceRental(arg1);
}

@When("^Number of Cars as \"([^\"]*)\"$")
public void number_of_Cars_as(String arg1) throws Throwable {
    controlG.inputCarsNoRental(arg1);
}

@When("^click on \"([^\"]*)\" Button$")
public void click_on_Button(String arg1) throws Throwable {
    controlG.clickBookNow(arg1);
}


}
