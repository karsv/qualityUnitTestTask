package org.testtask.dao.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.*;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.springframework.stereotype.Repository;
import org.testtask.dao.BikeDao;
import org.testtask.models.Bike;
import org.testtask.models.Ebike;
import org.testtask.models.FoldingBike;
import org.testtask.models.SpeedelecBike;

@Repository
public class BikeDaoMongoDbImpl implements BikeDao {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> bikes;

    @PostConstruct
    private void init(){
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("ecobike");
        bikes = database.getCollection("bikes");
    }

    @PreDestroy
    private void finish(){
        mongoClient.close();
    }

    @Override
    public boolean save(Bike bike) {
        var currentBike = bike instanceof SpeedelecBike
                ? (SpeedelecBike) bike : bike instanceof Ebike
                ? (Ebike) bike : (FoldingBike) bike;
        Document document = new Document(convertObjectToMap(bike));
        bikes.insertOne(document);
        return false;
    }

    @Override
    public List getAll() {
        FindIterable<Document> documents = bikes.find();
        ArrayList<Object> list = new ArrayList<>();
        for(Document document : documents){
            list.add(document);
        }
        return list;
    }

    @Override
    public Bike getById(Integer id) {
        return null;
    }

    private Map<String, Object> convertObjectToMap(Bike bike){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(bike, Map.class);
    }
}
