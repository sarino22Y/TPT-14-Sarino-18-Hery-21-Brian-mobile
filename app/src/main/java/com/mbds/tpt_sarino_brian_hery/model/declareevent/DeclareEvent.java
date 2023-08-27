package com.mbds.tpt_sarino_brian_hery.model.declareevent;

import java.util.Date;

public class DeclareEvent {
    private int IdDeclareEvent;
    private String Title;
    private String Description;
    private String Department;
    private String CitizenId;
    private String EventOwnerId;
    private String DateEvent;

    public DeclareEvent() {
    }

    public DeclareEvent(String Title, String Description, String Department, String CitizenId, String EventOwnerId) {
        this.Title = Title;
        this.Description = Description;
        this.Department = Department;
        this.CitizenId = CitizenId;
        this.EventOwnerId = EventOwnerId;
        this.DateEvent = new Date().toString();
    }

    public int getIdDeclareEvent() {
        return IdDeclareEvent;
    }

    public void setIdDeclareEvent(int idDeclareEvent) {
        IdDeclareEvent = idDeclareEvent;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getCitizenId() {
        return CitizenId;
    }

    public void setCitizenId(String citizenId) {
        CitizenId = citizenId;
    }

    public String getEventOwnerId() {
        return EventOwnerId;
    }

    public void setEventOwnerId(String eventOwnerId) {
        EventOwnerId = eventOwnerId;
    }

    public String getDateEvent() {
        return DateEvent;
    }

    public void setDateEvent(String dateEvent) {
        DateEvent = dateEvent;
    }

    @Override
    public String toString() {
        return DateEvent + " " + Title;
    }
}
