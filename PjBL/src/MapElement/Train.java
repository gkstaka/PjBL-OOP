package MapElement;

import Vect.*;

public class Train extends MapElement{
    private String id;
    private double absoluteSpeed;
    private Vect speed;
    private double timeToNextStation;

    public Train(String id, Vect position, double absoluteSpeed) {
        super(position);
        this.id = id;
        this.absoluteSpeed = absoluteSpeed;
        this.speed = new Vect(0, 0);

    }

    public void setDirection(Vect positionNextStation) {
        Vect directionVect = getPosition().subtract(positionNextStation);
        Vect unitDirectionVect = directionVect.getUnitVect();
        this.speed = new Vect(absoluteSpeed * unitDirectionVect.getX(), absoluteSpeed * unitDirectionVect.getY());

    }

    public double getTimeToNextStation(Vect positionNextStation) {
        return getPosition().subtract(positionNextStation).absolute() / absoluteSpeed;
    }

    public void updatePosition() {
        setPosition(getPosition().add(this.speed)) ;
    }

    public void printStatus(Vect nextStation) {
        System.out.println("ID: " + id);
        System.out.println("x: " + getPosition().getX() + ", y: " + getPosition().getY());
        System.out.println("Vx: " + speed.getX() + ", Vy: " + speed.getY());
        System.out.println("Time remaining: " + (int) getTimeToNextStation(nextStation) / 60 +
                " minutes and " + (int) getTimeToNextStation(nextStation) % 60 + " seconds");
    }
}
