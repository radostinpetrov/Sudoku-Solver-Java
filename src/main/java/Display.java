import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Display implements Updatable {

  private JButton[][] cells;

  Display(Sudoku.Controller controller) {

    JFrame frame = new JFrame("Sudoku");
    frame.setSize(900, 600);
    frame.setLayout(new BorderLayout());

    JPanel sudokuPane = new JPanel();
    final int ROWS = 9;
    final int COLUMNS = 9;
    cells = new JButton[ROWS][COLUMNS];
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        JButton cell = new JButton();
        int finalI = i;
        int finalJ = j;
        cell.addKeyListener(new KeyListener() {
          @Override
          public void keyTyped(KeyEvent keyEvent) {
            // auto generated method
          }

          @Override
          public void keyPressed(KeyEvent keyEvent) {
            int num = keyEvent.getKeyChar() - 48;
            if (num >= 1 && num <= 9) {
              controller.updateCell(finalI, finalJ, num);
              updateButton(finalI, finalJ, Integer.toString(num));
            }
          }

          @Override
          public void keyReleased(KeyEvent keyEvent) {
            // auto generated method
          }
        });
        cells[i][j] = cell;
        sudokuPane.add(cell);
      }
    }
    sudokuPane.setBorder(new EmptyBorder(3, 3, 2, 2));
    sudokuPane.setLayout(new GridLayout(9, 9, 2, 2));

    frame.add(sudokuPane);

    JPanel menuPane = new JPanel();
    menuPane.setBorder(new EmptyBorder(4, 4, 4, 4));
    menuPane.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JButton startButton = new JButton("Start");
    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        controller.initiate();
        for (int i = 0; i < ROWS; i++) {
          for (int j = 0; j < COLUMNS; j++) {
            updateButton(i, j, String.valueOf(controller.getGrid()[i][j]));
          }
        }
      }
    });
    menuPane.add(startButton, gbc);
    gbc.gridy++;
    JButton solveButton = new JButton("Solve");
    solveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        controller.solve();
        for (int i = 0; i < ROWS; i++) {
          for (int j = 0; j < COLUMNS; j++) {
            updateButton(i, j, String.valueOf(controller.getGrid()[i][j]));
          }
        }
      }
    });
    menuPane.add(solveButton, gbc);
    gbc.gridy++;
    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        controller.reset();
        for (int i = 0; i < ROWS; i++) {
          for (int j = 0; j < COLUMNS; j++) {
            updateButton(i, j, String.valueOf(controller.getGrid()[i][j]));
          }
        }
      }
    });
    menuPane.add(resetButton, gbc);

    frame.add(menuPane, BorderLayout.AFTER_LINE_ENDS);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


  @Override
  public void updateButton(int row, int col, String num) {
    if ((!num.equals("0"))) {
      cells[row][col].setText(num);
    } else {
      cells[row][col].setText(null);
    }

  }


}
