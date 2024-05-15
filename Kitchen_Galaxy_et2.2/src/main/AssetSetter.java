package main;


import objects.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new Ob_Dish(gp);
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[7] = new Ob_MakeBurger(gp);
        gp.obj[7].worldX = 15 * gp.tileSize;
        gp.obj[7].worldY = 9 * gp.tileSize-3;

        /*gp.obj[2] = new Ob_Burger();
        gp.obj[2].worldX = 13 * gp.tileSize;
        gp.obj[2].worldY = 9 * gp.tileSize;*/

        gp.obj[4] = new Ob_Salad(gp);
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 13 * gp.tileSize-4;

        gp.obj[5] = new Ob_Steak(gp);
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize-4;

        gp.obj[6] = new Ob_Bread(gp);
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize-3;

    }
    public void setAgainConsumablesOnMap(){
        gp.obj[0] = new Ob_Dish(gp);
        gp.obj[0].worldX = 13 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[7] = new Ob_MakeBurger(gp);
        gp.obj[7].worldX = 15 * gp.tileSize;
        gp.obj[7].worldY = 9 * gp.tileSize-3;

        gp.obj[4] = new Ob_Salad(gp);
        gp.obj[4].worldX = 13 * gp.tileSize;
        gp.obj[4].worldY = 13 * gp.tileSize-4;

        gp.obj[5] = new Ob_Steak(gp);
        gp.obj[5].worldX = 11 * gp.tileSize;
        gp.obj[5].worldY = 13 * gp.tileSize-4;

        gp.obj[6] = new Ob_Bread(gp);
        gp.obj[6].worldX = 12 * gp.tileSize;
        gp.obj[6].worldY = 9 * gp.tileSize-3;
    }
    public void makeBurger() {
        if (gp.player.dish_cnt > 0 && gp.player.salad_cnt > 0 && gp.player.steak_cnt > 0 && gp.player.bread_cnt > 0) {

            if (gp.obj[1] != null) {
                gp.obj[1] = new Ob_Burger(gp);
                gp.obj[1].worldX = 16 * gp.tileSize;
                gp.obj[1].worldY = 6 * gp.tileSize;
            } else {
                gp.obj[2] = new Ob_Burger(gp);
                gp.obj[2].worldX = 14 * gp.tileSize;
                gp.obj[2].worldY = 6 * gp.tileSize;


                gp.player.dish_cnt--;
                gp.player.salad_cnt--;
                gp.player.steak_cnt--;
                gp.player.bread_cnt--;

            }
        }
    }
    public void spawnShip(){

            gp.obj[8] = new Ob_Ship(gp);
            gp.obj[8].worldX = 23 * gp.tileSize;
            gp.obj[8].worldY = 9 * gp.tileSize;

    }

}
