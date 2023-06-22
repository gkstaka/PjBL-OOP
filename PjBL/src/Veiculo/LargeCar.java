package Veiculo;

import Excecoes.VehicleExceptionInvalidMaker;
import Excecoes.VehicleExceptionInvalidModel;
import Excecoes.VehicleExceptionInvalidPlate;

import java.time.Duration;
import java.time.LocalDateTime;

public class LargeCar extends Car {
    public LargeCar(String maker, String model, String plate) throws VehicleExceptionInvalidMaker, VehicleExceptionInvalidModel, VehicleExceptionInvalidPlate {
        super(maker, model, plate);
        minimumPrice = 15;
        pricePerHour = 18;
        pricePerDay = 50;
    }

    @Override
    public double payParking() {
        Duration duration = Duration.between(super.getParkedTime(), LocalDateTime.now());
        double price = 0;
        if (duration.toHours() == 0 && duration.toMinutesPart() > 15) {
            price = minimumPrice;
        } else if (duration.toHours() > 0 && duration.toHoursPart() < 5) {
            price = duration.toHoursPart() * pricePerHour;
        } else price = duration.toDaysPart() * pricePerDay;
        return price;
    }
}
