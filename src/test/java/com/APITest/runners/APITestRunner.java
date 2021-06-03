package com.APITest.runners;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;

	@CucumberOptions(features="src/test/resources/features/APITest.feature",
			plugin ="json:target/jsonReports/cucumber-report.json",
			glue= "com.APITest.steps")
	public class APITestRunner {
		@Test
		public void runCukes() {
			
			new TestNGCucumberRunner(getClass()).runCukes();
			
		}
	}


