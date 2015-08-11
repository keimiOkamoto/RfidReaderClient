package javaCode.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;

import java.util.List;

public interface Fridge {
    @DynamoDBHashKey(attributeName="FridgeID")
    String getFridgeId();

    void setFridgeId(String fridgeId);

    @DynamoDBAttribute(attributeName="Contents")
    List<String> getContents();

    void setContents(List<String> contents);
}
