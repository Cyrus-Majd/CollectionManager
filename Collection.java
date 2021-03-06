/** Class that defines the array list that stores albums and provides methods to organize and manipulate the collection.
 * 
 * @author Cyrus Majd
 * @author Nikodem Kisielewski
 *
 */
public class Collection {
	/** An array containing all of the albums in the collection.*/
    private Album[] albums = new Album[4];
    /** The number of albums currently in the collection.*/
    private int numAlbums = 0;

    /** Method that finds the specified album object in the collection. Returns the index of the album or -1 if the album could not be found.
     * 
     * @param album an album object that
     * @return the index of the desired album object or -1 if the album could not be found
     * 
     */
    private int find(Album album) { // find the album index or return "NOT_FOUND". for NOT_FOUND I'm just gonna return -1.
        for (int i = 0; i < albums.length; i++) {
            if (album.equals(albums[i])) {
                return i;
            }
        }
        return -1;
    }

    /** Increases the capacity of the album collection by 4.*/
    private void grow() {   // increase the capacity of the arraylist by 4
        Album[] newAlbums = new Album[albums.length + 4];
        for (int i = 0; i < newAlbums.length; i++) {
            // copy the old values as much as you can, and the new values are just chillin there.
            if (i < albums.length) {
                newAlbums[i] = albums[i];
                numAlbums = i;
            }
        }
        albums = newAlbums;
    }

    /** Adds an album to the collection. If the collection is full, will call the grow method to create more space for the new album.
     * If the album is already in the collection, returns false and does not add the album.
     * 
     * @param album the album object to be added
     * @return True if the album is added successfully; False if the album is already in the collection
     * 
     */
    public boolean add(Album album) {
        int searchResult = find(album);
        if (searchResult == -1) {   // The element is not found, which means we are clear to add it.
            if (numAlbums < albums.length) {    // if this is true, it means we have space in the array and we can add.
                albums[numAlbums] = album;
                numAlbums++;
                return true;
            }
            else if (numAlbums >= albums.length) {  // if this is true, it means we need to expand the array before we add more.
                grow();
                numAlbums++;
                albums[numAlbums] = album;
                numAlbums++;
                return true;
            }
            return false;   // if something catastrophic happens, this statment will run.
        }
        else {  // we return false if the element already exists in the arraylist, so that we dont have to add it again.
            return false;
        }
    }

    /** Removes the specified album from the collection. Returns true if successful or false if the album could not be removed.
     * 
     * @param album the album object to be removed
     * @return True if the album is removed successfully; False if the album could not be found
     * 
     */
    public boolean remove(Album album) {
        int searchResult = find(album);
        if (searchResult == -1) {
            // the element doesn't even exist in the list :( return false because we are sad now.
            return false;
        }
        else {  // logic for deleting and reordering. It basically shifts all values down by one.
            for (int i = searchResult; i < albums.length-1; i++) {
                albums[i] = albums[i+1];
            }
            numAlbums--;
            return true;
        }
    }

    /** Finds the specified album in the collection and sets its availability to not available. The album has been "lended out."
     * 
     * @param album the album object to be lended out
     * @return True if the album is lended out successfully; False if the album could not be found
     * 
     */
    public boolean lendingOut(Album album) {    // set to not available
        int index = find(album);
        if (index > -1) {
            albums[index].setAvailability(false);
            return true;
        }
        return false;
    }

    /** Finds the specified album in the collection and sets its availability to available. The album has been "returned."
     * 
     * @param album the album object to be returned
     * @return True if the album is returned successfully; False if the album could not be found
     * 
     */
    public boolean returnAlbum(Album album) {   // set to available
        int index = find(album);
        if (index > -1) {
            albums[index].setAvailability(true);
            return true;
        }
        return false;
    }

    /** Prints all of the albums in the collection in no particular order.*/
    public void print() {   // display the list without specifying the order
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        System.out.println("*List of albums in the collection.");
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
        System.out.println("*End of list");
    }

    /** Prints all of the albums in the collection in order of release date.*/
    public void printByReleaseDate() {  // we want old to be at index 0 and new to be at the latest index.
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        for (int j = 0; j < numAlbums; j++) {
            for (int i = 0; i < numAlbums-1; i++) {
                if (albums[i].getDate().compareTo(albums[i+1].getDate()) == -1) {   // 1 means that album at i is more recent. 0 means album at i+1 is more recent.
                    Album tmp = albums[i];
                    albums[i] = albums[i+1];
                    albums[i+1] = tmp;
                }
            }
        }
        System.out.println("*Album collection by the release dates.");
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
        System.out.println("*End of list");
    }

    /** Prints all of the albums in the collection in order of genre.*/
    public void printByGenre() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }

        // IDEA: this is stupid but ez. list for each genre. find all in each genre. add lists. YOLO.

        Album classicalList[] = new Album[numAlbums];
        Album countryList[] = new Album[numAlbums];
        Album jazzList[] = new Album[numAlbums];
        Album popList[] = new Album[numAlbums];
        Album unknownList[] = new Album[numAlbums];

        int classicalIndex = 0;
        int countryIndex = 0;
        int jazzIndex = 0;
        int popIndex = 0;
        int unknownIndex = 0;

        for (int i = 0; i < numAlbums; i++) {   // classical loop
            switch(albums[i].getGenre().toString()) {
                case "CLASSICAL":
                    classicalList[classicalIndex] = albums[i];
                    classicalIndex++;
                    break;
                case "COUNTRY":
                    countryList[countryIndex] = albums[i];
                    countryIndex++;
                    break;
                case "JAZZ":
                    jazzList[jazzIndex] = albums[i];
                    jazzIndex++;
                    break;
                case "POP":
                    popList[popIndex] = albums[i];
                    popIndex++;
                    break;
                case "UNKNOWN":
                    unknownList[unknownIndex] = albums[i];
                    unknownIndex++;
                    break;
            }
        }

        Album listByGenre[] = new Album[numAlbums];

        for (int i = 0; i < classicalIndex; i++) {
            listByGenre[i] = classicalList[i];
        }
        for (int i = 0; i < countryIndex; i++) {
            listByGenre[classicalIndex + i] = countryList[i];
        }
        for (int i = 0; i < jazzIndex; i++) {
            listByGenre[classicalIndex + countryIndex + i] = jazzList[i];
        }
        for (int i = 0; i < popIndex; i++) {
            listByGenre[classicalIndex + countryIndex + jazzIndex + i] = popList[i];
        }
        for (int i = 0; i < unknownIndex; i++) {
            listByGenre[classicalIndex + countryIndex + jazzIndex + popIndex + i] = unknownList[i];
        }


        System.out.println("*Album collection by genre.");
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(listByGenre[i].toString());
        }
        System.out.println("*End of list");
    }
}
