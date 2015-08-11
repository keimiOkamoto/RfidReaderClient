package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

import java.util.List;
import java.util.Map;

public interface Fridge {
    @DynamoDBHashKey(attributeName="FridgeID")
    String getFridgeId();

    void setFridgeId(String fridgeId);

    @DynamoDBAttribute(attributeName="Contents")
    List<Map<String, Integer>> getContents();

    void setContents(List<Map<String, Integer>> contents);
}
