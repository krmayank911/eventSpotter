package com.buggyarts.eventspotter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.buggyarts.eventspotter.R;
import com.buggyarts.eventspotter.models.Event;

import java.util.ArrayList;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventVH>{

    ArrayList<Event> events;
    Context mContext;

    public EventListAdapter(Context context, ArrayList<Event> events){
        this.events = events;
        this.mContext = context;
    }

    @NonNull
    @Override
    public EventVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_event,parent,false);
        return new EventVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventVH holder, int position) {
        Event e = events.get(position);

        holder.eventName.setText(e.getEventName());
        holder.eventDescription.setText(e.getEventDescription());
        holder.eventOrganizerName.setText(e.getEventOrganizerName());
        holder.eventOrganizerContact.setText(e.getEventOrganizerContact());
        holder.eventSchedule.setText(e.getEventSchedule());
        holder.eventPlace.setText(e.getEventPlace());
    }

    @Override
    public int getItemCount() {
        if(events.size() != 0){
            return events.size();
        }
        return 0;
    }

    public class EventVH extends RecyclerView.ViewHolder{

        TextView eventName;
        TextView eventOrganizerName;
        TextView eventDescription;
        TextView eventOrganizerContact;
        TextView eventSchedule;
        TextView eventPlace;

        public EventVH(View itemView) {
            super(itemView);

            eventName = itemView.findViewById(R.id.eventName);
            eventDescription = itemView.findViewById(R.id.eventDescription);
            eventOrganizerName = itemView.findViewById(R.id.eventOrganizerName);
            eventOrganizerContact = itemView.findViewById(R.id.eventOrganizerContact);
            eventSchedule = itemView.findViewById(R.id.eventSchedule);
            eventPlace = itemView.findViewById(R.id.eventPlace);

        }
    }
}
