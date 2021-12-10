
import java.awt.HeadlessException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;

/**
 *
 * @author Win7
 */
public class OneDFrame extends JFrame implements PropertyChangeListener {

    private SingePanel sPanel;

    private SingleCellularAutomata automata;

    public OneDFrame(int width, int height, int iterations,int rule) throws HeadlessException {
        super("1-D cellular Automata");
        this.setResizable(false);
        sPanel = new SingePanel(width, height);
        automata = new SingleCellularAutomata(width, height,iterations, rule);
        automata.addProperty(this);

        
        automata.start(0);
        
        add(sPanel);

        pack();
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        sPanel.setBoard(automata.getBoard());
        sPanel.repaint();
    }

}
