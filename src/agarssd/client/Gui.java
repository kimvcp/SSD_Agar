package agarssd.client;

import agarssd.model.Item;
import agarssd.model.Player;
import agarssd.model.World;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private World world;
    private Player myPlayer;
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
                paintItems(g);
                paintPlayers(g);
            }
        };
        panel.setDoubleBuffered(true);
        add(panel);
        setSize(500, 500);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void update(World world, Player myPlayer) {
        this.world = world;
        this.myPlayer = myPlayer;
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
            if(!p.alive) {
                continue;
            }
            if(p.id == myPlayer.id) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.red);
            }
            g.fillOval((int) (p.positionX - p.size),
                    (int) (p.positionY - p.size),
                    (int) p.size * 2,
                    (int) p.size * 2);
        }
    }

    private void paintItems(Graphics g) {
        if(world == null) {
            return;
        }
        for(Item i : world.items) {
            g.setColor(Color.blue);
            g.fillOval((int) (i.positionX - i.size),
                    (int) (i.positionY - i.size),
                    (int) i.size * 2,
                    (int) i.size * 2);
        }
    }
}
