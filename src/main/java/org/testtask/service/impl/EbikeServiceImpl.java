package org.testtask.service.impl;

import java.util.Collections;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.testtask.dao.BikeDao;
import org.testtask.models.Ebike;
import org.testtask.service.BikeService;

@Service
public class EbikeServiceImpl implements BikeService<Ebike> {
    private static final Logger logger = LogManager.getLogger(EbikeServiceImpl.class);
    private final BikeDao<Ebike> bikeDao;

    public EbikeServiceImpl(BikeDao<Ebike> bikeDao) {
        this.bikeDao = bikeDao;
    }

    @Override
    public boolean save(Ebike bike) {
        boolean status = bikeDao.save(bike);
        Collections.sort(bikeDao.getAll());
        return status;
    }

    @Override
    public List<Ebike> getAll() {
        return bikeDao.getAll();
    }

    @Override
    public Ebike getById(Integer id) {
        return bikeDao.getById(id);
    }
}
