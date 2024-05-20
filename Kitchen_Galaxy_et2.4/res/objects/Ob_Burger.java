package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Burger extends MyObjects{
    GamePanel gp;
    public Ob_Burger(GamePanel gp){
        this.gp  = gp;
        name = "burger";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/burger_dish.png"));
//            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
