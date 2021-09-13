// @author Cyrus Majd
// @author PARTNER

import java.util.Scanner;
import java.util.Arrays;

public class Collection {

    int[] arrayList;    // arraylist

    public Collection() {
        // init the arraylist
        this.arrayList = new int[5];
        for (int i = 0; i < arrayList.length; i++) {
            this.arrayList[i] = -1;  // -1 is the standard for "uninitialized"
        }
    }

    public void add(int data) {
        System.out.println("ADDING " + data);
        int searchResult = search(this.arrayList, -1);
        if (searchResult == -1) {
            System.out.println("NO SPACE! NEED TO EXPAND.");
            expand();
            add(data);
        }
        else {
            this.arrayList[searchResult] = data;
        }
    }

    public void remove(int data) {
        System.out.println("REMOVING " + data);
        int searchResult = search(this.arrayList, data);
        if (searchResult == -1) {
            System.out.println("ERROR: ELEMENT DOES NOT EXIST IN THE LIST.");
        }
        else {  //logic for deleting and reordering.
            for (int i = searchResult; i < arrayList.length-1; i++) {
                arrayList[i] = arrayList[i+1];
            }
        }
    }

    private int search(int[] arrayList, int data) {
        for (int i = 0; i < arrayList.length; i++) {
            // System.out.println("COMPARE " + data + " WITH " + arrayList[i]);
            if (arrayList[i] == data) {
                return i;
            }
        }
        return -1;
    }

    private void expand() {
        int[] newArrayList = new int[this.arrayList.length + 4];
        for (int i = 0; i < newArrayList.length; i++) {
            // copy the old values as much as you can, and the new values are init to -1 just as before.
            if (i < this.arrayList.length) {
                newArrayList[i] = this.arrayList[i];
            }
            else {
                newArrayList[i] = -1;
            }
        }
        this.arrayList = newArrayList;
    }

    public static void main(String[] args) {

        Collection obj = new Collection();
        obj.add(3);
        obj.add(4);
        obj.add(5);
        obj.add(6);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.remove(6);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.remove(3);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(7);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.remove(5);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(52);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(8);
        System.out.println(Arrays.toString(obj.arrayList));
        
        obj.remove(52);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(69);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(420);
        System.out.println(Arrays.toString(obj.arrayList));

        obj.add(1337);
        System.out.println(Arrays.toString(obj.arrayList));
    }
}