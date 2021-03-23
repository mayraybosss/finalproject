package com.shani.domain;

public class Lamp {

    private Long id;
    private String lampCountry;
    private boolean work;
    private String roomName;

    public Lamp(Long id, String lampCountry, boolean work, String roomName) {
        this.id = id;
        this.lampCountry = lampCountry;
        this.work = work;
        this.roomName = roomName;
    }

    public Lamp(String lampCountry, boolean work, String roomName) {
        this.lampCountry = lampCountry;
        this.work = work;
        this.roomName = roomName;
    }

    public Lamp() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLampCountry() {
        return lampCountry;
    }

    public void setLampCountry(String lampCountry) {
        this.lampCountry = lampCountry;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Lamp{" +
                "id=" + id +
                ", lampCountry='" + lampCountry + '\'' +
                ", work=" + work +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
