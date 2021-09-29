package com.example.anushka;

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
    List<Orders> ordersList;
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
        databaseReference = FirebaseDatabase
                .getInstance()
                .getReference("Orders");
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
                        Orders orders = mydata.getValue(Orders.class);
                        ordersList.add(orders);
                        id = orders.getOrderNo();
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
        Orders newOrders = ordersList.get(position);
        Intent intent = new Intent(Orderdashboard.this,RestaurantOrder.class);
        intent.putExtra("orderno",newOrders.getOrderNo());
        startActivity(intent);
    }

    @Override
    public void deleteOnClick(View v, int position) {
        Orders newOrders = ordersList.get(position);
        newOrders.setStatus("pending");
        ordersList.clear();
        databaseReference.child(newOrders.getOrderNo()).removeValue();
        retrieve();
    }

    public void deliveryStatus(View v)
    {
        Intent i = new Intent(Orderdashboard.this,Delivery.class);
        startActivity(i);
    }
}