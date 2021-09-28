// @author Cyrus Majd
// @author Nikodem Kisielewski

public class Collection {
    private Album[] albums = new Album[4];
    private int numAlbums = 0;  // number of albums currently in the collection

    private int find(Album album) { // find the album index or return "NOT_FOUND". for NOT_FOUND I'm just gonna return -1.
        for (int i = 0; i < albums.length; i++) {
            if (album.equals(albums[i])) {
                return i;
            }
        }
        return -1;
    }

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

    public boolean lendingOut(Album album) {    // set to not available
        int index = find(album);
        if (index > -1) {
            albums[index].setAvailability(false);
            return true;
        }
        return false;
    }

    public boolean returnAlbum(Album album) {   // set to available
        int index = find(album);
        if (index > -1) {
            albums[index].setAvailability(true);
            return true;
        }
        return false;
    }

    public void print() {   // display the list without specifying the order
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
        System.out.println("*End of list");
    }

    public void printByReleaseDate() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
        
    }

    public void printByGenre() {
        if (numAlbums == 0) {
            System.out.println("The collection is empty!");
            return;
        }
    }