package CSC372_projects.CTA4;

public class Cylinder extends Shape {
    
    private double radius;
    private double height;

    public Cylinder(){
        this.radius = 0.0;
        this.height = 0.0;
    }

    public Cylinder(Double inputRadius, Double inputHeight){
        this.radius = inputRadius;
        this.height = inputHeight;
    }
    
    @Override
    public double surface_area(){
        return (2 * Math.PI * radius * height) + (2 * Math.PI * radius * radius);
    }

    @Override
    public double volume(){
        return (radius * radius) * Math.PI * height;
    }

    @Override
    public String toString() {
        return "Cylinder surface area: " + surface_area() + "\nCylinder volume: " + volume() + "\n";
    }
}