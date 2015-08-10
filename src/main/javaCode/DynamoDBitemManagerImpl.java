package javaCode;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBitemManagerImpl implements DynamoDBItemManager {

    private DynamoDBMapper mapper;

    public DynamoDBitemManagerImpl(AmazonDynamoDBClient dynamoDBClient) {
        mapper = new DynamoDBMapper(dynamoDBClient);
    }

    @Override
    public FoodItem get(String id) {
        return mapper.load(FoodItemImpl.class, id);
    }

    @Override
    public boolean removeItem() {
        return false;
    }

    @Override
    public void put(FoodItem foodItem) {

    }
}
