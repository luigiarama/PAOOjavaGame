package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Bread extends MyObjects{
    GamePanel gp;
    public Ob_Bread(GamePanel gp){
        this.gp = gp;
        name = "bread";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/07_bread.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);


        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
