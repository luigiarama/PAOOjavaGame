package main;

import objects.Ob_Burger;
import objects.Ob_Salad;

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
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");


    public UI(GamePanel gp) {
        this.gp = gp;
        fixedsys_40 = new Font("Bauhaus 93", Font.PLAIN, 40);
        Ob_Burger ob = new Ob_Burger();
        borgarImage = ob.image;
    }
    public void showMessage(String msg) {
        message = msg;
        messageOn = true;


    }
    public void paint(Graphics2D g2) {
        this.g2 = g2;

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleState();
        }
        // STORY STATE
        if(gp.gameState == gp.storyState){
            drawStoryState();
        }
        //In Game State
        if(gp.gameState == gp.playState){
            g2.setFont(fixedsys_40);
            g2.setColor(new Color(248, 23, 23));
            g2.drawImage(borgarImage,gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x "+ gp.player.borgar_cnt, 85, 70);
            playTime += (double)1/60;
            g2.drawString("Time : "+dFormat.format(playTime), gp.tileSize*20, 70);

            if(messageOn){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize, gp.tileSize * 5);
                messageCounter++;

                if(messageCounter > 180){
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

    private void drawTitleState() {
        //Menu

        try {
            BufferedImage bf;
            bf = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/title&story/TitleScreen2.png")));
            g2.drawImage(bf,0,0,gp.screenWidth,gp.screenHeight, null);

            BufferedImage salad;
            Ob_Salad d = new Ob_Salad();
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
}
