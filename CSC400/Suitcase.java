package CSC400;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

//suitcase is a class of ADT Bag that contains objects.  You can pack anything in a suitcase.
public class Suitcase<T> {

    //Initialize list
    private HashMap<Integer, T> myBag = new HashMap<>(); //we don't need to order or sort anything, but we want to lookup, add, and remove really quickly.  using a hashmap makes sense.
    private String bagName;

    //basic constructor
    public Suitcase() {
        this.myBag = new HashMap<>();
        this.bagName = "";

    }

    //Add item
    public void add(T thing) {
        myBag.put((myBag.size() + 1), thing);
    }

    //Remove item
    public void remove(int index) {
        myBag.remove(index);
    }

    //remove errythang
    public void removeAll() {
        myBag.clear();
    }

    //gets an item from the bag
    public T get(Integer i) {
        return myBag.get(i);
    }

    //returns the bag item count
    public Integer size() {
        return myBag.size();
    }

    //Return true if list contains object
    public Boolean contains(T thing) {
        if (myBag.containsValue(thing)) {
            return true;
        } else {
            return false;
        }
    }

    //find an index of an item in the bag
    public int findIndex(T thing) {
        for (Map.Entry<Integer, T> item : myBag.entrySet()) { //I'm not totally sure what is going on here, I copied from stackoverflow.
            if (item.getValue() != null && item.getValue().equals(thing)) {
                return item.getKey();
            } else {
                continue;
            }
        }
        return -1;
    }

    //return a string of all the items in the bag.
    public String printBag() {
        return myBag.toString();
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

    //setter for the bag title
    public void setBagName(String bagName) {
        this.bagName = bagName;
    }

    //getter for the bag title
    public String getBagName() {
        return bagName;
    }

    //merges two bags
    public void merge(Suitcase<T> otherBag) {
        for (T thing : otherBag.myBag.values()) {
            myBag.put(myBag.size() + 1, thing);
        }
    }

    //returns a new bag with all duplicates removed from the first bag
    public Suitcase<T> distinct() { 
        HashSet<T> newHashSet = new HashSet<>(myBag.values());
        Suitcase<T> newBag = new Suitcase<T>();
        for (T thing : newHashSet) {
            newBag.add(thing);
        }
        return newBag;
    }

    public String addRandomItem() {
        Random rand = new Random();
        int i = rand.nextInt(10);
        switch (i) {
            case 0: return "The artifact";
            case 1: return "Nuclear Warhead";
            case 2: return "A gentle breeze";
            case 3: return "Paintbrush";
            case 4: return "A cricket";
            case 5: return "A tingling feeling";
            case 6: return "Birdseed";
            case 7: return "Green goo";
            case 8: return "The feeling that you're being watched";
            case 9: return "A puppy";
            default: return "An empty space";
        }
    }
}