package PortableItem;

import java.awt.image.BufferedImage;

public class Resource {

    private String pictureName;
    private BufferedImage sourceImage;

    public Resource(String pictureName, BufferedImage sourceImage) {
        this.pictureName = pictureName;
        this.sourceImage = sourceImage;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public BufferedImage getSourceImage() {
        return sourceImage;
    }

    public void setSourceImage(BufferedImage sourceImage) {
        this.sourceImage = sourceImage;
    }
}
