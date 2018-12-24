package controllers;

import databases.AdminAccountDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {
    @BeforeEach
    void setup(){
        AdminAccountDB.getAccountID();
    }
    @Test
    public void test(){
        //click("#")
    }
}