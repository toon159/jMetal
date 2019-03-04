package org.uma.jmetal.algorithm.multiobjective.ansga.util;

/*
 * The Point class models a 2D point at (x, y).
 */
public class Point {
    // The private instance variables
    private double x, y;

    // The constructors (overloaded)
    public Point() {  // The default constructor
        this.x = 0;
        this.y = 0;
    }
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // The public getters and setters
    public double getX() {
        return this.x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return this.y;
    }
    public void setY(double y) {
        this.y = y;
    }

    // Return "(x,y)"
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    // Return a 2-element int array containing x and y.
    public double[] getXY() {
        double[] results = new double[2];
        results[0] = this.x;
        results[1] = this.y;
        return results;
    }

    // Set both x and y.
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Return the distance from this instance to the given point at (x,y).
    public double distance(int x, int y) {
        double xDiff = this.x - x;
        double yDiff = this.y - y;
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
    // Return the distance from this instance to the given Point instance (called another).
    public double distance(Point another) {
        double xDiff = this.x - another.x;
        double yDiff = this.y - another.y;
        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
    // Return the distance from (0,y) to (x,0) which pass through both points.
    public double distance0ytox0(Point another) {
//        create line instance
        Line line = new Line(this, another);
        double xDiff = line.xDiff();
        double yDiff = line.yDiff();

        return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
    }
    // Return the distance from this instance to (0,0).
    public double distance() {
        return Math.sqrt(this.x*this.x + this.y*this.y);
    }



}

class Line {
    double m, c;
    public Line(Point a, Point b){
//        find m
        double x1, x2, y1, y2;
        x1 = a.getX();
        y1 = a.getY();
        x2 = b.getX();
        y2 = b.getY();
        m = (y1 - y2)/(x1 - x2);

//        find c
        c = y1 - (m * x1);
    }

    public double xDiff() {
        return Math.abs(c/m);
    }

    public double yDiff() {
        return c;
    }
}