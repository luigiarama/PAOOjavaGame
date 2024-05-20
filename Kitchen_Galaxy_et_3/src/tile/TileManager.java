package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[100];
        mapTileNum = new int [gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/map02.txt", 0);
        loadMap("/maps/map03.txt",1);
        loadMap("/maps/map04.txt",2);
    }
    public void getTileImage(){


            setup(1, "check_table1",true);
            setup(2,"check_table2",true);
            setup(3,"check_table3",true);
            setup(4,"check_table4",true);
            setup(5,"check",false);
            setup(6,"check_blat_ob",true);
            setup(7, "white",false);
            setup(9, "check_blat",true);
            setup(10,"cyan",false);
            setup(11, "check_corner",true);
            setup(12,"check_tabletop",true);
            setup(13,"check_blat_burger",true);
            setup(14,"check_getbread",true);
            setup(15,"kitchen_stove",true);
            setup(16,"check_getdish",true);
            setup(17,"check_getRawSteak",true);
            setup(18,"check_getSalad",true);
            setup(19,"check_trash",false);
            setup(20,"check_corner2",true);
            setup(21,"check_arrow",true);
            setup(22,"check_sink",true);
            setup(23,"carosabil",true);
            setup(24,"street",false);
            setup(25,"cyan_blat",true);
            setup(26,"cyan_corner",true);
            setup(27,"cyan_blat_corner2",true);
            setup(28, "sidewalk2",false);
            setup(29,"floorlv2",true);
            setup(30,"street_ship1",true);
            setup(31,"street_ship2",true);
            setup(32,"street_ship3",true);
            setup(33,"street_ship1v2",true);
            setup(34,"street_ship1v3",true);
            setup(35,"street_ship2v2",true);
            setup(36,"street_parking",false);
            setup(37,"cyan_blat_ice",true);
            setup(38,"cyan_blat_bowl",true);
            setup(39,"cyan_corner_pile",true);
            setup(40, "cyan_blat_obj",true);
            setup(41,"cyan_corner_obj",true);
            setup(42,"map3_floor",false);
            setup(50,"map3_blat",true);
            setup(51,"map3_corner",true);
            setup(52,"map3_world",true);
            setup(53,"map3_blat_obj",true);
    }
    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();
        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize,gp.tileSize);
            tile[index].collision = collision;
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath, int map){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row  < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[map][col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){

        }
    }
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
            worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image,screenX,screenY,null);
            }

            worldCol++;

            if(worldCol == gp.maxScreenCol) {
                worldCol = 0;

                worldRow++;
            }
        }

    }
}
