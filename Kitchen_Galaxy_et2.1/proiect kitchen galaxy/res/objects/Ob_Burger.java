package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Burger extends MyObjects{
    public Ob_Burger(){
        name = "burger";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/burger_dish.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
