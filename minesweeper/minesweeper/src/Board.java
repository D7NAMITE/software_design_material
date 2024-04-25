import java.util.Random;

public class Board {
    private Cell[][] cells;
    public Board(int boardSize, int mineCount){
        initializeCells(boardSize);
        generateMines(mineCount);
        setSurroundingMines();

    }

    public Cell getCell(int x, int y){
        return cells[x][y];
    }
    private void initializeCells(int boardSize){
        cells = new Cell[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                cells[i][j] = new Cell();
            }
        }
    }

    private void generateMines(int mineCount){
        Random random = new Random();
        int minesPlaced = 0;
        while(minesPlaced < mineCount){
            int x = random.nextInt(cells.length);
            int y = random.nextInt(cells.length);
            if(!cells[x][y].isMine()){
                cells[x][y].setMine(true);
                minesPlaced++;
            }
        }
    }

    private void setSurroundingMines(){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells.length; j++){
                int count = getSurroundingMinesForCell(i, j);
                cells[i][j].setSurroundingMines(count);
            }
        }
    }

    private int getSurroundingMinesForCell(int x, int y){
        int mines = 0;
        for(int dx = -1; dx <= 1; dx++){
            for(int dy = -1; dy <= 1; dy++){
               int nx = x + dx;
               int ny = y + dy;
               if(dx == 0 && dy == 0){
                   continue;
               }
               if(nx < 0 || nx >= cells.length || ny < 0 || ny >= cells.length){
                   continue;
               }
               if(cells[nx][ny].isMine()){
                   mines++;
               }
            }
        }
        return mines;
    }
}
