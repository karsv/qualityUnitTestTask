package org.testtask.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.factory.BikeFactory;
import org.testtask.models.Bike;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;
import org.testtask.service.BikeService;
import org.testtask.service.DataService;

@Service
public class DataServiceImpl implements DataService {
    private static final Logger logger = LogManager.getLogger(DataServiceImpl.class);
    private final static String DELIMETER = "; ";

    private final BikeFactory bikeFactory;
    private final BikeService<FoldingBike> foldingBike;
    private final BikeService<Ebike> ebike;
    private final BikeService<SpeedelecBike> speedelecBike;

    public DataServiceImpl(BikeFactory bikeFactory,
                           @Qualifier("bikeMongodbServiceImpl")
                           BikeService<FoldingBike> foldingBike,
                           @Qualifier("bikeMongodbServiceImpl")
                           BikeService<Ebike> ebike,
                           @Qualifier("bikeMongodbServiceImpl")
                           BikeService<SpeedelecBike> speedelecBike) {
        this.bikeFactory = bikeFactory;
        this.foldingBike = foldingBike;
        this.ebike = ebike;
        this.speedelecBike = speedelecBike;
    }

    public void parseListOfStrings(List<String> stringList) throws DataIsNotValidException {
        for (String s : stringList) {
            if (s.contains("SPEEDELEC")) {
                speedelecBike.save(bikeFactory.getSpeedelecFromString(s));
                continue;
            }
            if (s.contains("E-BIKE")) {
                ebike.save(bikeFactory.getEbikeFromString(s));
                continue;
            }
            if (s.contains("FOLDING BIKE")) {
                foldingBike.save(bikeFactory.getFoldingBikeFromString(s));
                continue;
            }
        }

//        Collections.sort(speedelecBike.getAll());
//        Collections.sort(ebike.getAll());
//        Collections.sort(foldingBike.getAll());
    }

    public List<? extends Bike> getAllBikes() {
        List<Bike> bikes = new ArrayList<>();
        bikes.addAll(ebike.getAll());
        bikes.addAll(foldingBike.getAll());
        bikes.addAll(speedelecBike.getAll());
        return bikes;
    }

    public List<String> getListOfDataFoldingbikes(List<FoldingBike> list) {
        List<String> bikes = new ArrayList<>();
        bikes.addAll(foldingBike.getAll().stream()
                .map(b -> transformFoldingToString(b))
                .collect(Collectors.toList()));
        return bikes;
    }

    public List<String> getListOfDataSpeedelec(List<SpeedelecBike> list) {
        List<String> bikes = new ArrayList<>();
        bikes.addAll(speedelecBike.getAll().stream()
                .map(b -> transformSpeedelecToString(b))
                .collect(Collectors.toList()));
        return bikes;
    }

    public List<String> getListOfDataEbike(List<Ebike> list) {
        List<String> bikes = new ArrayList<>();
        bikes.addAll(ebike.getAll().stream()
                .map(b -> transformEbikeToString(b))
                .collect(Collectors.toList()));
        return bikes;
    }

    private String transformFoldingToString(FoldingBike bike) {
        StringBuilder builder = new StringBuilder();
        builder.append(bike.getType()).append(" ")
                .append(bike.getBrand()).append(DELIMETER)
                .append((int) bike.getWheelsSize()).append(DELIMETER)
                .append(bike.getNumberOfGears()).append(DELIMETER)
                .append((int) bike.getWeight()).append(DELIMETER)
                .append(bike.isAvailableLights()).append(DELIMETER)
                .append(bike.getColor()).append(DELIMETER)
                .append((int) bike.getPrice());
        return builder.toString();
    }

    private String transformSpeedelecToString(SpeedelecBike bike) {
        StringBuilder builder = new StringBuilder();
        builder.append(bike.getType()).append(" ")
                .append(bike.getBrand()).append(DELIMETER)
                .append((int) bike.getMaxSpeed()).append(DELIMETER)
                .append((int) bike.getWeight()).append(DELIMETER)
                .append(bike.isAvailableLights()).append(DELIMETER)
                .append((int) bike.getBatteryCapacity()).append(DELIMETER)
                .append(bike.getColor()).append(DELIMETER)
                .append((int) bike.getPrice());
        return builder.toString();
    }

    private String transformEbikeToString(Ebike bike) {
        StringBuilder builder = new StringBuilder();
        builder.append(bike.getType()).append(" ")
                .append(bike.getBrand()).append(DELIMETER)
                .append((int) bike.getMaxSpeed()).append(DELIMETER)
                .append((int) bike.getWeight()).append(DELIMETER)
                .append(bike.isAvailableLights()).append(DELIMETER)
                .append((int) bike.getBatteryCapacity()).append(DELIMETER)
                .append(bike.getColor()).append(DELIMETER)
                .append((int) bike.getPrice());
        return builder.toString();
    }


}
