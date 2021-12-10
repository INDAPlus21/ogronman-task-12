

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Win7
 */
public class SingePanel extends JPanel {

    private int width, height;
    private ArrayList<int[]> board = new ArrayList<>();
    private int number = 0;

    public SingePanel(int width, int height) {
        this.width = width;
        this.height = height;

        this.setPreferredSize(new Dimension(width * 5, height * 5));

    }

    public void setBoard(ArrayList<int[]> board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        if (board.size() > height) {
            number++;
        }

        try {
            for (int x = number; x < board.size(); x++) {
                int[] temp = board.get(x);
                for (int y = 0; y < width; y++) {
                    if (temp[y] == 1) {
                        g.fillRect(y * 5, (x-number) * 5, 5, 5);
                    }
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {

        }

    }

}
