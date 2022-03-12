package com.example.bankingsystem.sideCode;

public class Transaction {
    private int TID;
    private int sender_id;
    private int receiver_id;
    private String sender_name;
    private String receiver_name;
    private double amount;
    private String TTime;
    private String status;


    public Transaction(int sender_id, int receiver_id, String sender_name, String receiver_name, double amount, String TTime, String status) {
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.sender_name = sender_name;
        this.receiver_name = receiver_name;
        this.amount = amount;
        this.TTime = TTime;
        this.status = status;

    }

    public Transaction() {
    }

    //setters

    public void setSender_id(int sender_id) {

        this.sender_id = sender_id;

    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTTime(String TTime) {
        this.TTime = TTime;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //getters

    public int getTID() {
        return TID;
    }

    public int getSender_id() {
        return sender_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public double getAmount() {
        return amount;
    }

    public String getTTime() {
        return TTime;
    }

    public String getStatus() {
        return status;
    }
}
