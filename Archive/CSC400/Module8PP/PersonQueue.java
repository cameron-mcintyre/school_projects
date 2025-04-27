package CSC400.Module8PP;
import java.util.LinkedList;
import java.util.Comparator;
import java.util.Queue;

public class PersonQueue {
    
    //list of people
    private Queue<Person> peopleList = new LinkedList<Person>();

    /**
    * Purpose: Push a person onto the end of the queue
    * Inputs: None.
    * Outputs: None.
    **/
    public void addPerson(Person person){
        peopleList.add(person);
    }

    /**
    * Purpose: Pop a person from the front of the queue.
    * Inputs: None.
    * Outputs: A person, removed from the queue.
    **/
    public Person removePerson(){
        return peopleList.poll();
    }

    /**
    * Purpose: Get the size of the queue.
    * Inputs: None.
    * Outputs: Int size of queue.
    **/
    public int getSize(){
        return peopleList.size();
    }

    /**
    * Purpose: Set up the quicksort to determine what field we are sorting on (name or age).
    * Inputs: integer for the type of sort; 1 = age, 2 = name.
    * Outputs: returns a sorted list by either name or age.
    **/
    public Queue<Person> sortPeopleList(int sortType){
        
        if(sortType == 1){

            Comparator<Person> ageCompare = new AgeComparator();
            peopleList = quickSortPeopleQueue(peopleList, ageCompare);
        } else if(sortType == 2){

            Comparator<Person> lnCompare = new LastnameComparator();
            peopleList = quickSortPeopleQueue(peopleList, lnCompare);
        } else {

            System.out.println("Error - invalid comparison call.");
        }

        return peopleList;
    }

    /**
    * Purpose: Performs a quicksort using the front element of the queue as the pivot.
    * Inputs: The queue of people; the appropriate comparator for the sort.
    * Outputs: returns a sorted list by either name or age.
    **/
    private Queue<Person> quickSortPeopleQueue(Queue<Person> peopleList, Comparator<Person> comparator){
        
        Queue<Person> smallQueue = new LinkedList<Person>();
        Queue<Person> largeQueue = new LinkedList<Person>();
        Queue<Person> sortedQueue = new LinkedList<Person>();

        if(peopleList.size() <= 1){ 
            return peopleList;  //base condition - a list of 1 element is sorted
        }

        Person pivot = peopleList.poll();  //pivot point must be front in a queue (can only poll front element)

        while(peopleList.isEmpty() == false){
            Person now = peopleList.poll();
            if(comparator.compare(now, pivot) >= 0){
                smallQueue.add(now);  //put current element in large or smaller queue
            } else {
                largeQueue.add(now);
            }
        }

        sortedQueue.addAll(quickSortPeopleQueue(smallQueue, comparator));
        sortedQueue.add(pivot);  //combine the queues into a sorted queue
        sortedQueue.addAll(quickSortPeopleQueue(largeQueue, comparator));

        return sortedQueue;  //return the sorted queue
    }

    /**
    * Purpose: Creates a string with all the people displayed neatly.
    * Inputs: None.
    * Outputs: A string of people's name using the Person.printPersonInfo() method.
    **/
    public String printAllPeople(){
        String output = "";

        for(int i = 0; i < peopleList.size(); i++){
            Person outputPerson = peopleList.poll();
            output = output + outputPerson.printPersonInfo();
            peopleList.add(outputPerson);
        }
        return output;
    }

    /**
    * Purpose: A custom comparator to compare ages.
    * Inputs: Two Person objects.
    * Outputs: A neg number if p2 < p1, or an even number if p2 > p1.
    **/
    private class AgeComparator implements Comparator<Person>{
        
        public int compare(Person p1, Person p2){
            return p1.getAge().compareTo(p2.getAge());
        }
    }

    /**
    * Purpose: A custom comparator to compare last names lexicographically.
    * Inputs: Two Person objects.
    * Outputs: A neg number if p2 < p1, or an even number if p2 > p1.
    **/
    public class LastnameComparator implements Comparator<Person>{

        //returns neg if p2 < p1, pos if p2 > p1 lexicographically
        public int compare(Person p1, Person p2){
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }
}