package com.example.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<DataList> data;
    CustomeItemDelete customeItemDelete;
    private Context context;
    Database database;

    public RecyclerAdapter(Context context, ArrayList<DataList> data,CustomeItemDelete customeItemDelete) {

        this.context = context;
        this.data = data;
        this.customeItemDelete=customeItemDelete;

    }


    @NonNull
    @Override


    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.task.setText(data.get(position).getData());
        holder.date.setText(data.get(position).getDate());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


customeItemDelete.customDelteListener(data.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView task;
        TextView date;
        Button delete;

        public MyViewHolder(View itemView) {
            super(itemView);
            task = itemView.findViewById(R.id.task);
            date=itemView.findViewById(R.id.taskDate);
            delete= itemView.findViewById(R.id.delete);

        }
    }
}