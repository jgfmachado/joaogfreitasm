package common.singleton;

import org.sikuli.script.Screen;

public class Screenio {

	private static Screenio screenio = null;
	
	private Screen screen;
	
	private Screenio() {
		this.screen = new Screen();
	}
	
	public static Screenio getInstance() {
		if (screenio == null) { // if there is no instance available... create new one
			screenio = new Screenio();
		}

		return screenio;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
}
