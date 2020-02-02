import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Display implements Updatable {

  Display(Sudoku.Controller controller) {

    JFrame frame = new JFrame("Sudoku");
    frame.setSize(900, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(new SudokuBoard());
    frame.add(new MenuPane(), BorderLayout.AFTER_LINE_ENDS);
    frame.setVisible(true);
  }


  public static class MenuPane extends JPanel {

    public MenuPane() {
      setBorder(new EmptyBorder(4, 4, 4, 4));
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      add(new JButton("Start"), gbc);
      gbc.gridy++;
      add(new JButton("Solve"), gbc);
      gbc.gridy++;
      add(new JButton("Reset"), gbc);

    }

  }

  // Set up the board with 3x3 sectors (3x3 each)
  public static class SudokuBoard extends JPanel{
    public final int rows = 3;
    public final int cols = 3;

    private Sector[][] sectors;

    public SudokuBoard() {
      setBorder(new EmptyBorder(3,3,2,2));
      sectors = new Sector[rows][cols];
      setLayout(new GridLayout(rows, cols, 2, 2));
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          //int index = (i * rows) + j;
          Sector sector = new Sector(rows, cols);
          sector.setBorder(new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(4, 4, 4, 4)));
          sectors[i][j] = sector;
          add(sector);
        }
      }
    }

  }

  public static class Sector extends JPanel {
    private JButton[][] cells;

    public Sector(int rows, int cols) {
      setBorder(new LineBorder(Color.LIGHT_GRAY));
      setLayout(new GridLayout(rows, cols, 2, 2));
      cells = new JButton[rows][cols];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          JButton cell = new JButton();
          cells[i][j] = cell;
          add(cell);
        }
      }
    }
  }

  @Override
  public void updateButton(JButton button, String num) {
    button.setText(num);
  }
}
