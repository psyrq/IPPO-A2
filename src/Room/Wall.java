package Room;

import PortableItem.Item;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Wall {

    private Image wallImage;
    private String doorTo;
    private List<Item> items;

    public Wall(Image wallImage, String doorTo) {
        this.wallImage = wallImage;
        this.doorTo = doorTo;
        items = new ArrayList<>();
    }

    public Wall(Image wallImage, String doorTo, List<Item> items) {
        this.wallImage = wallImage;
        this.doorTo = doorTo;
        this.items = items;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public String getDoorTo() {
        return doorTo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.removeIf(currentItem -> currentItem.equals(item));
    }

    public void setItem(Item item) {
        if (items.contains(item)) {
            for (Item value : items) {
                if (item.equals(value)) {
                    value.setImage(null);
                }
            }
        }
    }
}