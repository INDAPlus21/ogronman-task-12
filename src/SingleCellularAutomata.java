

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Win7
 */
public class SingleCellularAutomata implements Runnable {

    private int indexHeight = 0;
    private int[] ruleset = new int[8];
    private ArrayList<int[]> board = new ArrayList<>();
    private int[] currentGen;
    private int width;
    private int height;
    private PropertyChangeSupport pcs;
    private int iterations = 0;
    private Thread t;

    public SingleCellularAutomata(int width, int height, int iterations,int rule) {

        this.pcs = new PropertyChangeSupport(this);
        this.iterations = iterations;
        this.width = width;
        this.height = height;

        String s = Integer.toBinaryString(rule);
        int j = 8 - s.length();
        for (int i = 0; i < 8; i++) {
            if (i < j) {
                ruleset[i] = 0;
            } else {
                ruleset[i] = Character.getNumericValue((s.charAt(i - j)));
            }
        }

        currentGen = new int[width];
        t = new Thread(this);
    }

    public void step() {
        int[] nextGen = new int[width];

        for (int i = 1; i < width - 1; i++) {
            int leftNe = currentGen[i - 1];
            int current = currentGen[i];
            int rightNe = currentGen[i + 1];
            nextGen[i] = worseRules(leftNe, current, rightNe);
        }
        indexHeight++;
        currentGen = nextGen;
        board.add(currentGen);
        pcs.firePropertyChange("Single", 0, 1);
        try {
            Thread.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(SingleCellularAutomata.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int worseRules(int a, int b, int c) {
        if (a == 1 && b == 1 && c == 1) {
            return ruleset[0];
        }
        if (a == 1 && b == 1 && c == 0) {
            return ruleset[1];
        }
        if (a == 1 && b == 0 && c == 1) {
            return ruleset[2];
        }
        if (a == 1 && b == 0 && c == 0) {
            return ruleset[3];
        }
        if (a == 0 && b == 1 && c == 1) {
            return ruleset[4];
        }
        if (a == 0 && b == 1 && c == 0) {
            return ruleset[5];
        }
        if (a == 0 && b == 0 && c == 1) {
            return ruleset[6];
        }
        if (a == 0 && b == 0 && c == 0) {
            return ruleset[7];
        }
        return 0;
    }

    private int rules(int n, int k, int l) {
        String s = "" + n + k + l;
        System.out.println(s);
        int index = Integer.parseInt(s, 2);
        //System.out.println(index);
        //System.out.println(ruleset[index]);
        return ruleset[index];
    }

    public ArrayList<int[]> getBoard() {
        return board;
    }

    public void start(int startType) {
        for (int i = 0; i < currentGen.length; i++) {
            currentGen[i] = 0;
        }

        currentGen[currentGen.length / 2] = 1;

        board.add(currentGen);
        t.start();
    }

    public void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //System.out.print(board[i][j]);
            }
        }
    }

    public void pause() {
        t.interrupt();
    }

    public void resume() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (indexHeight < iterations) {
            step();
        }
        printBoard();
        t.interrupt();
    }

    public void addProperty(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeProperty(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

}

