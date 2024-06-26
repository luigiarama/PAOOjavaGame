package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Ship2 extends MyObjects{
    GamePanel gp;
    public Ob_Ship2(GamePanel gp){
        this.gp = gp;
        name = "ship2";
        scaleX = 1;
        scaleY = 1;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/space_ship.png")));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}