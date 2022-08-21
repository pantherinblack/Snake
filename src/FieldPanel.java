import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

public class FieldPanel extends JPanel {
    private final JLabel label = new JLabel();
    private int time = 0;
    public static final int FREE_STATUS = 0;
    public static final int BONUS_STATUS = -1;
    public static final int SNAKE_STATUS = 1;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status = 0;

    public void incrementTime() {
        time++;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public FieldPanel() {
        add(label);
        setColor(Color.BLACK);
    }

    public void setColor(Color color){
        setBackground(color);
        setBorder(null);
    }

    public void setColor(Color color, int direction, int lastdirection){
        setColor(color);
        int top = 4;
        int right = 4;
        int bot = 4;
        int left = 4;
        switch (direction){
            case 0 -> top = 0;
            case 1 -> right = 0;
            case 2 -> bot = 0;
            case 3 -> left = 0;
        }
        switch (lastdirection){
            case 2 -> top = 0;
            case 3 -> right = 0;
            case 0 -> bot = 0;
            case 1 -> left = 0;
        }
        setBorder(new MatteBorder(top,left,bot,right,Color.black));
    }

    public void setColor(Color color, boolean random){
        if (random) setColor(new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0)));
        else setColor(color);
    }

    public void setColor(Color color, int direction, int lastdirection, boolean random){
        if (random) setColor(new Color((int)(Math.random()*255.0),(int)(Math.random()*255.0),(int)(Math.random()*255.0)), direction, lastdirection);
        else setColor(color,direction,lastdirection);
    }

}
