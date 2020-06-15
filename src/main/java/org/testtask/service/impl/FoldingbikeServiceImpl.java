package org.testtask.service.impl;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.testtask.dao.BikeDao;
import org.testtask.models.FoldingBike;
import org.testtask.service.BikeService;

@Service
public class FoldingbikeServiceImpl implements BikeService<FoldingBike> {
    private static final Logger logger = LogManager.getLogger(FoldingbikeServiceImpl.class);
    private final BikeDao<FoldingBike> bikeDao;

    public FoldingbikeServiceImpl(BikeDao<FoldingBike> bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public boolean save(FoldingBike bike) {
        boolean status = bikeDao.save(bike);
        Collections.sort(bikeDao.getAll());
        return status;
    }

    @Override
    public List<FoldingBike> getAll() {
        return bikeDao.getAll();
    }

    @Override
    public FoldingBike getById(Integer id) {
        return bikeDao.getById(id);
    }
}
