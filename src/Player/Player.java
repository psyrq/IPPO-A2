package Player;

import PortableItem.Item;
import Room.*;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Item> heldItems;
    private Wall faceTo;
    private Room currentRoom;

    public Player(Wall faceTo) {
        this.faceTo = faceTo;
        heldItems = new ArrayList<>();
    }

    //getter and setter for heldItems
    public List<Item> getHeldItems() {
        return heldItems;
    }

    private void addHeldItems(Item item) {
        heldItems.add(item);
    }

    private void removeHeldItems(Item item) {

        for (int i = 0; i < heldItems.size(); i++) {
            if (item.getName().equals(heldItems.get(i).getName())) {
                heldItems.remove(i);
                break;
            }
        }
//        System.out.println("held items: " + heldItems.size());
//        heldItems.removeIf(currentItem -> currentItem.equals(item));
    }

    //getter and setter for wall faced to
    public Wall getFaceTo() {
        return faceTo;
    }

    public void setFaceTo(Wall faceTo) {
        this.faceTo = faceTo;
    }

    //getter and setter for current room
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void pickUp(Item item) {
        faceTo.removeItem(item);
        addHeldItems(item);
    }

    public void putDown(Item item) {
        faceTo.addItem(item);
        removeHeldItems(item);
    }
}
