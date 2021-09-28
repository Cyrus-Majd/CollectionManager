import java.util.Scanner;
import java.util.StringTokenizer;

/** Class that handles input and output. 
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 *
 */
public class CollectionManager {
	/** Boolean to detect when user wishes to exit the program.*/
	private boolean active;
	/** The album collection.*/
	private Collection collection;
	
	/** The correct number of arguments to add an album to a collection. Used for checking command validity.*/
	private static final int ADD_ARGS = 5;
	/** Correct number of arguments for all other commands. Used for checking command validity.*/
	private static final int OTHER_ARGS = 3;
	
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
			collection.print();
			break;
		case "PD":
			collection.printByReleaseDate();
			break;
		case "PG":
			collection.printByGenre();
			break;
		case "A":
			// Checking if the user has the correct amount of arguments for the given command
			if (numArgs == ADD_ARGS) {
				addCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "D":
			// Checking if the user has the correct amount of arguments for the given command
			if (numArgs == OTHER_ARGS) {
				removeCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "L":
			// Checking if the user has the correct amount of arguments for the given command
			if (numArgs == OTHER_ARGS) {
				lendCommand(command);
			} else {
				System.out.println("ERROR: Incorrect number of arguments, try again!");
			}
			break;
		case "R":
			// Checking if the user has the correct amount of arguments for the given command
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
			System.out.println("Invalid Command!");
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
			System.out.println("Invalid Date!");
			return;
		}
		
		// Create the new album object
		Album newAlbum = new Album(title, artist, genre, releaseDate);
		
		// Adding the album and checking if the addition was successful
		boolean success = collection.add(newAlbum);
		
		if (success) {
			System.out.println(newAlbum.toString() + " >> added.");
		} else {
			System.out.println(newAlbum.toString() + " >> is already in the collection.");
		}
	}
	
	/** Performs the remove album command.
	 * 
	 * @param command StringTokenizer containing the remove album parameters
	 * 
	 */
	private void removeCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;		// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();		// Date is also unimportant
		
		// Create the album object that is to be removed
		Album removeAlbum = new Album(title, artist, genre, releaseDate);
		
		// Removing the album and checking if it was successful
		boolean success = collection.remove(removeAlbum);
		
		if (success) {
			System.out.println(title + "::" + artist + " >> deleted.");
		} else {
			System.out.println(removeAlbum.toString() + " >> is not in the collection.");
		}
	}
	
	/** Performs the lend album command.
	 * 
	 * @param command StringTokenizer containing the lend album parameters
	 * 
	 */
	private void lendCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;		// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();		// Date is also unimportant
		
		// Create the album object to be lended
		Album lendAlbum = new Album(title, artist, genre, releaseDate);
		
		// Lending out the album and checking if it was successful
		boolean success = collection.lendingOut(lendAlbum);
		
		if (success) {
			System.out.println(lendAlbum.toString() + " >> lending out and set to not available.");
		} else {
			System.out.println(lendAlbum.toString() + " >> could not be lended out.");
		}
	}
	
	/** Performs the return album command.
	 * 
	 * @param command StringTokenizer containing the return album parameters
	 * 
	 */
	private void returnCommand(StringTokenizer command) {
		// Setting the parameters of the album
		String title = command.nextToken();
		String artist = command.nextToken();
		Genre genre = Genre.UNKNOWN;		// Genre does not matter since we do not need it to delete the album
		Date releaseDate = new Date();		// Date is also unimportant
		
		// Create the album object to be returned
		Album returnAlbum = new Album(title, artist, genre, releaseDate);
		
		// Returning the album and checking if it was successful
		boolean success = collection.returnAlbum(returnAlbum);
		
		if (success) {
			System.out.println(returnAlbum.toString() + " >> returning and set to available.");
		} else {
			System.out.println(returnAlbum.toString() + " >> return cannot be completed.");
		}
	}
	
	/** Run method. Loop that reads user input and executes commands. Commands must be in proper format.
	 * 
	 * Commands:
	 * 		P = display the entire collection without a specific order
	 * 		PD = display the entire collection sorted by release date
	 * 		PG = display the entire collection sorted by genre
	 * 		A = add an album to the collection; must be in the form "A, Album Title, Album Artist, Genre, Release Date"
	 * 		D = remove an album from the collection; must be in the form "D, Album Title, Album Artist"
	 * 		L = lend out an album to a friend; must be in the form "L, Album Title, Album Artist"
	 * 		R = return an album that was lended out; must be in the form "R, Album Title, Album Artist"
	 * 		Q = stops the program execution
	 * 
	 */
	public void run() {
		// Create the scanner to read user input
		Scanner input = new Scanner(System.in);
		
		// Print a line to indicate the start of the program
		System.out.println("Collection manager starts running.");
		
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
