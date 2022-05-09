package Assigment3;

abstract public class Shape {
    /*
    Implement a group of classes that have common behavior/state as Shape.
    Create Circle, Rectangle and Square for now as later on we may need more shapes.
    They should have the ability to calculate the area. They should be able to compare using area.
    Please write a program to demonstrate the classes and comparison.  You can use either abstract or interface.
    Comparator or Comparable interface.
     */

    private String name;
    public Shape(String name) {
        this.name = name;
    }
    public abstract double getArea();
    public abstract double getPerimeter();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        super("Circle");
        this.radius = radius;
    }

    // Provide an implementation for inherited abstract getArea() method
    public double getArea() {
        return Math.PI * radius * radius;
    }

    // Provide an implementation for inherited abstract getPerimeter() method
    public double getPerimeter() {
        return 2.0 * Math.PI * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        // Set the shape name as "Rectangle"
        super("Rectangle");
        this.width = width;
        this.height = height;
    }

    // Provide an implementation for inherited abstract getArea() method
    public double getArea() {
        return width * height;
    }

    // Provide an implementation for inherited abstract getPerimeter() method
    public double getPerimeter() {
        return 2.0 * (width + height);
    }
}

class Square extends Shape {
    private double width;

    public Square(double width) {
        // Set the shape name as "Rectangle"
        super("Rectangle");
        this.width = width;
    }

    // Provide an implementation for inherited abstract getArea() method
    public double getArea() {
        return width * width;
    }

    // Provide an implementation for inherited abstract getPerimeter() method
    public double getPerimeter() {
        return 4.0 * width;
    }
}