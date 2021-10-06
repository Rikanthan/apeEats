package com.example.it20020880;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Orderdashboard extends AppCompatActivity implements OrderDashboardHolder.OrderDashboardHolderListener {
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    LinearLayoutManager linearLayoutManager;
    List<Products> ordersList;
    List<String> keys;
    OrderDashboardHolder orderHolder;
    String id ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdashboard);
        recyclerView = findViewById(R.id.order_dashboard_recycle);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ordersList = new ArrayList<>();
        keys = new ArrayList<>();
        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("McLJJXPnv3UNRjHyXGNZSBVe7lu2").child("Products");
        retrieve();
    }

    public void retrieve() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull
                                             DataSnapshot snapshot) {
                ordersList.clear();
                for (DataSnapshot mydata : snapshot.getChildren()) {
                    if (mydata.exists()) {
                        Products orders = mydata.getValue(Products.class);
                        ordersList.add(orders);
                        id = mydata.getKey();
                        if(id != null)
                        {
                            keys.add(id);
                        }
                    }
                }
                orderHolder = new OrderDashboardHolder(ordersList,Orderdashboard.this);
                recyclerView.setAdapter(orderHolder);
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void showDetailsOnClick(View v, int position) {
        Intent intent = new Intent(Orderdashboard.this,RestaurantOrder.class);
        intent.putExtra("orderno",keys.get(position));
        startActivity(intent);
    }

    @Override
    public void deleteOnClick(View v, int position) {
        Products newProducts = ordersList.get(position);
        ordersList.clear();
        databaseReference.child(keys.get(position)).removeValue();
        retrieve();
    }

    public void deliveryStatus(View v)
    {
        Intent i = new Intent(Orderdashboard.this,Delivery.class);
        startActivity(i);
    }
}