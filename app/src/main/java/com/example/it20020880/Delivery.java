package com.example.it20020880;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Delivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
    }
    public void pendingOrder(View v)
    {
        Intent intent = new Intent(Delivery.this,PendingOrders.class);
        startActivity(intent);
    }
    public void finishedOrder(View v)
    {
        Intent intent = new Intent(Delivery.this,FinishedOrders.class);
        startActivity(intent);
    }
}