package Entity;

import main.GamePanel;
import main.Keyboard;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    Keyboard keyH;
    public int borgar_cnt = 0;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, Keyboard keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,gp.tileSize-19, gp.tileSize-19);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        worldX=gp.tileSize * 12;
        worldY= gp.tileSize * 10;
        speed = 4;
        direction = "down";

    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
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
    public void pickUpObject(int i){
        if(i != 15){

            String objectName = gp.obj[i].name;

            switch (objectName){
                case"Dish":
                    gp.obj[i] = null;
                    gp.ui.showMessage("Dish Picked!");
                    // va urma adaugarea obiectului in inventarul jucatorului
                    System.out.println("Dish picked up! ");
                    break;
                case"burger":
                    gp.obj[i] = null;
                    gp.ui.showMessage("Borgar picked up!");
                    // va urma adaugarea obiectului in inventarul jucatorului

                    System.out.println("Borgar added for delivery! ");
                    borgar_cnt++;
                    break;
                case"steak":
                    gp.ui.showMessage("Uncooked steak picked up!");
                    break;
                case"salad":
                    gp.ui.showMessage("Salad picked up!");
                    break;
                case"bread":
                    gp.ui.showMessage("Bread picked!");
                    break;
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
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);
    }
}
