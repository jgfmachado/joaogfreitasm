package common.utils;

import static common.utils.General.waitXMiliseconds;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

import common.singleton.Robotio;
import common.singleton.Screenio;

public class Sikulix {

	private static Robot robot = Robotio.getInstance().getRobot();
	private static Screen screen = Screenio.getInstance().getScreen();
	private static final int SCREEN_TIME_OUT = 30;
	
	private Sikulix() {
		throw new IllegalStateException("Utility class");
	}
	
	public static void robotMouseClickWithDelay() {
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100); // Click and hold for 1/10 of a second
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		waitXMiliseconds(250);
	}
	
	public static void robotKeyPressWithDelay(int i) {
		robot.keyPress(i);
		robot.delay(8); // Click and hold for 8 miliseconds
		robot.keyRelease(i);
		waitXMiliseconds(20);
	}
	
	public static void sleepForRandomTime(int i) {
		try {
			Thread.sleep(randomInt(i));
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
	
	public static int randomInt(int i) {
		Random r = new Random();
		return r.nextInt(i)+501;
	}
	
	public static void click(String s) {
		try {
			screen.find(s).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void mouseMoveTo(String string) {
		try {
			screen.wait(string, SCREEN_TIME_OUT);
			screen.mouseMove(string);
		} catch (FindFailed e) {
			System.out.println(e);
		}
	}
	
	public static void mouseMoveToAndClick(String string) {
		mouseMoveTo(string);
		robotMouseClickWithDelay();
	}
	
	public static void scrollDown(int numberOfScrolls) {
		for(int j = 0; j < numberOfScrolls; j++){
            //scroll and wait a bit to give the impression of smooth scrolling
            robot.mouseWheel(1);
            try { 
            	Thread.sleep(50); 
            } catch(InterruptedException e) {
            	System.out.println(e);
            }
        }
	}
	
	public static void scrollUp(int numberOfScrolls) {
		for(int j = 0; j < numberOfScrolls; j++){
            //scroll and wait a bit to give the impression of smooth scrolling
            robot.mouseWheel(-1);
            try { 
            	Thread.sleep(50); 
            } catch(InterruptedException e) {
            	System.out.println(e);
            }
        }
	}
	
}
