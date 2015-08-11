package javaCode;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocalFridgeManagerImplTest {

    private Fridge fridge;
    private FridgeManager localFridgeManager;
    private String someProductId;
    private List<Map<String, Integer>> contents;
    private Map<String, Integer> item;

    @Before
    public void buildip() {
        fridge = mock(Fridge.class);
        localFridgeManager = new LocalFridgeManagerImpl(fridge);
        someProductId = "00001";
        contents = new ArrayList<>();
        item = new HashMap<>();

    }


    @Test
    public void shouldBeAbleToAddItemToExistingContents() {
        item.put(someProductId, 1);
        contents.add(0, item);

        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.addToContents(someProductId);
        localFridgeManager.addToContents(someProductId);
        localFridgeManager.addToContents(someProductId);

        int actual = contents.get(0).get(someProductId);
        int expected = 4;

        assertEquals(expected, actual);
    }

    @Test
    public void shouldBeAbleToAddNewItemIntoContents() {
        when(fridge.getContents()).thenReturn(contents);

        localFridgeManager.addToContents(someProductId);

        int actual = contents.get(0).get(someProductId);
        int expected = 1;

        assertEquals(expected, actual);
    }

}
