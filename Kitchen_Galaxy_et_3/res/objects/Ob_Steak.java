package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Steak extends MyObjects{
    GamePanel gp;
    public Ob_Steak(GamePanel gp){
        this.gp = gp;
        name = "steak";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/steak.png")));
           // uTool.scaleImage(image, gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
