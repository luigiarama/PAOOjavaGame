package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Steak extends MyObjects{
    public Ob_Steak(){
        name = "steak";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/steak.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
