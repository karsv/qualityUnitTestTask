package org.testtask.dao.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.testtask.dao.BikeDao;
import org.testtask.models.SpeedelecBike;

@Repository
public class SpeedelecDaoIpml implements BikeDao<SpeedelecBike> {
    private static final Logger logger = LogManager.getLogger(SpeedelecDaoIpml.class);
    private final List<SpeedelecBike> list;

    public SpeedelecDaoIpml(List<SpeedelecBike> list) {
        this.list = list;
    }

    @Override
    public boolean save(SpeedelecBike bike) {
        return list.add(bike);
    }

    @Override
    public List<SpeedelecBike> getAll() {
        return list.subList(0, list.size());
    }

    @Override
    public SpeedelecBike getById(Integer id) {
        return list.get(id);
    }
}
