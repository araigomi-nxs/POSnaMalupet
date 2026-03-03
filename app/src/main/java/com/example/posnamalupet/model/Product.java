package com.example.posnamalupet.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    private String name;
    private int image;
    private double price;
    private int quantity;
    private static final List<Product> productList=new ArrayList<>();
    private static final List<Product> checkoutList=new ArrayList<>();

    public Product(String name,int image, double price,int quantity){
        this.name=name;
        this.image=image;
        this.price=price;
        this.quantity=quantity;
    }
    public static void addProductList(Product product){
        productList.add(product);
    }
    public static void addCheckoutList(Product product){
        checkoutList.add(product);
    }
    public static List<Product> getAllProducts(){
        return productList;
    }
    public static List<Product> getAllCheckout(){
        return checkoutList;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
