

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author Win7
 */
public abstract class Cell implements Runnable {

    protected PropertyChangeSupport pcs;

    public Cell() {
        this.pcs = new PropertyChangeSupport(this);
    }

    public void addProperty(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removeProperty(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

    public abstract int[][] getBoard();

    public abstract void start(int startType);

    public abstract void setBoard(int[][] board);

    public abstract void pause();

    public abstract void resume();

    public abstract void setTile(int x, int y, int value);

}
