package CSC372_projects.DiscussionPosts;

import java.util.*;

public class Collections {

    public static void main(String[] args) {

        Set <String> set = new TreeSet<String>();
        List <String> list = new ArrayList<String>();

        set.add("End");
        set.add("Start");
        set.add("Middle");

        list.add("End");
        list.add("Start");
        list.add("Middle");

        System.out.println("Set: " + set);

        System.out.println("List: " + list);
    }
}
