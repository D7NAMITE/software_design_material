import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game extends JFrame {
    private int boardSize = 10;
    private int mineCount = 10;
    private Board board = new Board(boardSize, mineCount);
    private Gui gui = new Gui();
    private boolean gameOver = false;
    private int flagCounter = mineCount;
    private JLabel flagCountLabel;
    private int lastClickedMineX = -1;
    private int lastClickedMineY = -1;

    public Game() {
        flagCountLabel = new JLabel("Flags: " + flagCounter);
        add(flagCountLabel, BorderLayout.NORTH);
        add(gui);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void updateFlagCountLabel() {
        flagCountLabel.setText("Flags: " + flagCounter);
    }

    private void revealCell(int x, int y) {
        Cell cell = board.getCell(x, y);
        if (!cell.isHidden() || cell.isFlagged()) {
            return;
        }
        cell.setHidden(false);
        if (cell.isMine()) {
            gameOver = true;
            lastClickedMineX = x;
            lastClickedMineY = y;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    Cell c = board.getCell(i, j);
                    if (c.isFlagged() && !c.isMine()) {
                        c.setFlagged(false);
                        c.setMine(true);
                        c.setCrossed(true);
                        c.setHidden(true);
                    }
                    if (c.isFlagged()) {
                        c.setMine(false);
                    }
                    if (c.isFlagged() && c.isMine()) {
                        c.setFlagged(false);
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Game Over");
            showAllMines();
            return;
        }
        if (cell.getSurroundingMines() == 0) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    int nx = x + dx;
                    int ny = y + dy;
                    if (dx == 0 && dy == 0) {
                        continue;
                    }
                    if (nx < 0 || nx >= boardSize || ny < 0 || ny >= boardSize) {
                        continue;
                    }
                    revealCell(nx, ny);
                }
            }
        }
    }

    private void toggleFlagOnCell(int x, int y) {
        Cell cell = board.getCell(x, y);
        if (!cell.isHidden()) {
            return;
        }
        if (cell.isFlagged()) {
            cell.setFlagged(false);
            flagCounter++;
        } else {
            cell.setFlagged(true);
            flagCounter--;
        }
        updateFlagCountLabel();
    }

    private void checkWinning() {
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                Cell cell = board.getCell(x, y);
                if (cell.isHidden() && !cell.isMine()) {
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "You Win!");
        System.exit(0);
    }

    private void showAllMines() {
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                Cell cell = board.getCell(x, y);
                if (cell.isMine()) {
                    cell.setHidden(false);
                }
            }
        }
    }

    class Gui extends JPanel {
        private int cellSize = 30;
        private int numPaddingx = 10, numPaddingy = 20;
        private Image imageCell = new ImageIcon("Images/Cell.png").getImage();
        private Image imageFlag = new ImageIcon("Images/Flag.png").getImage();
        private Image imageMine = new ImageIcon("Images/Mine.png").getImage();
        private Image imageCross = new ImageIcon("Images/Cross.png").getImage();
        private Color[] numberColors = new Color[]{
                Color.blue,
                Color.green,
                Color.red,
                Color.black,
                Color.orange,
                Color.black,
                Color.pink,
                Color.yellow,
                Color.BLACK
        };

        public Gui() {
            setPreferredSize(new Dimension(boardSize * cellSize, boardSize * cellSize));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    checkWinning();
                    if (gameOver) {
                        showAllMines();
                        return;
                    }
                    int x = e.getX() / cellSize;
                    int y = e.getY() / cellSize;
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        revealCell(x, y);
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        toggleFlagOnCell(x, y);
                    }
                    repaint();
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int x = 0; x < boardSize; x++) {
                for (int y = 0; y < boardSize; y++) {
                    Cell cell = board.getCell(x, y);
                    int px = x * cellSize;
                    int py = y * cellSize;
                    if (cell.isHidden()) {
                        g.drawImage(imageCell, px, py, cellSize, cellSize, this);
                    } else {
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(px, py, cellSize, cellSize);
                        if (cell.isMine()) {
                            if (gameOver && x == lastClickedMineX && y == lastClickedMineY) {
                                g.setColor(Color.RED);
                                g.fillRect(px, py, cellSize, cellSize);
                            }
                            g.drawImage(imageMine, px, py, cellSize, cellSize, this);
                        } else if (cell.getSurroundingMines() > 0) {
                            g.setColor(numberColors[cell.getSurroundingMines() - 1]);
                            g.drawString(cell.getSurroundingMines() + "", px + numPaddingx, py + numPaddingy);
                        }
                    }
                    if (cell.isFlagged()) {
                        g.drawImage(imageFlag, px, py, cellSize, cellSize, this);
                    }
                    if (cell.isCrossed()) {
                        g.drawImage(imageCross, px, py, cellSize, cellSize, this);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
