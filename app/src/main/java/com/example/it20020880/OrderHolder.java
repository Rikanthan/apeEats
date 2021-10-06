package com.example.it20020880;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class OrderHolder extends RecyclerView.Adapter<OrderHolder.ImageViewHolder> {
    private final Context mContext;
    private final List<Products> mProducts;
    private static OnItemClickListener mListener;
    public OrderHolder(Context context, List<Products> Carts) {
        mContext = context;
        mProducts = Carts;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.order_recycle, parent, false);
        return new ImageViewHolder(v);
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Products order = mProducts.get(position);
        holder.orderNo.setText( order.getpID());
        holder.orderName.setText(order.getpFoodname());
    }
    @Override
    public int getItemCount() {
        return mProducts.size();
    }
    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView orderNo,orderName,orderStatus;

        @Override
        public void onClick(View v) {
            if(mListener !=null)
            {
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(position);
                }
            }
        }

        public ImageViewHolder(View itemView) {
            super(itemView);
            orderNo = itemView.findViewById(R.id.orderno);
            orderName =itemView.findViewById(R.id.ordername);
            itemView.setOnClickListener(this);
        }

    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener= listener;
    }

}
