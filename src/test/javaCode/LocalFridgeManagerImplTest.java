package javaCode;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LocalFridgeManagerImplTest {

    private Fridge fridge;
    private FridgeManager localFridgeManager;
    private String someProductId;
    private List<Map<String, Integer>> contents;
    private Map<String, Integer> item;
    private int index;

    @Before
    public void buildUp() {
        fridge = mock(Fridge.class);
        localFridgeManager = new LocalFridgeManagerImpl(fridge);
        someProductId = "00001";
        contents = new ArrayList<>();
        item = new HashMap<>();
        index = 0;
    }


    @Test
    public void shouldBeAbleToAddItemToExistingContents() {
        item.put(someProductId, 1);
        contents.add(index, item);

        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.addToContents(someProductId);
        localFridgeManager.addToContents(someProductId);
        localFridgeManager.addToContents(someProductId);

        int expected = 4;
        int actual = contents.get(0).get(someProductId);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToAddNewItemIntoContents() {
        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.addToContents(someProductId);

        int expected = 1;
        int actual = contents.get(index).get(someProductId);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfProductIdIsEmptyWhenTryingToAddItem() {
        localFridgeManager.addToContents("");
        verify(fridge).getContents();
    }

    @Test
    public void shouldBeAbleToRemoveAnItemFromContents() {
        item.put(someProductId, 1);
        contents.add(index, item);

        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.addToContents(someProductId);
        localFridgeManager.addToContents(someProductId);

        localFridgeManager.removeFromContents(someProductId);

        int actual = contents.get(index).get(someProductId);
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToRemoveLastItemFromContents() {
        item.put(someProductId, 1);
        contents.add(index, item);

        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.removeFromContents(someProductId);

        int expected = 0;
        int actual = contents.get(index).get(someProductId);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfProductIdIsEmptyWhenTryingToRemoveItem() {
        localFridgeManager.removeFromContents("");
        verify(fridge).getContents();
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionIfItemIdToRemoveIsNotFound() {
        localFridgeManager.removeFromContents("NonExistentProduct");
        verify(fridge).getContents();
    }
}
