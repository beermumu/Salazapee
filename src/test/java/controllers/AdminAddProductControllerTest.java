package controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminAddProductControllerTest {
    AdminAddAdminController adminAddAdminController;

    @BeforeEach
    void init(){
        adminAddAdminController = new AdminAddAdminController();

    }

    @Test
    void test_numeric(){
        assertEquals(true,adminAddAdminController.isNumeric("1"));
    }

    @Test
    void test_numeric_should_return_false_when_insert_character(){
        assertEquals(false,adminAddAdminController.isNumeric("g"));
    }



}