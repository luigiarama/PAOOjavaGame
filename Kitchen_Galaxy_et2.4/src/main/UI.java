package main;

import Entity.Player;
import main.GamePanel;
import objects.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font fixedsys30 = new Font("Bauhaus 93", Font.PLAIN, 30);
    Font fixedsys40 = new Font("Bauhaus 93", Font.PLAIN, 40);
    Font arial50 = new Font("Arial", Font.BOLD, 50);
    Font fixedsys60 = new Font("Bauhaus 93", Font.PLAIN, 60);
    Font fixedsys100 = new Font("Cooper Black", Font.BOLD, 100);
    Font arial30 = new Font("Arial", Font.BOLD, 30);
    Font arial25 = new Font("Arial", Font.BOLD, 25);

    //FOR BURGER TUTORIAL IN UI
    Ob_Burger ob = new Ob_Burger(gp);
    BufferedImage borgarImage = ob.image;

    Ob_Salad ob_salad = new Ob_Salad(gp);
    BufferedImage saladImage = ob_salad.image;

    Ob_Dish ob_dish = new Ob_Dish(gp);
    BufferedImage dishImage = ob_dish.image;

    Ob_Steak ob_steak = new Ob_Steak(gp);
    BufferedImage steakImage = ob_steak.image;

    Ob_Bread ob_bread = new Ob_Bread(gp);
    BufferedImage breadImage = ob_bread.image;

    Ob_MakeBurger ob_hammer = new Ob_MakeBurger(gp);
    BufferedImage hammerImage = ob_hammer.image;

    //FOR BURRITO TUTORIAL IN UI
    Ob_Lipie ob_lipie = new Ob_Lipie(gp);
    BufferedImage lipieImage = ob_lipie.image;

    Ob_Chicken ob_chicken = new Ob_Chicken(gp);
    BufferedImage chickenImage = ob_chicken.image;

    Ob_Buritto ob_buritto = new Ob_Buritto(gp);
    BufferedImage burritoImage = ob_buritto.image;

    Ob_MakeBurrito ob_makeBurrito = new Ob_MakeBurrito(gp);
    BufferedImage makeBurritoImage = ob_makeBurrito.image;

    //FOR SALMON TUTORIAL IN UI
    Ob_MakeSalmon ob_makeSalmon = new Ob_MakeSalmon(gp);
    BufferedImage makeSalmonImage = ob_makeSalmon.image;

    Ob_Fish ob_fish = new Ob_Fish(gp);
    BufferedImage fishImage = ob_fish.image;

    Ob_Salmon ob_salmon = new Ob_Salmon(gp);
    BufferedImage salmonImage = ob_salmon.image;


    boolean drawUI = false;
    boolean messageOn = false;
    String message = "";
    int commandNum = 0;
    int nextImage = 1;
    int messageCounter = 0;

    // Timer
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    UtilityTool uTool = new UtilityTool();



    public UI(GamePanel gp) {
        this.gp = gp;
        this.uTool = new UtilityTool();
    }

    public double getPlayTime() {
        return playTime;
    }

    public void setPlayTime(double playTime) {
        this.playTime = playTime;
    }

    public void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }

    public void paint(Graphics2D g2) {
        this.g2 = g2;

        switch (gp.gameState) {
            case GamePanel.titleState:
                drawTitleState();
                break;
            case GamePanel.storyState:
                drawStoryState();
                break;
            case GamePanel.storyState2:
                drawStoryState2();
            case  GamePanel.storyState3:
                drawStoryState3();
            case GamePanel.storyState4:
                drawStoryState4();
            case GamePanel.playState:
                drawPlayState();
                break;
            case GamePanel.pauseState:
                drawPauseScreen();
                break;
            case GamePanel.gameOverState:
                drawGameOverScreen();
                break;
            default:
                // Handle unknown state
                break;
        }
    }


    private void drawPlayState() {
        if (gp.currentMap == 0) {
            drawLevel1UI();
        }
        if(gp.currentMap == 1) {
            drawLevel2UI();
        }
        if(gp.currentMap == 2){
            drawLevel3UI();
        }
    }

    private void drawLevel1UI() {
        drawCommonUIElements();
        drawLevelOneSpecificDetails();
    }
    private void drawLevel2UI(){
        if(nextImage == 8){
            drawUI = true;
            drawCommonUIElements();
            drawLevelTwoSpecificDetails();
        }
    }
    private void drawLevel3UI(){
        if(nextImage == 13){
            drawUI = true;
            drawCommonUIElements();
            drawLevelThreeSpecificDetails();
        }
    }

    private void drawCommonUIElements() {
        drawScore();
        drawInfo();
        drawRequirements();
        drawInventory();
    }
    private void drawScore(){
        int x =gp.tileSize*24;
        int y = gp.tileSize*2;
        g2.setFont(fixedsys40);
        if(gp.currentMap == 0){
            g2.setColor(new Color(158, 8, 202));
        }else if(gp.currentMap == 1){
            g2.setColor(new Color(8, 208, 226));
        }else if (gp.currentMap == 2){
            g2.setColor(new Color(22, 237, 8));
        }
        g2.drawString("Score: "+ gp.player.score,x,y);
    }

    private void drawInfo() {

            int x = gp.tileSize;
            int y = gp.tileSize * 7;
            int width = gp.tileSize * 5 + 15;
            int height = gp.tileSize * 8;
            drawSubWindow(x, y, width, height);

    }

    private void drawRequirements() {
        int x = gp.tileSize;
        int y = gp.tileSize;
        int width = gp.tileSize * 5 + 15;
        int height = gp.tileSize * 6-20;
        drawSubWindow(x, y, width, height);


    }

    private void drawInventory() {
        int x = gp.tileSize * 19;
        int y = gp.tileSize * 10;
        int width = gp.tileSize * 10;
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

    }

    private void drawSubWindow(int x, int y, int width, int height) {

        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 25, 25);


        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 20, 20);
    }


    private void drawLevelOneSpecificDetails() {
        g2.setFont(arial30);
        g2.setColor(new Color(158, 8, 202));
        // INFO
        String[] levelDetails = {
                "        ---LEVEL 1--- ",
                "  Help Chef Alex",
                "  score 250 points  ",
                "  TIME LIMIT 60s",
                "  Burger recipe:",
                "  Dish + Bread" ,
                "  Steak + Salad",
                "  Assemble on table"
        };

        int yPos = gp.tileSize * 7+40;
        for (String detail : levelDetails) {
            g2.drawString(detail, gp.tileSize + 2, yPos);
            yPos += gp.tileSize;  // Increment position for the next item
        }
        //REQUIREMENTS
        g2.setFont(arial30);
        g2.setColor(new Color(158,8,202));
        String[] requirements = {
                "   Score 250 points",
                "   1 x Burger = 50 ",
        };
        int x = gp.tileSize;
        int y = gp.tileSize;
        int textY = y + 100;
        for (String req : requirements) {
            g2.drawString(req, x + 10, textY);
            textY += 25;
        }
        g2.drawImage(borgarImage,gp.tileSize+2, gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*5-40);
        g2.drawImage(breadImage,gp.tileSize*2+10,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(steakImage,gp.tileSize*2+90,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*5-40);
        g2.drawImage(hammerImage,gp.tileSize*2+200,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);



        g2.setFont(arial25);
        g2.setColor(new Color(158, 8, 202));

        //INVENTORY
        //DONE BURGERS

        g2.drawImage(borgarImage,gp.tileSize*19+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.borgar_cnt, gp.tileSize*20, gp.tileSize*11-25);
        //SALAD
        g2.drawImage(saladImage,gp.tileSize*19+10, gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.salad_cnt, gp.tileSize*20, gp.tileSize*12-25);

        //  STEAK
        g2.drawImage(steakImage,gp.tileSize*19+10,gp.tileSize*12, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.steak_cnt, gp.tileSize*20, gp.tileSize*13-25);

        // LIST TEXT INVENTORY
        g2.drawString("LIST OF AVAILABLE INGREDIENTS", gp.tileSize* 22, gp.tileSize*13-25);

        //BREAD
        g2.drawImage(breadImage,gp.tileSize*19+10,gp.tileSize*13,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.bread_cnt,gp.tileSize*20, gp.tileSize*14-25);

        //DISH
        g2.drawImage(dishImage,gp.tileSize*19+10,gp.tileSize*14,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.dish_cnt,gp.tileSize*20, gp.tileSize*15-25);

        updateLevel1Timer();
    }

    private void updateLevel1Timer() {
        // Update playTime if conditions are met
        if (gp.player.score < 250 && gp.currentMap == 0) {
            playTime += (double) 1 / 60;
        }

        // Draw the current time on the screen
        g2.setFont(fixedsys40);
        g2.setColor(new Color(158, 8, 202));
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 24, 70);

        // Check if the player has completed the level
        if (playTime > 60) {
            gp.gameState = gp.gameOverState;
        } else if (gp.player.score >= 250) {
            // If timer hasn't run out and score is 250 or more
            g2.setColor(Color.yellow);
            g2.drawString("Congratulations! You finished Level 1 in " + dFormat.format(playTime) + " seconds, go to the UFO!", gp.tileSize * 6, gp.tileSize * 10 - 20);
        }
    }
    private void drawLevelTwoSpecificDetails(){
        g2.setFont(arial30);
        g2.setColor(new Color(8, 208, 226));
        // INFO
        String[] levelDetails = {
                "        ---LEVEL 2--- ",
                "  Help Chef Alex to",
                "  score 400 points ",
                "  TIME LIMIT 90s",
                "  Burrito recipe:",
                "  Dish + Pita" ,
                "  Ham + Salad",
                "  Assemble on table"
        };

        int yPos = gp.tileSize * 7+40;
        for (String detail : levelDetails) {
            g2.drawString(detail, gp.tileSize + 2, yPos);
            yPos += gp.tileSize;  // Increment position for the next item
        }
        //REQUIREMENTS
        g2.setFont(arial30);
        g2.setColor(new Color(8,208,226));
        String[] requirements = {
                "     Score 400 points",
                "     1 x Burrito = 75 ",
                "     1 x Burger = 50 ",
        };
        int x = gp.tileSize-30;
        int y = gp.tileSize;
        int textY = y + 100;
        for (String req : requirements) {
            g2.drawString(req, x + 10, textY);
            textY += 25;
        }
        //reqs for burger
        g2.drawImage(borgarImage,gp.tileSize+2, gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*5-40);
        g2.drawImage(breadImage,gp.tileSize*2+10,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(steakImage,gp.tileSize*2+90,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);

        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*5-40);
        g2.drawImage(hammerImage,gp.tileSize*2+200,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);

        //reqs for burrito
        g2.drawImage(burritoImage,gp.tileSize+2, gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*6-40);
        g2.drawImage(lipieImage,gp.tileSize*2+10,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(chickenImage,gp.tileSize*2+90,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);

        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*6-40);
        g2.drawImage(makeBurritoImage,gp.tileSize*2+200,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);

        //INVENTORY
        g2.setFont(arial25);

        g2.drawImage(borgarImage,gp.tileSize*19+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.borgar_cnt, gp.tileSize*20, gp.tileSize*11-25);

        //DONE BURRITOS
        g2.drawImage(burritoImage,gp.tileSize*21+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.burrito_cnt, gp.tileSize*22, gp.tileSize*11-25);

        //SALAD
        g2.drawImage(saladImage,gp.tileSize*23+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.salad_cnt, gp.tileSize*24, gp.tileSize*11-25);

        //DISH
        g2.drawImage(dishImage,gp.tileSize*25+10,gp.tileSize*10,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.dish_cnt,gp.tileSize*26, gp.tileSize*11-25);

        //PITA
        g2.drawImage(lipieImage,gp.tileSize*21+10, gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.lipie_cnt, gp.tileSize*22, gp.tileSize*12-25);

        //  STEAK
        g2.drawImage(steakImage,gp.tileSize*19+10,gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.steak_cnt, gp.tileSize*20, gp.tileSize*12-25);

        //BREAD
        g2.drawImage(breadImage,gp.tileSize*19+10,gp.tileSize*12,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.bread_cnt,gp.tileSize*20, gp.tileSize*13-25);

        //CHICKEN
        g2.drawImage(chickenImage,gp.tileSize*21+10,gp.tileSize*12,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.chicken_cnt,gp.tileSize*22, gp.tileSize*13-25);

        // LIST TEXT INVENTORY
        g2.drawString("LIST OF AVAILABLE INGREDIENTS", gp.tileSize* 21, gp.tileSize*14-25);
        updateLevel2Timer();
    }
    private void updateLevel2Timer() {
        if (gp.player.score <= 400 && gp.currentMap ==1) {
            playTime += (double) 1 / 60;
        }
        g2.setFont(fixedsys40);
        g2.setColor(new Color(8,208,226));
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 24, 70);

        // Check if the player has completed the level
        if (playTime > 90) {
            gp.gameState = gp.gameOverState;
        } else if (gp.player.score >= 400) {
            g2.setColor(Color.yellow);
            g2.drawString("Congratulations! You finished Level 2 in " + dFormat.format(playTime) + " seconds, go to the UFO!", gp.tileSize * 6, gp.tileSize * 10 - 20);
        }
    }
    private void drawLevelThreeSpecificDetails(){
        g2.setFont(arial30);
        g2.setColor(new Color(22, 237, 8));
        // INFO
        String[] levelDetails = {
                "        ---LEVEL 3--- ",
                "  Help Chef Alex to",
                "  score 810 points ",
                "  TIME LIMIT 180s",
                "  Salmon recipe:",
                "  Dish + Salad" ,
                "  Fish ",
                "  Assemble on table"
        };

        int yPos = gp.tileSize * 7+40;
        for (String detail : levelDetails) {
            g2.drawString(detail, gp.tileSize + 2, yPos);
            yPos += gp.tileSize;  // Increment position for the next item
        }
        //REQUIREMENTS
        g2.setFont(arial30);
        g2.setColor(new Color(22, 237, 8));
        String[] requirements = {
                "     Score 810 points",
                "     1 x Burrito = 75 ",
                "     1 x Burger = 50 ",
                "     1 x Salmon = 35 "
        };
        int x = gp.tileSize;
        int y = gp.tileSize;
        int textY = y + 50;
        for (String req : requirements) {
            g2.drawString(req, x + 10, textY);
            textY += 35;
        }
        //reqs for burger
        g2.drawImage(borgarImage,gp.tileSize+2, gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*5-40);
        g2.drawImage(breadImage,gp.tileSize*2+10,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(steakImage,gp.tileSize*2+90,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);

        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*5-40);
        g2.drawImage(hammerImage,gp.tileSize*2+200,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);

        //reqs for burrito
        g2.drawImage(burritoImage,gp.tileSize+2, gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*6-40);
        g2.drawImage(lipieImage,gp.tileSize*2+10,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(chickenImage,gp.tileSize*2+90,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);

        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*6-40);
        g2.drawImage(makeBurritoImage,gp.tileSize*2+200,gp.tileSize*5-20, gp.tileSize, gp.tileSize, null);


        //reqs for salmon
        g2.drawImage(salmonImage, gp.tileSize+2, gp.tileSize*6-20, gp.tileSize, gp.tileSize, null);
        g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*7-40);
        g2.drawImage(fishImage,gp.tileSize*2+10,gp.tileSize*6-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*6-20, gp.tileSize, gp.tileSize, null);
        g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*6-20, gp.tileSize, gp.tileSize, null);

        g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*7-40);
        g2.drawImage(makeSalmonImage,gp.tileSize*2+200,gp.tileSize*6-20, gp.tileSize, gp.tileSize, null);

        //INVENTORY
        g2.setFont(arial25);

        g2.drawImage(borgarImage,gp.tileSize*19+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.borgar_cnt, gp.tileSize*20, gp.tileSize*11-25);

        //DONE BURRITOS
        g2.drawImage(burritoImage,gp.tileSize*21+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+ gp.player.burrito_cnt, gp.tileSize*22, gp.tileSize*11-25);

        //DONE SALMON
        g2.drawImage(salmonImage,gp.tileSize*23+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.salmon_cnt, gp.tileSize*24, gp.tileSize*11-25);

        //FISH
        g2.drawImage(fishImage,gp.tileSize*23+10, gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.fish_cnt, gp.tileSize*24, gp.tileSize*12-25);

        //SALAD
        g2.drawImage(saladImage,gp.tileSize*25+10, gp.tileSize*10, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.salad_cnt, gp.tileSize*26, gp.tileSize*11-25);

        //DISH
        g2.drawImage(dishImage,gp.tileSize*27+10,gp.tileSize*10,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.dish_cnt,gp.tileSize*28, gp.tileSize*11-25);

        //PITA
        g2.drawImage(lipieImage,gp.tileSize*21+10, gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.lipie_cnt, gp.tileSize*22, gp.tileSize*12-25);

        //  STEAK
        g2.drawImage(steakImage,gp.tileSize*19+10,gp.tileSize*11, gp.tileSize, gp.tileSize, null);
        g2.drawString("x "+gp.player.steak_cnt, gp.tileSize*20, gp.tileSize*12-25);

        //BREAD
        g2.drawImage(breadImage,gp.tileSize*19+10,gp.tileSize*12,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.bread_cnt,gp.tileSize*20, gp.tileSize*13-25);

        //CHICKEN
        g2.drawImage(chickenImage,gp.tileSize*21+10,gp.tileSize*12,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.chicken_cnt,gp.tileSize*22, gp.tileSize*13-25);

        // LIST TEXT INVENTORY
        g2.drawString("LIST OF AVAILABLE INGREDIENTS", gp.tileSize* 21, gp.tileSize*14-25);
        updateLevel3Timer();
    }

    private void updateLevel3Timer() {
        if (gp.player.score <= 810 && gp.currentMap ==2) {
            playTime += (double) 1 / 60;
        }
        g2.setFont(fixedsys40);
        g2.setColor(new Color(22, 237, 8));
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 24, 70);

        // Check if the player has completed the level
        if (playTime > 180) {
            gp.gameState = gp.gameOverState;
        } else if (gp.player.score >= 810) {
            g2.setColor(Color.yellow);
            g2.drawString("Congratulations! You finished Level 3 in " + dFormat.format(playTime) + " seconds, go to the UFO!", gp.tileSize * 6, gp.tileSize * 10 - 20);
        }
    }


    public void drawStoryState() {
        try {
            BufferedImage img1, img2, img3, img4;
            int x;
            int y = gp.tileSize * 5;

            if(nextImage == 1) {
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/prestory1.png")));
                uTool.scaleImage(img1,gp.tileSize,gp.tileSize);
                g2.drawImage(img1, 0, 0, gp.screenWidth, gp.screenHeight, null);

                /*g2.setFont(arial30);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space- ";
                x = gp.tileSize * 18;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);*/
            }
            if(nextImage == 2) {
                img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/prestory2.png")));
                uTool.scaleImage(img2,gp.tileSize,gp.tileSize);
                g2.drawImage(img2, 0, 0, gp.screenWidth, gp.screenHeight, null);

                /*g2.setFont(arial30);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space to continue- ";
                x = gp.tileSize * 17;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);*/
            }
            if(nextImage == 3) {
                img3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/firstlevel1.png")));
                uTool.scaleImage(img3,gp.tileSize,gp.tileSize);
                g2.drawImage(img3, 0, 0, gp.screenWidth, gp.screenHeight, null);

                /*g2.setFont(arial30);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space to continue- ";
                x = gp.tileSize * 18;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);*/
            }
            if(nextImage == 4) {
                img4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/firstlevel2.png")));
                uTool.scaleImage(img4,gp.tileSize,gp.tileSize);
                g2.drawImage(img4,0,0,gp.screenWidth,gp.screenHeight,null);

                g2.setFont(arial50);
                g2.setColor(new Color(158, 8, 202));
                String text1 = "Ready to play?  ";
                x = getXforCenteredText(text1);
                y += gp.tileSize * 9;
                g2.drawString(text1, x, y);

                g2.setFont(arial30);
                g2.setColor(new Color(255, 253, 253));
                String text2 = "Press Space to START! ";
                x = getXforCenteredText(text2);
                y += gp.tileSize-10;
                g2.drawString(text2, x, y);


            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void drawStoryState2(){

        try{
            BufferedImage img1, img2, img3, img4;

            if(nextImage == 5){
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel1.png")));
                uTool.scaleImage(img1,gp.tileSize,gp.tileSize);
                g2.drawImage(img1,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 6){
                img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel2.png")));
                uTool.scaleImage(img2,gp.tileSize,gp.tileSize);
                g2.drawImage(img2,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 7){
                img3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel3.png")));
                uTool.scaleImage(img3,gp.tileSize,gp.tileSize);
                g2.drawImage(img3,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 8){
                img4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel4.png")));
                uTool.scaleImage(img4,gp.tileSize,gp.tileSize);
                g2.drawImage(img4,0,0,gp.screenWidth,gp.screenHeight,null);

            }



        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void drawStoryState3(){
        try{
            BufferedImage img1, img2, img3, img4;

            if(nextImage == 9){
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/thirdlevel1.png")));
                uTool.scaleImage(img1,gp.tileSize,gp.tileSize);
                g2.drawImage(img1,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 10){
                img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/thirdlevel2.png")));
                uTool.scaleImage(img2,gp.tileSize,gp.tileSize);
                g2.drawImage(img2,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 11){
                img3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/thirdlevel3.png")));
                uTool.scaleImage(img3,gp.tileSize,gp.tileSize);
                g2.drawImage(img3,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 12){
                img4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/thirdlevel4.png")));
                uTool.scaleImage(img4,gp.tileSize,gp.tileSize);
                g2.drawImage(img4,0,0,gp.screenWidth,gp.screenHeight,null);
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void drawStoryState4(){
        try{
            BufferedImage img;
            if(nextImage == 14){
                img = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/finale.png")));
                uTool.scaleImage(img,gp.tileSize,gp.tileSize);
                g2.drawImage(img,0,0,gp.screenWidth,gp.screenHeight,null);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    private void drawTitleState() {
        try {
            BufferedImage titleImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/TitleScreen2.png")));
            uTool.scaleImage(titleImage, gp.tileSize, gp.tileSize);
            g2.drawImage(titleImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

            BufferedImage salad;
            Ob_Salad d = new Ob_Salad(gp);
            salad = d.image;

            g2.setFont(fixedsys60);
            g2.setColor(Color.ORANGE);
            int y = gp.tileSize * 2;

            String text = "PLAY";
            int x = getXforCenteredText(text);

            y += (int) (gp.tileSize * 3.5);
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }

    public void drawPauseScreen() {
        drawOverlay();
        BufferedImage salad;
        Ob_Salad d = new Ob_Salad(gp);
        salad = d.image;

        String pauseText = "PAUSED";
        g2.setFont(new Font("Bauhaus 93", Font.BOLD, 80));
        g2.setColor(Color.white);
        int x = getXforCenteredText(pauseText);
        int y = gp.screenHeight / 2;
        g2.drawString(pauseText, x, y);

        String text = "MUSIC MUTED";
        g2.setFont(fixedsys40);
        x = getXforCenteredText(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);
        }

         String resumeText = "Press R to resume the game";
        g2.setFont(new Font("Bauhaus 93", Font.PLAIN, 40));
        g2.setColor(Color.orange);
        x = getXforCenteredText(resumeText);
        y += 50;
        g2.drawString(resumeText, x, y);
    }

    private void drawOverlay() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    }

    private void drawGameOverScreen(){
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        BufferedImage salad;
        Ob_Salad d = new Ob_Salad(gp);
        salad = d.image;

        int x;
        int y;
        String text;
        g2.setFont(fixedsys100);

        text = "GAME OVER! TIME RAN OUT!";
        //shadow
        g2.setColor(Color.red);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.white);
        g2.drawString(text, x - 4, y - 4);

        //RETRY
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);

        if (commandNum == 0) {
            g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);

        }

        //BACK TO THE TITLE SCREEN
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);

        if (commandNum == 1) {
            g2.drawImage(salad, x - gp.tileSize, y - 50, gp.tileSize, gp.tileSize, null);

        }
    }



}
