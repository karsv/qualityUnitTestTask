package org.testtask.service.impl;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.testtask.dao.BikeDao;
import org.testtask.models.SpeedelecBike;
import org.testtask.service.BikeService;

@Service
public class SpeedelecBikeServiceImpl implements BikeService<SpeedelecBike> {
    private static final Logger logger = LogManager.getLogger(SpeedelecBikeServiceImpl.class);
    private final BikeDao<SpeedelecBike> bikeDao;

    public SpeedelecBikeServiceImpl(BikeDao<SpeedelecBike> bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public boolean save(SpeedelecBike bike) {
        boolean status = bikeDao.save(bike);
        Collections.sort(bikeDao.getAll());
        return status;
    }

    @Override
    public List<SpeedelecBike> getAll() {
        return bikeDao.getAll();
    }

    @Override
    public SpeedelecBike getById(Integer id) {
        return bikeDao.getById(id);
    }
}
