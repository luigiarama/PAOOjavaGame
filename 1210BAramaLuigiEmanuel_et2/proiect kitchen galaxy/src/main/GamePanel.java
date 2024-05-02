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
    public final int maxScreenRow = 19;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    // 768 x 576
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Keyboard key = new Keyboard();
    ThemeSong sound = new ThemeSong();
    Thread gameThread;
    public CollisionTest cTest = new CollisionTest(this);
    public Player player = new Player(this,key);
    public MyObjects[] obj = new MyObjects[10];
    public AssetSetter aSetter = new AssetSetter(this);

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    public void setupGame(){

        aSetter.setObject();
        playSong(0);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
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
                e.printStackTrace();
            }
        }


    }
//    public void run(){
//        double drawInterval = 1000000000/FPS;
//        double delta = 0;
//        long lastTime = System.nanoTime();
//        long currentTime;
    //    long timer = 0;
    //    int drawCount = 0;
//        while(gameThread != null){
//            currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            lastTime = currentTime;
//            if(delta >= 1){
//                update();
//                repaint();
//                delta--;
    //            drawTime++;
//            }
//        }
//    }
    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //for tiles
        tileM.draw(g2);
        //for object interactions
        for(int i=0; i< obj.length;i++){
            if(obj[i] != null){
                obj[i].draw(g2,this);

            }
        }
        player.draw(g2);
        g2.dispose();
    }

    public void playSong(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopSong(){
        sound.stop();
    }
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
