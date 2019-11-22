package Controller;

import Player.Player;
import PortableItem.Item;
import PortableItem.Location;
import Room.Room;
import Room.Wall;
import Utils.ReadUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    /*
    public variables declaration
     */

    public ImageView mainImageView;
    public AnchorPane mainScreen;
    public GridPane root;
    public TextField numOfBasket;

    /*
    private variables declaration
     */

    private List<Room> allRooms;
    private List<ImageView> itemsView;

    private Player player;

    private int wallIndex = 0;
    private boolean pickup;
    private boolean putDown;

    private final String BASKET = "basket.png";

//    private AnchorPane secondScreen;

    /*
    public functions
     */

    public void initialize() {

        loadAllRooms();
        resetPickPut();

        itemsView = new ArrayList<>();

        player = new Player(allRooms.get(0).getWallList().get(0));
        player.setCurrentRoom(allRooms.get(0));

        Thread thread = new Thread(() -> {

            Runnable updater = this::mouseListener;

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
                Platform.runLater(updater);
                numOfBasket();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    public void start() {
        Image image = player.getCurrentRoom().getWallList().get(wallIndex).getWallImage();
        setFaceTo(player.getCurrentRoom().getWallList().get(wallIndex));
        setMainImageView(image, player.getCurrentRoom().getWallList().get(wallIndex));
    }

    public void pickUpBasket() {
        pickup = false;
        putDown = true;

    }

    public void numOfBasket() {

        int num = 0;

        List<Item> itemList = player.getHeldItems();
        if (itemList.size() != 0) {
            for (Item item : itemList) {
                if (item.getName().equals("basket")) {
                    num += 1;
                }
            }
        }

        numOfBasket.setText(String.valueOf(num));
    }

    public void moveForward() {

        String faceTo = player.getFaceTo().getDoorTo();

        if (faceTo != null) {
            for (Room room : allRooms) {
                if (room.getName().equals(faceTo)) {
                    player.setCurrentRoom(room);
                    break;
                }
            }
        } else {
            System.out.println("no available room in the front!");
        }
    }

    public void turnLeft() {

        if (wallIndex == 3) wallIndex = 0;
        else wallIndex += 1;

        Image image = player.getCurrentRoom().getWallList().get(wallIndex).getWallImage();
        setFaceTo(player.getCurrentRoom().getWallList().get(wallIndex));
        setMainImageView(image, player.getCurrentRoom().getWallList().get(wallIndex));
    }

    public void turnBackward() {

        if (wallIndex > 1) wallIndex -= 2;
        else wallIndex += 2;

        Image image = player.getCurrentRoom().getWallList().get(wallIndex).getWallImage();
        setFaceTo(player.getCurrentRoom().getWallList().get(wallIndex));
        setMainImageView(image, player.getCurrentRoom().getWallList().get(wallIndex));
    }

    public void turnRight() {

        if (wallIndex == 0) wallIndex = 3;
        else wallIndex -= 1;

        Image image = player.getCurrentRoom().getWallList().get(wallIndex).getWallImage();
        setFaceTo(player.getCurrentRoom().getWallList().get(wallIndex));
        setMainImageView(image, player.getFaceTo());
    }

    /*
    private functions
     */

    private void mouseListener() {

        mainScreen.setOnMouseClicked(event -> {

            double mouseX = event.getSceneX();
            double mouseY = event.getSceneY();

            if (clickOnImageView(mouseX, mouseY)) {

                if (pickup && !putDown) {

                    System.out.println("x: " + mouseX + "\ty: " + mouseY);

                    for (int i = 0; i < player.getFaceTo().getItems().size(); i++) {

                        Item item = player.getFaceTo().getItems().get(i);
                        if (clickOnItem(mouseX, mouseY, item.getLocation())) {
                            player.pickUp(item);
                            displayItems(player.getFaceTo());
                            System.out.println("get a new item");
                        }
                    }

                    System.out.println("number of items remained: " + player.getFaceTo().getItems().size());
                }

                else if (!pickup && putDown) {
                    Image image = getImage(BASKET);
                    Location location = new Location((int)mouseX, (int)mouseY);
                    Item item = new Item(location, image);
                    player.putDown(item);
                    displayItems(player.getFaceTo());
                    System.out.println("put down a new item");
                    System.out.println("number of items remained: " + player.getFaceTo().getItems().size());
                }
            }
        });
    }

    private void resetPickPut() {
        pickup = true;
        putDown = false;
    }

    private boolean clickOnImageView(double x, double y) {
        return x <= 447 && x >= 15 && y <= 596 && y >= 20;
    }

    private boolean clickOnItem(double x, double y, Location location) {
        return x >= location.getxAxis() && x <= location.getxAxis() + 50 && y >= location.getyAxis() && y <= location.getyAxis() + 50;
    }

    private void displayItems(Wall wall) {

        mainScreen.getChildren().removeAll(itemsView);
        itemsView.clear();

        List<Item> items = wall.getItems();
        if (items.size() != 0) {

            for (Item item : items) {

                Image image = item.getImage();
                Location location = item.getLocation();

                ImageView itemView = new ImageView();
                itemView.setImage(image);
                itemView.setX(location.getxAxis());
                itemView.setY(location.getyAxis());
                itemView.setFitWidth(50);
                itemView.setFitHeight(50);

                itemsView.add(itemView);
            }
            mainScreen.getChildren().addAll(itemsView);

        }
    }

    private void setFaceTo(Wall faceTo) {
        player.setFaceTo(faceTo);
    }

    private void setMainImageView(Image image, Wall wall) {
        mainImageView.setImage(image);
        displayItems(wall);
    }

    private Image getImage(String imageName) {
        String temp = "Resources/";
        String path = temp + imageName;
        return new Image(path);
    }

    private String getContents() {

        String dir = getRootPath();
        String temp = "/src/Resources/";
        String fileName = "initialize.json";
        String finalPath = dir + temp + fileName;

        return ReadUtils.readJsonFile(finalPath);
    }

    /*
    load all rooms and walls resources from data file
     */
    private void loadAllRooms() {

        allRooms = new ArrayList<Room>();

        /*
        load all into a string
         */
        String contents = getContents();

        /*
        turn string into object
         */
        JSONObject overviews = JSON.parseObject(contents);

        /*
        get all room objects
         */
        JSONArray rooms = overviews.getJSONArray("room");

        for (Object room : rooms) {

            /*
            load room recursively
             */
            JSONObject currentRoom = (JSONObject) room;

            String name = currentRoom.getString("name");
            JSONArray walls = currentRoom.getJSONArray("wall");

            /*
            all the walls in one room
             */
            List<Wall> allWalls = new ArrayList<Wall>();

            for(Object wall : walls) {

                /*
                load wall recursively
                 */
                JSONObject currentWall = (JSONObject) wall;
                String currentWallImage = currentWall.getString("wall-image");

                /*
                whether the wall contains a door to a new room
                 */
                String doorTo;
                boolean hasDoor = currentWall.getBoolean("has-door");

                if (hasDoor) doorTo = currentWall.getString("door-to");
                else doorTo = null;

                /*
                all the items placed in current wall
                 */
                List<Item> loadItems = new ArrayList<>();

                if (currentWall.containsKey("items")) {

                    JSONArray items = currentWall.getJSONArray("items");

                    for (Object item : items) {
                        /*
                        load items recursively
                         */
                        JSONObject getItem = (JSONObject) item;
                        String resource = getItem.getString("item-image");

                        int xAxis = getItem.getInteger("x-axis");
                        int yAxis = getItem.getInteger("y-axis");

                        Image image = getImage(resource);
                        Location location = new Location(xAxis, yAxis);
                        String itemName = resource.split("\\.")[0];

                        Item currentItem = new Item(location, image);
                        currentItem.setName(itemName);
                        loadItems.add(currentItem);
                    }
                }

                Image image = getImage(currentWallImage);
                Wall getWall = new Wall(image, doorTo, loadItems);
                allWalls.add(getWall);
            }

            Room getRoom = new Room(name, allWalls);
            allRooms.add(getRoom);
        }
    }

    private String getRootPath() {

        try {
            File directory = new File("");
            return directory.getCanonicalPath();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
