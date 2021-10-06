package com.example.it20020880;

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
        orders.setOrderName("Kothu");
        orders.setOrderNo("5");
        orders.setStatus("pending");
        orders.setCustomerAddress("Colombo");
        orders.setCustomerName("Anushka");
        orders.setCustomerPhone("0735435705");
        orders.setFoodImageUrl("https://media.istockphoto.com/photos/sri-lanka-traditional-street-food-kothu-kottu-picture-id1150648881?s=612x612");
        databaseReference.child(orderno.getText().toString().trim()).setValue(orders);
    }
}