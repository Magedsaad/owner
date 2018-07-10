package com.trying.developing.owner.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trying.developing.owner.R;
import com.trying.developing.owner.model.Order;
import com.trying.developing.owner.ui.OrderDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developing on 7/10/2018.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {

    List<Order> data=new ArrayList<>();

    public OrderAdapter(List<Order> data) {
        this.data = data;
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);

        return new OrderHolder(view);
    }

    @Override
    public void onBindViewHolder(OrderHolder holder, int position) {
        Order order=data.get(position);
        holder.address_Text.setText(order.getAddress());
        holder.status_Text.setText(order.getStatus());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class OrderHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView address_Text,status_Text;


        public OrderHolder(View itemView) {
            super(itemView);
            address_Text=(TextView) itemView.findViewById(R.id.address_id);
            status_Text=(TextView) itemView.findViewById(R.id.status_id);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(view.getContext(), OrderDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("key",data.get(getAdapterPosition())
            .key);
            bundle.putString("status",data.get(getAdapterPosition())
                    .status);
            bundle.putString("address",data.get(getAdapterPosition())
                    .address);
            bundle.putString("details",data.get(getAdapterPosition())
                    .details);
            bundle.putDouble("lat",data.get(getAdapterPosition())
                    .lat);
            bundle.putDouble("lng",data.get(getAdapterPosition())
                    .lng);
            i.putExtras(bundle);
            view.getContext().startActivity(i);
        }
    }
}
