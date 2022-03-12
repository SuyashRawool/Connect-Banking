package com.example.bankingsystem;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int check = 0;
    public void begin(View view) {
//        Intent intent = new Intent(this, ViewAll.class );
//        startActivity(intent);
//        Log.d("Intent Point","Sucessfully Completed");
        Intent intent = new Intent(this, ViewCustomers.class);
        startActivity(intent);
    }

    public void showHistory(View view){
        Intent intent = new Intent(this, TransactionHistory.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //Exit on back button
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(check == 1){
                    finish();
                    System.exit(1);
                }else{
                    Toast.makeText(MainActivity.this, "Press once more to exit", Toast.LENGTH_SHORT).show();
                }
                check++;
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}