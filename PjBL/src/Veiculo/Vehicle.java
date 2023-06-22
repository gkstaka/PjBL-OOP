package Veiculo;

import Excecoes.*;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Vehicle implements Serializable{
    private String maker;
    private String model;
    private String plate;

    private boolean isParked;
    private int parkingSpotIndex;
    private LocalDateTime parkedTime;
    protected double minimumPrice;
    protected double pricePerHour;
    protected double pricePerDay;

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
        isParked = false;
        parkingSpotIndex = -1;
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

    public boolean isParked() {
        return isParked;
    }

    public void setParked(boolean parked) {
        isParked = parked;
    }

    public LocalDateTime getParkedTime() {
        return parkedTime;
    }

    public void setParkedTime(LocalDateTime parkedTime) {
        this.parkedTime = parkedTime;
    }

    public abstract double payParking();

}
