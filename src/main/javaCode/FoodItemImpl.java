package javaCode;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Map;

@DynamoDBTable(tableName = "Products")
public class FoodItemImpl implements FoodItem {
    private String id;
    private int allergenCode;
    private String expiryDate;
    private List<String> feed;
    private int foodCode;
    private boolean gm;
    private String name;
    private List<Map<String, Double>> nutritionDetails;
    private boolean organic;
    private int netContent;

    @DynamoDBHashKey(attributeName = "ProductID")
    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "AllergenCode")
    @Override
    public int getAllergenCode() {
        return allergenCode;
    }

    public void setAllergenCode(int allergenCode) {
        this.allergenCode = allergenCode;
    }

    @DynamoDBAttribute(attributeName = "ExpiryDate")
    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @DynamoDBAttribute(attributeName = "Feed")
    public List<String> getFeed() {
        return feed;
    }

    public void setFeed(List<String> feed) {
        this.feed = feed;
    }

    @Override
    @DynamoDBAttribute(attributeName = "FoodCode")
    public int getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(int foodCode) {
        this.foodCode = foodCode;
    }

    @DynamoDBAttribute(attributeName = "GM")
    public boolean isGm() {
        return gm;
    }

    @Override
    public void setGm(boolean gm) {
        this.gm = gm;
    }

    @DynamoDBAttribute(attributeName = "Name")
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DynamoDBAttribute(attributeName = "Nutrition")
    @Override
    public List<Map<String, Double>> getNutritionDetails() {
        return nutritionDetails;
    }

    public void setNutritionDetails(List<Map<String, Double>> nutritionDetails) {
        this.nutritionDetails = nutritionDetails;
    }

    @DynamoDBAttribute(attributeName = "Organic")
    @Override
    public boolean isOrganic() {
        return organic;
    }

    public void setOrganic(boolean organic) {
        this.organic = organic;
    }

    @DynamoDBAttribute(attributeName = "NetContent")
    @Override
    public int getNetContent() {
        return netContent;
    }

    public void setNetContent(int netContent) {
        this.netContent = netContent;
    }
}
