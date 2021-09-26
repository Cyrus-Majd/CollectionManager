import java.util.Scanner;
import java.util.StringTokenizer;

/** Class that handles input and output. 
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 *
 */
public class CollectionManager {
	private boolean active;						// Boolean to detect when user wishes to exit the program
	private Collection collection;				// The album collection
	private static final int ADD_ARGS = 5;		// Correct number of arguments to add an album to the collection
	private static final int OTHER_ARGS = 3;	// Correct number of arguments for all other commands
	
	/** Default constructor.*/
	public CollectionManager() {
		// Active set to true by default
		active = true;
		
		// Create the collection
		collection = new Collection();
	}
	
	/** Executes the given command or prints an error string if the command produced an error.
	 * 
	 * @param command all of the tokens of the user input
	 * 
	 */
	private void executeCommand(StringTokenizer command) {
		// Counter to make sure the command has the correct amount of arguments
		int numArgs = command.countTokens();
		
		// Switch statement to determine the command
		switch (command.nextToken()) {
		case "P":
			//collection.print();
			break;
		case "PD":
			//collection.printByReleaseDate();
			break;
		case "PG":
			//collection.printByGenre();
			break;
		case "A":
			if (numArgs == ADD_ARGS) {
				addCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "D":
			if (numArgs == OTHER_ARGS) {
				removeCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "L":
			if (numArgs == OTHER_ARGS) {
				lendCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "R":
			if (numArgs == OTHER_ARGS) {
				returnCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "Q":
			System.out.println("Exiting the collection manager.");
			active = false;
			break;
		default:
			System.out.println("ERROR: Invalid Command!");
		}
	}
	
	/** Performs the add album command.
	 * 
	 * @param command StringTokenizer containing the new album parameters
	 * 
	 */
	private void addCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre;
		switch(command.nextToken().toLowerCase()) {
		case "pop":
			genre = Genre.POP;
		case "country":
			genre = Genre.COUNTRY;
		case "classical":
			genre = Genre.CLASSICAL;
		case "jazz":
			genre = Genre.JAZZ;
		default:
			genre = Genre.UNKNOWN;
		}
		Date releaseDate = new Date(command.nextToken());
		
		// Check if the date is valid
		if (!releaseDate.isValid()) {
			System.out.println("ERROR: Date is invalid!");
			return;
		}
		
		// Create the new album object
		Album newAlbum = new Album(title, artist, genre, releaseDate);
		
		// Adding the album
		collection.add(newAlbum);
	}
	
	/** Performs the remove album command.
	 * 
	 * @param command command StringTokenizer containing the remove album parameters
	 */
	private void removeCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;			// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();			// Date is also unimportant
		
		// Create the new album object
		Album removeAlbum = new Album(title, artist, genre, releaseDate);
		
		// Adding the album
		collection.remove(removeAlbum);
	}
	
	/** Performs the lend album command.
	 * 
	 * @param command command StringTokenizer containing the lend album parameters
	 */
	private void lendCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;			// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();			// Date is also unimportant
		
		// Create the new album object
		Album lendAlbum = new Album(title, artist, genre, releaseDate);
		
		collection.lendingOut(lendAlbum);
	}
	
	/** Performs the return album command.
	 * 
	 * @param command command StringTokenizer containing the return album parameters
	 */
	private void returnCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;			// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();			// Date is also unimportant
		
		// Create the new album object
		Album returnAlbum = new Album(title, artist, genre, releaseDate);
		
		collection.returnAlbum(returnAlbum);
	}
	
	/** Run method. Loop that reads user input and executes commands. Commands must be in proper format.
	 * 
	 * Commands:
	 * 		P = display the entire collection without a specific order
	 * 		PD = display the entire collection sorted by release date
	 * 		PG = display the entire collection sorted by genre
	 * 		A = add an album to the collection -> must be in the form "A, Album Title, Album Artist, Genre, Release Date"
	 * 		D = remove an album from the collection -> must be in the form "D, Album Title, Album Artist"
	 * 		L = lend out an album to a friend -> must be in the form "L, Album Title, Album Artist"
	 * 		R = return an album that was lended out -> must be in the form "R, Album Title, Album Artist"
	 * 		Q = stops the program execution
	 * 
	 */
	public void run() {
		// Create the scanner to read user input
		Scanner input = new Scanner(System.in);
		
		// Print a line to indicate the start of the program
		System.out.println("Collection manager has started.");
		
		// While loop for receiving and executing commands
		while (active) {
			// Get the user's command
			String command = input.nextLine();
			
			// Break up the user command into tokens, separate tokens by comma
			StringTokenizer commandTokens = new StringTokenizer(command, ",");
			
			// Execute the given command
			executeCommand(commandTokens);
		}
		// Close the scanner
		input.close();
	}
}
