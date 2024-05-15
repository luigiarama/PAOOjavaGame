package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Dish extends MyObjects{
    GamePanel gp;
    public Ob_Dish(GamePanel gp){
        this.gp = gp;
        name = "Dish";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/dish.png"));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
