package org.testtask.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.dao.BikeDao;
import org.testtask.dao.impl.EbikeDaoImpl;
import org.testtask.models.Ebike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EbikeServiceImplTest {
    private BikeDao<Ebike> bikeDao;

    private EbikeServiceImpl ebikeService;

    private Ebike ebike;
    private Ebike wrongEbike;
    private List<Ebike> list;

    @BeforeEach
    public void init() {
        bikeDao = Mockito.mock(EbikeDaoImpl.class);
        ebikeService = new EbikeServiceImpl(bikeDao);

        ebike = new Ebike();
        ebike.setType("E-BIKE");
        ebike.setBrand("ElectrO");
        ebike.setAvailableLights(true);
        ebike.setColor("beige");
        ebike.setPrice(1025);
        ebike.setWeight(19300);
        ebike.setBatteryCapacity(14000);
        ebike.setMaxSpeed(20);

        wrongEbike = new Ebike();

        list = new ArrayList<>();
        list.add(ebike);
        when(bikeDao.save(ebike)).thenReturn(true);
        when(bikeDao.getById(1)).thenReturn(ebike);
        when(bikeDao.getById(2)).thenThrow(new IndexOutOfBoundsException());
        when(bikeDao.getAll()).thenReturn(list);
    }

    @Test
    void save() {
        assertEquals(true, ebikeService.save(ebike));
    }

    @Test
    void getAll() {
        assertEquals(list, ebikeService.getAll());
    }

    @Test
    void getById() {
        assertEquals(ebike, ebikeService.getById(1));
        assertThrows(IndexOutOfBoundsException.class, () -> ebikeService.getById(2));
    }
}
