package CSC372_projects.CTA4;

public class Cone extends Shape {

    private double radius;
    private double height;

    public Cone(){
        this.radius = 0.0;
        this.height = 0.0;
    }

    public Cone(Double inputRadius, Double inputHeight){
        this.radius = inputRadius;
        this.height = inputHeight;
    }
    
    @Override
    public double surface_area(){
        return Math.PI * radius * (radius + (Math.sqrt((height * height) + (radius * radius))));
    }

    @Override
    public double volume(){
        return Math.PI * (radius * radius) * (height / 3);
    }

    @Override
    public String toString() {
        return "Cone surface area: " + surface_area() + "\nCone volume: " + volume() + "\n";
    }
}
