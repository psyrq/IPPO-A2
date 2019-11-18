package Room;

import java.awt.image.BufferedImage;

public class Wall {

    private BufferedImage wallImage;

    public Wall(BufferedImage wallImage) {
        this.wallImage = wallImage;
    }

    public BufferedImage getWallImage() {
        return wallImage;
    }
}
