package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Finale extends MyObjects{
    GamePanel gp;
    public Ob_Finale(GamePanel gp){
        this.gp = gp;
        name = "finale";
        scaleX = 2;
        scaleY = 1;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/finale.png")));
            uTool.scaleImage(image, gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
