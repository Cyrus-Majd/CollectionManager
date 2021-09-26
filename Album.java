/** 
 * This class defines the Album data type.
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 * 
 */
public class Album {
	private String title;
	private String artist;
	private Genre genre;	// enum: POP, COUNTRY, CLASSICAL, JAZZ, UNKNOWN
	private Date releaseDate;
	private boolean isAvailable;
	
	/** Constructor. Must have values for each value of the class. isAvailable is set to true by default.
	 * 
	 * @param title the album's title
	 * @param artist the album artist
	 * @param genre Genre enum. The album's genre
	 * @param date Date object. The album's release date
	 * 
	 */
	public Album(String title, String artist, Genre genre, Date releaseDate) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
		this.releaseDate = releaseDate;
		isAvailable = true;
	}
	
	/** Returns true or false depending on whether or not two albums have the same artist and title.
	 * 
	 * @param Album an Album object.
	 * @return True if the two albums have the same title and artist; False otherwise.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Album) {
			// Cast the input object as an Album
			Album secondAlbum = (Album)obj;
			
			// Compare the two album's titles and artists using string comparisons
			if (this.title.equalsIgnoreCase(secondAlbum.title) && this.artist.equalsIgnoreCase(secondAlbum.artist))
				return true;
		}
		return false;
	}
	
	/** Returns the Album instance as a string.
	 * 
	 * @return The Album instance in the form "title::artist::genre::releaseDate::is (not) available"
	 * 
	 */
	@Override
	public String toString() {
		
		// Convert the album genre into a string
		String genreString = "";
		switch (genre) {
		case POP:
			genreString = "Pop";
			break;
		case COUNTRY:
			genreString = "Country";
			break;
		case CLASSICAL:
			genreString = "Classical";
			break;
		case JAZZ:
			genreString = "Jazz";
			break;
		case UNKNOWN:
			genreString = "Unknown";
			break;
		}
		
		// Convert isAvailable into a string
		String availableString = (isAvailable) ? "is available" : "is not available";
		
		// Construct and return the full album string
		return title + "::" + artist + "::" + genreString + "::" + releaseDate.toString() + "::" + availableString;
	}
}
