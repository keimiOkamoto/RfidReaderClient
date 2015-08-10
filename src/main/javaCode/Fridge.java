package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName="Fridges")
public class Fridge {
    private String fridgeId;
    private String ownerId;
    private List<String> subscribers;
    private List<Map<String, Integer>> contents;

    @DynamoDBHashKey(attributeName="FridgeID")
    public String getFridgeId() {
        return fridgeId;
    }

    public void setFridgeId(String fridgeId) {
        this.fridgeId = fridgeId;
    }

    @DynamoDBAttribute(attributeName="OwnerID")
    public String getOwnerID() {
        return ownerId;
    }

    public void setOwnerID(String ownerId) {
        this.ownerId = ownerId;
    }

    @DynamoDBAttribute(attributeName="Subscribers")
    public List<String> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<String> subscribers) {
        this.subscribers = subscribers;
    }

    @DynamoDBAttribute(attributeName="Contents")
    public List<Map<String, Integer>> getContents() {
        return contents;
    }

    public void setContents(List<Map<String, Integer>> contents) {
        this.contents = contents;
    }

}
