package org.example;

import java.awt.*;
import javax.swing.*;

public class FlappyBird extends JPanel {
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

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.blue);

        // Load images (using absolute path)
        backgroundImage = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybirdbg.png").getImage();
        birdImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybird.png").getImage();
        bottomPipeImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/bottompipe.png").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        // Draw background
        g.drawImage(backgroundImage, 0, 0, boardWidth, boardHeight, null);
    }
}
