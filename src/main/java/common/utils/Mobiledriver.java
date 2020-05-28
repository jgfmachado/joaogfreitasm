package common.utils;

import java.util.ArrayList;

import javax.annotation.Nullable;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.singleton.Androidriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class Mobiledriver {
	
	private static AppiumDriver<MobileElement> driver = Androidriver.getInstance().getMobileDriver();
	
	private Mobiledriver() {
		throw new IllegalStateException("Utility class");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Nullable
	private static String getWebContext(AppiumDriver driver) {
		ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
		for (String context : contexts) {
			if (!context.equals("NATIVE_APP")) {
				return context;
			}
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	private static void executeJS(String code) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		ArrayList<String> contexts = new ArrayList(driver.getContextHandles());
		driver.context(contexts.get(1));
		wait.until(ExpectedConditions.javaScriptThrowsNoExceptions(code));
		driver.context(contexts.get(0));
	}
	
}
