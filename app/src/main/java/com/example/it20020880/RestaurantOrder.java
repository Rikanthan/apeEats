package com.example.it20020880;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class RestaurantOrder extends AppCompatActivity {
    String orderNo = "";
    TextView resOrderNo, resFoodName, resCustomerName,resCustomerPhone ,resCustomerAddress;
    ImageView foodImage;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_order);
        resOrderNo = findViewById(R.id.res_orderno);
        resFoodName = findViewById(R.id.res_foodname);
        resCustomerName = findViewById(R.id.res_customerName);
        resCustomerPhone = findViewById(R.id.res_customerPhone);
        resCustomerAddress = findViewById(R.id.res_customerAddress);
        foodImage = findViewById(R.id.food_image);
        orderNo = getIntent().getStringExtra("orderno");
        reference = FirebaseDatabase.getInstance()
                .getReference("McLJJXPnv3UNRjHyXGNZSBVe7lu2").child("Products");
        showOrder();
    }
    public void showOrder()
    {
        reference
                .child(orderNo)
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if(snapshot.exists())
                                {
                                    Products products = snapshot.getValue(Products.class);
                                    resOrderNo.setText(products.getpID());
                                    resFoodName.setText(products.getpFoodname());
                                    resCustomerName.setText(products.getpDeliveryAvailable());
                                    resCustomerPhone.setText(products.getpDescription());
                                    resCustomerAddress.setText(products.getpPrice());
                                    Picasso
                                            .get()
                                            .load(products.getpImage())
                                            .into(foodImage);

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        }
                );
    }

    public void delivered(View v)
    {
        Intent intent = new Intent(RestaurantOrder.this,DetailsOfDelivery.class);
        intent.putExtra("orderno",orderNo);
        startActivity(intent);
    }
}