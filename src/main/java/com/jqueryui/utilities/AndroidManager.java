package com.jqueryui.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class AndroidManager {

	public static ThreadLocal<AndroidDriver> dr = new ThreadLocal<AndroidDriver>();

	public static AndroidDriver getDriver() {

		return dr.get();

	}

	public static void setWebDriver(AndroidDriver driver) {

		dr.set(driver);
	}

}
