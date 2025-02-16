package CSC400;

import java.util.LinkedList;

public class Bag<T> {

    //Initialize list
    private LinkedList<T> myBag = new LinkedList<>(); //generic class

    //Add item
    public void add(T thing) {
        myBag.add(thing);
    }

    //Remove item
    public void remove(T thing) {
        myBag.remove(thing);
    }

    //Return true if list contains object
    public Boolean contains(T thing) {
        if (myBag.contains(thing)) {
            return true;
        } else {
            return false;
        }
    }

    //find an index of an item in the bag
    public int findIndex(T thing) {
        for (int i = 0; i < myBag.size(); i++) {
            if (myBag.get(i).equals(thing)) {
                return i;
            } else {
                continue;
            }
        }
        return -1;
    }

    //return a string of all the items in the bag.  note that the return is limited to 2^31 characters, so very large bags are problematic
    public String printBag() {
        String output = "";
        for (int i = 0; i < myBag.size(); i++) {
            output = output + myBag.get(i) + ", ";
        }
        return output;
    }

    //returns the bag item count
    public Integer countBag() {
        return myBag.size();
    }

    //return count of a particular item
    public Integer countItemAmount(T thing) {
        int count = 0;
        for (int i = 0; i < myBag.size(); i++) {
            if (myBag.get(i).equals(thing)) {
                count = count + 1;
            } else {
                continue;
            }
        }
        return count;
    }
}