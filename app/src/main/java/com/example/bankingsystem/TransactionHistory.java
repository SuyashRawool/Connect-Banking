package com.example.bankingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;


import com.example.bankingsystem.adapter.RecylerViewAdapter;
import com.example.bankingsystem.sideCode.Customer;
import com.example.bankingsystem.sideCode.DbHandler;
import com.example.bankingsystem.sideCode.Transaction;

import java.util.ArrayList;
import java.util.Collections;

public class TransactionHistory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecylerViewAdapter recyclerViewAdapter;
    private ArrayList<Transaction> transactionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_transaction_history);

        recyclerView = findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Getting data through DB
        DbHandler db = new DbHandler(this);
        ArrayList<Customer> customers = db.getCustomers();
        transactionList = db.getTransactionDetails(customers);
        if(transactionList.size() == 0)
            Toast.makeText(this, "First Please make some transactions", Toast.LENGTH_LONG).show();
        Collections.reverse(transactionList);

        //Setting the adapter
        recyclerViewAdapter = new RecylerViewAdapter(TransactionHistory.this, transactionList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}