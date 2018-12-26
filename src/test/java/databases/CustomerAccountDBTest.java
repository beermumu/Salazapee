package databases;

import models.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

    }

}