package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

public class DynamoDBFoodItemManager {
    private DynamoDBMapper dynamoDBMapper;

    public DynamoDBFoodItemManager(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public FoodItem get(String id) {
        return dynamoDBMapper.load(FoodItemImpl.class, id);
    }

}
