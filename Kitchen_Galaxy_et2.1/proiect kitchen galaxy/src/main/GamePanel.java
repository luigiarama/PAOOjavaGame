package main;

import Entity.Player;
import objects.MyObjects;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 32;
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 30;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    // 768 x 576
    public final int maxWorldCol = 45;
    public final int maxWorldRow = 45;

    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Keyboard key = new Keyboard(this);
    ThemeSong sound = new ThemeSong();
    ThemeSong SE = new ThemeSong();
    Thread gameThread;
    public CollisionTest cTest = new CollisionTest(this);
    public Player player = new Player(this,key);
    //Objects on the map

    public MyObjects[] obj = new MyObjects[10];
    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);

    //Game State
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int storyState = 3;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    public void setupGame(){

        aSetter.setObject();
       // playSong(0);

        gameState = titleState;
        playSong(1);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval =  (double) 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread !=null){
            long currentTime = System.nanoTime();

            //System.out.println("Game running");
            //1.update
            update();


            //2.draw
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }catch(InterruptedException e){
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();

        }
        if(gameState == pauseState){

        }




    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //DEBUG
        long drawStart = 0;
        drawStart = System.nanoTime();
        if(key.checkDrawTime == true){
            drawStart = System.nanoTime();
        }

        // for Menu
        if(gameState == titleState){
            ui.paint(g2);
        }
        else{
            //for tiles
            tileM.draw(g2);
            for(int i=0; i< obj.length;i++){
                if(obj[i] != null){
                    obj[i].draw(g2,this);

                }
            }
            player.draw(g2);
            ui.paint(g2);

            //DEBUG
            if(key.checkDrawTime == true){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw time: "+ passed,10, 400);
                System.out.println("Draw time: "+ passed);
            }

            g2.dispose();

        }
        //for object interactions

    }

    public void playSong(int i){

        if(gameState == titleState){
            i=1;
            sound.setFile(i);
            sound.play();
            sound.loop();

        }
         if(gameState == playState) {
            sound.stop();
            i=0;
            sound.setFile(i);
            sound.play();
            sound.loop();
        }



    }
    public void stopSong(){
        sound.stop();
    }
    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }
}
