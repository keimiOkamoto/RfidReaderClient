package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName="Fridges")
public class FridgeImpl implements Fridge {
    private String fridgeId;
    private List<Map<String, Integer>> contents;

    @Override
    @DynamoDBHashKey(attributeName="FridgeID")
    public String getFridgeId() {
        return fridgeId;
    }

    @Override
    public void setFridgeId(String fridgeId) {
        this.fridgeId = fridgeId;
    }

    @Override
    @DynamoDBAttribute(attributeName="Contents")
    public List<Map<String, Integer>> getContents() {
        return contents;
    }

    @Override
    public void setContents(List<Map<String, Integer>> contents) {
        this.contents = contents;
    }

}
