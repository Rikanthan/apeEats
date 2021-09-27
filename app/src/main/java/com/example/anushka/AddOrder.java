package com.example.anushka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddOrder extends AppCompatActivity {
    FirebaseDatabase database;
DatabaseReference databaseReference;
EditText orderno;
EditText ordername;
EditText orderStatus;
Orders orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        ordername = (EditText)findViewById(R.id.addorderName);
        orderno = (EditText)findViewById(R.id.addorderNo);
        orderStatus = (EditText)findViewById(R.id.addorderstatus);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Orders");
        orders = new Orders();
    }
    public void add(View v)
    {
        orders.setOrderName(ordername.getText().toString().trim());
        orders.setOrderNo(orderno.getText().toString().trim());
        orders.setStatus("pending");
        orders.setCustomerAddress("Colombo");
        orders.setCustomerName("Anushka");
        orders.setCustomerPhone("0735435705");
        databaseReference.child(orderno.getText().toString().trim()).setValue(orders);
    }
}