package common.utils;

import static common.utils.General.cutString;
import static common.utils.General.waitXMiliseconds;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.singleton.Chromedriver;

public class Webdriver {

	private static WebDriver driver = Chromedriver.getInstance().getWebDriver();
	private static WebDriverWait wait = Chromedriver.getInstance().getWebDriverWait();
	private static JavascriptExecutor js = Chromedriver.getInstance().getJavascriptExecutor();
	private static Actions actions = Chromedriver.getInstance().getActions();

	
	private Webdriver() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Returns a String that identifies the given element.
	 * @param element
	 * @return String
	 */
	public static String elementToString(WebElement element) {
		String elementString = element.toString();
		String elementSubstring = elementString.substring(elementString.indexOf("-> "));
		return "By."+elementSubstring.replace("-> ", "").replaceFirst("]", "");
	}
	
	/**
	 * Method that tries to create an element given By, <br>
	 * else it will throw an exception and print it.
	 * @param by (id, xpath, etc...)
	 * @return WebElement
	 * @Beta May not work as intended.
	 */
	public static WebElement createWebElementBy(By by) {
		WebElement element = null;
		try {
			element = driver.findElement(by);
		} catch (Exception e) {
			System.out.println(e);
		}
		return element;	
	}
	
	/**
	 * Method that tries to create an element List given By, <br>
	 * else it will throw an exception and print it.
	 * @param by (id, xpath, etc...)
	 * @return WebElement
	 * @Beta May not work as intended.
	 */
	public static List<WebElement> createWebElementListBy(By by) {
		List<WebElement> elements = null;
		try {
			elements = driver.findElements(by);
		} catch (Exception e) {
			System.out.println(e);
		}
		return elements;	
	}
	
	/**
	 * Performs a stroke of the Keyboard keys that will be pressed <br>
	 * a given number of times, with a delay.
	 * @param key what key will be pressed
	 * @param times how many times it will be pressed
	 */
	public static void movement(Keys key, int times) {
		if(times > 0) {
			for (int i = 0; i < times; i++) {
				movement(key);
			}
		}
	}

	/**
	 * Performs a stroke of the Keyboard keys that will be pressed <br>
	 * with a delay
	 * @param key, what key will be pressed
	 */
	public static void movement(Keys key) {
		waitXMiliseconds(200);
		driver.findElement(By.cssSelector("body")).sendKeys(key);
	}
	
	/**
	 * Performs a stroke of the Keyboard keys that will be pressed <br>
	 * without a delay
	 * @param key, what key will be pressed
	 */
	public static void movementNoDelay(Keys key) {
		driver.findElement(By.cssSelector("body")).sendKeys(key);
	}

	/**
	 * Change the value of the inner HTML
	 * @param id the id of the element
	 * @param value the value for which you want to change
	 */
	public static void changeValueById(String id, String value) {
		js.executeScript("document.getElementById('" + id + "').innerHTML = '" + value + "'");
		js.executeScript("document.getElementById('" + id + "').value = '" + value + "'");
	}
	
	/**
	 * Focus on a given WebElement.
	 * @param element
	 * @beta May not work as intended.
	 */
	public static WebElement moveToElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		actions.moveToElement(element).build().perform();
		return element;
	}
	
	/**
	 * Focus on a given WebElement.
	 * @param by
	 * @return 
	 * @beta May not work as intended.
	 */
	public static WebElement moveToElement(By by) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		WebElement element = createWebElementBy(by);
		actions.moveToElement(element).build().perform();
		return element;
	}
	
	/**
	 * Get Text from given element.
	 * @param by
	 * @beta May not work as intended.
	 */
	public static String getTextFromElement(By by) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		WebElement element = createWebElementBy(by);
		actions.moveToElement(element).build().perform();
		return element.getText();
	}
	
	/**
	 * Get Text from given element.
	 * @param by
	 * @beta May not work as intended.
	 */
	public static String getTextFromElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		actions.moveToElement(element).build().perform();
		return element.getText();
	}
	
	/**
	 * Click on a given WebElement
	 * @param element
	 * @beta May not work as intended.
	 */
	public static void clickOnElementBy(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
		WebElement element = createWebElementBy(by);
		actions.click(element).build().perform();
	}
	
	/**
	 * Click on a given WebElement
	 * @param element
	 * @beta May not work as intended.
	 */
	public static void clickOnElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		actions.click(element).build().perform();
	}
	
	/**
	 * Send keys sequence to the given Element
	 * @param by
	 * @param string
	 */
	public static void sendKeys(By by, String string) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement element = createWebElementBy(by);
		element.sendKeys(string);
	}
	
	/**
	 * Send keys sequence to the given Element
	 * @param by
	 * @param string
	 */
	public static void sendKeys(By by, Keys keys) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		WebElement element = createWebElementBy(by);
		element.sendKeys(keys);
	}
	
	/**
	 * Send keys sequence to the given Element
	 * @param by
	 * @param string
	 */
	public static void sendKeys(WebElement element, String string) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(string);
	}
	
	/**
	 * Still needs some adjustments, use with caution.
	 * Run method until condition has been met.
	 * @param runnable
	 * @param booleanSupplier
	 */
	public static void runMethodUntilCondition(Runnable runnable, BooleanSupplier booleanSupplier) {
		while(booleanSupplier.getAsBoolean()) {
			runnable.run();
		}
	}
	
	/**
	 * Get text from the given element.
	 * @param element
	 */
	public static String getElementText(WebElement element) {
		String text = null;
		text = element.getText();
		if(text.length()==0) {
			text = element.getAttribute("id");
		}		
		return cutString(text, 20);
	}

	/**
	 * Waits until given element <b>is</b> visible.
	 * @param id
	 */
	public static void waitUntilElementIsVisible(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * Waits until given element <b>is not</b> visible.
	 * @param id
	 */
	public static void waitUntilElementIsNotVisible(By by) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	
	/**
	 * Waits until given element <b>is</b> visible <br>
	 * and then becomes <b>not</b> visible.
	 * @param id
	 */
	public static void waitUntilElementIsAndIsNotVisible(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	/**
	 * Check if given element is <b>enabled</b> in the DOM.
	 * @param by
	 * @return boolean
	 */
	public static boolean isElementEnabled(By by) {
		try {
			driver.findElement(by).isEnabled();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Check if given element is <b>visible</b> in the DOM.
	 * @param by
	 * @return boolean
	 */
	public static boolean isElementVisible(By by) {
		try {
			String display = driver.findElement(by).getCssValue("display");
			return (!display.equals("none") && !display.equals("null"));
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Waits until element, <b>given by</b>, becomes <b>clickable</b>.
	 * @param by
	 * @return WebElement
	 */
	public static WebElement waitUntilElementToBeClickable(By by) {
		return wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * Waits until given element becomes <b>clickable</b>.
	 * @param element
	 * @return WebElement
	 */
	public static WebElement waitUntilElementToBeClickable(WebElement element) {
		return wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Returns value from javascript command.
	 * @param var
	 * @return String
	 */
	public static String getJavascriptValue(String var) {
		JavascriptExecutor js = (JavascriptExecutor) driver; // to ignore the printing to events
		String string = null;
		try {
			string = (String) js.executeScript("return " + var);
		} catch (Exception e) {
			System.out.println(e);
		}
		return string;
	}
	
}
