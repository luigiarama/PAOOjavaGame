package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Dish extends MyObjects{
    public Ob_Dish(){
        name = "Dish";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/dish.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }
}
