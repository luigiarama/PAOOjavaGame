package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Salad extends MyObjects{
    GamePanel gp;
    public Ob_Salad(GamePanel gp){
        this.gp=gp;
    name = "salad";
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/objects/Lettuce.png"));
        uTool.scaleImage(image, gp.tileSize,gp.tileSize);
    }catch(IOException e){
        e.printStackTrace();
    }
}
}
