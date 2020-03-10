public class Model {

  private final int ROWS = 9;
  private final int COLS = 9;

  Updatable display;
  int[][] grid;

  Model(Updatable display) {
    this.display = display;
    resetGrid();
  }

  void initiateGrid() {
    grid = new int[][]{{3, 0, 6, 5, 0, 8, 4, 0, 0},
        {5, 2, 0, 0, 0, 0, 0, 0, 0},
        {0, 8, 7, 0, 0, 0, 0, 3, 1},
        {0, 0, 3, 0, 1, 0, 0, 8, 0},
        {9, 0, 0, 8, 6, 3, 0, 0, 5},
        {0, 5, 0, 0, 9, 0, 6, 0, 0},
        {1, 3, 0, 0, 0, 0, 2, 5, 0},
        {0, 0, 0, 0, 0, 0, 0, 7, 4},
        {0, 0, 5, 2, 0, 6, 3, 0, 0}};
  }

  void resetGrid() {
    grid = new int[ROWS][COLS];
  }

  int[][] getGrid() {
    return grid;
  }

  void updateCell(int gridX, int gridY, int num) {
    grid[gridX][gridY] = num;
  }

  boolean isValid(int num, int x, int y) {
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
      for (int j = sector_y * 3; j < sector_y * 3 + 3; j++) {
        if ((x != i || y != j) && grid[i][j]==num) {
          return false;
        }
      }
    }
    return true;
  }

}
