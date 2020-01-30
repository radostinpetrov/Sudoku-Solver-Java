public class Model {

  private final Updatable display;
  private int[][] grid;

  Model(Updatable display) {
    this.display = display;
    grid = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        grid[i][j] = 0;
      }
    }
  }

  int[][] getGrid() {
    return grid;
  }

  void updateCell(int gridX, int gridY, int num) {
    grid[gridX][gridY] = num;
  }
}
