package com.saucedo.molino.utils;

public class Valid {
	public static boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
		}catch(NumberFormatException e) {
			return false;
		}		
		return true;
	}
}
