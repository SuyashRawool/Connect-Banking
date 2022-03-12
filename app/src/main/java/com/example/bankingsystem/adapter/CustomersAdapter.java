package com.example.bankingsystem.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingsystem.R;
import com.example.bankingsystem.TransactionActivity;
import com.example.bankingsystem.sideCode.Customer;

import java.util.ArrayList;


public class CustomersAdapter extends RecyclerView.Adapter<CustomersAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Customer> customerList;

    public CustomersAdapter(Context context, ArrayList<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @NonNull
    @Override
    public CustomersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row2, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomersAdapter.ViewHolder holder, int position) {

        Customer cus = customerList.get(position);
        try {
            holder.CID.setText(cus.getId()+"");
            holder.cName.setText(cus.getName());
            holder.cBalance.setText("\u20B9 "+cus.getBalance());
        }catch (Exception e){
            Toast.makeText(context, "error :"+e.getMessage(), Toast.LENGTH_LONG).show();
        }



       // Toast.makeText(context, "SET ViewHolder "+cus.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView CID;
        public TextView cName;
        public TextView cBalance;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            CID = itemView.findViewById(R.id.cus_id);
            cName = itemView.findViewById(R.id.cus_name);
            cBalance = itemView.findViewById(R.id.cus_balance);

        }

        @Override
        public void onClick(View view) {
            int pos = this.getAbsoluteAdapterPosition();
            Intent intent = new Intent(context, TransactionActivity.class);
            intent.putExtra("cid",pos+1);
            context.startActivity(intent);

        }
    }
}
