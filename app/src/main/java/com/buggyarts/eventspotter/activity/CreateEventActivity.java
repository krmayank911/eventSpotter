package com.buggyarts.eventspotter.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.buggyarts.eventspotter.AppUtils;
import com.buggyarts.eventspotter.R;
import com.buggyarts.eventspotter.models.Event;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

    Event mEvent;

    TextInputEditText eventNameText;
    TextInputEditText eventOrganizerNameText;
    TextInputEditText eventOrganizerContactText;
    TextInputEditText eventDescriptionText;
    TextInputEditText eventPlaceText;
    TextInputEditText eventScheduleText;
    FrameLayout btnCreateEvent;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference dBEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        eventNameText = findViewById(R.id.nameField);
        eventOrganizerNameText = findViewById(R.id.organizerField);
        eventDescriptionText = findViewById(R.id.descriptionField);
        eventOrganizerContactText = findViewById(R.id.contactField);
        eventScheduleText = findViewById(R.id.scheduleField);
        eventPlaceText = findViewById(R.id.placeField);
        btnCreateEvent = findViewById(R.id.btn_create_event);

        btnCreateEvent.setOnClickListener(this);

        mEvent = new Event();

        firebaseDatabase = FirebaseDatabase.getInstance();
        dBEvents = firebaseDatabase.getReference("events");

    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_create_event){
            if(validate()){
                mEvent.setEventName(eventNameText.getText().toString());
                mEvent.setEventOrganizerName(eventOrganizerNameText.getText().toString());
                mEvent.setEventDescription(eventDescriptionText.getText().toString());
                String phoneNumber = "+91" + eventOrganizerContactText.getText().toString();
                mEvent.setEventOrganizerContact(phoneNumber);
                mEvent.setEventSchedule(eventScheduleText.getText().toString());
                mEvent.setEventPlace(eventPlaceText.getText().toString());

                Toast.makeText(this,"Event Posted",Toast.LENGTH_SHORT).show();

                dBEvents.child(AppUtils.getEsUser().getUid()).push().setValue(mEvent);
                this.finish();
            }
        }
    }

    private Boolean validate(){

        if(eventNameText.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.error_event_name),Toast.LENGTH_SHORT).show();
            return false;
        }

        if(eventOrganizerNameText.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.error_event_organizer_name),Toast.LENGTH_SHORT).show();
            return false;
        }

        if(eventDescriptionText.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.error_event_description),Toast.LENGTH_SHORT).show();
            return false;
        }

        if(eventOrganizerContactText.getText().toString().length() == 0
                || eventOrganizerContactText.getText().toString().length() != 10){
            Toast.makeText(this,getResources().getString(R.string.error_event_contact),Toast.LENGTH_SHORT).show();
            return false;
        }

        if(eventScheduleText.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.error_event_schedule),Toast.LENGTH_SHORT).show();
            return false;
        }

        if(eventPlaceText.getText().toString().length() == 0){
            Toast.makeText(this,getResources().getString(R.string.error_event_place),Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


}
