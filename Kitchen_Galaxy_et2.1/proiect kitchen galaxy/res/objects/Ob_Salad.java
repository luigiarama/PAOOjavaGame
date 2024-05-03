package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Salad extends MyObjects{
    public Ob_Salad(){
    name = "salad";
    try {
        image = ImageIO.read(getClass().getResourceAsStream("/objects/Lettuce.png"));

    }catch(IOException e){
        e.printStackTrace();
    }
}
}
