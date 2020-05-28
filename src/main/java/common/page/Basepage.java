package common.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.singleton.Chromedriver;

public class Basepage {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected JavascriptExecutor executor;
	protected Actions actions;
	
	public Basepage(WebDriver driver) {
		this.driver = Chromedriver.getInstance().getWebDriver();
		this.wait = Chromedriver.getInstance().getWebDriverWait();
		this.executor = Chromedriver.getInstance().getJavascriptExecutor();
		this.actions = Chromedriver.getInstance().getActions();
	}
	
}
