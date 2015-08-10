package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Map;
import java.util.stream.Collectors;

public class DynamoDBFridgeManagerImpl implements FridgeManager {
    private String fridgeId;
    private DynamoDBMapper dynamoDBMapper;
    private Fridge fridgeRetrieved;

    public DynamoDBFridgeManagerImpl(String fridgeId, DynamoDBMapper dynamoDBMapper) {
        this.fridgeId = fridgeId;
        this.dynamoDBMapper = dynamoDBMapper;
        fridgeRetrieved = dynamoDBMapper.load(Fridge.class, fridgeId);
    }

    @Override
    public void addToFridge(String itemId) {
        Map<String, Integer> itemAndQuantity = getItemAndQuantity(itemId);
        itemAndQuantity.put(itemId, itemAndQuantity.get(itemId) + 1);
        dynamoDBMapper.save(fridgeRetrieved);
    }

    @Override
    public void remove(String itemId) {
        Map<String, Integer> itemAndQuantity = getItemAndQuantity(itemId);
        itemAndQuantity.put(itemId, (itemAndQuantity.get(itemId) - 1));
        dynamoDBMapper.save(fridgeRetrieved);
    }

    private Map<String, Integer> getItemAndQuantity(String itemId) {
        return fridgeRetrieved.getContents().stream().filter(map -> map.containsKey(itemId)).collect(Collectors.toList()).get(0);
    }
}