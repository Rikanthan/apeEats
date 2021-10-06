package com.example.it20020880;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FinishedOrders extends AppCompatActivity implements OrderHolder.OnItemClickListener{
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;
    List<Products> ordersList;
    OrderHolder orderHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_orders);
        recyclerView = findViewById(R.id.order_recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ordersList = new ArrayList<>();
        retrieve();
    }
    public void retrieve()
    {
        ordersList.clear();
        databaseReference = FirebaseDatabase.getInstance()
                .getReference("McLJJXPnv3UNRjHyXGNZSBVe7lu2").child("Products");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull
                                             DataSnapshot snapshot) {
                for(DataSnapshot mydata: snapshot.getChildren())
                {
                    if(mydata.exists())
                    {
                      Products orders = mydata.getValue(Products.class);
                        assert orders != null;
                        ordersList.add(orders);
//                        if(orders.getpCatogory().contains("Yes"))
//                        {
//                            ordersList.add(orders);
//                        }
                    }
                }
                orderHolder = new OrderHolder(FinishedOrders.this,ordersList);
                orderHolder.setOnItemClickListener(FinishedOrders.this);
                recyclerView.setAdapter(orderHolder);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}