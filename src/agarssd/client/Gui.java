package agarssd.client;

import agarssd.model.Player;
import agarssd.model.World;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private World world;
    private JPanel panel;

    public Gui() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(world == null) {
                    return;
                }
                paintBackground(g);
                paintGrid(g);
                paintPlayers(g);
                paintItems(g);
            }
        };
        panel.setDoubleBuffered(true);
        add(panel);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void repaintWorld(World world) {
        this.world = world;
        panel.repaint();
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintGrid(Graphics g) {

    }

    private void paintPlayers(Graphics g) {
        if(world == null) {
            return;
        }
        for(Player p : world.players) {
            g.setColor(Color.red);
            g.fillOval((int) (p.positionX - p.size/2),
                    (int) (p.positionY - p.size/2),
                    (int) p.size,
                    (int) p.size);
        }
    }

    private void paintItems(Graphics g) {

    }
}
