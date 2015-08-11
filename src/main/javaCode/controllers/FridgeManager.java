package javaCode.controllers;

public interface FridgeManager {
    void add(String item);

    void remove(String itemId);

    boolean contains(String itemId);
}
