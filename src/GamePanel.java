import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private final FieldPanel[][] fieldPanels;
    public GamePanel(int with, int height) {
        fieldPanels = new FieldPanel[with][height];
        setBounds(0,50,800,800);
        setLayout(new GridLayout(height,with,0,0));
        for (int x=0; x<fieldPanels.length; x++) {
            for (int y=0; y<fieldPanels[x].length; y++) {
                fieldPanels[x][y] = new FieldPanel();
                add(fieldPanels[x][y]);
            }
        }
    }

    public FieldPanel getFieldPanel(int x, int y) {
        return fieldPanels[x][y];
    }

    public int getXLength(){
        return fieldPanels.length;
    }

    public int getYLength(){
        return fieldPanels[0].length;
    }
}
