package CSC372_projects.DiscussionPosts;

public class OOP {
    public static void main(String[] args) {
        Square square = new Square(3);
        System.out.println(square.FindPerimeter(square));
    }
}

class Square {
    private int side;

    public Square(int side) {
        this.side = side;}

    public int FindPerimeter(Square square) {
        return 4 * this.side;}
}