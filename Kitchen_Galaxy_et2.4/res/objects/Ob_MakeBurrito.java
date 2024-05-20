package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_MakeBurrito extends MyObjects{
    GamePanel gp;
    public Ob_MakeBurrito(GamePanel gp){
        this.gp = gp;

        name = "makeBurrito";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/table_burrito.png")));
          //  uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
