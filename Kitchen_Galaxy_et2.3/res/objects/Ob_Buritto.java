package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Buritto extends MyObjects{
    GamePanel gp;
    public Ob_Buritto(GamePanel gp) {
        this.gp = gp;

        name = "burrito";
        try{

            image = ImageIO.read(getClass().getResourceAsStream("/objects/19_burrito_dish.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
