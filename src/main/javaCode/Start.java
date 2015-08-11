package javaCode;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        dynamoDBClient.setRegion(Region.getRegion(Regions.EU_WEST_1));

        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);

        DynamoDBItemManager dynamoDBItemManager = new DynamoDBitemManagerImpl(mapper);
        RfidReader reader = RfidReaderImpl.getInstance();

        DynamoDBFridgeManagerImpl dynamoDBFridgeMAnager = new DynamoDBFridgeManagerImpl("FRDG00001", mapper);
        boolean start = true;

        FridgeImpl fridgeImpl = new FridgeImpl();
        fridgeImpl.setFridgeId("FRDG00001");
        fridgeImpl.setContents(new ArrayList<Map<String, Integer>>());

        LocalFridgeManagerImpl localFridgeManagerImpl = new LocalFridgeManagerImpl(fridgeImpl);
        localFridgeManagerImpl.addToContents("2449785860");
        localFridgeManagerImpl.addToContents("2449785860");

        while (start) {
            System.out.println("Waiting for input...");
            Scanner scanner = new Scanner(System.in);
            String id = reader.readCard(scanner);
            System.out.println("Read: " + id);

            if (!id.isEmpty() || !id.equals("")) {
                dynamoDBFridgeMAnager.addToContents(id);
            } else {
                System.out.println("Id was empty.");
            }
        }
    }
}
