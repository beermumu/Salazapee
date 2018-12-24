package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Cart {
    private ObservableList<Item> cart = FXCollections.observableArrayList();

    public void addItem(Item item) {
        cart.add(item);
    }

    public void deleteItem(String id) {
        for (Item item : cart) {
            if (item.getId().equals(id)) {
                cart.remove(item);
            }
        }
    }

    public void clearCart() {
        cart.clear();
    }

    public ObservableList<Item> getCart() {
        return cart;
    }
}
