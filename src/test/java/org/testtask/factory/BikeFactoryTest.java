package org.testtask.factory;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BikeFactoryTest {
    public static final String CORRECT_EBIKE =
            "E-BIKE ElectrO; 20; 19300; true; 14000; beige; 1025";
    public static final String EBIKE_WITH_MISSING_PARAMETER =
            "E-BIKE ElectrO; 19300; true; 14000; beige; 1025";
    public static final String EBIKE_WITH_WRONG_PARAMETER =
            "E-BIKE ElectrO; wrong; 19300; true; 14000; beige; 1025";

    public static final String CORRECT_SPEEDELEC =
            "SPEEDELEC EcoRide; 15; 8300; true; 15600; blue; 1055";
    public static final String SPEEDELEC_WITH_MISSING_PARAM =
            "SPEEDELEC EcoRide; 15; 8300; true; 15600; blue";
    public static final String SPEEDELEC_WITH_WRONG_PARAM =
            "SPEEDELEC EcoRide; 15; 8300b; true; 15600; blue; 1055";

    public static final String CORRECT_FOLDINGBIKE =
            "FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009";
    public static final String FOLDINGBIKE_WITH_MISSING_PARAM =
            "FOLDING BIKE Benetti; 24; 27; false; rose; 1009";
    public static final String FOLDINGBIKE_WITH_WRONG_PARAM =
            "FOLDING BIKE Benetti; 24; 27; 11400b; false; rose; 1009";

    private final BikeFactory bikeFactory = Mockito.spy(new BikeFactory());

    @Test
    void checkEbikeFactory() {
        Ebike ebike = new Ebike();
        ebike.setType("E-BIKE");
        ebike.setBrand("ElectrO");
        ebike.setAvailableLights(true);
        ebike.setColor("beige");
        ebike.setPrice(1025);
        ebike.setWeight(19300);
        ebike.setBatteryCapacity(14000);
        ebike.setMaxSpeed(20);
        assertEquals(ebike, bikeFactory.getEbikeFromString(CORRECT_EBIKE));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getEbikeFromString(EBIKE_WITH_MISSING_PARAMETER));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getEbikeFromString(EBIKE_WITH_WRONG_PARAMETER));
    }

    @Test
    void checkSpeedelecFactory() {
        SpeedelecBike speedelecBike = new SpeedelecBike();
        speedelecBike.setType("SPEEDELEC");
        speedelecBike.setBrand("EcoRide");
        speedelecBike.setAvailableLights(true);
        speedelecBike.setColor("blue");
        speedelecBike.setPrice(1055);
        speedelecBike.setWeight(8300);
        speedelecBike.setBatteryCapacity(15600);
        speedelecBike.setMaxSpeed(15);
        assertEquals(speedelecBike, bikeFactory.getSpeedelecFromString(CORRECT_SPEEDELEC));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getSpeedelecFromString(SPEEDELEC_WITH_MISSING_PARAM));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getSpeedelecFromString(SPEEDELEC_WITH_WRONG_PARAM));
    }

    @Test
    void checkFoldingbikeFactory() {
        FoldingBike foldingBike = new FoldingBike();
        foldingBike.setType("FOLDING BIKE");
        foldingBike.setBrand("Benetti");
        foldingBike.setAvailableLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(1009);
        foldingBike.setWeight(11400);
        foldingBike.setNumberOfGears(27);
        foldingBike.setWheelsSize(24);
        assertEquals(foldingBike, bikeFactory.getFoldingBikeFromString(CORRECT_FOLDINGBIKE));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getFoldingBikeFromString(FOLDINGBIKE_WITH_MISSING_PARAM));
        assertThrows(DataIsNotValidException.class, () ->
                bikeFactory.getFoldingBikeFromString(FOLDINGBIKE_WITH_WRONG_PARAM));
    }
}
