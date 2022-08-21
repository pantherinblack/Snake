import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame {
    private final GamePanel gamePanel = new GamePanel(MAX_X,MAX_Y);
    private int ypos = 0;
    private int xpos = -1;
    private int direction = 1;
    private int points = 5;
    private int bonusCount = 0;
    private static final int MAX_BONUS = 3;
    private static final int MAX_X = 20;
    private static final int MAX_Y = 20;
    private static final int BONUS_CHANCE = 3;
    private int lastDirection = 1;
    private boolean running = false;
    private final boolean randomColor = true;

    public GameFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,850);
        setUndecorated(true);
        setLayout(null);
        add(new Menu(this));
        add(gamePanel);
        setVisible(true);

        addKeyListener(new DirectionListener());
    }

    public void start() {
        if (running) running=false;
        else {
            xpos = -1;
            ypos = 0;
            direction = 1;
            points = 3;
            bonusCount = 0;
            lastDirection = 1;

            for (int x = 0; x < gamePanel.getXLength(); x++) {
                for (int y = 0; y < gamePanel.getYLength(); y++) {
                    gamePanel.getFieldPanel(x, y).setStatus(FieldPanel.FREE_STATUS);
                    gamePanel.getFieldPanel(x, y).setColor(Color.BLACK);
                    gamePanel.getFieldPanel(x, y).setTime(0);
                }
            }
            new Thread(this::run).start();
        }
    }

    public void tick() {
        requestFocus();
        if (ypos >= 0 && ypos <= MAX_Y-1 && xpos >= 0 && xpos <= MAX_X-1) gamePanel.getFieldPanel(ypos, xpos).setColor(Color.GREEN, direction, lastDirection, randomColor);
        lastDirection = direction;
        switch (direction) {
            case 0 -> ypos--;
            case 1 -> xpos++;
            case 2 -> ypos++;
            case 3 -> xpos--;
        }
        if (ypos <0 || ypos >MAX_Y-1 || xpos <0 || xpos >MAX_X-1) endGame();
        for (int x=0; x<gamePanel.getXLength(); x++) {
            for (int y = 0; y< gamePanel.getYLength(); y++) {
                if (gamePanel.getFieldPanel(x,y).getStatus()==1){
                    if (gamePanel.getFieldPanel(x,y).getTime()>points){
                        gamePanel.getFieldPanel(x,y).setStatus(FieldPanel.FREE_STATUS);
                        gamePanel.getFieldPanel(x,y).setTime(0);
                        gamePanel.getFieldPanel(x,y).setColor(Color.BLACK);
                    } else {
                        gamePanel.getFieldPanel(x,y).incrementTime();
                        if (randomColor) gamePanel.getFieldPanel(x,y).setColor(Color.GREEN, true);
                    }
                }
            }
        }
        if (gamePanel.getFieldPanel(ypos, xpos).getStatus()==1) endGame();
        else if (gamePanel.getFieldPanel(ypos, xpos).getStatus()==-1){
            gamePanel.getFieldPanel(ypos, xpos).setStatus(FieldPanel.SNAKE_STATUS);
            gamePanel.getFieldPanel(ypos, xpos).setColor(Color.GREEN, randomColor);
            points++;
            bonusCount--;
        } else {
            gamePanel.getFieldPanel(ypos, xpos).setStatus(FieldPanel.SNAKE_STATUS);
            gamePanel.getFieldPanel(ypos, xpos).setColor(Color.GREEN, randomColor);
        }

        if (bonusCount<MAX_BONUS && (Math.random()*BONUS_CHANCE)<1){
            addBonus();
        }
    }

    public void addBonus(){
        if (bonusCount<MAX_BONUS && bonusCount+points<MAX_X*MAX_Y){
            if (bonusCount+points==MAX_X*MAX_Y-1){
                for (int x=0; x< gamePanel.getXLength(); x++){
                    for (int y = 0; y< gamePanel.getYLength(); y++){
                        if (gamePanel.getFieldPanel(x,y).getStatus()==0){
                            gamePanel.getFieldPanel(x,y).setStatus(FieldPanel.BONUS_STATUS);
                            bonusCount++;
                            break;
                        }
                    }
                }
            } else {
                while (true) {
                    int x = (int) (Math.random()*MAX_X);
                    int y = (int) (Math.random()*MAX_Y);
                    if (gamePanel.getFieldPanel(x,y).getStatus()==0){
                        gamePanel.getFieldPanel(x,y).setStatus(FieldPanel.BONUS_STATUS);
                        gamePanel.getFieldPanel(x,y).setColor(Color.orange);
                        bonusCount++;
                        break;
                    }
                }
            }
        }
    }

    public void run(){
        running = true;
        while (points<MAX_Y*MAX_X && running) {
            tick();
            try {
                Thread.sleep(200-(100L *(points/MAX_Y/MAX_X)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void endGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new GameFrame();
    }

    class DirectionListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W, KeyEvent.VK_UP -> {
                    if (lastDirection!=2) direction=0;
                }
                case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                    if (lastDirection!=3) direction=1;
                }
                case KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                    if (lastDirection!=0) direction=2;
                }
                case KeyEvent.VK_A, KeyEvent.VK_LEFT -> {
                    if (lastDirection!=1) direction=3;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
