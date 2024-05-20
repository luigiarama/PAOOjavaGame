package objects;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Ob_MakeSalmon extends MyObjects{
    GamePanel gp;
    public Ob_MakeSalmon(GamePanel gp) {
        this.gp = gp;

        name = "makeSalmon";
        try{

            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/table_salmon.png")));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
