package CSC372_projects.CTA4;

public class Sphere extends Shape {
    
    private double radius;

    public Sphere() {
        this.radius = 0.0;
    }

    public Sphere(Double inputRadius) {
        this.radius = inputRadius;
    }

    @Override
    public double surface_area() {
        return (radius * radius) * Math.PI * 4;
    }

    @Override
    public double volume() {
        return Math.pow(radius, 3.0) * Math.PI * (4.0 / 3.0);
    }

    @Override
    public String toString() {
        return "Sphere surface area: " + surface_area() + "\nSphere volume: " + volume() + "\n";
    }
}
