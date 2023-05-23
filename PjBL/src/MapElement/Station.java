package MapElement;
import Vect.*;

public class Station extends MapElement{
    private String name;

    public Station(Vect position, String name){
        super(position);
        this.name = name;
    }


}
