package main;

import objects.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new Ob_Dish();
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;
        gp.obj[1] = new Ob_Burger();
        gp.obj[1].worldX = 14 * gp.tileSize;
        gp.obj[1].worldY = 9 * gp.tileSize-3;
        gp.obj[2] = new Ob_Burger();
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;
        gp.obj[3] = new Ob_Burger();
        gp.obj[3].worldX = 11 * gp.tileSize;
        gp.obj[3].worldY = 7 * gp.tileSize;

        gp.obj[4] = new Ob_Salad();
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 13 * gp.tileSize-4;

        gp.obj[5] = new Ob_Steak();
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize-4;

        gp.obj[6] = new Ob_Bread();
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize+2;
    }
}
