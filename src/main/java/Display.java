import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Display implements Updatable {

  private static Sudoku.Controller controller;

  Display(Sudoku.Controller controller) {

    Display.controller = new Sudoku.Controller();

    JFrame frame = new JFrame("Sudoku");
    frame.setSize(900, 600);
    frame.setLayout(new BorderLayout());

    frame.add(new SudokuBoard());
    frame.add(new MenuPane(), BorderLayout.AFTER_LINE_ENDS);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


  public class MenuPane extends JPanel {

    public MenuPane() {
      setBorder(new EmptyBorder(4, 4, 4, 4));
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.weightx = 1;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      JButton startButton = new JButton("Start");
      startButton.addActionListener(e -> controller.initiate());
      add(startButton, gbc);
      gbc.gridy++;
      JButton solveButton = new JButton("Solve");
      solveButton.addActionListener(e -> controller.solve());
      add(solveButton, gbc);
      gbc.gridy++;
      JButton resetButton = new JButton("Reset");
      resetButton.addActionListener(e -> controller.reset());
      add(resetButton, gbc);
    }

  }

  // Set up the board with 3x3 sectors (3x3 each)
  public class SudokuBoard extends JPanel {

    public final int rows = 3;
    public final int cols = 3;

    private Sector[][] sectors;

    public SudokuBoard() {
      setBorder(new EmptyBorder(3, 3, 2, 2));
      sectors = new Sector[rows][cols];
      setLayout(new GridLayout(rows, cols, 2, 2));
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          Sector sector = new Sector(rows, cols, (3 * i + j));
          sector.setBorder(
              new CompoundBorder(new LineBorder(Color.GRAY, 1), new EmptyBorder(4, 4, 4, 4)));
          sectors[i][j] = sector;
          add(sector);
        }
      }
    }

  }

  public class Sector extends JPanel {

    private JButton[][] cells;

    public Sector(int rows, int cols, int sectorNum) {
      setBorder(new LineBorder(Color.LIGHT_GRAY));
      setLayout(new GridLayout(rows, cols, 2, 2));
      cells = new JButton[rows][cols];
      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          JButton cell = new JButton();
          int boardI = (sectorNum / 3) * 3 + i ;
          int boardJ = (sectorNum % 3) * 3 + j;
          cell.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
              // auto generated method
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
              int num = keyEvent.getKeyChar() - 48;
              if (num >= 1 && num <= 9) {
                controller.updateCell(boardI, boardJ, num);
                cell.setText(String.valueOf(keyEvent.getKeyChar()));
              }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
              // auto generated method
            }
          });

          cells[i][j] = cell;
          add(cell);
        }
      }
    }
  }

  @Override
  public void updateBoard() {

  }


}
