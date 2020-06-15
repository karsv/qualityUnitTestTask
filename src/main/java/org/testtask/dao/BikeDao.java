package org.testtask.dao;

import java.util.List;
import org.testtask.models.Bike;

public interface BikeDao<T extends Bike> {
    boolean save(T bike);

    List<T> getAll();

    T getById(Integer id);
}
