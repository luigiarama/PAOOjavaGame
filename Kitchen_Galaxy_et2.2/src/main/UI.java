package main;

import objects.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Objects;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font fixedsys_40;
    Font fixedsys_80;
    BufferedImage borgarImage;
    public boolean messageOn = false;
    public String message = "";
    public int commandNum = 0;
    public int nextImage = 1;
    int messageCounter = 0;

    //timer
    double playTime;

    DecimalFormat dFormat = new DecimalFormat("#0.00");




    public UI(GamePanel gp) {
        this.gp = gp;
        fixedsys_40 = new Font("Bauhaus 93", Font.PLAIN, 40);
        Ob_Burger ob = new Ob_Burger(gp);
        borgarImage = ob.image;

    }
    public void showMessage(String msg) {
        message = msg;
        messageOn = true;
    }
    public void paint(Graphics2D g2) {
        this.g2 = g2;
        Ob_Salad ob_salad = new Ob_Salad(gp);
        BufferedImage saladImage = ob_salad.image;

        Ob_Dish ob_dish = new Ob_Dish(gp);
        BufferedImage dishImage = ob_dish.image;

        Ob_Steak ob_steak = new Ob_Steak(gp);
        BufferedImage steakImage = ob_steak.image;

        Ob_Bread ob_bread = new Ob_Bread(gp);
        BufferedImage breadImage = ob_bread.image;

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleState();
        }
        // STORY STATE
        if(gp.gameState == gp.storyState){
            drawStoryState();
        }
        if(gp.gameState == gp.storyState2){
            gp.stopSong();
            //gp.playSong(2);
            drawStoryState2();
        }
        //In Game State
        if(gp.gameState == gp.playState){
            Font arial_40 = new Font("Arial", Font.BOLD, 30);
            //INFO FOR LEVEL PANEL
            drawInfo();
            g2.setFont(arial_40);
            g2.setColor(new Color(158,8,202));
            g2.drawString("        ---LEVEL 1--- ", gp.tileSize+2, gp.tileSize*8);
            g2.drawString("  Make using tools", gp.tileSize+2, gp.tileSize*9);
            g2.drawString("  5 burgers in", gp.tileSize+2, gp.tileSize*10);
            g2.drawString("  record time", gp.tileSize+2, gp.tileSize*11);

            g2.drawString("  Burger recipe: ", gp.tileSize+2, gp.tileSize*12);
            g2.drawString("  Dish + Bread", gp.tileSize+2, gp.tileSize*13);
            g2.drawString("  Steak + Salad", gp.tileSize+2, gp.tileSize*14);
            g2.drawString("  Touch Hammer", gp.tileSize+2, gp.tileSize*15-15);

            //REQUIREMENTS

            drawRequirements();
            g2.setFont(arial_40);
            g2.setColor(new Color(158,8,202));
            g2.drawString("  Make 5 burgers  ",gp.tileSize+2, gp.tileSize*2);
            g2.drawString("  using these:  ",gp.tileSize+2, gp.tileSize*3);
            g2.drawImage(borgarImage,gp.tileSize+2, gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
            g2.drawString(" = ",gp.tileSize*2-10, gp.tileSize*5-40);
            g2.drawImage(breadImage,gp.tileSize*2+10,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
            g2.drawImage(saladImage,gp.tileSize*2+50,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
            g2.drawImage(steakImage,gp.tileSize*2+90,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);
            g2.drawImage(dishImage,gp.tileSize*2+130,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);

            g2.drawString(" x ",gp.tileSize*2+180, gp.tileSize*5-40);
            Ob_MakeBurger ob_hammer = new Ob_MakeBurger(gp);
            BufferedImage hammerImage = ob_hammer.image;
            g2.drawImage(hammerImage,gp.tileSize*2+200,gp.tileSize*4-20, gp.tileSize, gp.tileSize, null);


            drawInventory();
            g2.setFont(fixedsys_40);
            g2.setColor(new Color(158, 8, 202));
            //DONE BURGERS
            Font arial_25 = new Font("Arial", Font.BOLD, 25);
            g2.setFont(arial_25);

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


            //TIME IN LEVEL
            Font fixedsys_40 = new Font("Bauhaus 93", Font.PLAIN, 40);
            g2.setFont(fixedsys_40);
            g2.setColor(new Color(158, 8, 202));
            if(gp.player.borgar_cnt < 5){
                playTime += (double) 1 /25;
            }

            g2.drawString("Time : "+dFormat.format(playTime), gp.tileSize*24, 70);

            if(gp.player.borgar_cnt >= 5) {
                Font fixedsys_50 = new Font("Bauhaus 93", Font.PLAIN, 50);
                g2.setFont(fixedsys_50);
                g2.setColor(Color.yellow);
                g2.drawString("Congratulations! You finished Level 1 in " + dFormat.format(playTime) + ", go to the UFO!", gp.tileSize * 6, gp.tileSize * 10 - 20);

            }



            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize, gp.tileSize * 5);
                messageCounter++;

                if(messageCounter > 120){
                    messageCounter = 0;
                    messageOn = false;
                }
            }


        }
        //Paused Game State
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
    }
    private String formatTime(double time) {
        int minutes = (int) (time / 60);
        int seconds = (int) (time % 60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    private void drawStoryState(){
        try{
            BufferedImage img1, img2, img3, img4;
            int x;
            int y = gp.tileSize * 5;

            if(nextImage == 1) {
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/prestory1.png")));
                g2.drawImage(img1, 0, 0, gp.screenWidth, gp.screenHeight, null);
                Font fixedsys_40 = new Font("Arial", Font.PLAIN, 30);
                g2.setFont(fixedsys_40);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space- ";
                x = gp.tileSize * 18;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);
            }
            if(nextImage == 2) {
                img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/prestory2.png")));
                g2.drawImage(img2, 0, 0, gp.screenWidth, gp.screenHeight, null);
                Font fixedsys_40 = new Font("Arial", Font.PLAIN, 30);
                g2.setFont(fixedsys_40);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space to continue- ";
                x = gp.tileSize * 17;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);
            }
            if(nextImage == 3) {
                img3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/firstlevel1.png")));
                g2.drawImage(img3, 0, 0, gp.screenWidth, gp.screenHeight, null);
                Font fixedsys_40 = new Font("Arial", Font.PLAIN, 30);
                g2.setFont(fixedsys_40);
                g2.setColor(new Color(158, 8, 202));
                String text = "-press Space to continue- ";
                x = gp.tileSize * 18;
                y += gp.tileSize * 9;
                g2.drawString(text, x, y);
            }
            if(nextImage == 4) {
                img4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/firstlevel2.png")));
                g2.drawImage(img4,0,0,gp.screenWidth,gp.screenHeight,null);
                Font fixedsys_40 = new Font("Arial", Font.BOLD, 50);
                g2.setFont(fixedsys_40);
                g2.setColor(new Color(158, 8, 202));
                String text1 = "Ready to play?  ";
                x = getXforCenteredText(text1);
                y += gp.tileSize * 9;
                g2.drawString(text1, x, y);
                Font fixedsys_20 = new Font("Arial", Font.PLAIN, 30);
                g2.setFont(fixedsys_20);
                g2.setColor(new Color(255, 253, 253));
                String text2 = "Press 2xSpace to START! ";
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
            int x;
            int y = gp.tileSize * 5;

            if(nextImage == 6){
                img1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel1.png")));
                g2.drawImage(img1,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 7){
                img2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel2.png")));
                g2.drawImage(img2,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 8){
                img3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel3.png")));
                g2.drawImage(img3,0,0,gp.screenWidth,gp.screenHeight,null);
            }
            if(nextImage == 9){
                img4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/secondlevel4.png")));
                g2.drawImage(img4,0,0,gp.screenWidth,gp.screenHeight,null);
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void drawTitleState() {
        //Menu

        try {
            BufferedImage bf;
            bf = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/TitleScreen2.png")));
            g2.drawImage(bf,0,0,gp.screenWidth,gp.screenHeight, null);

            BufferedImage salad;
            Ob_Salad d = new Ob_Salad(gp);
            salad = d.image;

            Font fixedsys_60 = new Font("Bauhaus 93", Font.BOLD, 60);
            g2.setFont(fixedsys_60);
            g2.setColor(Color.ORANGE);
            int y = gp.tileSize * 2;

            String text = "PLAY";
            int x = getXforCenteredText(text);

            y += (int) (gp.tileSize * 3.5);
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawImage(salad,x - gp.tileSize, y-50, gp.tileSize, gp.tileSize, null);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 5;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawImage(salad,x - gp.tileSize, y-50, gp.tileSize, gp.tileSize, null);
            }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawImage(salad,x - gp.tileSize, y-50, gp.tileSize, gp.tileSize, null);

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawPauseScreen(){

        fixedsys_80 = new Font("Bauhaus 93", Font.BOLD, 80);
        String text = "PAUSED";
        g2.setFont(Font.getFont(text,fixedsys_80));
        g2.setColor(Color.white);
        int x,y;

        x = getXforCenteredText(text);
        y = gp.screenHeight/2;
        g2.drawString(text,x,y);

        text = "Press R to resume the game";
        g2.setFont(Font.getFont(text,fixedsys_40));
        g2.setColor(Color.orange);
        x = 15;
        y = 35;
        g2.drawString(text,x,y);
    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();

        return gp.screenWidth/2 - length/2;
    }
    public void drawTimeOverScreen(){
        String text ="TIME OVER, press ESC to enter the menu";
        g2.drawString(text, getXforCenteredText(text),gp.screenHeight/2);
    }
    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(0, 0, 0,200));
        g2.fillRoundRect(x,y,width,height,30,30);

        g2.setColor(new Color(255, 255, 255));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10,30,30);
    }
    public void drawInventory(){

        //FRAME ON THE SCREEN
        int frameX = gp.tileSize*19;
        int frameY = gp.tileSize*10;
        int frameWidth = gp.tileSize*10;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    }
    public void drawInfo(){
        int frameX = gp.tileSize;
        int frameY = gp.tileSize*7;
        int frameWidth = gp.tileSize*5;
        int frameHeight = gp.tileSize*8;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    }
    public void drawRequirements(){
        int frameX = gp.tileSize;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*5;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);
    }
}


