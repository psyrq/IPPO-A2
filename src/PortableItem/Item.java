package PortableItem;

import javafx.scene.image.Image;

public class Item {

    private Location location;
    private Image image;

    private String name;

    public Item(Location location, Image image) {
        this.location = location;
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
