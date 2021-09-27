import java.util.Calendar;
import java.util.StringTokenizer;

/** 
 * This class defines the Date data type.
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 * 
 */
public class Date implements Comparable<Date> {
	/** Integer representation of the day.*/
	private int day;
	/** Integer representation of the month.*/
	private int month;
	/** Integer representation of the year.*/
	private int year;
	
	// Numbers used for calculating date validity
	/** Used for calculating leap year.*/
	private static final int QUADRENNIAL = 4;
	/** Used for calculating leap year.*/
	private static final int CENTENNIAL = 100;
	/** Used for calculating leap year.*/
	private static final int QUARTERCENNIAL = 400;
	/** Integer representation of the year 1980. Used when checking date validity.*/
	private static final int THE_EIGHTIES = 1980;
	/** Maximum amount of days in a normal month. Used when checking date validity.*/
	private static final int NORMAL_MONTH = 31;
	/** Maximum amount of days in April, June, September, and November. Used when checking date validity.*/
	private static final int SHORT_MONTH = 30;
	/** Maximum amount of days in February in a leap year. Used when checking date validity.*/
	private static final int FEB_LEAP_YEAR = 29;
	/** Minimum amount of days in any given month; Minimum month value. Used when checking date validity.*/
	private static final int DATE_LOWER_BOUND = 1;
	/** Maximum month value. Used when checking date validity.*/
	private static final int MONTH_UPPER_BOUND = 12;
	
	/** Method to check if the given year is a leap year.
	 * 
	 * @param year The integer representation of a year
	 * @return True if the year is a leap year; False otherwise.
	 * 
	 */
	private boolean isLeapYear(int year) {
		if (year % QUADRENNIAL == 0) {
			if (year % CENTENNIAL == 0) {
				if (year % QUARTERCENNIAL == 0) {
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
	
	/** Default constructor. Creates a Date instance with today's date as the parameters.*/
	public Date() {
		// Create a calendar instance to get date parameters
		Calendar calendar = Calendar.getInstance();
		
		// Get today's month, day, and year
		month = calendar.get(Calendar.MONTH) + 1;	// +1 since January is indexed as 0
		day = calendar.get(Calendar.DAY_OF_MONTH);
		year = calendar.get(Calendar.YEAR);
	}
	
	/** Takes in a date string and creates an instance of Date.
	 * 
	 * @param date The string representation of a date in the form "mm/dd/yyyy".
	 * 
	 */
	public Date(String date) {
		// Constructs a StringTokenizer to individually set the Date parameters
		StringTokenizer dateTokens = new StringTokenizer(date, "/");
		
		// Set each date parameter
		month = Integer.parseInt(dateTokens.nextToken());
		day = Integer.parseInt(dateTokens.nextToken());
		year = Integer.parseInt(dateTokens.nextToken());
	}
	
	/** Checks to see if a given date us valid. A date cannot be
	 * before the year 1980 and cannot be after today's date. A date
	 * must also contain the proper amount of days. For example, no day
	 * can exceed 31, if the month is April, June, September, or November,
	 * the day cannot exceed 30. February can only have a day value
	 * of 29 if it is a leap year.
	 * 
	 * @return True or False depending on whether the date is considered valid.
	 * 
	 */
	public boolean isValid() {		
		// Check if the date is after today
		Date today = new Date();
		if (today.compareTo(this) > 0) {
			return false;
		}
		
		// Check if the month is valid (a month cannot be less than 1 or greater than 12)
		if (month < DATE_LOWER_BOUND || month > MONTH_UPPER_BOUND) {
			return false;
		}
		
		// Check if the day is at least 1 and no greater than 31
		if (day < DATE_LOWER_BOUND || day > NORMAL_MONTH) {
			return false;
		}
		
		// Check if the year is before 1980
		if (year < THE_EIGHTIES) {
			return false;
		}
		
		// If it is February, check for date validity
		if (month == Calendar.FEBRUARY + 1) {
			// The day value cannot exceed 29 in February
			if (day > FEB_LEAP_YEAR) {
				return false;
			}
			// If it is not a leap year and the day is 29, the date is invalid
			if (!isLeapYear(year) && day == FEB_LEAP_YEAR) {
				return false;
			}
		}
		
		// Check to see if the other month and day combinations are valid
		// April, June, September, and November only have 30 days
		if (day > SHORT_MONTH && (month == Calendar.APRIL + 1 || month == Calendar.JUNE + 1 || month == Calendar.SEPTEMBER + 1 || month == Calendar.NOVEMBER + 1)) {
			return false;
		}
		
		// Return true if the date passes all of the validity checks
		return true;
	}
	
	/** Overrides the compareTo method and compares 2 dates.
	 * 
	 * @param date an instance of Date.
	 * @return -1, 0, or 1. If this Date is more recent, returns -1; If the Date argument is more recent, return 1; If the dates are equivalent, return 0
	 * 
	 */
	@Override
	public int compareTo(Date date) {
		// Check if this date's year is more recent
		if (this.year > date.year) {
			return -1;
		}
		// Check if the years are equal
		if (this.year == date.year) {
			// Check if this date's month is more recent
			if (this. month > date.month) {
				return -1;
			}
			// Check if the months are equal
			if (this.month == date.month) {
				// Check if this date's day is more recent
				if (this.day > date.day) {
					return -1;
				}
				// The dates are the exact same
				if (this.day == date.day) {
					return 0;
				}
			}
		}
		// This date object is more recent
		return 1;
	}
	
	/** Overrides the toString method and returns the Date instance as a string.
	 * 
	 * @return String representation of the Date instance in the form "mm/dd/yyyy"
	 * 
	 */
	@Override
	public String toString() {
		return Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
	}
	
	/** Returns the day of the Date instance.
	 * 
	 * @return The integer representation the release day.
	 * 
	 */
	public int getDay() {
		return day;
	}
	
	/** Returns the month of the Date instance.
	 * 
	 * @return The integer representation of the release month.
	 * 
	 */
	public int getMonth() {
		return month;
	}
	
	/** Returns the year of the Date instance.
	 * 
	 * @return The integer representation of the release year.
	 * 
	 */
	public int getYear() {
		return year;
	}
	
	/** Testbed main. Used to test the isValid method. More information in the test doc.*/
	public static void main(String args[]) {
		// Test case 1: Date cannot be before the year 1980.
		Date test1 = new Date("12/31/1979");
		System.out.println("Test case 1 (should be false): " + test1.isValid());
		
		// Test case 2: Date can be after the year 1980.
		Date test2 = new Date("1/1/1980");
		System.out.println("Test case 2 (should be true): " + test2.isValid());
		
		// Test case 3: Day value in any given month cannot exceed 31.
		Date test3 = new Date("1/32/1980");
		System.out.println("Test case 3 (should be false): " + test3.isValid());
		
		// Test case 4: Days value in any given month must be at least 1.
		Date test4 = new Date("1/0/1980");
		System.out.println("Test case 4 (should be false): " + test4.isValid());
		
		// Test case 5: Month value must be at least 1.
		Date test5 = new Date("0/15/1980");
		System.out.println("Test case 5 (should be false): " + test5.isValid());
		
		// Test case 6: Month value cannot exceed 12.
		Date test6 = new Date("13/15/1980");
		System.out.println("Test case 6 (should be false): " + test6.isValid());
		
		// Test case 7: Date cannot exceed today's date.
		Date test7 = new Date("10/15/2100");
		System.out.println("Test case 7 (should be false): " + test7.isValid());
		
		// Test case 8: The day value cannot exceed 28 if it is not a leap year.
		Date test8 = new Date("2/29/2021");
		System.out.println("Test case 8 (should be false): " + test8.isValid());
		
		// Test case 9: The day value cannot exceed 30 if it is April, June, September, or November.
		Date test9 = new Date("11/31/2020");
		System.out.println("Test case 9 (should be false): " + test9.isValid());
		
		// Test case 10: The date can be today's date.
		Date test10 = new Date();
		System.out.println("Test case 10 (should be true): " + test10.isValid());
	}
}
