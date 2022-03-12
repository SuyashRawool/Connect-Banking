package com.example.bankingsystem;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankingsystem.sideCode.Customer;
import com.example.bankingsystem.sideCode.DbHandler;
import com.example.bankingsystem.sideCode.Params;
import com.example.bankingsystem.sideCode.Transaction;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionActivity extends AppCompatActivity {

    private int CID;
    public void transferBtnClick(View view){
        LinearLayout layout = findViewById(R.id.innerLayout);
        layout.setVisibility(View.VISIBLE);

    }

    public void searchClick(View view){
        int id;

        //For NaN exception handling
        try{
            id = Integer.parseInt(((TextView)findViewById(R.id.userID)).getText().toString());
            TextView userName = findViewById(R.id.userName);
            //Getting Customer through database
            DbHandler db  = new DbHandler(TransactionActivity.this);

            userName.setVisibility(View.VISIBLE);
            Customer cust = db.getCustomerById(id);

            if(cust.getName().equals("#")){ //Exception checking 1
                userName.setText("Invalid User ID");

            }else if(cust.getId() == this.CID){ //Exception checking 2
                userName.setText("It's your User ID");

            }else{ //If no exception
                userName.setText("Name : "+cust.getName());

                LinearLayout layout = findViewById(R.id.innerLayout2);
                layout.setVisibility(View.VISIBLE);


            }

        }catch(Exception e){
            Toast.makeText(TransactionActivity.this, "Please Enter a number", Toast.LENGTH_SHORT).show();
        }

    }

    public void makeTransfer(View view){
        TextView amountView = findViewById(R.id.amount);
        DbHandler db = new DbHandler(TransactionActivity.this);
        int rid = Integer.parseInt(((TextView)findViewById(R.id.userID)).getText().toString());
        int sid = this.CID;
        String ttime = new String("");
        String status = new String("");
        Customer sender = db.getCustomerById(sid);
        Customer receiver = db.getCustomerById(rid);
        String sName = sender.getName();
        String rName = receiver.getName();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            ttime = date.toString()+" \\ "+time.toString();
        }

        double amount = 0.0;

        try {
            amount = Double.parseDouble(amountView.getText().toString());
            if(amount <= sender.getBalance()){
                status = "Success";

                //Change data only in case of success
                sender.setBalance(sender.getBalance() - amount);
                receiver.setBalance(receiver.getBalance() + amount);


            }else{
                status = "Failed";
            }

            Transaction tran = new Transaction(sid, rid, sName, rName, amount, ttime,status);
            //Get added into database in both cases success and failure
            db.addTransaction(tran);
            //Make changes to customer accounts
            db.updateCustomer(sender);
            db.updateCustomer(receiver);

            Toast.makeText(TransactionActivity.this, ""+status, Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(TransactionActivity.this, "Please enter a numeric value", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_transaction);

        //Getting id from the previous Activty
        Intent intent = getIntent();
        int id = intent.getIntExtra("cid",0);
        this.CID = id;//This is used in searchClick method

        //Database Handler
        DbHandler db = new DbHandler(TransactionActivity.this);

        //Setting up Customer object
        Customer cust = db.getCustomerById(id);

        //Setting up Customer details on the activity
        ((TextView)findViewById(R.id.cid)).setText("User ID : "+cust.getId());
        ((TextView)findViewById(R.id.name)).setText("Name : "+cust.getName());
        ((TextView)findViewById(R.id.email)).setText("Email : "+cust.getEmail());
        ((TextView)findViewById(R.id.balance)).setText("Current Balance : \u20B9 "+cust.getBalance());
        ((TextView)findViewById(R.id.phone)).setText("Phone No. : "+cust.getPhone());

        //Custom back button to refresh ViewAll activity
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intentBack = new Intent(TransactionActivity.this, ViewCustomers.class);
                startActivity(intentBack);

            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}