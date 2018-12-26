package databases;

import javafx.collections.ObservableList;
import models.Accounts;
import models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerAccountDBTest {
    private CustomerAccountDB customerAccountDB;
    private Invoice invoice;

    @BeforeEach
    void init(){
        customerAccountDB = new CustomerAccountDB();

    }

    @Test
    void test_getID(){
        ArrayList<String> integers = new ArrayList<>();

        ObservableList<Accounts> list = customerAccountDB.loadAccounts();
        for (Accounts a:list
             ) {
            integers.add(a.getId());
        }
        assertTrue(integers.contains("6"));
    }



}