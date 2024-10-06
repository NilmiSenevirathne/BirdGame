package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int boardWidth = 360;
    int boardHeight = 640;

    // Images
    Image backgroundImage;
    Image birdImg;
    Image bottomPipeImg;
    Image topPipeImg;

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

    //pipes
    int pipeX = boardWidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX;
        int y = pipeY;
        int width = pipeWidth;
        int height = pipeHeight;
        Image img;
        boolean passed= false;

        Pipe(Image img){
            this.img=img;
        }

    }

    //game logic
    Bird bird;
    int velocityX = -4;// move pipes to the left speed(simulate the bird moving right)
    int velocityY = 0;// move bird up/down speed
    int gravity = 1;

    ArrayList<Pipe> pipes;
    Random random = new Random();

    Timer gameLoop;
    Timer placePipesTimer;

    boolean gameOver = false;

    FlappyBird() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
//        setBackground(Color.blue);
        setFocusable(true);
        addKeyListener(this);


        // Load images (using absolute path)
        backgroundImage = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybirdbg.png").getImage();
        birdImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/flappybird.png").getImage();
        bottomPipeImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/bottompipe.png").getImage();
        topPipeImg = new ImageIcon("D:/My Projects/BirdGame/src/main/java/org/example/toppipe.png").getImage();

        //bird
        bird  = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();

        //place pipe timer
        placePipesTimer  = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placePipes();
            }
        });
        placePipesTimer.start();

        //game timer
        gameLoop  = new Timer(1000/60,this);
        gameLoop.start();
    }

    public void placePipes(){
        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int Spaceway = boardHeight/4;

        Pipe toppipe = new Pipe(topPipeImg);
        toppipe.y = randomPipeY;
        pipes.add(toppipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = toppipe.y+ pipeHeight + Spaceway;
        pipes.add(bottomPipe);
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

        //pipes
        for(int i =0;i<pipes.size();i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img,pipe.x,pipe.y,pipe.width,pipe.height,null);
        }
    }

    public void move(){
        //bird
        velocityY += gravity;
        bird.y += velocityY;
        bird.y = Math.max(bird.y,0);

        //pipes
        for(int i = 0;i<pipes.size();i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX;
        }
    }
}
