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
    public int fish_cnt = 0;
    public int salmon_cnt = 0;
    public int score = 0;


    public boolean dishPicked = false;
    public boolean steakPicked = false;
    public boolean saladPicked = false;
    public boolean breadPicked = false;
    public boolean chickenPicked = false;
    public boolean lipiePicked = false;
    public boolean fishPicked = false;

    public boolean makeBurger = false;
    public boolean makeBurrito = false;
    public boolean makeSalmon = false;



    public  int screenX;
    public  int screenY;


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

    public int getBorgar_cnt() {
        return borgar_cnt;
    }

    public void setBorgar_cnt(int borgar_cnt) {
        this.borgar_cnt = borgar_cnt;
    }

    public int getDish_cnt() {
        return dish_cnt;
    }

    public void setDish_cnt(int dish_cnt) {
        this.dish_cnt = dish_cnt;
    }

    public int getSalad_cnt() {
        return salad_cnt;
    }

    public void setSalad_cnt(int salad_cnt) {
        this.salad_cnt = salad_cnt;
    }

    public int getSteak_cnt() {
        return steak_cnt;
    }

    public void setSteak_cnt(int steak_cnt) {
        this.steak_cnt = steak_cnt;
    }

    public int getBread_cnt() {
        return bread_cnt;
    }

    public void setBread_cnt(int bread_cnt) {
        this.bread_cnt = bread_cnt;
    }

    public int getBurrito_cnt() {
        return burrito_cnt;
    }

    public void setBurrito_cnt(int burrito_cnt) {
        this.burrito_cnt = burrito_cnt;
    }

    public int getChicken_cnt() {
        return chicken_cnt;
    }

    public void setChicken_cnt(int chicken_cnt) {
        this.chicken_cnt = chicken_cnt;
    }

    public int getLipie_cnt() {
        return lipie_cnt;
    }

    public void setLipie_cnt(int lipie_cnt) {
        this.lipie_cnt = lipie_cnt;
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

            //update player speed according to the lore
            if(gp.currentMap == 1){
                speed = 3;
            }
            if(gp.currentMap == 2){
                speed = 5;
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
            if (gp.currentMap == 0) {
                switch (objectName) {

                    case "makeBurger":
                        makeBurger = true;
                        break;
                    case "burger":
                        gp.obj[gp.currentMap][i] = null;
                        borgar_cnt++;
                        score += 50;

                        break;
                    case "Dish":
                        gp.obj[gp.currentMap][i] = null;
                        dish_cnt++;
                        dishPicked = true;
                        break;
                    case "steak":
                        gp.obj[gp.currentMap][i] = null;
                        steak_cnt++;
                        steakPicked = true;

                        break;
                    case "salad":
                        gp.obj[gp.currentMap][i] = null;
                        salad_cnt++;
                        saladPicked = true;

                        break;
                    case "bread":
                        gp.obj[gp.currentMap][i] = null;
                        bread_cnt++;
                        breadPicked = true;

                        break;
                    case "ship":
                        gp.gameState = gp.storyState2;
                        gp.currentMap = 1;
                        break;
                }
            }
            if (gp.currentMap == 1) {
                switch (objectName) {
                    case "makeBurger":
                        makeBurger = true;
                        break;
                    case "burger":
                        gp.obj[gp.currentMap][i] = null;
                        borgar_cnt++;
                        score += 50;
                        break;
                    case "Dish":
                        gp.obj[gp.currentMap][i] = null;
                        dish_cnt++;
                        dishPicked = true;
                        break;
                    case "steak":
                        gp.obj[gp.currentMap][i] = null;
                        steak_cnt++;
                        steakPicked = true;

                        break;
                    case "salad":
                        gp.obj[gp.currentMap][i] = null;
                        salad_cnt++;
                        saladPicked = true;

                        break;
                    case "bread":
                        gp.obj[gp.currentMap][i] = null;
                        bread_cnt++;
                        breadPicked = true;
                        break;
                    //BURRITO ONLY
                    case "lipie":
                        gp.obj[gp.currentMap][i] = null;
                        lipie_cnt++;
                        lipiePicked = true;
                        break;
                    case "chicken":
                        gp.obj[gp.currentMap][i] = null;
                        chicken_cnt++;
                        chickenPicked = true;
                        break;
                    case "makeBurrito":
                        makeBurrito = true;
                        break;
                    case "burrito":

                        gp.obj[gp.currentMap][i] = null;
                        burrito_cnt++;
                        score += 75;
                        break;
                    case "ship2":
                        gp.gameState = gp.storyState3;
                        gp.currentMap = 2;
                        break;
                }
            }
            if (gp.currentMap == 2) {
                switch (objectName) {
                    case "makeBurger":
                        makeBurger = true;
                        break;
                    case "burger":
                        gp.obj[gp.currentMap][i] = null;
                        borgar_cnt++;
                        score += 50;
                        break;
                    case "Dish":
                        gp.obj[gp.currentMap][i] = null;
                        dish_cnt++;
                        dishPicked = true;
                        break;
                    case "steak":
                        gp.obj[gp.currentMap][i] = null;

                        steak_cnt++;
                        steakPicked = true;

                        break;
                    case "salad":
                        gp.obj[gp.currentMap][i] = null;

                        salad_cnt++;
                        saladPicked = true;

                        break;
                    case "bread":

                        gp.obj[gp.currentMap][i] = null;
                        bread_cnt++;
                        breadPicked = true;
                        break;
                    //BURRITO ONLY
                    case "lipie":

                        gp.obj[gp.currentMap][i] = null;
                        lipie_cnt++;
                        lipiePicked = true;
                        break;
                    case "chicken":
                        gp.obj[gp.currentMap][i] = null;
                        chicken_cnt++;
                        chickenPicked = true;
                        break;
                    case "makeBurrito":
                        makeBurrito = true;
                        System.out.println("daaa");
                        break;
                    case "burrito":
                        gp.obj[gp.currentMap][i] = null;
                        burrito_cnt++;
                        score += 75;
                        break;
                        //SALMON ONLY
                    case "fish":
                        gp.obj[gp.currentMap][i] = null;
                        fish_cnt++;
                        fishPicked = true;
                        break;
                    case"salmon_dish":
                        gp.obj[gp.currentMap][i] = null;
                        salmon_cnt++;
                        score += 35;
                        break;
                    case"makeSalmon":
                        makeSalmon = true;
                        break;
                    case"finale":

                        gp.gameState = gp.storyState4;
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
    public void resetConsumablesForSalmon(){
        makeSalmon = false;
        dishPicked = false;
        saladPicked = false;
        fishPicked = false;
    }
    public void resetCnts(){
        borgar_cnt = 0;
        dish_cnt = 0;
        salad_cnt = 0;
        steak_cnt = 0;
        bread_cnt = 0;
        burrito_cnt = 0;
        chicken_cnt = 0;
        lipie_cnt = 0;
        fish_cnt = 0;
        salmon_cnt = 0;
    }
}



