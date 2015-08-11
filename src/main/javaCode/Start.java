package javaCode;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import javaCode.RFIDReader.RfidReader;
import javaCode.RFIDReader.RfidReaderImpl;
import javaCode.controllers.DynamoDBFridgeManagerImpl;
import javaCode.controllers.FridgeManager;
import javaCode.controllers.LocalFridgeManagerImpl;
import javaCode.models.FridgeImpl;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        dynamoDBClient.setRegion(Region.getRegion(Regions.EU_WEST_1));
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);

        RfidReader reader = RfidReaderImpl.getInstance();

        FridgeImpl fridgeImpl = new FridgeImpl();
        fridgeImpl.setFridgeId("FRDG00001");

        FridgeManager networkFridgeManager = new DynamoDBFridgeManagerImpl("FRDG00001", mapper);
        FridgeManager localFridgeManagerImpl = new LocalFridgeManagerImpl(fridgeImpl);

        boolean poll = true;

        while (poll) {
            System.out.println("Waiting for input...");
            Scanner scanner = new Scanner(System.in);
            String id = reader.readCard(scanner);
            System.out.println("Read: " + id);

            if (!id.isEmpty() || !id.equals("")) {
                if (networkFridgeManager.contains(id)) {
                    System.out.println("Removing: " + id);
                    networkFridgeManager.remove(id);
                } else {
                    System.out.println("Adding: " + id);
                    networkFridgeManager.add(id);
                }
            } else if (id.equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Id was empty.");
            }
        }
    }
}
