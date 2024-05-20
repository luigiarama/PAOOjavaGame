package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Fish extends MyObjects {
    GamePanel gp;
    public Ob_Fish(GamePanel gp) {
        this.gp = gp;

        name = "fish";
        try{

            image = ImageIO.read(getClass().getResourceAsStream("/objects/salmon.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
