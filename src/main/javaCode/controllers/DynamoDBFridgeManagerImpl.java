package javaCode.controllers;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import javaCode.models.FridgeImpl;

public class DynamoDBFridgeManagerImpl implements FridgeManager {
    private DynamoDBMapper dynamoDBMapper;
    private FridgeImpl fridgeRetrieved;

    public DynamoDBFridgeManagerImpl(String fridgeId, DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        fridgeRetrieved = dynamoDBMapper.load(FridgeImpl.class, fridgeId);
    }

    @Override
    public void add(String itemId) throws NullPointerException {
        if (itemId.isEmpty()) throw new IllegalArgumentException();
        fridgeRetrieved.getContents().add(itemId);
        dynamoDBMapper.save(fridgeRetrieved);
    }

    @Override
    public void remove(String itemId) {
        if (itemId.isEmpty()) throw new IllegalArgumentException();
        fridgeRetrieved.getContents().remove(itemId);
        dynamoDBMapper.save(fridgeRetrieved);
    }

    @Override
    public boolean contains(String itemId) {
        return fridgeRetrieved.getContents().contains(itemId);
    }
}