package com.chat.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.chat.model.User;
import com.chat.repository.UserRepository;

/**
 * 
 * @author manish
 *
 */
public class Utility {
	/**
	 * Generate random password
	 * 
	 * @param len
	 * @return
	 */
	public static String generateRandomPassword(int len) {
		// A strong password has Cap_chars, Lower_chars,
		// numeric value and symbols. So we are using all of
		// them to generate our password
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";
		String values = Capital_chars + Small_chars + numbers + symbols;
		// Using random method
		Random rndm_method = new Random();
		String password = "";
		for (int i = 0; i < len; i++) {
			char ch = values.charAt(rndm_method.nextInt(values.length()));
			password += ch;
		}
		return password;
	}

	/**
	 * Generate OTP
	 * 
	 * @return
	 */
	public static Integer generateOTP() {
		// create instance of Random class
		Random rand = new Random();
		// Generate random integers in range 999999
		return 100000 + rand.nextInt(900000);
	}

	/**
	 * Convert list of string to list of integer
	 * 
	 * @param listOfString
	 * @param function
	 * @return
	 */
	public static <T, U> List<U> convertListOfStringToListOfInteger(List<T> listOfString, Function<T, U> function) {
		return listOfString.stream().map(function).collect(Collectors.toList());
	}

	/**
	 * Add hour, minute and seconds in add
	 * 
	 * @param date
	 * @param hourOfDay
	 * @param minute
	 * @param seconds
	 * @return
	 */
	public static Date addHourMinAndSecToDate(Date date, int hourOfDay, int minute, int seconds) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.AM_PM, 0);
			calendar.set(Calendar.HOUR, hourOfDay);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, seconds);
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getFormatedDateFromDate(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			return strDate;

		} catch (Exception e) {
		}
		return null;
	}

	public static Date getDate(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			return formatter.parse(date);

		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Check is number
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isNumber(String val) {
		try {
			Integer.parseInt(val.trim());
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Get Authorized Username
	 * 
	 * @return
	 */
	public static User getSessionUser(UserRepository userRepository) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		User user = userRepository.findByUsername(currentUsername);
		return user;
	}

	public static char generateNextAlphabate(char ch) {
		int c = ch;
		c++;
		return (char) c;
	}

	public static String addANDOrOR(boolean flag) {
		String condition = "";
		if (flag) {
			condition = " AND";
		}
		return condition;
	}

	public static String addWhere(boolean flag) {
		String condition = "";
		if (flag) {
			condition = " where";
		}
		return condition;
	}
}
