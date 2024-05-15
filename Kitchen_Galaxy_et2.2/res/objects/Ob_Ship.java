package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Ship extends MyObjects{
    GamePanel gp;
    public Ob_Ship(GamePanel gp){
        this.gp = gp;
        name = "ship";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objects/space_ship.png"));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
