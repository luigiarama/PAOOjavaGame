package main;

import objects.Ob_Burger;
import objects.Ob_Dish;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new Ob_Dish();
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;
        gp.obj[1] = new Ob_Burger();
        gp.obj[1].worldX = 9 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize;
    }
}
