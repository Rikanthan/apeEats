package com.example.anushka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class DetailsOfDelivery extends AppCompatActivity {
    String orderNo = "";
    DatabaseReference reference;
    CheckBox checkBox;
    Orders orders;
    TextView resOrderNo, resFoodName, resCustomerName,resCustomerPhone ,resCustomerAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_delivery);
        resOrderNo = findViewById(R.id.del_orderno);
        resFoodName = findViewById(R.id.del_foodname);
        resCustomerName = findViewById(R.id.del_customerName);
        resCustomerPhone = findViewById(R.id.del_customerPhone);
        resCustomerAddress = findViewById(R.id.del_customerAddress);
        orderNo = getIntent().getStringExtra("orderno");
        checkBox = findViewById(R.id.checkBox);
        orders = new Orders();
        reference = FirebaseDatabase.getInstance().getReference("Orders");
        showDetails();
    }
    public void showDetails()
    {
        reference
                .child(orderNo)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    orders = snapshot.getValue(Orders.class);
                                    resOrderNo.setText(orders.getOrderNo());
                                    resFoodName.setText(orders.getOrderName());
                                    resCustomerName.setText(orders.getCustomerName());
                                    resCustomerPhone.setText(orders.getCustomerPhone());
                                    resCustomerAddress.setText(orders.getCustomerAddress());
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        }
                );

    }
    public void deliveryStatus(View v)
    {
        if(checkBox.isChecked())
        {
            orders.setStatus("Finished");
            reference.child(orderNo).setValue(orders);
        }
        Intent intent = new Intent(DetailsOfDelivery.this,Delivery.class);
        startActivity(intent);
    }
}