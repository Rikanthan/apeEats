package com.example.anushka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderDashboardHolder extends RecyclerView.Adapter<OrderDashboardHolder.ImageViewHolder> {

    private List<Orders> ordersList;
    public OrderDashboardHolderListener onClickListener;


    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public Button showDetails;
        public ImageView delete;
        public LinearLayout linearLayoutManager;

        public ImageViewHolder(View view) {
            super(view);
            linearLayoutManager = (LinearLayout)view.findViewById(R.id.showOrders);
            showDetails = view.findViewById(R.id.orderButton);
            delete = view.findViewById(R.id.orderDelete);
            linearLayoutManager.setOnClickListener(v -> onClickListener
                    .showDetailsOnClick(v, getAdapterPosition()));
            linearLayoutManager.setOnClickListener(v -> onClickListener
                    .deleteOnClick(v, getAdapterPosition()));
        }

    }

    public OrderDashboardHolder(List<Orders> FollowersList, OrderDashboardHolderListener listener) {
        this.ordersList = FollowersList;
        this.onClickListener = listener;

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_dashboard_recycle, parent, false);
        return new ImageViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ImageViewHolder holder, final int position) {
        Orders order = ordersList.get(position);
        holder.showDetails.setText("order "+order.getOrderNo());
        holder.showDetails.setOnClickListener(v -> {
            onClickListener.showDetailsOnClick(v,position);
        });
        holder.delete.setOnClickListener(v -> {
            onClickListener.deleteOnClick(v,position);
        });
    }

    public interface OrderDashboardHolderListener {

        void showDetailsOnClick(View v, int position);
        void deleteOnClick(View v, int position);

    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }
}

