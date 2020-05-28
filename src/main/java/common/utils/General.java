package common.utils;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class General {

	private General() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * Removes special characters, etc..., from the given String.
	 * @param String -> the string that will be transformed
	 */
	public static String removeDiacriticalMarks(String string) {
	    return Normalizer.normalize(string, Form.NFD)
	        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	/**
	 * Cuts the given String within the max given length.
	 * @param string
	 * @param maxLength
	 * @return String
	 */
	public static String cutString(String string, int maxLength) {
		if(string.length() > maxLength){
			string = string.substring(0, maxLength);
		}
		return string;
	}
	
	/**
	 * @return String
	 */
	public static String getStringableDate() {
		return getOnlyDigits(getCurrentDateTime()).substring(0, 8);
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static String getOnlyDigits(String s) {
	    Pattern pattern = Pattern.compile("[^0-9]");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.replaceAll("");
	 }
	
	/**
	 * @param s
	 * @return
	 */
	public static String getOnlyStrings(String s) {
	    Pattern pattern = Pattern.compile("[^a-z A-Z]");
	    Matcher matcher = pattern.matcher(s);
	    return matcher.replaceAll("");
	 }
	
	/**
	 * Check if given String contains the given char.
	 * @param str
	 * @param chr
	 * @return
	 */
	public static boolean contains(String str, char chr ) {
        for(int i = 0; i < str.length(); i++)
            if(str.charAt(i) == chr)
                return true;
        return false;
	}
	
	/**
	 * Forces the thread to wait <b>x</b> miliseconds.
	 * @param x
	 */
	public static void waitXMiliseconds(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	/**
	 * Returns the current date and time <b>as a String</b>.
	 * @return String
	 */
	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = getCurrentDate();
		return dateFormat.format(date);
	}
	
	/**
	 * Returns the current date and time.
	 * @return Date
	 */
	private static Date getCurrentDate() {
		return new Date();
	}
	
}
