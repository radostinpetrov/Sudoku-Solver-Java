public class Model {

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
    grid = new int[9][9];
  }

  int[][] getGrid() {
    return grid;
  }

  void updateCell(int gridX, int gridY, int num) {
    grid[gridX][gridY] = num;
  }

}
