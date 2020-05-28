package common.singleton;

import java.awt.AWTException;
import java.awt.Robot;

public class Robotio {

	private static Robotio robotio = null;
	
	private Robot robot;
	
	private Robotio() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			System.out.println(e);
		}
	}
	
	public static Robotio getInstance() {
		if (robotio == null) { // if there is no instance available... create new one
			robotio = new Robotio();
		}

		return robotio;
	}
	
	public Robot getRobot() {
		return robot;
	}
	
}
