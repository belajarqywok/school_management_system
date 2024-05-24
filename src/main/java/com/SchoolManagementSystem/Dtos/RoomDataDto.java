package com.SchoolManagementSystem.Dtos;

public class RoomDataDto {
    public RoomDataDto() {}

    /**
     *  Room ID
     */
    private int roomId;

    // Room ID Setter
    public void setRoomId(int id) {
        this.roomId = id;
    }

    // Room ID Getter
    public int getRoomId() {
        return this.roomId;
    }


    /* 
        Room Code
    */
    private String roomCode;

    // Room Code Setter
    public void setRoomCode(String code) {
        this.roomCode = code;
    }

    // Room Code Getter
    public String getRoomCode() {
        return roomCode;
    }


    /* 
        Room Name
    */
    private String roomName;

    // Room Name Setter
    public void setRoomName(String name) {
        this.roomName = name;
    }

    // Room Name Getter
    public String getRoomName() {
        return roomName;
    }


    /* 
        Room Location
    */
    private String roomLocation;

    // Room Location Setter
    public void setRoomLocation(String location) {
        this.roomLocation = location;
    }

    // Room Location Getter
    public String getRoomLocation() {
        return roomLocation;
    }


    /* 
        Room Wide
    */
    private int roomWide;

    // Room Wide Setter
    public void setRoomWide(int wide) {
        this.roomWide = wide;
    }

    // Room Wide Getter
    public int getRoomWide() {
        return roomWide;
    }


    /* 
        Room Capacity
    */
    private int roomCapacity;

    // Room Capacity Setter
    public void setRoomCapacity(int capacity) {
        this.roomCapacity = capacity;
    }

    // Room Capacity Getter
    public int getRoomCapacity() {
        return roomCapacity;
    }


    /* 
        Room Price
    */
    private int roomPrice;

    // Room Price Setter
    public void setRoomPrice(int price) {
        this.roomPrice = price;
    }

    // Room Price Getter
    public int getRoomPrice() {
        return roomPrice;
    }


    /* 
        Room Note
    */
    private String roomNote;

    // Room Note Setter
    public void setRoomNote(String note) {
        this.roomNote = note;
    }

    // Room Note Getter
    public String getRoomNote() {
        return roomNote;
    }
}
