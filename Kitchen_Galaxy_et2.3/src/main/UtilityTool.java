package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {
    public int map;
    public int col;
    public int row;

    public BufferedImage scaleImage(BufferedImage original, int Width, int Height) {
        BufferedImage scaledImage = new BufferedImage(Width,Height,original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original,0,0,Width,Height,null);
        g2.dispose();
        return scaledImage;
    }
    public BufferedImage cropImage(BufferedImage spritesheet, int x, int y, int width, int height) {
        return spritesheet.getSubimage(x, y, width, height);
    }
}
