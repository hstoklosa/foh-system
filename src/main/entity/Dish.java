package main.entity;
public class Dish {
    private final int id;
    private String name;
    private double price;
    private String description;

    public Dish(int id, String name, double price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return name;
    }
//    @Override
//    public String toString() {
//        return name + ", £" + price;
//    }
}
