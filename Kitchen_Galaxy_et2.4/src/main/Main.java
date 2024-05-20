package main;

import DB.Test;
import main.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = Singleton.getInstance();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Kitchen Galaxy");

        GamePanel gamePanel =  new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
        Test test = new Test();
        test.fetchData();


    }
}