package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_MakeBurger extends MyObjects{
    GamePanel gp;
    public Ob_MakeBurger(GamePanel gp){
        this.gp = gp;

        name = "makeBurger";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/Hammer.png")));
            uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
