package org.testtask.service;

import java.util.List;
import org.testtask.models.Bike;

public interface BikeService<T extends Bike> {
    boolean save(T bike);

    List<T> getAll();

    T getById(Integer id);
}
