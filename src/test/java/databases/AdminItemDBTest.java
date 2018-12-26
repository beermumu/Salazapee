package databases;

import cucumber.api.java.ca.I;
import javafx.collections.ObservableList;
import models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdminItemDBTest {
    private AdminItemDB adminItemDB;
    private ArrayList<Item> itemArrayList;

    @BeforeEach
    void init(){
        adminItemDB = new AdminItemDB();
        itemArrayList = new ArrayList<>();
        ObservableList<Item> itemsObserverbleList = adminItemDB.loadItem();
        for (Item i:itemsObserverbleList
        ) {
            itemArrayList.add(i);
        }
    }


    @Test
    void test_load_item_should_return_true_when_find_cost_that_in_database(){
        ArrayList<Integer> costList = new ArrayList<>();
        for (Item i: itemArrayList
             ) {
            costList.add(i.getCost());

        }

        assertTrue(costList.contains(30));


    }

    @Test
    void test_load_item_test_should_true_when_find_name_that_in_database(){
        ArrayList<String> nameList = new ArrayList<>();
        for (Item i:itemArrayList
             ) {
            nameList.add(i.getName());
        }
        assertTrue(nameList.contains("Lay"));
    }



}