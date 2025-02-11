package CSC372_projects.DiscussionPosts;

public class Recursion {

    public static void bidding(int maxBid, int bidAmount, String indentAmount){
        
        if(maxBid < bidAmount){
            System.out.println("Max bid reached!  Final amount: " + bidAmount);
        } else {
            System.out.println(indentAmount + "Another bid made! " + bidAmount);
            bidding(maxBid, bidAmount + (bidAmount/2), indentAmount + "   ");
        }
    }
    public static void main(String[] args){
        int maxBid = 110;
        int bidAmount = 7;

        bidding(maxBid, bidAmount, "   ");
    }
}
