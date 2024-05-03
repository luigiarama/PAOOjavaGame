package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Ob_Bread extends MyObjects{
    public Ob_Bread(){
        name = "bread";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/07_bread.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
