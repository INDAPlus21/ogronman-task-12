

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 *
 * @author Win7
 */
public class WireWorldPanel extends CellularPanel {

    private int oldX, oldY, currentX, currentY;
    private int size;
    private int width, height;
    private int buttonClicked = 0;
    private int tool = 1;
    private TwoDFrame frame;


    public WireWorldPanel(int width, int height, Color c, TwoDFrame frame, int size) {
        super(width, height, c);
        this.width = width;
        this.height = height;
        this.size = size;

        this.frame = frame;

        this.addKeyListener(new KeyAdapter() {


            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("hej");
                if (tool == 1) {
                    tool = 2;
                } else {
                    tool = 1;
                }
            }

        });

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                frame.pause();
                buttonClicked = e.getButton();

                oldX = e.getX();
                oldY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (tool == 2) {
                    currentX = e.getX();
                    currentY = e.getY();
                }
                frame.resume();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                
                if(tool == 1){
                    frame.setTile(currentX/size, currentY/size, 1);
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (tool == 1 && buttonClicked == 1) {
                    currentX = e.getX();
                    currentY = e.getY();
                    oldX = currentX;
                    oldY = currentY;
                    frame.setTile(currentX / size, currentY / size, 3);
                } else if (tool == 1 && buttonClicked == 3) {
                    currentX = e.getX();
                    currentY = e.getY();
                    oldX = currentX;
                    oldY = currentY;
                    frame.setTile(currentX / size, currentY / size, 0);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] tempBoard = getBoard();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                try {
                    if (tempBoard[x][y] == 0) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x * size, y * size, size, size);
                    } else if (tempBoard[x][y] == 1) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x * size, y * size, size, size);
                    } else if (tempBoard[x][y] == 2) {
                        g.setColor(Color.RED);
                        g.fillRect(x * size, y * size, size, size);
                    } else {
                        g.setColor(Color.YELLOW);
                        g.fillRect(x * size, y * size, size, size);
                    }
                } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    System.out.println(x + " and " + y);
                }
            }
        }

    }

}
