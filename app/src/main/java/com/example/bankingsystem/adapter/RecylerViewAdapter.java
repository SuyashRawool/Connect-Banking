package com.example.bankingsystem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankingsystem.R;
import com.example.bankingsystem.sideCode.Transaction;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Transaction> transactionList;

    public RecylerViewAdapter(Context context, ArrayList<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public RecylerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapter.ViewHolder holder, int position) {

        Transaction tran = transactionList.get(position);

        holder.sender.setText(tran.getSender_name()+"("+tran.getSender_id()+")");
        holder.receiver.setText(tran.getReceiver_name()+"("+tran.getReceiver_id()+")");
        holder.amount.setText("\u20B9 "+tran.getAmount());
        holder.dateTime.setText(tran.getTTime());
        holder.status.setText(tran.getStatus());


    }

    @Override
    public int getItemCount() {
        return this.transactionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView sender;
        public TextView receiver;
        public TextView amount;
        public TextView dateTime;
        public TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            sender = itemView.findViewById(R.id.sender);
            receiver = itemView.findViewById(R.id.receiver);
            amount = itemView.findViewById(R.id.amountHistory);
            dateTime = itemView.findViewById(R.id.transactionTime);
            status = itemView.findViewById(R.id.status);
        }

        @Override
        public void onClick(View view) {
            int pos = this.getAbsoluteAdapterPosition();
            Transaction tran = transactionList.get(pos);

        }
    }
}
