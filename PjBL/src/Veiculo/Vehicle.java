package Veiculo;

import Excecoes.*;

import java.io.Serializable;
import java.util.Locale;

public class Vehicle implements Serializable {
    private String maker;
    private String model;
    private String plate;

    public Vehicle(String maker, String model, String plate) throws VehicleExceptionInvalidMaker, VehicleExceptionInvalidModel, VehicleExceptionInvalidPlate {
        try {
            this.maker = capitalize(maker);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new VehicleExceptionInvalidMaker();
        }
        try {
            this.model = capitalize(model);
        } catch (StringIndexOutOfBoundsException exception) {
            throw new VehicleExceptionInvalidModel();
        }
        if (plate.length() == 0) throw new VehicleExceptionInvalidPlate();
        this.plate = plate.toUpperCase();
    }

    public String getPlate() {
        return plate;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    private String capitalize(String text) throws StringIndexOutOfBoundsException {
        String textFormatted = "";
        String[] aux = text.split(" ");
        for (int i = 0; i < aux.length; i++) {
            textFormatted += aux[i].substring(0, 1).toUpperCase() + aux[i].substring(1).toLowerCase() + " ";
        }
        textFormatted.trim();
        return textFormatted;
    }

    @Override
    public String toString() {
        return maker +
                model + "- " +
                plate;
    }


}
