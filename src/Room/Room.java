package Room;

import PortableItem.Item;

import java.util.ArrayList;
import java.util.List;

public class Room {

    private List<Item> itemList;
    private List<Wall> wallList;

    public Room(List<Wall> wallList) {
        this.itemList = new ArrayList<>();
        this.wallList = wallList;
    }

    public Room(List<Item> itemList, List<Wall> wallList) {
        this.itemList = itemList;
        this.wallList = wallList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    public void removeItem(Item item) {
        itemList.removeIf(currentItem -> currentItem.equals(item));
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public List<Wall> getWallList(){
        return wallList;
    }
}
