package javaCode;

public interface DynamoDBItemManager {

    FoodItem get(String id);

    boolean removeItem();

    void put(FoodItem foodItem);
}
