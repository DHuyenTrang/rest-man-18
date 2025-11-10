package model;

public class Dish {
    private int id;
    private String name;
    private String detail;
    private float price;

    public Dish() {}
    public Dish(int id, String name, String detail, float price) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}