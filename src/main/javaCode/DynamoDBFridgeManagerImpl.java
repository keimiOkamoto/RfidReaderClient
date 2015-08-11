package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DynamoDBFridgeManagerImpl implements FridgeManager {
    private DynamoDBMapper dynamoDBMapper;
    private FridgeImpl fridgeRetrieved;

    public DynamoDBFridgeManagerImpl(String fridgeId, DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
        fridgeRetrieved = dynamoDBMapper.load(FridgeImpl.class, fridgeId);
    }

    @Override
    public void addToContents(String itemId) throws NullPointerException {
        Optional<Map<String, Integer>> product = getItemAndQuantity(itemId);

        if (!product.isPresent()) {
            List<Map<String, Integer>> content = fridgeRetrieved.getContents();
            Map<String, Integer> item = new HashMap<>();
            item.put(itemId, 1);
            content.add(item);
        } else {
            product.get().put(itemId, product.get().get(itemId) + 1);
        }
        dynamoDBMapper.save(fridgeRetrieved);
    }

    @Override
    public void removeFromContents(String itemId) {
        Optional<Map<String, Integer>> itemAndQuantity = getItemAndQuantity(itemId);
        itemAndQuantity.get().put(itemId, (itemAndQuantity.get().get(itemId) - 1));
        dynamoDBMapper.save(fridgeRetrieved);
    }

    private Optional<Map<String, Integer>> getItemAndQuantity(String itemId) {
        return fridgeRetrieved.getContents().stream().filter(map -> map.containsKey(itemId)).findFirst();
    }
}