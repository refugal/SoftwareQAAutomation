package com.AppiumTest.runners;

import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;


	@CucumberOptions(features="src/test/resources/features/AppiumTest.feature",
			glue= "com.AppiumTest.steps"
			)
	public class AppiumTestRunner {
		@Test
		public void runCukes() {
			
			new TestNGCucumberRunner(getClass()).runCukes();
			
		}
	}

