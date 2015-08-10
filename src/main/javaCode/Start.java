package javaCode;


import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class Start {
    public static void main(String[] args) {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        dynamoDBClient.setRegion(Region.getRegion(Regions.EU_WEST_1));

        DynamoDBItemManager dynamoDBItemManager = new DynamoDBitemManagerImpl(dynamoDBClient);
        RfidReader reader = RfidReaderImpl.getInstance();
        boolean start = true;

        while (start) {
            System.out.println("Waiting for input...");
            String id = reader.readCard();
            System.out.println("Read: " + id);

            if (!id.isEmpty() || !id.equals("")) {
                FoodItem foodItem = dynamoDBItemManager.get(id);

                if (foodItem != null) {
                    System.out.println("GOT: " + foodItem.getName() + ". Allergen Code: " + foodItem.getAllergenCode() + ". Expiry Date: " + foodItem.getExpiryDate());
                    System.out.println("success");
                }
            } else {
                System.out.println("Id was empty.");
            }
        }

    }
}