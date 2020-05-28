package common.singleton;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Chromedriver {

	private static Chromedriver chromedriver = null;
	private static final long TIME_OUT = 30;			// Driver timeout in seconds.
	private static final boolean IS_REMOTE = true;		// If it controls on the fly, or not.
	
	private WebDriver driver;
	private WebDriverWait wait;
	private JavascriptExecutor executor;
	private Actions actions;
	
	private Chromedriver() {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		if (!IS_REMOTE) {
			this.driver = new ChromeDriver();
		} else {		
			// Note: You need these extra command lines on your Chrome browser for remote control
			// Clean user: --remote-debugging-port=9222 --user-data-dir="C:\Selenium\AutomationProfile"
			// Default user: --remote-debugging-port=9222 --user-data-dir="C:\Users\YourUsername\AppData\Local\Google\Chrome\User Data"	
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
			this.driver = new ChromeDriver(options);
		}
		this.driver.manage().timeouts().implicitlyWait(TIME_OUT, TimeUnit.SECONDS);
		try {
			this.driver.manage().window().maximize();
		} catch (Exception e) {
			// ignore
		}
		this.wait = new WebDriverWait(driver, TIME_OUT);
		this.executor = (JavascriptExecutor) driver;
		this.actions = new Actions(driver);
	}
	
	public static Chromedriver getInstance() {
		if (chromedriver == null) { // if there is no instance available... create new one
			chromedriver = new Chromedriver();
		}
		return chromedriver;
	}
	
	public WebDriver getWebDriver() {
		return driver;
	}
	
	public WebDriverWait getWebDriverWait() {
		return wait;
	}
	
	public JavascriptExecutor getJavascriptExecutor() {
		return executor;
	}
	
	public Actions getActions() {
		return actions;
	}
	
}
