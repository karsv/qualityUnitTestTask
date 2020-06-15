package org.testtask.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;

public class BikeRepository {
    private final List<SpeedelecBike> speedelecBikes = new ArrayList();
    private final List<Ebike> ebikes = new ArrayList();
    private final List<FoldingBike> foldingBikes = new ArrayList();

    @Bean
    public List<SpeedelecBike> getSpeedelecBikes() {
        return speedelecBikes;
    }

    @Bean
    public List<Ebike> getEbikes() {
        return ebikes;
    }

    @Bean
    public List<FoldingBike> getFoldingBikes() {
        return foldingBikes;
    }
}
