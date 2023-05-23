import MapElement.*;
import Vect.*;


public class Main {
    public static void main(String[] args) {
        Station station1 = new Station(new Vect(0,0), "Station1");
        Vect trainPosition = station1.getPosition();
        Train train = new Train("00012", trainPosition,12);
        Station station2 = new Station(new Vect(75,100), "Station 2");
        train.setDirection(station2.getPosition());
        double timer = 50;
        double lastUpdate = System.currentTimeMillis();
        while (true) {
            if (System.currentTimeMillis() - lastUpdate >= timer) {
                lastUpdate = System.currentTimeMillis();
                train.updatePosition();
                train.printStatus(station2.getPosition());
            }
        }
    }

    public static void refresh(Train train) {

    }
}
