

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Win7
 */
public class TwoDFrame extends JFrame implements PropertyChangeListener {

    private CellularPanel cellPanel;

    private Cell automata;

    private int height = 100;
    private int width = 100;

    public TwoDFrame(int width, int height, int type, int startType, Color c) throws HeadlessException {
        super("2D Celluar Automata");
        this.width = width;
        this.height = height;
        this.setResizable(true);
        
        if (type == 0) {
            automata = new GameOfLife(this.width, this.height);
            cellPanel = new CellularPanel(width, height, c);
        } else if (type == 1) {
            automata = new LangtonsAnt(this.width, this.height);
            cellPanel = new CellularPanel(width, height, c);
        }else if (type == 2){
            automata = new WireWorld(this.width, this.height);
            cellPanel = new WireWorldPanel(width, height, c, this, startType);
            this.setPreferredSize(new Dimension(width*startType,height*startType));
            this.setResizable(false);
        }
        automata.addProperty(this);

        automata.start(startType);
        
        add(cellPanel);
        cellPanel.setBoard(automata.getBoard());
        cellPanel.repaint();

        pack();
        setVisible(true);
    }
    
    public void setTile(int x, int y, int type){
        if(automata instanceof  WireWorld){
            automata.setTile(x, y, type);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        cellPanel.setBoard(automata.getBoard());
        cellPanel.repaint();
    }

    public void pause() {
        System.out.println("Paused frame");
        automata.pause();
    }

    public void resume() {
        System.out.println("Resumed frame");
        automata.resume();
    }

}
