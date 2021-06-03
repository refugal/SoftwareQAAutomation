package com.APITest.steps;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.jqueryui.ExtentListners.ExtentManager;
import com.jqueryui.ExtentListners.ExtentTestManager;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Data;
import pojo.GetPage;
import testDataBuild.TestData;

public class APITestSteps extends BaseSteps{
	
	
	RequestSpecification req;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	GetPage resp;
	PrintStream log;
	TestData data =new TestData();
	
	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;
	
	@Before
	public synchronized void  before(Scenario scenario) throws FileNotFoundException {
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri("https://reqres.in")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 
		 x = x + 1;
		 this.scenario = scenario;
			scenarioName = scenario.getName();
			ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
			ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
		}	 
	

	@After
	public void after(Scenario scenario) {
		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

	}

	@Given("^URI \"([^\"]*)\" and user calls with GET http request$")
	public void uri(String arg1) throws Throwable {
		

		
		response=given().spec(req).when().get("/api/users?page=2").then().extract().response();
		resp=given().spec(req).expect().defaultParser(Parser.JSON)
				.when().get("/api/users?page=2").as(GetPage.class);
		ExtentTestManager.logInfo("Get request is sent succesfully");
	}

	@Then("^the API call got success with status code (\\d+)$")
	public void the_API_call_got_success_with_status_code(int code) throws Throwable {
		Assert.assertEquals(response.getStatusCode(), code);
		ExtentTestManager.logInfo("Response is coming with expected status code: "+code);
	}

	@Then("^\"([^\"]*)\" for id \"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void for_id_in_response_body_is(String arg1, String arg2, String name) throws Throwable {
		int id;
	    List<Data> datai= resp.getData();
	    System.out.println(datai.get(0));
	    for(int i=0;i<datai.size();i++) {
	    	id= datai.get(i).getId();
	    	if(id==10) {
	    		System.out.println("first name is :"+datai.get(i).getFirst_name());
	    		Assert.assertEquals(datai.get(i).getFirst_name(), name,"first name is coming as expected");
	    		 ExtentTestManager.logInfo("first name is coming as expected");
	    	}
	    }
	   
	}

	@Given("^Json body with name as \"([^\"]*)\" and job as \"([^\"]*)\"$")
	public void json_body_with_name_as_and_job_as(String name, String job) throws Throwable {
		
		res=given().spec(req)
				.body(data.addPayLoad(name, job));
		
	}

	@When("^user calls with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String arg1) throws Throwable {
		response=res.when().post("/api/users").then().extract().response();
		ExtentTestManager.logInfo("Post request is sent to the server");
		
	}

	@Then("^id must be generated$")
	public void id_must_be_generated() throws Throwable {
		String idgenerated=getJsonPath(response,"id");
		System.out.println("id is :"+idgenerated);
		if(idgenerated==null) {
			Assert.assertTrue(false, "id is not generated");
			ExtentTestManager.logInfo("id is not generated");
		}else {
			Assert.assertTrue(true, "id is generated");
			ExtentTestManager.logInfo("id is generated");
		}
	}

	@Then("^the API Post call got success with status code (\\d+)$")
	public void the_API_Post_call_got_success_with_status_code(int code) throws Throwable {
		Assert.assertEquals(response.getStatusCode(), code);
		ExtentTestManager.logInfo("Expected status code is coming :"+code);
	}
	
}
