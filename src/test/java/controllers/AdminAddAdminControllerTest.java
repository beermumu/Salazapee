package controllers;

import databases.AdminAccountDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminAddAdminControllerTest {
    AdminAccountDB adminAccountDB;
    AdminAddAdminController adminAddAdminController;
    @BeforeEach
    void init(){
        adminAccountDB = new AdminAccountDB();
        adminAddAdminController = new AdminAddAdminController();

    }

    @Test
    void test_add_new_add_min_to_database_and_indatabase(){
        adminAccountDB.saveAdmin("0001","Tan","Rutthanawin","xalo","0009","11/1","xxx@gmail.com","0955173159");
        assertEquals(true,adminAddAdminController.checkID("0001"));
    }

    @Test
    void test_check_no_id_in_database(){
        adminAccountDB.saveAdmin("0001","Tan","Rutthanawin","xalo","0009","11/1","xxx@gmail.com","095173159");
        assertEquals(false,adminAddAdminController.checkID("0000"));

    }


}