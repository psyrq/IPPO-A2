package Room;

import java.util.List;

public class Room {

    private String name;
    private List<Wall> wallList;

    public Room(String name, List<Wall> wallList) {
        this.name = name;
        this.wallList = wallList;
    }

    public List<Wall> getWallList(){
        return wallList;
    }

    public String getName() {
        return name;
    }
}
