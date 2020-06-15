package org.testtask.factory;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;

@Component
public class BikeFactory {
    private static final Logger logger = LogManager.getLogger(BikeFactory.class);
    private static final int CORRECT_NUMBER_PARAMS_EBIKE = 7;
    private static final int CORRECT_NUMBER_PARAMS_FOLDINGBIKE = 7;
    private static final int CORRECT_NUMBER_PARAMS_SPEEDELEC = 7;

    public Ebike getEbikeFromString(String bike) {
        String[] bikeParams = Arrays.stream(bike.split(";"))
                .map(x -> x.trim())
                .toArray(String[]::new);

        checkCorrectParameters(bikeParams, CORRECT_NUMBER_PARAMS_EBIKE);

        Ebike ebike = new Ebike();
        ebike.setType(bikeParams[0].split(" ")[0]);
        ebike.setBrand(bikeParams[0].split(" ")[1]);
        ebike.setMaxSpeed(getDoubleFromString(bikeParams[1]));
        ebike.setWeight(getDoubleFromString(bikeParams[2]));
        ebike.setAvailableLights(getBooleanFromString(bikeParams[3]));
        ebike.setBatteryCapacity(getDoubleFromString(bikeParams[4]));
        ebike.setColor(bikeParams[5]);
        ebike.setPrice(getDoubleFromString(bikeParams[6]));

        return ebike;
    }

    public SpeedelecBike getSpeedelecFromString(String bike) {
        String[] bikeParams = Arrays.stream(bike.split(";"))
                .map(x -> x.trim())
                .toArray(String[]::new);

        checkCorrectParameters(bikeParams, CORRECT_NUMBER_PARAMS_SPEEDELEC);

        SpeedelecBike speedelecBike = new SpeedelecBike();
        speedelecBike.setType(bikeParams[0].split(" ")[0]);
        speedelecBike.setBrand(bikeParams[0].split(" ")[1]);
        speedelecBike.setMaxSpeed(getDoubleFromString(bikeParams[1]));
        speedelecBike.setWeight(getDoubleFromString(bikeParams[2]));
        speedelecBike.setAvailableLights(getBooleanFromString(bikeParams[3]));
        speedelecBike.setBatteryCapacity(getDoubleFromString(bikeParams[4]));
        speedelecBike.setColor(bikeParams[5]);
        speedelecBike.setPrice(getDoubleFromString(bikeParams[6]));

        return speedelecBike;
    }

    public FoldingBike getFoldingBikeFromString(String bike) {
        String[] bikeParams = Arrays.stream(bike.split(";"))
                .map(x -> x.trim())
                .toArray(String[]::new);

        checkCorrectParameters(bikeParams, CORRECT_NUMBER_PARAMS_FOLDINGBIKE);

        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setType(bikeParams[0].split(" ")[0] + " " + bikeParams[0].split(" ")[1]);
        foldingBike.setBrand(bikeParams[0].split(" ")[2]);
        foldingBike.setWheelsSize(getDoubleFromString(bikeParams[1]));
        foldingBike.setNumberOfGears(getIntegerFromString(bikeParams[2]));
        foldingBike.setWeight(getDoubleFromString(bikeParams[3]));
        foldingBike.setAvailableLights(getBooleanFromString(bikeParams[4]));
        foldingBike.setColor(bikeParams[5]);
        foldingBike.setPrice(getDoubleFromString(bikeParams[6]));

        return foldingBike;
    }

    private void checkCorrectParameters(String[] parameters, int numberOfCorrectParameters) {
        if (parameters.length != numberOfCorrectParameters) {
            throw new DataIsNotValidException("Wrong input data!");
        }
    }

    private Double getDoubleFromString(String doubleParameter) {
        try {
            return Double.parseDouble(doubleParameter);
        } catch (NumberFormatException e) {
            throw new DataIsNotValidException("Parameter at input data is incorrect!");
        }
    }

    private Integer getIntegerFromString(String integerParameter) {
        try {
            return Integer.parseInt(integerParameter);
        } catch (NumberFormatException e) {
            throw new DataIsNotValidException("Parameter at input data is incorrect!");
        }
    }

    private Boolean getBooleanFromString(String booleanParameter) {
        try {
            return Boolean.parseBoolean(booleanParameter);
        } catch (NumberFormatException e) {
            throw new DataIsNotValidException("Parameter at input data is incorrect!");
        }
    }
}
