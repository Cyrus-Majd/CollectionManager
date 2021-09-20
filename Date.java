// TODO implement isValid and compareTo. Maybe implement other methods

/** 
 * This class defines the Date data type.
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 * 
 */
public class Date implements Comparable<Date> {
	private int day;
	private int month;
	private int year;
	
	/** Default constructor. Creates a Date instance with today's date as the parameters.*/
	public Date() {}
	
	/** Takes in a date string and creates an instance of Date.
	 * @param date a date string in the form "mm/dd/yyyy".
	 */
	public Date(String date) {
		
	}
	
	/** Checks to see if a given date us valid. A date cannot be
	 * before the year 1980 and cannot be after today's date. A date
	 * must also contain the proper amount of days. For example, no day
	 * can exceed 31, if the month is April, June, September, or November,
	 * the day cannot exceed 30. February can only have a day value
	 * of 29 if it is a leap year.
	 * @return True or False depending on whether the date is considered valid.
	 */
	public boolean isValid() {
		return true;
	}
	
	/** Overrides the compareTo method and compares 2 dates.
	 * @param date an instance of Date.
	 */
	@Override
	public int compareTo(Date date) {
		return 0;
	}
	
	/** Overrides the toString method and returns the Date instance as a string.
	 * @return The Date instance as a string in the form "mm/dd/yyyy"
	 */
	@Override
	public String toString() {
		return Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);
	}
	
	/** Returns the day of a Date instance.
	 * @return The integer representation of a day.
	 */
	public int getDay() {
		return day;
	}
	
	/** Returns the month of a date instance.
	 * @return The integer representation of a month.
	 */
	public int getMonth() {
		return month;
	}
	
	/** Returns the year of a date instance.
	 * @return The integer representation of a year.
	 */
	public int getYear() {
		return year;
	}
}
