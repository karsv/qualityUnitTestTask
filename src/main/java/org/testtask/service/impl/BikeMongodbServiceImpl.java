package org.testtask.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.testtask.dao.BikeDao;
import org.testtask.models.Bike;
import org.testtask.service.BikeService;

@Service
public class BikeMongodbServiceImpl implements BikeService {
    private final BikeDao bikeDao;

    public BikeMongodbServiceImpl(@Qualifier("bikeDaoMongoDbImpl") BikeDao bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public boolean save(Bike bike) {
        return bikeDao.save(bike);
    }

    @Override
    public List getAll() {
        return bikeDao.getAll();
    }

    @Override
    public Bike getById(Integer id) {
        return bikeDao.getById(id);
    }
}
