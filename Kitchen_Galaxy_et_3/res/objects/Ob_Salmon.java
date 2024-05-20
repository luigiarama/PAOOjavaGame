package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_Salmon extends MyObjects{
    GamePanel gp;
    public Ob_Salmon(GamePanel gp) {
        this.gp = gp;

        name = "salmon_dish";
        try{

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/salmon_dish.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
