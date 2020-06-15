package org.testtask.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testtask.dao.impl.FoldingbikeDaoImpl;
import org.testtask.models.FoldingBike;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class FoldingbikeServiceImplTest {
    private FoldingbikeDaoImpl foldingBikesRepository;

    private FoldingbikeServiceImpl foldingbikeService;

    private FoldingBike foldingBike;
    private FoldingBike wrongFoldingBike;
    private List<FoldingBike> list;

    @BeforeEach
    public void init() {
        foldingBikesRepository = Mockito.mock(FoldingbikeDaoImpl.class);
        foldingbikeService = new FoldingbikeServiceImpl(foldingBikesRepository);

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
        when(foldingBikesRepository.save(foldingBike)).thenReturn(true);
        when(foldingBikesRepository.getById(1)).thenReturn(foldingBike);
        when(foldingBikesRepository.getById(2)).thenThrow(new IndexOutOfBoundsException());
        when(foldingBikesRepository.getAll()).thenReturn(list);
    }

    @Test
    void save() {
        assertEquals(true, foldingbikeService.save(foldingBike));
    }

    @Test
    void getAll() {
        assertEquals(list, foldingbikeService.getAll());
    }

    @Test
    void getById() {
        assertEquals(foldingBike, foldingbikeService.getById(1));
        assertThrows(IndexOutOfBoundsException.class, () -> foldingbikeService.getById(2));
    }
}
