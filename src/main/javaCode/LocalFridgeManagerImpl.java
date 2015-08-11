package javaCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalFridgeManagerImpl implements FridgeManager {

    private Fridge fridgeImpl;

    public LocalFridgeManagerImpl(Fridge fridgeImpl) {
        this.fridgeImpl = fridgeImpl;
    }

    @Override
    public void addToContents(String item) {
        List<Map<String, Integer>> currentContents = fridgeImpl.getContents();
        Map<String, Integer> product = getItemAndQuantity(item);

        if (product == null) {
            System.out.println("product == null");
            product = new HashMap<>();
            product.put(item, 1);
            currentContents.add(product);
        } else {
            product.put(item, product.get(item) + 1);
            System.out.println("should see thios");
        }
        fridgeImpl.setContents(currentContents);
        System.out.println(fridgeImpl.getContents().get(0).get(item) + " wowpowowpw");
    }

    @Override
    public void removeFromContents(String itemId) {

    }

    private Map<String, Integer> getItemAndQuantity(String itemId) {
        if (!fridgeImpl.getContents().contains(itemId)) return null;
        return fridgeImpl.getContents().stream().filter(map -> map.containsKey(itemId)).collect(Collectors.toList()).get(0);
    }
}
