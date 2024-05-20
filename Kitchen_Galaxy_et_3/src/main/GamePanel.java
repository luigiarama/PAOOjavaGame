package main;

import DB.MyJDBC;
import Entity.Player;
import objects.MyObjects;
import tile.TileManager;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

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
    ThemeSong sound = new ThemeSong();
    ThemeSong SE = new ThemeSong();
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
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int storyState = 3;
    public static final int storyState2 = 4;
    public static final int storyState3 = 5;
    public static final int gameOverState = 6;
    public static final int storyState4 = 7;




    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);

    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(int currentMap) {
        this.currentMap = currentMap;
    }

    public void setupGame(){

        aSetter.setObject();

        playSong(0);

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
            //level 3 respawn items for burger
            if (!reseter && player.breadPicked && player.saladPicked && player.steakPicked && player.dishPicked &&player.makeBurger && currentMap==2) {

                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponents();
                    scheduler.shutdown();
                }, 1, TimeUnit.SECONDS);

            }
            //level 3 respawn items for burrito
            if (!reseter && player.saladPicked &&  player.dishPicked &&player.makeBurrito && player.lipiePicked && player.chickenPicked && currentMap==2) {

                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponentsBurr();

                }, 1, TimeUnit.SECONDS);
            }
            //level 3 respawn items for salmon
            if (!reseter && player.saladPicked &&  player.dishPicked &&player.makeSalmon && player.fishPicked && currentMap==2) {

                scheduler = Executors.newScheduledThreadPool(1);
                reseter = true;
                scheduler.schedule(() -> {
                    reseter = false;
                    // Reset the flag for future checks
                    resetComponentsSalmon();

                }, 1, TimeUnit.SECONDS);
            }

            //set burgers on map
            if (player.makeBurger) {
                if(currentMap==0) {
                    aSetter.makeBurger();
                }else if(currentMap==1) {
                    aSetter.makeBurger2();

                }else if (currentMap == 2){
                    aSetter.makeBurger3();
                }
            }
            // set burritos on map
            if(player.makeBurrito && currentMap==1){
                aSetter.makeBurrito();
            }else if (player.makeBurrito && currentMap == 2){
                aSetter.makeBurrito2();
            }
            //set salmon on map
            if(player.makeSalmon && currentMap==2){
                aSetter.makeSalmon();
            }

            if(player.score >=0){
                aSetter.spawnShip();
            }
            //400
            if(player.score >= 0){
                aSetter.spawnShip2();
            }
            //810
            if(player.score >= 1000){
                aSetter.spawnFinale();
            }

        }
    }
    public void resetComponentsSalmon(){
        player.resetConsumablesForSalmon();
        aSetter.setObject();
    }
    public void resetComponentsBurr(){
        player.resetConsumablesForBurr();
        aSetter.setObject();
    }
    public void resetComponents(){
        player.resetConsumables();
        aSetter.setObject();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
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



            g2.dispose();

        }
    }

    //SONGS
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
         if(ui.nextImage == 8){
             sound.stop();
             i=3;
             sound.setFile(i);
             sound.play();
             sound.loop();
         }
         if(ui.nextImage > 11){
             sound.stop();
             sound.setFile(2);
             sound.play();
             sound.loop();

         }
    }
    public void stopSong(){
        sound.stop();
    }
    public void playSE(int i){
       /* SE.setFile(i);
        SE.play();*/
    }
    //DATA BASE
    public void insertDataToDatabase() {
        Connection conn = MyJDBC.getConnection();
        if (conn == null) {
            System.err.println("Failed to establish a database connection.");
            return;
        }
        //(`id_load_level_map`, `posX`, `posY`, `burger_cnt`, `dish_cnt`, `salad_cnt`, `steak_cnt`, `lipie_cnt`, `chicken_cnt`, `burrito_cnt`, `timer`)
        String sql = "INSERT INTO load_level (id_load_level_map, posX, posY, burger_cnt, dish_cnt, salad_cnt, steak_cnt, lipie_cnt, chicken_cnt, burrito_cnt, timer) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set values for the prepared statement
            stmt.setInt(1, getCurrentMap()); // assuming id_load_level_map is equivalent to currentMap
            stmt.setInt(2, player.getWorldX()); // posX
            stmt.setInt(3, player.getWorldY()); // posY
            stmt.setInt(4, player.getBorgar_cnt()); // burger_cnt
            stmt.setInt(5, player.getDish_cnt()); // dish_cnt
            stmt.setInt(6, player.getSalad_cnt()); // salad_cnt
            stmt.setInt(7, player.getSteak_cnt()); // steak_cnt
            stmt.setInt(8, player.getLipie_cnt()); // lipie_cnt
            stmt.setInt(9, player.getChicken_cnt()); // chicken_cnt
            stmt.setInt(10, player.getBurrito_cnt()); // burrito_cnt
            stmt.setDouble(11, ui.getPlayTime()); // timer
            // Executarea comenzii SQL
            stmt.executeUpdate();
            System.out.println("Data inserted successfully.");



        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJDBC.closeConnection(conn);
        }
    }
    public void setVariablesFromDatabase() {
        Connection connection = MyJDBC.getConnection();

        try {
            // Creează o interogare SQL pentru a obține valorile dorite din baza de date
            String query = "SELECT id_load_level_map, posX, posY, burger_cnt, dish_cnt, salad_cnt, steak_cnt, lipie_cnt, chicken_cnt, burrito_cnt, timer FROM load_level ";


            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execută interogarea și obține rezultatul
            ResultSet resultSet = preparedStatement.executeQuery();

            // Verifică dacă există un rând în rezultat (ar trebui să existe doar unul)
            if (resultSet.next()) {
                // Setează variabilele din clasele corespunzătoare cu valorile din baza de date
                setCurrentMap(resultSet.getInt("id_load_level_map"));
                player.setWorldX(resultSet.getInt("posX"));
                player.setWorldY(resultSet.getInt("posY"));
                player.setBorgar_cnt(resultSet.getInt("burger_cnt"));
                player.setDish_cnt(resultSet.getInt("dish_cnt"));
                player.setSalad_cnt(resultSet.getInt("salad_cnt"));
                player.setSteak_cnt(resultSet.getInt("steak_cnt"));
                player.setLipie_cnt(resultSet.getInt("lipie_cnt"));
                player.setChicken_cnt(resultSet.getInt("chicken_cnt"));
                player.setBurrito_cnt(resultSet.getInt("burrito_cnt"));
                ui.setPlayTime(resultSet.getDouble("timer"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyJDBC.closeConnection(connection);
        }
    }
}
