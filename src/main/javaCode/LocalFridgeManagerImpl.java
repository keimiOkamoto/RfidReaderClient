package javaCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LocalFridgeManagerImpl implements FridgeManager {

    private Fridge fridge;

    public LocalFridgeManagerImpl(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public void addToContents(String item) {
        List<Map<String, Integer>> currentContents = fridge.getContents();
        Optional<Map<String, Integer>> product = getItemAndQuantity(item);

        if(product.isPresent()) {
            product.get().put(item, product.get().get(item) + 1);
        } else {
            Map<String, Integer>productNew = new HashMap<>();
            productNew.put(item, 1);
            currentContents.add(productNew);
        }
        fridge.setContents(currentContents);
    }

    @Override
    public void removeFromContents(String itemId) {
    }

    private Optional<Map<String, Integer>> getItemAndQuantity(String itemId) {
       return fridge.getContents().stream().filter(map -> map.containsKey(itemId)).findFirst();
    }
}
