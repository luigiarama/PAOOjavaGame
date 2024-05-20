package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Chicken extends MyObjects{
    GamePanel gp;
    public Ob_Chicken(GamePanel gp){
        this.gp = gp;
        name = "chicken";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chicken.png")));
           // uTool.scaleImage(image, gp.tileSize, gp.tileSize);


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
