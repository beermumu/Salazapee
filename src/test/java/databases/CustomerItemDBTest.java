package databases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerItemDBTest {
    private CustomerItemDB customerItemDB;

    @BeforeEach
    void init(){
        customerItemDB = new CustomerItemDB();
    }

    @Test
    void test_get_itemID_in_database(){

        assertEquals("1",customerItemDB.searchItem("1").getId());

    }

    @Test
    void test_get_cost_from_ID1_in_database(){

        assertEquals(15,customerItemDB.searchItem("3").getCost());

    }
    @Test
    void test_get_list_of_items(){
        System.out.println(customerItemDB.loadData());
    }

}