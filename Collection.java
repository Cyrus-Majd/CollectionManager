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
        for (int i = 0; i < albums.length; i++) {
            if (album.equals(albums[i])) {
                // idk how to do this because the isAvailable method is private.
                album.setAvailability(false);
                return true;
            }
        }
        return false;
    }

    public boolean returnAlbum(Album album) {   // set to available
        for (int i = 0; i < albums.length; i++) {
            if (album.equals(albums[i])) {
                album.setAvailability(true);
            }
            return true;
        }
        return false;
    }

    public void print() {   // display the list without specifying the order
        System.out.println("numArrays: " + numAlbums);
        for (int i = 0; i < numAlbums; i++) {
            System.out.println(albums[i].toString());
        }
        System.out.println("*End of list" + "\t\t numArrays: " + numAlbums);
    }

    public void printByReleaseDate() {

    }

    public void printByGenre() {

    }

// public class Collection {

//     int[] arrayList;    // arraylist

//     public Collection() {
//         // init the arraylist
//         this.arrayList = new int[5];
//         for (int i = 0; i < arrayList.length; i++) {
//             this.arrayList[i] = -1;  // -1 is the standard for "uninitialized"
//         }
//     }

//     public void add(int data) {
//         System.out.println("ADDING " + data);
//         int searchResult = search(this.arrayList, -1);
//         if (searchResult == -1) {
//             System.out.println("NO SPACE! NEED TO EXPAND.");
//             expand();
//             add(data);
//         }
//         else {
//             this.arrayList[searchResult] = data;
//         }
//     }

//     public void remove(int data) {
//         System.out.println("REMOVING " + data);
//         int searchResult = search(this.arrayList, data);
//         if (searchResult == -1) {
//             System.out.println("ERROR: ELEMENT DOES NOT EXIST IN THE LIST.");
//         }
//         else {  //logic for deleting and reordering.
//             for (int i = searchResult; i < arrayList.length-1; i++) {
//                 arrayList[i] = arrayList[i+1];
//             }
//         }
//     }

//     private int search(int[] arrayList, int data) {
//         for (int i = 0; i < arrayList.length; i++) {
//             // System.out.println("COMPARE " + data + " WITH " + arrayList[i]);
//             if (arrayList[i] == data) {
//                 return i;
//             }
//         }
//         return -1;
//     }

//     private void expand() {
//         int[] newArrayList = new int[this.arrayList.length + 4];
//         for (int i = 0; i < newArrayList.length; i++) {
//             // copy the old values as much as you can, and the new values are init to -1 just as before.
//             if (i < this.arrayList.length) {
//                 newArrayList[i] = this.arrayList[i];
//             }
//             else {
//                 newArrayList[i] = -1;
//             }
//         }
//         this.arrayList = newArrayList;
//     }

    // public static void main(String[] args) {

    //     Collection obj = new Collection();
    //     obj.add(3);
    //     obj.add(4);
    //     obj.add(5);
    //     obj.add(6);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.remove(6);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.remove(3);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(7);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.remove(5);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(52);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(8);
    //     System.out.println(Arrays.toString(obj.arrayList));
        
    //     obj.remove(52);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(69);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(420);
    //     System.out.println(Arrays.toString(obj.arrayList));

    //     obj.add(1337);
    //     System.out.println(Arrays.toString(obj.arrayList));
    // }
}
