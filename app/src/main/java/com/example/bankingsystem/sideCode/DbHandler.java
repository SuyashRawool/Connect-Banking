package com.example.bankingsystem.sideCode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bankingsystem.ViewAll;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    public DbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+Params.DB_CUSTOMER+
                "(" + Params.CID+" INTEGER PRIMARY KEY, " + Params.C_NAME
                + " TEXT, "+ Params.C_EMAIL+" TEXT, " + Params.C_PHONE+" TEXT, " + Params.C_BALANCE+
                " Text" + ")";

        Log.d("DB_LOG","Query: "+create);
        String create2 = "CREATE TABLE "+Params.DB_TRANSACTION+
                "(" + Params.TID+" INTEGER PRIMARY KEY, " + Params.T_SID
                + " INTEGER, "+ Params.T_RID+" INTEGER, " + Params.T_AMT+" TEXT, " + Params.T_TIME+
                " Text, " +Params.T_STATUS+" TEXT" +")";



        sqLiteDatabase.execSQL(create);
        sqLiteDatabase.execSQL(create2);
        Log.d("db_tr","Successfully Created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<Transaction> getTransactionDetails(ArrayList<Customer> customers){
        ArrayList<Transaction> transactionsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Reading
        String select = "SELECT * FROM " + Params.DB_TRANSACTION;
        Cursor cur = db.rawQuery(select, null);
        Log.d("db_tr", "Successfully SELECT Query");

        //Loop over select statement
        if(cur.moveToFirst()){
            do{
                Log.d("db_tr", "Addding to list");
                Transaction tran = new Transaction();
                tran.setTID(Integer.parseInt(cur.getString(0)));
                tran.setSender_id(Integer.parseInt(cur.getString(1)));
                tran.setReceiver_id(Integer.parseInt(cur.getString(2)));
                tran.setAmount(Double.parseDouble(cur.getString(3)));
                tran.setTTime(cur.getString(4));
                tran.setStatus(cur.getString(5));

                tran.setSender_name(customers.get(cur.getInt(1)-1).getName());
                tran.setReceiver_name(customers.get(cur.getInt(2)-1).getName());

                transactionsList.add(tran);

            }while (cur.moveToNext());
        }

        return transactionsList;
    }

    public void addTransaction(Transaction tran){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.T_SID, tran.getSender_id());
        values.put(Params.T_RID, tran.getReceiver_id());
        values.put(Params.T_AMT,tran.getAmount());
        values.put(Params.T_TIME, tran.getTTime());
        values.put(Params.T_STATUS, tran.getStatus());

        db.insert(Params.DB_TRANSACTION, null, values);
        Log.d("db_tr", "Successfully Inserted");
        db.close();
    }

    public void addCustomer(Customer cust){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.C_NAME, cust.getName());
        values.put(Params.C_EMAIL, cust.getEmail());
        values.put(Params.C_PHONE,cust.getPhone());
        values.put(Params.C_BALANCE, ""+cust.getBalance());

        db.insert(Params.DB_CUSTOMER, null, values);
        Log.d("db_tr", "Successfully Inserted");
        db.close();
    }

    public ArrayList<Customer> getCustomers(){
        ArrayList<Customer> customerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        //Reading
        String select = "SELECT * FROM " + Params.DB_CUSTOMER;
        Cursor cur = db.rawQuery(select, null);
        Log.d("db_tr", "Successfully SELECT Query");

        //Loop over select statement
        if(cur.moveToFirst()){
            do{
                Log.d("db_tr", "Addding to list");
                Customer cust = new Customer();
                cust.setId(Integer.parseInt(cur.getString(0)));
                cust.setName(cur.getString(1));
                cust.setEmail(cur.getString(2));
                cust.setPhone(cur.getString(3));
                cust.setBalance(Double.parseDouble(cur.getString(4)));

                customerList.add(cust);

            }while (cur.moveToNext());
        }

        return customerList;

    }

    public Customer getCustomerById(int id){
        Customer cust = new Customer();
        SQLiteDatabase db = this.getReadableDatabase();

        //SQL Query
        String select = "SELECT * FROM " + Params.DB_CUSTOMER + " WHERE " + Params.CID + " = "+id;
        Cursor cur = db.rawQuery(select,null);

        try{
            cur.moveToFirst();
            cust.setId(Integer.parseInt(cur.getString(0)));
            cust.setName(cur.getString(1));
            cust.setEmail(cur.getString(2));
            cust.setPhone(cur.getString(3));
            cust.setBalance(Double.parseDouble(cur.getString(4)));
        }catch (Exception e){
            Log.d("db_tr", "getCustomerById Error");
        }

        return cust;
    }

    public void updateCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.C_BALANCE,customer.getBalance());

        db.update(Params.DB_CUSTOMER, values, Params.CID+"=?",
                new String[]{String.valueOf(customer.getId())});
    }
}

