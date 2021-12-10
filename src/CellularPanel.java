

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Win7
 */
public class CellularPanel extends JPanel {

    private int[][] board;

    private int width;
    private int height;
    
    private Color lifeColor = Color.RED;

    public CellularPanel(int width, int height, Color c) {
        this.width = width;
        this.height = height;
        
        this.setPreferredSize(new Dimension(width*5, height*5));

        this.board = new int[width][height];
        
        this.lifeColor = c;

    }
    
    public void setBoard(int[][] board){
        this.board = board;
    }
    
    public int[][] getBoard(){
        return this.board;
    }
    
    public void setTile(int x, int y){
        this.board[x][y] = 3;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);

        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (board[x][y] == 1) {
                    g.fillRect(x * 5, y * 5, 5, 5);
                }
            }
        }

    }

}
