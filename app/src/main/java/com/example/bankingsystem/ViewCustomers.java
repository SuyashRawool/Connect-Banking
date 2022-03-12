package com.example.bankingsystem;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingsystem.adapter.CustomersAdapter;
import com.example.bankingsystem.adapter.RecylerViewAdapter;
import com.example.bankingsystem.sideCode.Customer;
import com.example.bankingsystem.sideCode.DbHandler;
import com.example.bankingsystem.sideCode.Transaction;

import java.util.ArrayList;

public class ViewCustomers extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomersAdapter recyclerViewAdapter;
    private ArrayList<Customer> customersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_customers);
        TextView copy = findViewById(R.id.copyright2);
        copy.setText("\u00A9 Copyright 2022 Suyash Rawool");


        //This line of code must be customize in future for improving performance
        DbHandler db_handler = new DbHandler(ViewCustomers.this);
        ArrayList<Customer> check = db_handler.getCustomers();

        //Adding Details( Executed once in new device )
        if(check.size() == 0) {
            Customer suyash = new Customer("Suyash Rawool", "suyashrawool236@gmail.com", "9945718964", 8900.0);
            db_handler.addCustomer(suyash);
            Customer suraj = new Customer("Suraj Hiremath", "surajhiremath2303@gmail.com", "8987476233", 9000.0);
            db_handler.addCustomer(suraj);
            Customer ashish = new Customer("Ashish Magadum", "ashishmagadum123@gmail.com", "9344564728", 19000.0);
            db_handler.addCustomer(ashish);
            Customer shinu = new Customer("Shrinivas Patil", "introvertshinu69@gmail.com", "8987453622", 20000.0);
            db_handler.addCustomer(shinu);
            Customer rehan = new Customer("Rehan Gadag", "rehangadag456@gmail.com", "8287454678", 999999.0);
            db_handler.addCustomer(rehan);
            Customer vivek = new Customer("Vivek Jadhav", "vivekjadhav73@gmail.com", "9187476233", 29000.0);
            db_handler.addCustomer(vivek);
            Customer sumeet = new Customer("Sumeet Bafna", "sumeetbafna@gmail.com", "9987467233", 500000.0);
            db_handler.addCustomer(sumeet);
            Customer vinya = new Customer("Tony Stark", "iamironman@gmail.com", "1433000", 1000000000000.0);
            db_handler.addCustomer(vinya);
            Customer rohit = new Customer("Rohit Sharma", "hitman200@gmail.com", "8889997776", 299000.0);
            db_handler.addCustomer(rohit);
            Customer steph = new Customer("Stephen Curry", "stepcurry333@gmail.com", "1232352556", 987000.0);
            db_handler.addCustomer(steph);
        }


        try {

            customersList = db_handler.getCustomers();

            recyclerView = findViewById(R.id.CustomersView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            //Setting the adapter
            recyclerViewAdapter = new CustomersAdapter(ViewCustomers.this, customersList);
            recyclerView.setAdapter(recyclerViewAdapter);

        }catch (Exception e){
            Toast.makeText(this, "error "+e, Toast.LENGTH_LONG).show();
        }


        //Back button at this activity should only go to MainActivity
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ViewCustomers.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}