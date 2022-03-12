package com.example.bankingsystem.sideCode;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private double balance;

    public Customer(String name, String email, String phone, double balance) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.balance = balance;
    }

    public Customer() {
        this.name = "#";
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {
        return id;
    }

    //Setters


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
