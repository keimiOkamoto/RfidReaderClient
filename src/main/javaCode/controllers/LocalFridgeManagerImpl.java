package javaCode.controllers;

import javaCode.models.Fridge;

import java.util.*;

public class LocalFridgeManagerImpl implements FridgeManager {
    private Fridge fridge;

    public LocalFridgeManagerImpl(Fridge fridge) {
        this.fridge = fridge;
    }

    @Override
    public void add(String itemId) throws IllegalArgumentException {
        if (itemId.isEmpty()) throw new IllegalArgumentException();
        List<String> currentContents = fridge.getContents();
        currentContents.add(itemId);
        fridge.setContents(currentContents);
    }

    @Override
    public void remove(String itemId) throws IllegalArgumentException, NoSuchElementException {
        if (itemId.isEmpty()) throw new IllegalArgumentException();
        if(!contains(itemId)) throw new NoSuchElementException(itemId);
        List<String> currentContents = fridge.getContents();
        currentContents.remove(itemId);
    }

    @Override
    public boolean contains(String itemId) {
        return fridge.getContents().contains(itemId);
    }
}
