package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Map;
import java.util.stream.Collectors;

public class DynamoDBFridgeManagerImpl implements FridgeManager {
    private DynamoDBMapper dynamoDBMapper;
    private FridgeImpl fridgeImplRetrieved;

    public DynamoDBFridgeManagerImpl(String fridgeId, DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        fridgeImplRetrieved = dynamoDBMapper.load(FridgeImpl.class, fridgeId);
    }

    @Override
    public void addToContents(String itemId) {
        Map<String, Integer> itemAndQuantity = getItemAndQuantity(itemId);
        itemAndQuantity.put(itemId, itemAndQuantity.get(itemId) + 1);
        dynamoDBMapper.save(fridgeImplRetrieved);
    }

    @Override
    public void removeFromContents(String itemId) {
        Map<String, Integer> itemAndQuantity = getItemAndQuantity(itemId);
        itemAndQuantity.put(itemId, (itemAndQuantity.get(itemId) - 1));
        dynamoDBMapper.save(fridgeImplRetrieved);
    }

    private Map<String, Integer> getItemAndQuantity(String itemId) {
        return fridgeImplRetrieved.getContents().stream().filter(map -> map.containsKey(itemId)).collect(Collectors.toList()).get(0);
    }
}