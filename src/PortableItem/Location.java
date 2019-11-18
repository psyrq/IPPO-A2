package PortableItem;

import Room.Room;

public class Location {

    private int xAxis;
    private int yAxis;
    private Room locatedRoom;

    public Location(int xAxis, int yAxis, Room locatedRoom) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.locatedRoom = locatedRoom;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public Room getLocatedRoom() {
        return locatedRoom;
    }

    public void setLocatedRoom(Room locatedRoom) {
        this.locatedRoom = locatedRoom;
    }
}
