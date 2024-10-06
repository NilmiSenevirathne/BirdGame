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
