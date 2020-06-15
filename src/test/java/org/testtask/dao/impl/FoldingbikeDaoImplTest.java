package org.testtask.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.models.FoldingBike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FoldingbikeDaoImplTest {
    private List<FoldingBike> foldingBikesRepository;

    private FoldingbikeDaoImpl foldingbikeDao;

    private FoldingBike foldingBike;
    private FoldingBike wrongFoldingBike;
    private List<FoldingBike> list;

    @BeforeEach
    public void init() {
        foldingBikesRepository = Mockito.mock(ArrayList.class);
        foldingbikeDao = new FoldingbikeDaoImpl(foldingBikesRepository);

        foldingBike = new FoldingBike();
        foldingBike.setType("E-FOLDING BIKE");
        foldingBike.setBrand("Benetti");
        foldingBike.setAvailableLights(true);
        foldingBike.setColor("grey");
        foldingBike.setPrice(1000);
        foldingBike.setWeight(19200);
        foldingBike.setWheelsSize(29);
        foldingBike.setNumberOfGears(18);

        wrongFoldingBike = new FoldingBike();

        list = new ArrayList<>();
        list.add(foldingBike);
        when(foldingBikesRepository.add(foldingBike)).thenReturn(true);
        when(foldingBikesRepository.size()).thenReturn(1);
        when(foldingBikesRepository.get(1)).thenReturn(foldingBike);
        when(foldingBikesRepository.get(2)).thenThrow(new IndexOutOfBoundsException());
        when(foldingBikesRepository.subList(0, foldingBikesRepository.size())).thenReturn(list);
    }

    @Test
    void save() {
        assertEquals(true, foldingbikeDao.save(foldingBike));
    }

    @Test
    void getAll() {
        assertEquals(list, foldingbikeDao.getAll());
    }

    @Test
    void getById() {
        assertEquals(foldingBike, foldingbikeDao.getById(1));
        assertThrows(IndexOutOfBoundsException.class, () -> foldingbikeDao.getById(2));
    }
}
