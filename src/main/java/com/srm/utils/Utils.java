package com.srm.utils;

public class Utils {

	public static boolean isNumber(String number) {
		boolean isNumber = false;
		try {
			isNumber = Integer.parseInt(number) >= 0;
		} catch (NumberFormatException e) {
		}

		return isNumber;
	}
	
	public static Float calculaTaxaconformeRisco(String risco) {
		float taxa = 0;
		if (risco.equals("2")) {
			taxa = 10;
		}
		if (risco.equals("3")) {
			taxa = 20;
		}
		return taxa;

	}
	
}
