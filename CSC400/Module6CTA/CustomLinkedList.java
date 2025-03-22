package CSC400.Module6CTA;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomLinkedList {

    private Node head;
    private int size;

    //define a node
    private class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //return size of list
    public int size(){return size;}

    //add a new item to the end of the list
    public void insert(int data){
        Node newData = new Node(data);
        
        //check if first item in list
        if(head == null){
            head = newData;
            size = 1;
        } else {
            //if not first item, work through list until the end is found
            Node tail = head;
            while(tail.next != null){
                tail = tail.next;
            }
            //set the new data to the last data in the list
            tail.next = newData;
            size++;
        }
    }

    //delete the first instance of an item from the list
    public void delete(int data){
        Node now = head;
        Node before = null;

        //take care of some edge cases first
        //if the list has nothing in it, we need to accomodate an empty delete
        if(now == null){
            return;
        }
        //if the first value is the correct one, we need to move the head up one
        if(head.data == data){
            head = head.next;
            size--;
            return;
        
        }
        //goes through the list and stops when the node data == input data
        while(now.next != null && now.data != data){
            before = now;
            now = now.next;
        }
        
        //if the node we stopped at isn't empty, we can remove it and decrement
        if(now != null){
            before.next = now.next;
            size--;
            System.gc(); //run the garbage collection now that nothing points to the deleted node
        }
                   
        return;
    }
    
    //iterator constructor
    public Iterator<Integer> iterator() {return new LinkedListIterator();}

    //prof provided iterator
    private class LinkedListIterator implements Iterator<Integer> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {throw new NoSuchElementException();}

            int data = current.data;
            current = current.next;
            return data;
        }
    }
}