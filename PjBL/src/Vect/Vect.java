package Vect;

public class Vect implements VectOperations {
    private double x;
    private double y;

    public Vect(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vect() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double absolute() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    @Override
    public Vect subtract(Vect v1) { // this.vector - v1 (direction from this to v1)
        double x = v1.getX() - this.x;
        double y = v1.getY() - this.y;
        return new Vect(x, y);
    }

    @Override
    public Vect add(Vect v1) {
        return new Vect(x + v1.getX(), y + v1.getY());

    }

    @Override
    public Vect getUnitVect() {
        return new Vect(x/absolute(),y/absolute());
    }
}
