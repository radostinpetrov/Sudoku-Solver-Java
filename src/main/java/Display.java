import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Display implements Updatable {

  Display(Sudoku.Controller controller) {
    JFrame frame = new JFrame("Sudoku");
    frame.setSize(600, 600);

    JPanel gridPanel = new JPanel();
    gridPanel.setLayout(new GridLayout(9, 9));
    for (int i = 0; i < 81; i++) {
      JButton button = new JButton();
      gridPanel.add(button);
      button.addKeyListener(new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
          int num = e.getKeyChar() - 48;
          if (num >= 1 && num <= 9) {
            int gridX = (button.getX() - 4) / 64;
            int gridY = (button.getY() - 1) / 62;
            if (controller.isValid(num, gridX, gridY)) {
              updateButton(button, String.valueOf(e.getKeyChar()));
              controller.updateGrid(gridX, gridY, e.getKeyChar());
            }
          }
        }

        @Override
        public void keyPressed(KeyEvent e) {
          int num = e.getKeyChar() - 48;
          if (num >= 1 && num <= 9) {
            int gridX = (button.getX() - 4) / 64;
            int gridY = (button.getY() - 1) / 62;
            if (controller.isValid(num, gridX, gridY)) {
              updateButton(button, String.valueOf(e.getKeyChar()));
              controller.updateGrid(gridX, gridY, e.getKeyChar());
            }
          }
        }

        @Override
        public void keyReleased(KeyEvent e) {
          int num = e.getKeyChar() - 48;
          if (num >= 1 && num <= 9) {
            int gridX = (button.getX() - 4) / 64;
            int gridY = (button.getY() - 1) / 62;
            if (controller.isValid(num, gridX, gridY)) {
              updateButton(button, String.valueOf(e.getKeyChar()));
              controller.updateGrid(gridX, gridY, e.getKeyChar());
            }
          }
        }
      });

    }

    frame.getContentPane().add(gridPanel);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }

  @Override
  public void updateButton(JButton button, String num) {
    button.setText(num);
  }
}
