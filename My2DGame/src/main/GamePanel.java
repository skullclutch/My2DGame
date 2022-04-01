package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16X16 tile
    final int scale = 3; // 16X3 = 48 pixels

    public int tileSize = originalTileSize * scale; //48X48 tile
    final int maxScreenCol = 16; //16 tiles horizontally
    final int maxScreenRow = 12; //12 tiles vertically
    final int screenWidth = tileSize * maxScreenCol; //768 pixels horizontally
    final int screenHeight = tileSize * maxScreenRow; //576 pixels vertically

    //FPS
    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // Set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;



    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // If set to true all the drawing from this component will be done in an offscreen painting buffer(can improve game's rendering performance)
        this.addKeyListener(keyH);
        this.setFocusable(true); // with this, this GamePanel can be "focused" to receive key input


    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();//automatically calls run method
    }

//    @Override
//    public void run() {
//        //GameLoop
//
//        double drawInterval = 100000000 / FPS; //1billion nanoseconds which is 1 seconds. We use 1 Billion there bc our game is running in nanoseconds(Draw the screen every 0.01666 seconds)
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//
////            long currentTime = System.nanoTime(); // Returns the current value of the running Java Virtual Machine's high-resolution time source, in nanoseconds
////            long currentTime2 = System.currentTimeMillis(); // Returns the current time in milliseconds
//
//
//            // 1 UPDATE: update information such as character positions
//            update();
//
//            // 2 DRAW: draw the screen with the updated information
//            repaint(); // to call paintComponent method
//
//
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//
//    }

    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update() {

        //Update method gets called 60 times per second every where it is called

        player.update();

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g); //Parent class is JPanel

        Graphics2D g2 = (Graphics2D)g;

        player.draw(g2);

        g2.dispose(); //Dispose of this graphics context and release any system resources that it is using



    }
}
