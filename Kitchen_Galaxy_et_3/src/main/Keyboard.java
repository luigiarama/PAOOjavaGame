package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.security.Key;
import Entity.Entity;


public class Keyboard implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;

    boolean checkDrawTime = false;
    Entity entity;
    public Keyboard(GamePanel gp) {
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //Title State
        if(gp.gameState == gp.titleState){
            if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
            }
            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.storyState;
                    gp.currentMap = 0;
                    gp.ui.playTime = 0;
                    gp.player.resetCnts();
                    gp.player.setDefaultValues();
                    gp.ui.nextImage = 1;
                    gp.player.score = 0;

                }
                if(gp.ui.commandNum == 1){
                    gp.stopSong();
                    gp.playSong(2);
                    gp.setVariablesFromDatabase();
                    gp.gameState = gp.playState;
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }

        }
        if(gp.gameState == gp.storyState) {
            if (code == KeyEvent.VK_SPACE) {
                if(gp.ui.nextImage < 4) {
                    gp.ui.nextImage++;
                }else {
                    gp.gameState = gp.playState;
                    gp.stopSong();
                    gp.playSong(0);

                }
            }
        }
        if(gp.gameState == gp.storyState2) {
            if (code == KeyEvent.VK_SPACE) {
                if(gp.ui.nextImage < 8) {
                    gp.ui.nextImage++;
                }else {
                    gp.gameState = gp.playState;
                    gp.stopSong();
                    gp.playSong(2);

                }
            }
        }
        if(gp.gameState == gp.storyState3) {
            if (code == KeyEvent.VK_SPACE) {
                if(gp.ui.nextImage < 13) {
                    gp.ui.nextImage++;
                }else {
                    gp.gameState = gp.playState;

                }

            }
        }
        if(gp.gameState == gp.storyState4){
            gp.ui.nextImage = 14;
            if (code == KeyEvent.VK_SPACE) {
                gp.gameState = gp.titleState;
            }
        }
        if(gp.gameState == gp.playState) {



            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }
            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }
            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if(code == KeyEvent.VK_SPACE){

            }

            //map reseter
            if(code == KeyEvent.VK_M){
                switch (gp.currentMap){
                    case 0:
                        gp.tileM.loadMap("/maps/map02.txt",0);
                        break;
                    case 1:
                        gp.tileM.loadMap("/maps/map03.txt",1);
                        break;
                }

            }
            //DEBUG
            if(code == KeyEvent.VK_T){
                if(checkDrawTime == false){
                    checkDrawTime = true;
                }
                else if(checkDrawTime == true){
                    checkDrawTime = false;
                }
            }
            if(code == KeyEvent.VK_L){
                gp.insertDataToDatabase();

            }
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                gp.stopSong();
            }
        } else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_R){
                gp.gameState = gp.playState;

                if(gp.ui.commandNum ==0){
                    gp.stopSong();
                }else gp.playSong(0);

            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.stopSong();


            gp.gameState = gp.titleState;
            gp.sound.setFile(1);
            gp.playSong(1);
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState){
            if(code == KeyEvent.VK_UP || code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;

                }
            }
            if(code == KeyEvent.VK_DOWN || code == KeyEvent.VK_S) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }

            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState=gp.playState;
                        if(gp.currentMap == 0){
                            gp.currentMap = 0;
                        }else if(gp.currentMap == 1){
                            gp.currentMap = 1;
                        }else if(gp.currentMap == 2){
                            gp.currentMap = 2;
                        }

                        gp.ui.playTime = 0;
                        gp.player.resetCnts();
                        gp.player.setDefaultValues();
                        gp.player.score = 0;

                    //gp.playMusic(0);
                }
                else if(gp.ui.commandNum == 1){
                    gp.gameState=gp.titleState;

                }
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }

    }
}
