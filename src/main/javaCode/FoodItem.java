package javaCode;

import java.util.List;
import java.util.Map;

public interface FoodItem {

    String getId();

    void setId(String id);

    int getAllergenCode();

    String getExpiryDate();


    int getFoodCode();

    void setGm(boolean gm);

    String getName();

    List<Map<String,String>> getNutritionDetails();

    boolean isOrganic();

}
