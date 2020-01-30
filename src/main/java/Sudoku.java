import java.util.ArrayList;
import java.util.List;

public class Sudoku {

  private Display sudokuDisplay = new Display(new Controller());
  private Model sudokuModel = new Model(sudokuDisplay);

  class Controller {

    void updateGrid(int gridX, int gridY, char input) {
      int num = input - 48;
      sudokuModel.updateCell(gridX, gridY, num);
    }

    boolean isValid(int num, int x, int y) {
      int[][] grid = sudokuModel.getGrid();
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[x][j] == num && y != j) {
          return false;
        }
      }
      for (int i = 0; i < grid.length; i++) {
        if (grid[i][y] == num && x != i) {
          return false;
        }
      }

      int sector_x = x / 3;
      int sector_y = y / 3;

      for (int i = sector_x * 3; i < sector_x * 3 + 3; i++) {
        for (int j = sector_y * 3; j < sector_y * 3+ 3; j++) {
          if (x != i || y != j) {
            return grid[i][j] != num;
          }
        }
      }
      return true;
    }

    private List<Integer> findEmpty() {
      int[][] grid = sudokuModel.getGrid();
      List<Integer> emptyCell = new ArrayList<>();
      for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[i].length; j++) {
          if (grid[i][j] == 0) {
            emptyCell.add(i);
            emptyCell.add(j);
            return emptyCell;
          }
        }
      }
      return emptyCell;
    }

    private boolean solve() {
      int[][] grid = sudokuModel.getGrid();
      List<Integer> empty = findEmpty();
      if(empty.isEmpty()){
        return true;
      } else {
        int row = empty.get(0);
        int col = empty.get(1);
        for (int i = 1; i < 10; i++) {
          if(isValid(i, row, col)) {
            sudokuModel.updateCell(row, col, i);
            if (solve()) {
              return true;
            }
            sudokuModel.updateCell(row, col, 0);
          }
        }
      }
      return false;
    }

  }

  public static void main(String[] args) {
    new Sudoku();
  }
}
