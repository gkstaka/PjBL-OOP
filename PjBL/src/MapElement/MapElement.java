package MapElement;

import Vect.*;

public class MapElement {
    private Vect position;


    public MapElement(Vect v1){
        this.position = v1;
    }
    public MapElement(double x, double y){
        position.setX(x);
        position.setY(y);
    }

    public Vect getPosition(){
        return this.position;
    }

    public void setPosition(Vect v1){
        position.setX(v1.getX());
        position.setY(v1.getY());
    }
}
