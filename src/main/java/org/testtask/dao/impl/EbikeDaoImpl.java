package org.testtask.dao.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.testtask.dao.BikeDao;
import org.testtask.models.Ebike;

@Repository
public class EbikeDaoImpl implements BikeDao<Ebike> {
    private static final Logger logger = LogManager.getLogger(EbikeDaoImpl.class);
    private final List<Ebike> ebikeRepository;

    public EbikeDaoImpl(List<Ebike> list) {
        this.ebikeRepository = list;
    }

    @Override
    public boolean save(Ebike bike) {
        return ebikeRepository.add(bike);
    }

    @Override
    public List<Ebike> getAll() {
        return ebikeRepository.subList(0, ebikeRepository.size());
    }

    @Override
    public Ebike getById(Integer id) {
        return ebikeRepository.get(id);
    }
}
