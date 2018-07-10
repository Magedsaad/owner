package com.trying.developing.owner.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trying.developing.owner.R;
import com.trying.developing.owner.adapter.OrderAdapter;
import com.trying.developing.owner.model.Order;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<String> keyarray=new ArrayList<String>();
    private DatabaseReference mOrderDatabase;
    private FirebaseDatabase FDB;
    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private List<Order> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView) findViewById(R.id.recycler_id);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),LinearLayoutManager.VERTICAL));
        FDB=FirebaseDatabase.getInstance();

        list=new ArrayList<>();
        orderAdapter=new OrderAdapter(list);
        getOrder();

    }

    public void getOrder(){

        mOrderDatabase=FDB.getReference().child("order").getRef();
        mOrderDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Order order=dataSnapshot.getValue(Order.class);
                order = new Order(order.lat,order.lng,order.details,order.address,order.status,dataSnapshot.getKey());

                list.add(order);
                orderAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(orderAdapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
