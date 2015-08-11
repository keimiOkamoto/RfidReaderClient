package javaCode.controllers;

import javaCode.models.Fridge;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LocalFridgeManagerImplTest {

    private Fridge fridge;
    private FridgeManager localFridgeManager;
    private String someProductId1;
    private String someProductId2;
    private String someProductId3;
    private List<String> contents;
    private int index;

    @Before
    public void buildUp() {
        fridge = mock(Fridge.class);
        localFridgeManager = new LocalFridgeManagerImpl(fridge);
        someProductId1 = "00001";
        someProductId2 = "00002";
        someProductId3 = "00003";
        contents = new ArrayList<>();
        index = 0;
    }


    @Test
    public void shouldBeAbleToAddItemToExistingContents() {
        contents.add(someProductId1);

        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.add(someProductId1);
        localFridgeManager.add(someProductId2);
        localFridgeManager.add(someProductId3);

        int expected = 4;
        int actual = contents.size();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToAddNewItemIntoContents() {
        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.add(someProductId1);

        String expected = someProductId1;
        String actual = contents.get(0);

        assertEquals(expected, actual);
        verify(fridge).getContents();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfProductIdIsEmptyWhenTryingToAddItem() {
        localFridgeManager.add("");
        verify(fridge).getContents();
    }

    @Test
    public void shouldBeAbleToRemoveAnItemFromContents() {
        contents.add(someProductId1);
        contents.add(someProductId2);
        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.remove(someProductId1);

        String expected = someProductId2;
        String actual = contents.get(0);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfProductIdIsEmptyWhenTryingToRemoveItem() {
        localFridgeManager.remove("");
        verify(fridge).getContents();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionIfItemIdToRemoveIsNotFound() {
        localFridgeManager.remove("NonExistentProduct");
        verify(fridge).getContents();
    }
}
