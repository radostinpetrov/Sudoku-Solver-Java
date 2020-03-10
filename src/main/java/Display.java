import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Display implements Updatable {

  private Sudoku.Controller controller;
  private JButton[][] cells;
  static final int ROWS = 9;
  static final int COLS = 9;

  Display(Sudoku.Controller controller) {

    this.controller = controller;
    JFrame frame = new JFrame("Sudoku");
    frame.setSize(900, 600);
    frame.setLayout(new BorderLayout());

    // main board pane
    JPanel sudokuPane = new JPanel();

    cells = new JButton[ROWS][COLS];
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
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
              updateBoard();
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


    // setting up the menu buttons on the side
    JPanel menuPane = new JPanel();
    menuPane.setBorder(new EmptyBorder(4, 4, 4, 4));
    menuPane.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JButton startButton = new JButton("Start");
    startButton.addActionListener(actionEvent -> {
      controller.initiate();
    });
    menuPane.add(startButton, gbc);
    gbc.gridy++;
    JButton solveButton = new JButton("Solve");
    solveButton.addActionListener(actionEvent -> {
      controller.solve();
    });
    menuPane.add(solveButton, gbc);
    gbc.gridy++;
    JButton resetButton = new JButton("Reset");
    resetButton.addActionListener(actionEvent -> {
      controller.reset();
    });
    menuPane.add(resetButton, gbc);

    frame.add(menuPane, BorderLayout.AFTER_LINE_ENDS);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }


  public void updateBoard() {
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLS; j++) {
        if (controller.getCell(i,j) == 0) {
          cells[i][j].setText(null);
        } else {
          cells[i][j].setText(String.valueOf(controller.getCell(i, j)));
        }
      }
    }
  }


}
