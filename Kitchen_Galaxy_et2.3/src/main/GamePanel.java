package main;

import Entity.Player;
import objects.MyObjects;
import tile.TileManager;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
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



    //reseter
    public boolean reseter = false;


    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Keyboard key = new Keyboard(this);
    //ThemeSong sound = new ThemeSong();
    //ThemeSong SE = new ThemeSong();
    Thread gameThread;
    public CollisionTest cTest = new CollisionTest(this);
    public Player player = new Player(this,key);

    //Objects on the map
    public final int maxMap = 5;
    public int currentMap = 0;

    public MyObjects[][] obj = new MyObjects[maxMap][20];
    public AssetSetter aSetter = new AssetSetter(this);
    private ScheduledExecutorService scheduler;


    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);

    //Game State
    public int gameState;
    public int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int storyState = 3;
    public final int storyState2 = 4;
    public final int gameOverState = 5; // Assuming 5 is the game over state
    private long timerStart;
    private boolean timerRunning;
    private final long timerDuration = 120 * 1000; // 120 seconds in milliseconds


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }
    public void setupGame(){

        aSetter.setObject();

        //playSong(0);

        gameState = titleState;

    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }
    @Override
    public void run() {

        double drawInterval =   (double) 1000000000 /FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread !=null){


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
    /*@Override
    public void run(){
        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;


        while(gameThread !=null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime  = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
        }
    }*/

    public void update() {
        if (gameState == playState) {

            player.update();
            //level 1 respawn items
            if (!reseter && player.breadPicked && player.saladPicked && player.steakPicked && player.dishPicked && player.makeBurger && currentMap==0) {
                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponents();
                    scheduler.shutdown();
                }, 1, TimeUnit.SECONDS);
            }
            //level 2 respawn items for burger
            if (!reseter && player.breadPicked && player.saladPicked && player.steakPicked && player.dishPicked &&player.makeBurger && currentMap==1) {

                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponents();
                    scheduler.shutdown();
                }, 1, TimeUnit.SECONDS);

            }
            //level 2 respawn items for burrito
            if (!reseter && player.saladPicked &&  player.dishPicked &&player.makeBurrito && player.lipiePicked && player.chickenPicked && currentMap==1) {

                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponentsBurr();

                }, 1, TimeUnit.SECONDS);

            }
            if(gameState == storyState2 && !timerRunning){
                startTimer();
            }
            if(timerRunning){
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - timerStart;
                if (elapsedTime >= timerDuration) {
                    timerRunning = false;
                    gameState = gameOverState;
                } else {
                    // Display the remaining time
                    long remainingTime = timerDuration - elapsedTime;
                    //displayTime(remainingTime);
                }
            }


            //set burgers on map
            if (player.makeBurger) {
                if(currentMap==0) {
                    aSetter.makeBurger();
                }else if(currentMap==1) {
                    aSetter.makeBurger2();

                }
            }
            // set burritos on map
            if(player.makeBurrito){
                if(currentMap==1) {
                    aSetter.makeBurrito();
                }
            }
            if(player.borgar_cnt >=5 ){
                aSetter.spawnShip();
            }
            if(player.borgar_cnt >=2 && player.burrito_cnt >=4){

            }


            if (gameState == pauseState) {

            }
        }
    }
    public void resetComponentsBurr(){
        player.resetConsumablesForBurr();
        aSetter.setAgainConsumablesOnMap();
    }
    public void resetComponents(){
        player.resetConsumables();
        aSetter.setAgainConsumablesOnMap();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //DEBUG
        /*long drawStart = 0;
        drawStart = System.nanoTime();
        if(key.checkDrawTime){
            drawStart = System.nanoTime();
        }*/

        // for Menu
        if(gameState == titleState){
            ui.paint(g2);
        }
        else{
            //for tiles
            tileM.draw(g2);
            for(int i=0; i< obj[1].length;i++){
                if(obj[currentMap][i] != null){
                    obj[currentMap][i].draw(g2,this);

                }
            }



            //player
            player.draw(g2);
            ui.paint(g2);

            //DEBUG
            /*if(key.checkDrawTime){
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw time: "+ passed,10, 400);
                System.out.println("Draw time: "+ passed);
            }*/

            g2.dispose();

        }
        //for object interactions

    }
    private void startTimer()
    {
        timerStart = System.currentTimeMillis();
        timerRunning = true;
    }

    public void playSong(int i){
/*
        if(gameState == titleState){

            sound.setFile(1);
            sound.play();
            sound.loop();

        }
         if(gameState == playState) {
            sound.stop();

            sound.setFile(0);
            sound.play();
            sound.loop();
        }*/



    }
    public void stopSong(){
        //sound.stop();
    }
    public void playSE(int i){
       /* SE.setFile(i);
        SE.play();*/
    }
}
