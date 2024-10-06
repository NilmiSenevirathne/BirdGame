package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImage;
    Image birdImg;
    Image bottomPipeImg;

    //Bird
    int birdX = boardWidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24;

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }



    @Override
    public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_SPACE){
             velocityY = -9;
         }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    class Bird{
        int x = birdX;
        int y = birdY;
        int width =birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img=img;
        }
    }

    //game logic
    Bird bird;
    int velocityY = 0;
    int gravity = 1;
    Timer gameLoop;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);


        // Load images (using absolute path)
        backgroundImage = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybirdbg.png").getImage();
        birdImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybird.png").getImage();
        bottomPipeImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/bottompipe.png").getImage();

        //bird
        bird  = new Bird(birdImg);

        //game timer
        gameLoop  = new Timer(1000/60,this);
        gameLoop.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        System.out.println(" Running");
        // Draw background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);

        //Draw bird
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0);
    }
}
