package PortableItem;

public class Item {

    private Location location;
    private Resource resource;

    public Item(Location location, Resource resource) {
        this.location = location;
        this.resource = resource;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
