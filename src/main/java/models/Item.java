package models;

public class Item {
    private String id;
    private String type;
    private String name;
    private int quantity;
    private int cost;
    private String description;

    public Item(String id ,String type,String name,int quantity,int cost,String description){
        this.id = id;
        this.type = type;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
    }

    public String getId() {
        return id;
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

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }
}
