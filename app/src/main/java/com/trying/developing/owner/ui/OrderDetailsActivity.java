package com.trying.developing.owner.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.trying.developing.owner.R;
import com.trying.developing.owner.model.Order;
import com.trying.developing.owner.model.Order2;

public class OrderDetailsActivity extends AppCompatActivity {

    private DatabaseReference mOrderDatabase;
    private FirebaseDatabase FDB;
    String key,statuss,address,details;
    Double lng,lat;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView address_details,details_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        FDB = FirebaseDatabase.getInstance();
        Bundle b = getIntent().getExtras();
        key = b.getString("key");
        statuss = b.getString("status");
        address = b.getString("address");
        details=b.getString("details");
        lat=b.getDouble("lat");
        lng=b.getDouble("lng");



        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);
        address_details=(TextView) findViewById(R.id.address_details_id);
        details_detail=(TextView) findViewById(R.id.details_detail_id);

        Log.e("keys", key + " " + statuss + " " + address+" "+details);

        address_details.setText("address: "+address);
        details_detail.setText("details: "+details);

        mOrderDatabase = FDB.getReference().child("order").child(key);



    }
    public void response(View view){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String status=radioButton.getText().toString();
        Order order=new Order(lat,lng,details,address,status);
        mOrderDatabase.setValue(order);
        Intent intent=new Intent(OrderDetailsActivity.this,MainActivity.class);
        startActivity(intent);
        radioButton.setSelected(status.contentEquals(statuss));






    }
}
