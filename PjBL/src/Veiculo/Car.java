package Veiculo;

import Excecoes.VehicleExceptionInvalidMaker;
import Excecoes.VehicleExceptionInvalidModel;
import Excecoes.VehicleExceptionInvalidPlate;

public abstract class Car extends Vehicle {
    public Car(String maker, String model, String plate) throws VehicleExceptionInvalidMaker, VehicleExceptionInvalidModel, VehicleExceptionInvalidPlate {
        super(maker, model, plate);
    }

}
