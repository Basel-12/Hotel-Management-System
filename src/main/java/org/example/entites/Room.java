package org.example.entites;

public class Room {
    private int id;
    private String type;
    private boolean available;
    private int price;

    public Room() {
    }

    public Room(int id, String type, boolean available, int price) {
        this.id = id;
        this.type = type;
        this.available = available;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPrice() {
        return price;
    }

    public boolean setPrice(int price) {
        if (price <= 0)
            return false;
        this.price = price;
        return true;
    }

    @Override
    public String toString() {
        String s;
        if (available)
            s = "available";
        else
            s = "not available";
        return  id + " " + type + " " + s + " " + price;
    }
}
