package Entity;

import main.GamePanel;
import main.Keyboard;
import main.UtilityTool;
import objects.Ob_Steak;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Player extends Entity{

    GamePanel gp;
    Keyboard keyH;

    public int borgar_cnt = 0;
    public int dish_cnt = 0;
    public int salad_cnt = 0;
    public int steak_cnt = 0;
    public int bread_cnt = 0;
    public int burrito_cnt = 0;
    public int chicken_cnt = 0;
    public int lipie_cnt = 0;

    public boolean dishPicked = false;
    public boolean steakPicked = false;
    public boolean saladPicked = false;
    public boolean breadPicked = false;
    public boolean makeBurger = false;
    public boolean makeBurrito = false;
    public boolean chickenPicked = false;
    public boolean lipiePicked = false;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, Keyboard keyH){
        this.gp = gp;


        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,45, 45);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();


        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize * 19;
        worldY= gp.tileSize * 7;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){

        up1 = setup("player_up_1");
        up2 = setup("player_up_2");
        down1 = setup("player_down_1");
        down2 = setup("player_down_2");
        right1 = setup("player_right_1");
        right2 = setup("player_right_2");
        left1 = setup("player_left_1");
        left2 = setup("player_left_2");

    }
    public BufferedImage setup(String imageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try{
            scaledImage = ImageIO.read(getClass().getResourceAsStream("/player/"+imageName + ".png"));
            scaledImage = uTool.scaleImage(scaledImage, gp.tileSize, gp.tileSize);

        }catch(IOException e){
            e.printStackTrace();
        }
        return scaledImage;
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed== true || keyH.rightPressed == true){
            if(keyH.upPressed){
                direction = "up";

            }
            else if (keyH.downPressed){
                direction = "down";

            }
            else if (keyH.leftPressed){
                direction = "left";

            }
            else if (keyH.rightPressed){
                direction = "right";

            }

            //CHECK TILE COL
            collisionCh = false;
            gp.cTest.checkTile(this);

            // CHECK OBJ COLLISION
            int objIndex = gp.cTest.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            //update player speed according to lore
            if(gp.currentMap == 1){
                speed = 3;
            }



            //IF COL IS FALSE, PLAYER MOVES
            if(!collisionCh){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }

        }
    }
    // interactiunea cu obiectele din joc
    public void pickUpObject(int i) {


        if (i != 20) {

            String objectName = gp.obj[gp.currentMap][i].name;
            if(gp.currentMap==0){
                switch (objectName) {

                    case "makeBurger":
                        makeBurger = true;
                        if(gp.player.dish_cnt!=0 && gp.player.salad_cnt!=0 && gp.player.steak_cnt!=0 && gp.player.bread_cnt!=0) {
                            gp.ui.showMessage("  Burger produced!");
                        }else {
                            gp.ui.showMessage("  Burger produced!");

                        }
                        break;
                    case "burger":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Burger picked up!");
                        borgar_cnt++;
                        break;
                    case "Dish":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Dish Picked!");
                        dish_cnt++;
                        dishPicked = true;
                        break;
                    case "steak":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Steak picked up!");
                        steak_cnt++;
                        steakPicked = true;

                        break;
                    case "salad":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Salad picked up!");
                        salad_cnt++;
                        saladPicked = true;

                        break;
                    case "bread":
                        gp.ui.showMessage("  Bread picked up!");
                        gp.obj[gp.currentMap][i] = null;
                        bread_cnt++;
                        breadPicked = true;

                        break;
                    case"ship":
                        gp.gameState = gp.storyState2;
                        gp.currentMap=1;
                        break;
                }
            }
            if(gp.currentMap==1){
                switch (objectName) {
                    case "makeBurger":
                        makeBurger = true;
                        if(gp.player.dish_cnt!=0 && gp.player.salad_cnt!=0 && gp.player.steak_cnt!=0 && gp.player.bread_cnt!=0) {
                            gp.ui.showMessage("  Burger produced!");
                        }else {
                            gp.ui.showMessage("  Burger produced!");
                        }
                        break;
                    case "burger":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Burger picked up!");
                        borgar_cnt++;
                        break;
                    case "Dish":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Dish Picked!");
                        dish_cnt++;
                        dishPicked = true;
                        break;
                    case "steak":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Steak picked up!");
                        steak_cnt++;
                        steakPicked = true;

                        break;
                    case "salad":
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("  Salad picked up!");
                        salad_cnt++;
                        saladPicked = true;

                        break;
                    case "bread":
                        gp.ui.showMessage("  Bread picked up!");
                        gp.obj[gp.currentMap][i] = null;
                        bread_cnt++;
                        breadPicked = true;
                        break;
                        //BURRITO ONLY
                    case "lipie":
                        gp.ui.showMessage("   Pita picked up!");
                        gp.obj[gp.currentMap][i] = null;
                        lipie_cnt++;
                        lipiePicked = true;
                        break;
                    case "chicken":
                        gp.ui.showMessage("   Chicken picked up!");
                        gp.obj[gp.currentMap][i] = null;
                        chicken_cnt++;
                        chickenPicked = true;
                        break;
                    case "makeBurrito":
                        gp.ui.showMessage("Burrito picked up!");
                        makeBurrito = true;
                        if(gp.player.dish_cnt!=0 && gp.player.salad_cnt!=0 ) {
                            gp.ui.showMessage("  Burr produced!");
                        }else {
                            gp.ui.showMessage("  Burr produced!");
                        }
                        break;
                    case "burrito":
                        gp.ui.showMessage("  Burrito picked up!");
                        gp.obj[gp.currentMap][i] = null;
                        burrito_cnt++;
                        break;
                }
            }
        }
    }


    // afisarea pe harta a caracterului principal
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image=up1;
                }

                if(spriteNum == 2){
                    image=up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,screenX,screenY,null);
    }
    public void resetConsumables() {

        makeBurger = false;
        dishPicked = false;
        steakPicked = false;
        saladPicked = false;
        breadPicked = false;
    }
    public void resetConsumablesForBurr(){
        makeBurrito = false;
        dishPicked = false;
        saladPicked = false;
        chickenPicked = false;
        lipiePicked = false;
    }
}



