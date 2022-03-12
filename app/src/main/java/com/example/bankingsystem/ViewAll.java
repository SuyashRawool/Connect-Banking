package com.example.bankingsystem;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingsystem.sideCode.Customer;
import com.example.bankingsystem.sideCode.DbHandler;

import java.util.ArrayList;

public class ViewAll extends AppCompatActivity {

    public void startTransac(int id){// Called when user click on info table items
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.putExtra("cid",id);
        startActivity(intent);
    }

    public void refresh(View view){
        Intent intent = new Intent(this,ViewAll.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_all);
        TextView copy = findViewById(R.id.copyright);
        copy.setText("\u00A9 Copyright 2022 Suyash Rawool");

        DbHandler db_handler = new DbHandler(ViewAll.this);
        ArrayList<Customer> check = db_handler.getCustomers();

        //Adding Details
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


        //Assigning values to view
        try {
            ArrayList<Customer> cust = db_handler.getCustomers();
            //Toast.makeText(ViewAll.this, ""+cust.size(), Toast.LENGTH_SHORT).show();

            //Assigning ListViews
            ListView cid = findViewById(R.id.columnID);
            ListView name = findViewById(R.id.columnName);
            ListView balance =  findViewById(R.id.columnBalance);
            ListView info = findViewById(R.id.columnInfo);

            //List's for each column
            ArrayList<String> lid = new ArrayList<String>();
            ArrayList<String> lname = new ArrayList<String>();
            ArrayList<String> lbalance = new ArrayList<String>();
            ArrayList<String> linfo = new ArrayList<String>();

            //Assigning ArrayList
            for(int i=0; i < cust.size(); i++){
                lid.add(cust.get(i).getId()+"");
                lname.add(cust.get(i).getName());
                lbalance.add("\u20B9 "+cust.get(i).getBalance());
                linfo.add("->");
            }

            //Setting ArrayAdapter
            ArrayAdapter<String> aid = new ArrayAdapter<String>(ViewAll.this, android.R.layout.simple_list_item_1,
                    lid);
            cid.setAdapter(aid);
            ArrayAdapter<String> aname = new ArrayAdapter<String>(ViewAll.this, android.R.layout.simple_list_item_1,
                    lname);
            name.setAdapter(aname);
            ArrayAdapter<String> abalance = new ArrayAdapter<String>(ViewAll.this, android.R.layout.simple_list_item_1,
                    lbalance);
            balance.setAdapter(abalance);
            ArrayAdapter<String> ainfo = new ArrayAdapter<String>(ViewAll.this, android.R.layout.simple_list_item_1,
                    linfo);
            info.setAdapter(ainfo);


            //Setting up list event listener
            info.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startTransac(i+1);
                }
            });

        }catch (Exception e){
            Toast.makeText(ViewAll.this, ""+e+": error", Toast.LENGTH_LONG).show();
        }

        //Back button at this activity should only go to MainActivity
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(ViewAll.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


    }
}