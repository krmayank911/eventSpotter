package com.buggyarts.eventspotter.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.buggyarts.eventspotter.AppUtils;
import com.buggyarts.eventspotter.R;
import com.buggyarts.eventspotter.adapters.EventListAdapter;
import com.buggyarts.eventspotter.models.Event;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventListActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout btnCreateNewEvent;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dBEvents;

    RecyclerView eventRV;
    RecyclerView.LayoutManager layoutManager;
    EventListAdapter eventListAdapter;

    ArrayList<Event> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        btnCreateNewEvent = findViewById(R.id.btn_create_new_event);
        btnCreateNewEvent.setOnClickListener(this);

        eventRV = findViewById(R.id.event_list_rv);

        layoutManager = new LinearLayoutManager(this);
        eventRV.setLayoutManager(layoutManager);

        eventListAdapter = new EventListAdapter(this,events);
        eventRV.setAdapter(eventListAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dBEvents = firebaseDatabase.getReference("events").child(AppUtils.getEsUser().getUid());

        dBEvents.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Event event = dataSnapshot.getValue(Event.class);
                events.add(event);
                eventListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_create_new_event){
            Intent intent = new Intent(EventListActivity.this,CreateEventActivity.class);
            startActivity(intent);
        }
    }


}
