/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author DTS
 */
public class Discount {
    private int id;
    private String name;
    private String description;
    private double discount_percent;
    private boolean active;

    public Discount(int id, String name, String description, double discount_percent, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.discount_percent = discount_percent;
        this.active = active;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(double discount_percent) {
        this.discount_percent = discount_percent;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Discount{" + "id=" + id + ", name=" + name + ", description=" + description + ", discount_percent=" + discount_percent + ", active=" + active + '}';
    }
    
    
}
