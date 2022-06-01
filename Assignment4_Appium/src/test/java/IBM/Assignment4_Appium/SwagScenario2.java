package IBM.Assignment4_Appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import junit.framework.Assert;

public class SwagScenario2 {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		DesiredCapabilities dc  = new DesiredCapabilities();
		
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		
		dc.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
		dc.setCapability("appPackage", "com.swaglabsmobileapp");
		dc.setCapability("noReset", "true");

		AndroidDriver<AndroidElement> and  = new AndroidDriver<AndroidElement>(dc);
		
		System.out.println("android connected");
		
		Thread.sleep(3000);
		
		and.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
		and.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
		and.findElementByAccessibilityId("test-LOGIN").click();
		
		Thread.sleep(3000);
		
		//user adds one item and then remove that item to go back
		and.findElementByXPath("(//android.view.ViewGroup[@content-desc=\"test-ADD TO CART\"])[1]").click();
		System.out.println("1st Product added.");
		and.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]/android.view.ViewGroup/android.view.ViewGroup").click();
		System.out.println("Cart icon clicked.");
		Thread.sleep(3000);
		and.findElementByAccessibilityId("test-REMOVE").click();
		System.out.println("REMOVE button clicked.");
		Thread.sleep(3000);
		and.findElementByAccessibilityId("test-CONTINUE SHOPPING").click();
		System.out.println("Continue Shopping button clicked.");
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


		
//		AndroidElement finishbutton = and.findElementByAccessibilityId("test-FINISH");
//		((JavascriptExecutor) and).executeScript("arguments[0].scrollIntoView(true);", finishbutton);
//		Thread.sleep(500); 
		
		TouchAction tc = new TouchAction(and);
		and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[1]").isDisplayed();



		System.out.println("Checkout Overview page dislayed.");

		AndroidElement pos1 = and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: OVERVIEW\"]/android.view.ViewGroup/android.widget.TextView[1]");
		AndroidElement pos2 = and.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Description\"]/android.widget.TextView[1]");

		tc.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(pos1)).withDuration(Duration.ofSeconds(2)))
		.moveTo(ElementOption.element(pos2)).release().perform();
		
		and.findElementByAccessibilityId("test-FINISH").click();
		Thread.sleep(3000);

		//user verify final confirmation message
		String confMsg=and.findElementByXPath("//android.widget.ScrollView[@content-desc=\"test-CHECKOUT: COMPLETE!\"]/android.view.ViewGroup/android.widget.TextView[1]").getText();

		Assert.assertEquals(confMsg,"THANK YOU FOR YOU ORDER");
		
		
//		and.closeApp();
		
		
	}
	
}
