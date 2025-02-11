package CSC372_projects.CTA4;

import java.util.ArrayList;
import java.util.Scanner;

public class ShapeArray {
    
    public static void main(String[] args){

        ArrayList<Shape> shapeArray = new ArrayList<Shape>();
        Scanner scnr = new Scanner(System.in);
        double inputHeight;
        double inputRadius;
        
        System.out.println("Welcome to the shapes program!");
        System.out.println("Please enter a number that represents the radius of the object: ");
        inputRadius = scnr.nextDouble();
        System.out.println("Please enter the value that represents the height: ");
        inputHeight = scnr.nextDouble();
        System.out.println("");

        Shape shape1 = new Sphere(inputRadius);
        shapeArray.add(shape1);

        Cylinder shape2 = new Cylinder(inputRadius, inputHeight);
        shapeArray.add(shape2);

        Cone shape3 = new Cone(inputRadius, inputHeight);
        shapeArray.add(shape3);

        for(Shape shape : shapeArray){
            System.out.println(shape.toString());
        }

        scnr.close();
    }
}
