package javaCode;

import java.util.*;

public class LocalFridgeManagerImpl implements FridgeManager {

    private Fridge fridge;

    public LocalFridgeManagerImpl(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public void addToContents(String itemId) throws IllegalArgumentException {
        if (itemId.isEmpty()) throw new IllegalArgumentException();

        List<Map<String, Integer>> currentContents = fridge.getContents();
        Optional<Map<String, Integer>> product = getItemAndQuantity(itemId);

        if (product.isPresent()) {
            product.get().put(itemId, product.get().get(itemId) + 1);
        } else {
            Map<String, Integer> productNew = new HashMap<>();
            productNew.put(itemId, 1);
            currentContents.add(productNew);
        }
        fridge.setContents(currentContents);
    }

    @Override
    public void removeFromContents(String itemId) throws IllegalArgumentException, NoSuchElementException {
        if (itemId.isEmpty()) throw new IllegalArgumentException();

        List<Map<String, Integer>> currentContents = fridge.getContents();
        Optional<Map<String, Integer>> product = getItemAndQuantity(itemId);

        int quantity = product.get().get(itemId);

        if (quantity <= 0) {
            currentContents.remove(product.get());
        } else {
            product.get().put(itemId, product.get().get(itemId) - 1);
            currentContents.add(product.get());
            fridge.setContents(currentContents);
        }
    }

    public String getContents() {
        StringBuilder stringBuilder = new StringBuilder();
        fridge.getContents().forEach(x -> {
            stringBuilder.append(x.toString());
        });
        return stringBuilder.toString();
    }

    private Optional<Map<String, Integer>> getItemAndQuantity(String itemId) {
        return fridge.getContents().stream().filter(map -> map.containsKey(itemId)).findFirst();
    }
}
