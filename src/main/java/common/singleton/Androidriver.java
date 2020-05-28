package common.singleton;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Androidriver {

	private static Androidriver androidriver = null;
	private AppiumDriver<MobileElement> mobileDriver;
	private AppiumDriver<WebElement> webDriver;
	private static final long TIME_OUT = 30;
	private static String udid;
	private static String appPackage;
	private static String appActivity;
	private URL url;
	private DesiredCapabilities capabilities;
	
	private Androidriver() {
		// Note: If you inspect in Google Chrome the device you're able to check it's GoogleChrome version,
		// which helps you identify the correct chromedriver version.
		// Note: Don't forget to start the APPIUM server!
		// Note: Don't forget to start the APPIUM server as Advanced and set chromedriver path!
		// C:\Users\Guilherme\eclipse-workspace\joaogfreitasm.appium\resources\chromedriver.exe
		// Note: To change to adb remote -> adb tcpip 5555,
		// then do adb connect ip:5555
		this.capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Phone");
		capabilities.setCapability("udid", Androidriver.udid);
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("appPackage", Androidriver.appPackage);
		capabilities.setCapability("appActivity", Androidriver.appActivity);
		capabilities.setCapability("skipDeviceInitialization", true);
		capabilities.setCapability("skipServerInstallation", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		try {
			this.url = new URL("http://127.0.0.1:4723/wd/hub");
		} catch (MalformedURLException e) {
			javax.swing.JOptionPane.showMessageDialog(null, e);
		}
		this.mobileDriver = new AppiumDriver<MobileElement>(url, capabilities);
		this.mobileDriver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
		this.webDriver = new AppiumDriver<WebElement>(url, capabilities);
		this.webDriver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
	}
	
	public static Androidriver getInstance(String udid, String appPackage, String appActivity) {
		if (androidriver == null) { // if there is no instance available... create new one
			setCapabilities(udid, appPackage, appActivity);
			androidriver = new Androidriver();
		}
		return androidriver;
	}
	
	public static Androidriver getInstance() {
		if (androidriver == null) { // if there is no instance available... create new one
			if (udid == null || appPackage == null || appActivity == null) {
				javax.swing.JOptionPane.showMessageDialog(null,
						"Capabilities must be first set.");
				return null;
			}
			androidriver = new Androidriver();
		}
		return androidriver;
	}

	public static void setUdid(String udid) {
		Androidriver.udid = udid;
	}

	public static void setAppPackage(String appPackage) {
		Androidriver.appPackage = appPackage;
	}

	public static void setAppActivity(String appActivity) {
		Androidriver.appActivity = appActivity;
	}

	public AppiumDriver<MobileElement> getMobileDriver() {
		return mobileDriver;
	}
	
	public AppiumDriver<WebElement> getWebDriver() {
		return webDriver;
	}
	
	private static void setCapabilities(String udid, String appPackage, String appActivity) {
		Androidriver.udid = udid;
		Androidriver.appPackage = appPackage;
		Androidriver.appActivity = appActivity;
	}
	
}
