package Player;

import Directions.Directions;
import PortableItem.Item;
import Room.Room;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Item> heldItems;
    private Directions direction;
    private Room currentRoom;

    public Player() {
        direction = Directions.NORTH;
        heldItems = new ArrayList<>();
    }

    //getter and setter for heldItems
    public List<Item> getHeldItems() {
        return heldItems;
    }

    public void addHeldItems(Item item) {
        heldItems.add(item);
    }

    public void removeHeldItems(Item item) {
        heldItems.removeIf(currentItem -> currentItem.equals(item));
    }

    //getter and setter for directions
    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }

    //getter and setter for current room
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void turn(Directions dir) {
        direction = dir;
    }

    public void pickUp(Item item) {
        currentRoom.removeItem(item);
        addHeldItems(item);
    }

    public void putDown(Item item) {
        currentRoom.addItem(item);
        removeHeldItems(item);
    }
}
