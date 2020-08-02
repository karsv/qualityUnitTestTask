package org.testtask.util.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.factory.BikeFactory;
import org.testtask.models.Bike;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;
import org.testtask.service.BikeService;
import org.testtask.service.impl.DataServiceImpl;
import org.testtask.service.impl.EbikeServiceImpl;
import org.testtask.service.impl.FoldingbikeServiceImpl;
import org.testtask.service.impl.SpeedelecBikeServiceImpl;
import org.testtask.service.DataService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DataServiceImplTest {
    private static final String WRONG_INPUT_DATA_WITH_ABSENT_PARAMETER =
            "SPEEDELEC Booster; 35; 10900; false; 13200; green";
    private static final String WRONG_INPUT_DATA_WITH_WRONG_PARAMETER =
            "SPEEDELEC Booster; 35; wrong; false; 13200; green; 1200";
    private static final String EBIKE_STRING_FORM =
            "E-BIKE ElectrO; 20; 19300; true; 14000; beige; 1025";
    private static final String SPEEDELEC_STRING_FORM =
            "SPEEDELEC EcoRide; 15; 8300; true; 15600; blue; 1055";
    private static final String FOLDINGBIKE_STRING_FORM =
            "FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009";

    private BikeFactory bikeFactory;
    private BikeService<FoldingBike> foldingBikeService;
    private BikeService<Ebike> ebikeService;
    private BikeService<SpeedelecBike> speedelecBikeService;
    private DataService dataService;
    private Ebike ebike;
    private FoldingBike foldingBike;
    private SpeedelecBike speedelecBike;
    List<Ebike> ebikes;
    List<SpeedelecBike> speedelecBikes;
    List<FoldingBike> foldingBikes;
    List<String> ebikesInString;
    List<String> speedelecBikesInString;
    List<String> foldingBikesInString;
    List<Bike> bikes;

    @BeforeEach
    private void init() {
        bikeFactory = Mockito.mock(BikeFactory.class);
        foldingBikeService = Mockito.mock(FoldingbikeServiceImpl.class);
        ebikeService = Mockito.mock(EbikeServiceImpl.class);
        speedelecBikeService = Mockito.mock(SpeedelecBikeServiceImpl.class);
        dataService = new DataServiceImpl(bikeFactory, foldingBikeService,
                ebikeService, speedelecBikeService);

        ebike = new Ebike();
        ebike.setType("E-BIKE");
        ebike.setBrand("ElectrO");
        ebike.setAvailableLights(true);
        ebike.setColor("beige");
        ebike.setPrice(1025);
        ebike.setWeight(19300);
        ebike.setBatteryCapacity(14000);
        ebike.setMaxSpeed(20);

        foldingBike = new FoldingBike();
        foldingBike.setType("FOLDING BIKE");
        foldingBike.setBrand("Benetti");
        foldingBike.setAvailableLights(false);
        foldingBike.setColor("rose");
        foldingBike.setPrice(1009);
        foldingBike.setWeight(11400);
        foldingBike.setWheelsSize(24);
        foldingBike.setNumberOfGears(27);

        speedelecBike = new SpeedelecBike();
        speedelecBike.setType("SPEEDELEC");
        speedelecBike.setBrand("EcoRide");
        speedelecBike.setAvailableLights(true);
        speedelecBike.setColor("blue");
        speedelecBike.setPrice(1055);
        speedelecBike.setWeight(8300);
        speedelecBike.setBatteryCapacity(15600);
        speedelecBike.setMaxSpeed(15);

        ebikes = new ArrayList<>();
        ebikes.add(ebike);
        speedelecBikes = new ArrayList<>();
        speedelecBikes.add(speedelecBike);
        foldingBikes = new ArrayList<>();
        foldingBikes.add(foldingBike);
        bikes = new ArrayList<>();
        bikes.addAll(ebikes);
        bikes.addAll(foldingBikes);
        bikes.addAll(speedelecBikes);

        ebikesInString = new ArrayList<>();
        ebikesInString.add(EBIKE_STRING_FORM);
        speedelecBikesInString = new ArrayList<>();
        speedelecBikesInString.add(SPEEDELEC_STRING_FORM);
        foldingBikesInString = new ArrayList<>();
        foldingBikesInString.add(FOLDINGBIKE_STRING_FORM);

        when(bikeFactory.getSpeedelecFromString(WRONG_INPUT_DATA_WITH_ABSENT_PARAMETER)).thenThrow(new DataIsNotValidException());
        when(bikeFactory.getSpeedelecFromString(WRONG_INPUT_DATA_WITH_WRONG_PARAMETER)).thenThrow(new DataIsNotValidException());
        when(ebikeService.getAll()).thenReturn(ebikes);
        when(foldingBikeService.getAll()).thenReturn(foldingBikes);
        when(speedelecBikeService.getAll()).thenReturn(speedelecBikes);
    }

    @Test
    void parseListOfStrings() {
        List<String> inputAbsentParameter = new ArrayList<>();
        inputAbsentParameter.add(WRONG_INPUT_DATA_WITH_ABSENT_PARAMETER);
        assertThrows(DataIsNotValidException.class, () -> dataService.parseListOfStrings(inputAbsentParameter));

        List<String> inputWrongParameter = new ArrayList<>();
        inputWrongParameter.add(WRONG_INPUT_DATA_WITH_WRONG_PARAMETER);
        assertThrows(DataIsNotValidException.class, () -> dataService.parseListOfStrings(inputWrongParameter));
    }

    @Test
    void getAllBikes() {
        assertEquals(bikes, dataService.getAllBikes());
    }

    @Test
    void getListOfDataFoldingbikes() {
        assertEquals(foldingBikesInString, dataService.getListOfDataFoldingbikes(foldingBikes));
    }

    @Test
    void getListOfDataSpeedelec() {
        assertEquals(speedelecBikesInString, dataService.getListOfDataSpeedelec(speedelecBikes));
    }

    @Test
    void getListOfDataEbike() {
        assertEquals(ebikesInString, dataService.getListOfDataEbike(ebikes));
    }
}
