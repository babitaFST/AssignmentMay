package IBM.Assignment4_Appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import junit.framework.Assert;

public class SwagScenario3 {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities dc  = new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		dc.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
		dc.setCapability("appPackage", "com.swaglabsmobileapp");
		dc.setCapability("noReset", "true");

		AndroidDriver<AndroidElement>and  = new AndroidDriver<AndroidElement>(dc);
		
		System.out.println("android connected");
		Thread.sleep(3000);
		
		and.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
		and.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
		and.findElementByAccessibilityId("test-LOGIN").click();
		
		Thread.sleep(3000);
		
		//And user sorts item low to high "Name (A to Z)"
		and.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Modal Selector Button\"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.ImageView").click();
		Thread.sleep(1000);
		and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"Selector container\"]/android.view.ViewGroup/android.view.ViewGroup[4]/android.view.ViewGroup/android.widget.TextView").click();
		Thread.sleep(3000);

		//And user adds required item to cart
		and.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[2]").click();
		System.out.println("Product added.");

		//user proceeds to checkout
		and.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.widget.ImageView").click();
		System.out.println("Clicked Cart icon");

		Thread.sleep(3000);

		and.findElementByAccessibilityId("test-CHECKOUT").click();
		System.out.println("Clicked CHECKOUT button.");

		Thread.sleep(3000);

		//user enters the following details for checkout
		and.findElementByAccessibilityId("test-First Name").isDisplayed();
		System.out.println("Checkout Details page displayed");

		and.findElementByAccessibilityId("test-First Name").sendKeys("abc");
		and.findElementByAccessibilityId("test-Last Name").sendKeys("def");
		and.findElementByAccessibilityId("test-Zip/Postal Code").sendKeys("400087");



		and.findElementByAccessibilityId("test-CONTINUE").click();
		System.out.println("Clicked Continue button.");

		Thread.sleep(3000);


		
		TouchAction tc = new TouchAction(and);
		and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[1]").isDisplayed();



		System.out.println("Checkout Overview page dislayed.");

		AndroidElement pos1 = and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[1]");
		AndroidElement pos2 = and.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");

		tc.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(pos1)).withDuration(Duration.ofSeconds(2)))
		.moveTo(ElementOption.element(pos2)).release().perform();
		
		//user confirm checkout
		if(and.findElementByAccessibilityId("test-FINISH").isDisplayed())
				{
					System.out.println("Checkout Overview page dislayed.");
				}
		
		and.findElementByAccessibilityId("test-FINISH").click();
		Thread.sleep(3000);

		//user verify final confirmation message
		String confMsg=and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]").getText();

		Assert.assertEquals(confMsg,"THANK YOU FOR YOU ORDER");
		
		
		and.closeApp();
		
		
	}
	
}
