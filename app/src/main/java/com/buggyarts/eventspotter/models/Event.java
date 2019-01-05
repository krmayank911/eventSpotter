package com.buggyarts.eventspotter.models;

public class Event {

    String eventName;
    String eventOrganizerName;
    String eventDescription;
    String eventSchedule;
    String eventPlace;
    String eventOrganizerContact;

    public Event(){}

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventOrganizerName() {
        return eventOrganizerName;
    }

    public void setEventOrganizerName(String eventOrganizerName) {
        this.eventOrganizerName = eventOrganizerName;
    }

    public String getEventSchedule() {
        return eventSchedule;
    }

    public void setEventSchedule(String eventSchedule) {
        this.eventSchedule = eventSchedule;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventOrganizerContact() {
        return eventOrganizerContact;
    }

    public void setEventOrganizerContact(String eventOrganizerContact) {
        this.eventOrganizerContact = eventOrganizerContact;
    }
}
