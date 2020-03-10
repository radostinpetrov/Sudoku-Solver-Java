public class Model {

  private final int ROWS = 9;
  private final int COLS = 9;

  Updatable display;
  int[] grid;

  Model(Updatable display) {
    this.display = display;
    resetGrid();
  }

  void initiateGrid() {
    grid = new int[]{3, 0, 6, 5, 0, 8, 4, 0, 0,
        5, 2, 0, 0, 0, 0, 0, 0, 0,
        0, 8, 7, 0, 0, 0, 0, 3, 1,
        0, 0, 3, 0, 1, 0, 0, 8, 0,
        9, 0, 0, 8, 6, 3, 0, 0, 5,
        0, 5, 0, 0, 9, 0, 6, 0, 0,
        1, 3, 0, 0, 0, 0, 2, 5, 0,
        0, 0, 0, 0, 0, 0, 0, 7, 4,
        0, 0, 5, 2, 0, 6, 3, 0, 0};
  }

  void resetGrid() {
    grid = new int[ROWS*COLS];
  }

  int[] getGrid() {
    return grid;
  }

  void updateCell(int gridX, int gridY, int num) {
    grid[gridX * COLS + gridY] = num;
  }

  boolean isValid(int num, int x, int y) {

    // checking row validity
    for (int j = 0; j < COLS; j++) {
      if (grid[x * COLS + j] == num && y != j) {
        return false;
      }
    }

    // checking column validity
    for (int i = 0; i < ROWS; i++) {
      if (grid[i * COLS + y] == num && x != i) {
        return false;
      }
    }


    // checking sector (3x3) validity
    int sector_x = x / 3; // there are 3 sectors per row
    int sector_y = y / 3; // and 3 sectors per column

    for (int i = sector_x * 3; i < sector_x * 3 + 3; i++) {
      for (int j = sector_y * 3; j < sector_y * 3 + 3; j++) {
        if ((x != i || y != j) && grid[i * COLS + j]==num) {
          return false;
        }
      }
    }
    return true;
  }

}
