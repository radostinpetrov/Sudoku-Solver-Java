import java.util.ArrayList;
import java.util.List;

public class Sudoku {

  private Display sudokuDisplay = new Display(new Controller());
  private Model sudokuModel = new Model(sudokuDisplay);

  class Controller {

    void initiate() {
      sudokuModel.initiateGrid();
      sudokuDisplay.updateBoard();
    }

    void reset() {
      sudokuModel.resetGrid();
      sudokuDisplay.updateBoard();
    }

    void updateCell(int gridX, int gridY, int num) {
      sudokuModel.updateCell(gridX, gridY, num);
      sudokuDisplay.updateBoard();
    }



    public int getCell(int row, int col){
      return sudokuModel.getGrid()[row][col];
    }

    public boolean solve() {
      int[][] grid = sudokuModel.getGrid();
      for (int i = 0; i < Display.ROWS; i++) {
        for (int j = 0; j < Display.COLUMNS; j++) {
          if(grid[i][j] == 0) {
            for (int num = 1; num < 10; num++) {
              sudokuModel.updateCell(i, j, num);
              if (sudokuModel.isValid(num, i, j) && solve()) {
                return true;
              }
              sudokuModel.updateCell(i,j,0);
            }
            return false;
          }
        }
      }
      sudokuDisplay.updateBoard();
      return true;
    }
  }

  public static void main(String[] args) {
    new Sudoku();
  }
}
