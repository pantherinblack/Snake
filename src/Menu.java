import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Menu extends JPanel {


    private final GameFrame gameFrame;

    public Menu(GameFrame gameFrame) {
        final JLabel close = new JLabel("X");
        final JLabel start = new JLabel("Start");

        final JPanel sizePanel = new JPanel();
        final JPanel extraSizePanel = new JPanel();
        final JLabel sizeLabel = new JLabel("Size");
        final JTextField size = new JTextField("20", 3);

        final JPanel max_BonusPanel = new JPanel();
        final JPanel extraMax_BonusPanel = new JPanel();
        final JLabel max_BonusLabel = new JLabel("Max. Bonus");
        final JTextField max_Bonus = new JTextField("3", 3);

        final JPanel bonus_chancePanel = new JPanel();
        final JPanel extraBonus_chancePanel = new JPanel();
        final JLabel bonus_chanceLabel = new JLabel("Bonus Chance 1:x");
        final JTextField bonus_chance = new JTextField("3", 3);

        final JLabel colorLabel = new JLabel("Snake-color");

        final JLabel randomColorLabel = new JLabel("Random Color");
        this.gameFrame = gameFrame;
        setBounds(0, 0, 800, 50);
        setBackground(Color.DARK_GRAY);
        setLayout(new GridLayout(1, 7));

        close.addMouseListener(new ButtonListener(close, false));
        close.setForeground(Color.red);

        start.addMouseListener(new ButtonListener(start, true));

        colorLabel.addMouseListener(new ButtonListener(colorLabel, true));

        randomColorLabel.addMouseListener(new ButtonListener(randomColorLabel, true));

        extraSizePanel.setLayout(new BorderLayout());
        extraSizePanel.add(sizePanel, BorderLayout.CENTER);
        sizePanel.setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
        sizePanel.setLayout(new GridLayout(2, 1));
        sizePanel.add(sizeLabel);
        sizePanel.add(size);

        extraMax_BonusPanel.setLayout(new BorderLayout());
        extraMax_BonusPanel.add(max_BonusPanel, BorderLayout.CENTER);
        max_BonusPanel.setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
        max_BonusPanel.setLayout(new GridLayout(2, 1));
        max_BonusPanel.add(max_BonusLabel);
        max_BonusPanel.add(max_Bonus);

        extraBonus_chancePanel.setLayout(new BorderLayout());
        extraBonus_chancePanel.add(bonus_chancePanel, BorderLayout.CENTER);
        bonus_chancePanel.setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
        bonus_chancePanel.setLayout(new GridLayout(2, 1));
        bonus_chancePanel.add(bonus_chanceLabel);
        bonus_chancePanel.add(bonus_chance);


        add(start);
        add(extraSizePanel);
        add(extraMax_BonusPanel);
        add(extraBonus_chancePanel);
        add(colorLabel);
        add(randomColorLabel);
        add(close);
    }

    class ButtonListener implements MouseListener {
        private final boolean border;

        public ButtonListener(JLabel self, boolean border) {
            this.border = border;
            if (border) self.setBorder(BorderFactory.createLineBorder(Color.green, 3, true));
            self.setBackground(Color.DARK_GRAY);
            self.setOpaque(true);
            self.setForeground(Color.cyan);
            self.setHorizontalAlignment(0);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (((JLabel) e.getSource()).getText()) {
                case "X" -> System.exit(0);
                case "Start" -> gameFrame.start();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(new Color(80, 80, 80));
            if (border)
                ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(new Color(20, 100, 20), 3, true));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JLabel) e.getSource()).setBackground(Color.darkGray);
            if (border) ((JLabel) e.getSource()).setBorder(BorderFactory.createLineBorder(Color.green, 3, true));

        }
    }
}
