package org.testtask.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.dao.impl.SpeedelecDaoIpml;
import org.testtask.models.SpeedelecBike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class SpeedelecBikeServiceImplTest {
    private SpeedelecDaoIpml speedelecDaoIpml;

    private SpeedelecBikeServiceImpl speedelecBikeService;

    private SpeedelecBike speedelecBike;
    private SpeedelecBike wrongSpeedelecBike;
    private List<SpeedelecBike> list;

    @BeforeEach
    public void init() {
        speedelecDaoIpml = Mockito.mock(SpeedelecDaoIpml.class);
        speedelecBikeService = new SpeedelecBikeServiceImpl(speedelecDaoIpml);

        speedelecBike = new SpeedelecBike();
        speedelecBike.setType("SPEEDELEC");
        speedelecBike.setBrand("EcoRide");
        speedelecBike.setAvailableLights(true);
        speedelecBike.setColor("beige");
        speedelecBike.setPrice(999);
        speedelecBike.setWeight(12300);
        speedelecBike.setBatteryCapacity(21000);
        speedelecBike.setMaxSpeed(28);

        wrongSpeedelecBike = new SpeedelecBike();

        list = new ArrayList<>();
        list.add(speedelecBike);
        when(speedelecDaoIpml.save(speedelecBike)).thenReturn(true);
        when(speedelecDaoIpml.getById(1)).thenReturn(speedelecBike);
        when(speedelecDaoIpml.getById(2)).thenThrow(new IndexOutOfBoundsException());
        when(speedelecDaoIpml.getAll()).thenReturn(list);
    }

    @Test
    void save() {
        assertEquals(true, speedelecDaoIpml.save(speedelecBike));
    }

    @Test
    void getAll() {
        assertEquals(list, speedelecDaoIpml.getAll());
    }

    @Test
    void getById() {
        assertEquals(speedelecBike, speedelecDaoIpml.getById(1));
        assertThrows(IndexOutOfBoundsException.class, () -> speedelecDaoIpml.getById(2));
    }
}
