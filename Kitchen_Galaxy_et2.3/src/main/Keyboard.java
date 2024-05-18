package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
                    gp.ui.nextImage = 1;
                }
                if(gp.ui.commandNum == 1){

                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }

        }
        if(gp.gameState == gp.storyState) {
            if (code == KeyEvent.VK_SPACE) {
                if(gp.ui.nextImage < 5) {
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
                if(gp.ui.nextImage < 9) {
                    gp.ui.nextImage++;
                }else {
                    gp.gameState = gp.playState;
                    /*gp.stopSong();
                    gp.playSong(2);*/

                }
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
            if (code == KeyEvent.VK_P) {
                gp.gameState = gp.pauseState;
                gp.stopSong();
            }
        }
        else if(gp.gameState == gp.pauseState){
            if(code == KeyEvent.VK_R){
                gp.gameState = gp.playState;
                gp.playSong(0);

            }
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.stopSong();


            gp.gameState = gp.titleState;
            /*gp.sound.setFile(1);
            gp.playSong(1);*/
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
