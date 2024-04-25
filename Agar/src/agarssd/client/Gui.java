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
    private int size = 500;
    private boolean gameOver = false;

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
        setSize(size, size);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void registerMyPlayer(Player myPlayer) {
        this.myPlayer = myPlayer;
    }

    public void update(World world) {
        this.world = world;
        checkMyPlayerAlive();
        panel.repaint();
    }

    private void checkMyPlayerAlive() {
        if(gameOver) {
            return;
        }
        for(Player p: world.players) {
            if(p.id != myPlayer.id) {
                continue;
            }
            if(!p.alive && !gameOver) {
                gameOver = true;
                JOptionPane.showMessageDialog(
                        null,
                        "You Lose",
                        "Try Again :)",
                        0);
            }
        }
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void paintGrid(Graphics g) {
        int cellWidth = 20;
        int cellCount = size/cellWidth;
        g.setColor(Color.lightGray);
        for(int i = 0; i < cellCount; i++) {
            g.drawLine(0, cellWidth * i, size, cellWidth * i);
            g.drawLine(i * cellCount, 0,  i * cellCount, size);
        }
    }

    private void paintPlayers(Graphics g) {
        if(world == null) {
            return;
        }
        for(Player p : world.players) {
            if(!p.alive) {
                continue;
            }
            // Border
            int border = 1;
            g.setColor(Color.black);
            g.fillOval((int) (p.positionX - p.size - border ),
                    (int) (p.positionY - p.size - border),
                    (int) p.size * 2 + border * 2,
                    (int) p.size * 2 + border * 2);

            // Inner
            if(p.id == myPlayer.id) {
                g.setColor(Color.green);
            } else {
                g.setColor(Color.red);
            }
            g.fillOval((int) (p.positionX - p.size),
                    (int) (p.positionY - p.size),
                    (int) p.size * 2,
                    (int) p.size * 2);
            if(p.id == myPlayer.id) {
                g.drawString("You", (int)(p.positionX + p.size), (int)(p.positionY + p.size));
            }
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
