package models;

import gherkin.lexer.Ca;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Item item;
    private Cart cart;

    @BeforeEach
    public void init(){
        Item item = new Item("111","Snack","Lay",10,20,"Air in the bag");
        Cart cart = new Cart();
    }

    @Test
    public void test_add_to_cart(){

        cart.addItem(item);
    }

    @Test
    void test_check_item_is_in_cart(){
        Cart cart = new Cart();
        Item item = new Item("111","Snack","Lay",10,20,"Air in the bag");
        cart.addItem(item);
        assertEquals(true,cart.isInCart(item));


    }

    @Test
    void test_check_item_is_not_in_cart(){


    }



}