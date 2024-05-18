package main;


import objects.*;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        //LEVEL 1
        int mapNum = 0;
        gp.obj[mapNum][0] = new Ob_Dish(gp);
        gp.obj[mapNum][0].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][4] = new Ob_Salad(gp);
        gp.obj[mapNum][4].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 13 * gp.tileSize-4;

        gp.obj[mapNum][5] = new Ob_Steak(gp);
        gp.obj[mapNum][5].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 13 * gp.tileSize-4;

        gp.obj[mapNum][6] = new Ob_Bread(gp);
        gp.obj[mapNum][6].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 9 * gp.tileSize-3;

        gp.obj[mapNum][7] = new Ob_MakeBurger(gp);
        gp.obj[mapNum][7].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 9 * gp.tileSize-3;



        //LEVEL 2
        mapNum = 1;

        gp.obj[mapNum][0] = new Ob_Dish(gp);
        gp.obj[mapNum][0].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][4] = new Ob_Salad(gp);
        gp.obj[mapNum][4].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][5] = new Ob_Steak(gp);
        gp.obj[mapNum][5].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 11 * gp.tileSize-4;

        gp.obj[mapNum][6] = new Ob_Bread(gp);
        gp.obj[mapNum][6].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 11 * gp.tileSize-4;

        gp.obj[mapNum][7] = new Ob_MakeBurger(gp);
        gp.obj[mapNum][7].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][12] = new Ob_Lipie(gp);
        gp.obj[mapNum][12].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][12].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][13] = new Ob_Chicken(gp);
        gp.obj[mapNum][13].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][13].worldY = 5 * gp.tileSize-4;

        gp.obj[mapNum][10] = new Ob_MakeBurrito(gp);
        gp.obj[mapNum][10].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][10].worldY = 3 * gp.tileSize;

    }
    public void setAgainConsumablesOnMap(){
        //LEVEL 1
        int mapNum = 0;
        gp.obj[mapNum][0] = new Ob_Dish(gp);
        gp.obj[mapNum][0].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 13 * gp.tileSize;

        gp.obj[mapNum][4] = new Ob_Salad(gp);
        gp.obj[mapNum][4].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 13 * gp.tileSize-4;

        gp.obj[mapNum][5] = new Ob_Steak(gp);
        gp.obj[mapNum][5].worldX = 11 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 13 * gp.tileSize-4;

        gp.obj[mapNum][6] = new Ob_Bread(gp);
        gp.obj[mapNum][6].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 9 * gp.tileSize-3;

        gp.obj[mapNum][7] = new Ob_MakeBurger(gp);
        gp.obj[mapNum][7].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 9 * gp.tileSize-3;

        //LEVEL 2
        mapNum = 1;

        gp.obj[mapNum][0] = new Ob_Dish(gp);
        gp.obj[mapNum][0].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][0].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][4] = new Ob_Salad(gp);
        gp.obj[mapNum][4].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][4].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][5] = new Ob_Steak(gp);
        gp.obj[mapNum][5].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][5].worldY = 11 * gp.tileSize-4;

        gp.obj[mapNum][6] = new Ob_Bread(gp);
        gp.obj[mapNum][6].worldX = 15 * gp.tileSize;
        gp.obj[mapNum][6].worldY = 11 * gp.tileSize-4;

        gp.obj[mapNum][7] = new Ob_MakeBurger(gp);
        gp.obj[mapNum][7].worldX = 17 * gp.tileSize;
        gp.obj[mapNum][7].worldY = 8 * gp.tileSize;

        gp.obj[mapNum][10] = new Ob_MakeBurrito(gp);
        gp.obj[mapNum][10].worldX = 21 * gp.tileSize;
        gp.obj[mapNum][10].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][12] = new Ob_Lipie(gp);
        gp.obj[mapNum][12].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][12].worldY = 3 * gp.tileSize;

        gp.obj[mapNum][13] = new Ob_Chicken(gp);
        gp.obj[mapNum][13].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][13].worldY = 5 * gp.tileSize-4;

    }
    public void makeBurger() {
        gp.currentMap=0;
        if (gp.player.dish_cnt > 0 && gp.player.salad_cnt > 0 && gp.player.steak_cnt > 0 && gp.player.bread_cnt > 0 ) {

            if (gp.obj[gp.currentMap][1] != null) {
                gp.obj[gp.currentMap][1] = new Ob_Burger(gp);
                gp.obj[gp.currentMap][1].worldX = 16 * gp.tileSize;
                gp.obj[gp.currentMap][1].worldY = 6 * gp.tileSize;
            } else {
                gp.obj[gp.currentMap][2] = new Ob_Burger(gp);
                gp.obj[gp.currentMap][2].worldX = 13 * gp.tileSize;
                gp.obj[gp.currentMap][2].worldY = 6 * gp.tileSize - 3;


                gp.player.dish_cnt--;
                gp.player.salad_cnt--;
                gp.player.steak_cnt--;
                gp.player.bread_cnt--;

            }
        }
    }

    //LEVEL 2
    public void makeBurger2(){
        gp.currentMap=1;
            if (gp.player.dish_cnt > 0 && gp.player.salad_cnt > 0 && gp.player.steak_cnt > 0 && gp.player.bread_cnt > 0 ) {
                if (gp.obj[gp.currentMap][1] != null) {
                    gp.obj[gp.currentMap][1] = new Ob_Burger(gp);
                    gp.obj[gp.currentMap][1].worldX = 16 * gp.tileSize;
                    gp.obj[gp.currentMap][1].worldY = 6 * gp.tileSize;
                } else {

                    gp.obj[gp.currentMap][2] = new Ob_Burger(gp);
                    gp.obj[gp.currentMap][2].worldX = 20 * gp.tileSize;
                    gp.obj[gp.currentMap][2].worldY = 8 * gp.tileSize ;


                    gp.player.dish_cnt--;
                    gp.player.salad_cnt--;
                    gp.player.steak_cnt--;
                    gp.player.bread_cnt--;


                }
            }
    }

    public void makeBurrito(){
        gp.currentMap=1;
        if (gp.player.dish_cnt > 0 && gp.player.salad_cnt > 0 && gp.player.lipie_cnt > 0 && gp.player.chicken_cnt > 0 ) {
            if (gp.obj[gp.currentMap][11] != null) {
                gp.obj[gp.currentMap][11] = new Ob_Buritto(gp);
                gp.obj[gp.currentMap][11].worldX = 16 * gp.tileSize;
                gp.obj[gp.currentMap][11].worldY = 6 * gp.tileSize;
            } else {

                gp.obj[gp.currentMap][9] = new Ob_Buritto(gp);
                gp.obj[gp.currentMap][9].worldX = 23 * gp.tileSize;
                gp.obj[gp.currentMap][9].worldY = 6 * gp.tileSize;


                gp.player.dish_cnt--;
                gp.player.salad_cnt--;
                gp.player.lipie_cnt--;
                gp.player.chicken_cnt--;

            }
        }
    }
    public void spawnShip(){
            int mapNum = 0;

            gp.obj[mapNum][8] = new Ob_Ship(gp);
            gp.obj[mapNum][8].worldX = 23 * gp.tileSize;
            gp.obj[mapNum][8].worldY = 8 * gp.tileSize;

            /*mapNum = 1;
            gp.obj[mapNum][8] = new Ob_Ship(gp);
            gp.obj[mapNum][8].worldX = 25 * gp.tileSize;
            gp.obj[mapNum][8].worldY = 11 * gp.tileSize;*/

    }

}
