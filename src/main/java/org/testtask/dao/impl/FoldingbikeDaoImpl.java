package org.testtask.dao.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.testtask.dao.BikeDao;
import org.testtask.models.FoldingBike;

@Repository
public class FoldingbikeDaoImpl implements BikeDao<FoldingBike> {
    private static final Logger logger = LogManager.getLogger(FoldingbikeDaoImpl.class);
    private final List<FoldingBike> list;

    public FoldingbikeDaoImpl(List<FoldingBike> list) {
        this.list = list;
    }

    @Override
    public boolean save(FoldingBike bike) {
        return list.add(bike);
    }

    @Override
    public List<FoldingBike> getAll() {
        return list.subList(0, list.size());
    }

    @Override
    public FoldingBike getById(Integer id) {
        return list.get(id);
    }
}
