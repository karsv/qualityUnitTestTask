package org.testtask.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.models.Ebike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class EbikeDaoImplTest {
    private List<Ebike> ebikeRepository;

    private EbikeDaoImpl ebikeDaoImpl;

    private Ebike ebike;
    private Ebike wrongEbike;
    private List<Ebike> list;

    @BeforeEach
    public void init() {
        ebikeRepository = Mockito.mock(ArrayList.class);
        ebikeDaoImpl = new EbikeDaoImpl(ebikeRepository);

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
        when(ebikeRepository.add(ebike)).thenReturn(true);
        when(ebikeRepository.size()).thenReturn(1);
        when(ebikeRepository.get(1)).thenReturn(ebike);
        when(ebikeRepository.get(2)).thenThrow(new IndexOutOfBoundsException());
        when(ebikeRepository.subList(0, ebikeRepository.size())).thenReturn(list);
    }

    @Test
    void save() {
        assertEquals(true, ebikeDaoImpl.save(ebike));
    }

    @Test
    void getAll() {
        assertEquals(list, ebikeDaoImpl.getAll());
    }

    @Test
    void getById() {
        assertEquals(ebike, ebikeDaoImpl.getById(1));
        assertThrows(IndexOutOfBoundsException.class, () -> ebikeDaoImpl.getById(2));
    }
}
