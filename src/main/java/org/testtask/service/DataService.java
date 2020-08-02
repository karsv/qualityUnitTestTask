package org.testtask.service;

import java.util.List;
import org.testtask.exceptions.DataIsNotValidException;
import org.testtask.models.Bike;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;

public interface DataService {
    void parseListOfStrings(List<String> stringList) throws DataIsNotValidException;

    List<? extends Bike> getAllBikes();

    List<String> getListOfDataFoldingbikes(List<FoldingBike> list);

    List<String> getListOfDataSpeedelec(List<SpeedelecBike> list);

    List<String> getListOfDataEbike(List<Ebike> list);
}
