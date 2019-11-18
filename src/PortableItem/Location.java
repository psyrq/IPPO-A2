package PortableItem;

public class Location {

    private int xAxis;
    private int yAxis;
    private String locatedImage;

    public Location(int xAxis, int yAxis, String locatedImage) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.locatedImage = locatedImage;
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

    public String getLocatedImage() {
        return locatedImage;
    }

    public void setLocatedImage(String locatedImage) {
        this.locatedImage = locatedImage;
    }
}
