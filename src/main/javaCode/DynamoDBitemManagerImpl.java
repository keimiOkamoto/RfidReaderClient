package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBitemManagerImpl implements DynamoDBItemManager {

    private DynamoDBMapper dynamoDBMapper;

    public DynamoDBitemManagerImpl(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    @Override
    public FoodItem get(String id) {
        return dynamoDBMapper.load(FoodItemImpl.class, id);
    }
}
