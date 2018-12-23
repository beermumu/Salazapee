package Models;

public class Item {

    private String type;
    private String name;
    private int quantity;
    private int cost;
    private String description;

    public Item(String type,String name,int quantity,int cost,String description){
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
