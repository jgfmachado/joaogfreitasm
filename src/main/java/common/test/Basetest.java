package common.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import common.singleton.Chromedriver;

public class Basetest {
	
	protected WebDriver driver = Chromedriver.getInstance().getWebDriver();
	protected WebDriverWait wait = Chromedriver.getInstance().getWebDriverWait();
	private static final String[] URL = {};
	
	@BeforeClass
	protected void setUp() { // Note: Can't be set as private
		driver.get(URL[0]);
	}
	
	@AfterClass
	protected void teardown() {
		driver.quit();
	}
	
}
