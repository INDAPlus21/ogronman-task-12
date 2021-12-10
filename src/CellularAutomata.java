

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Win7
 */
public class CellularAutomata extends JFrame {

    class GameOfLifePanel extends JPanel {

        private Image image;

        private JSlider sliderHeight;
        private JSlider sliderWidth;

        private JRadioButton startOne;
        private JRadioButton startTwo;
        private JRadioButton startThree;
        private JRadioButton startFour;

        private ButtonGroup bGroup;

        private JButton startGof;

        public GameOfLifePanel() throws IOException {

            BufferedImage background = ImageIO.read(new File("2dPanels.png"));
            setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

            JPanel helpPanel = new JPanel();

            JPanel sliderHPanel = new JPanel();
            sliderHPanel.setLayout(new BoxLayout(sliderHPanel, BoxLayout.Y_AXIS));
            JPanel sliderWPanel = new JPanel();
            sliderWPanel.setLayout(new BoxLayout(sliderWPanel, BoxLayout.Y_AXIS));

            helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
            sliderHeight = new JSlider();
            sliderHeight.setMinimum(5);
            sliderHeight.setMaximum(216);

            sliderWidth = new JSlider();
            sliderWidth.setMinimum(5);
            sliderWidth.setMaximum(396);
            startGof = new JButton("Start the game of life");
            startGof.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int start = 0;
                    if (startOne.isSelected()) {
                        start = 0;
                    } else if (startTwo.isSelected()) {
                        start = 1;
                    } else if (startThree.isSelected()) {
                        start = 2;
                    } else {
                        start = 3;
                    }
                    TwoDFrame CellularFrame = new TwoDFrame(sliderWidth.getValue(), sliderHeight.getValue(), 0, start, Color.MAGENTA);
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "START");
                }
            });

            this.setLayout(new GridLayout(2, 2, 100, 5));
            JLabel heightLabel = new JLabel("Current height is: " + sliderHeight.getValue() + "pxs");
            JLabel widthLabel = new JLabel("Current width is: " + sliderWidth.getValue() + "pxs");

            sliderHeight.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    heightLabel.setText("Current height is: " + sliderHeight.getValue() + "pxs");
                }
            });

            sliderWidth.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    widthLabel.setText("Current width is: " + sliderWidth.getValue() + "pxs");
                }
            });

            sliderHPanel.add(heightLabel);
            sliderHPanel.add(sliderHeight);

            sliderWPanel.add(widthLabel);
            sliderWPanel.add(sliderWidth);

            helpPanel.add(sliderHPanel);
            helpPanel.add(sliderWPanel);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(4, 1));

            bGroup = new ButtonGroup();

            startOne = new JRadioButton("Start one");
            bGroup.add(startOne);

            startTwo = new JRadioButton("Start two");
            bGroup.add(startTwo);

            startThree = new JRadioButton("Start three");
            bGroup.add(startThree);

            startFour = new JRadioButton("Start four");
            bGroup.add(startFour);

            buttonPanel.add(startOne);
            buttonPanel.add(startTwo);
            buttonPanel.add(startThree);
            buttonPanel.add(startFour);

            add(helpPanel);
            add(buttonPanel);
            add(startGof);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    class LangtonsAntPanel extends JPanel {

        private Image image;

        private JSlider sliderHeight;
        private JSlider sliderWidth;

        private JButton startAnt;

        public LangtonsAntPanel() throws IOException {

            BufferedImage background = ImageIO.read(new File("ant.png"));
            setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

            JPanel helpPanel = new JPanel();

            JPanel sliderHPanel = new JPanel();
            sliderHPanel.setLayout(new BoxLayout(sliderHPanel, BoxLayout.Y_AXIS));
            JPanel sliderWPanel = new JPanel();
            sliderWPanel.setLayout(new BoxLayout(sliderWPanel, BoxLayout.Y_AXIS));

            helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
            sliderHeight = new JSlider();
            sliderHeight.setMinimum(5);
            sliderHeight.setMaximum(216);

            sliderWidth = new JSlider();
            sliderWidth.setMinimum(5);
            sliderWidth.setMaximum(396);
            startAnt = new JButton("Start langtons ant");
            startAnt.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwoDFrame CellularFrame = new TwoDFrame(sliderWidth.getValue(), sliderHeight.getValue(), 1, 0, Color.MAGENTA);
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "START");
                }
            });

            this.setLayout(new GridLayout(2, 1));
            JLabel heightLabel = new JLabel("Current height is: " + sliderHeight.getValue() + "pxs");
            JLabel widthLabel = new JLabel("Current width is: " + sliderWidth.getValue() + "pxs");

            sliderHeight.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    heightLabel.setText("Current height is: " + sliderHeight.getValue() + "pxs");
                }
            });

            sliderWidth.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    widthLabel.setText("Current width is: " + sliderWidth.getValue() + "pxs");
                }
            });

            sliderHPanel.add(heightLabel);
            sliderHPanel.add(sliderHeight);

            sliderWPanel.add(widthLabel);
            sliderWPanel.add(sliderWidth);

            helpPanel.add(sliderHPanel);
            helpPanel.add(sliderWPanel);

            add(helpPanel);
            add(startAnt);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    class WireWorldPanel extends JPanel {

        private Image image;

        private JSlider sliderHeight;
        private JSlider brushSize;
        private JSlider sliderWidth;

        private JButton startWire;

        public WireWorldPanel() throws IOException {

            BufferedImage background = ImageIO.read(new File("2dPanels.png"));
            setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));

            JPanel helpPanel = new JPanel();

            JPanel sliderHPanel = new JPanel();
            sliderHPanel.setLayout(new BoxLayout(sliderHPanel, BoxLayout.Y_AXIS));
            JPanel sliderWPanel = new JPanel();
            sliderWPanel.setLayout(new BoxLayout(sliderWPanel, BoxLayout.Y_AXIS));

            helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
            sliderHeight = new JSlider();
            sliderHeight.setMinimum(5);
            sliderHeight.setMaximum(216);

            sliderWidth = new JSlider();
            sliderWidth.setMinimum(5);
            sliderWidth.setMaximum(396);
            startWire = new JButton("Start WireWorld");
            startWire.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    TwoDFrame CellularFrame = new TwoDFrame(sliderWidth.getValue(), sliderHeight.getValue(), 2, brushSize.getValue(), Color.MAGENTA);
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "START");
                }
            });

            this.setLayout(new GridLayout(3, 1));
            JLabel heightLabel = new JLabel("Current height is: " + sliderHeight.getValue() + "pxs");
            JLabel widthLabel = new JLabel("Current width is: " + sliderWidth.getValue() + "pxs");

            sliderHeight.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    heightLabel.setText("Current height is: " + sliderHeight.getValue() + "pxs");
                }
            });

            sliderWidth.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    widthLabel.setText("Current width is: " + sliderWidth.getValue() + "pxs");
                }
            });

            sliderHPanel.add(heightLabel);
            sliderHPanel.add(sliderHeight);

            sliderWPanel.add(widthLabel);
            sliderWPanel.add(sliderWidth);

            helpPanel.add(sliderHPanel);
            helpPanel.add(sliderWPanel);

            brushSize = new JSlider();
            brushSize.setMaximum(1);
            brushSize.setMaximum(50);

            JLabel brushLabel = new JLabel("Current brush size is: " + brushSize.getValue());

            brushSize.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    brushLabel.setText("Current brush size is: " + brushSize.getValue());
                }
            });

            JPanel brushPanel = new JPanel();
            brushPanel.setLayout(new GridLayout(2, 1));
            brushPanel.add(brushLabel);
            brushPanel.add(brushSize);

            add(helpPanel);
            add(brushPanel);
            add(startWire);

        }
    }

    class choose1DPanel extends JPanel {

        private JButton button;

        private JSlider sliderHeight;
        private JSlider sliderWidth;
        private JSlider rule;
        private JSlider iterations;

        public choose1DPanel() throws IOException {

            setLayout(new GridLayout(3, 1));

            JPanel helpPanel = new JPanel();

            JPanel sliderHPanel = new JPanel();
            sliderHPanel.setLayout(new BoxLayout(sliderHPanel, BoxLayout.Y_AXIS));
            JPanel sliderWPanel = new JPanel();
            sliderWPanel.setLayout(new BoxLayout(sliderWPanel, BoxLayout.Y_AXIS));

            helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.X_AXIS));
            sliderHeight = new JSlider();
            sliderHeight.setMinimum(5);
            sliderHeight.setMaximum(216);

            sliderWidth = new JSlider();
            sliderWidth.setMinimum(5);
            sliderWidth.setMaximum(396);

            JLabel heightLabel = new JLabel("Current height is: " + sliderHeight.getValue() + "pxs");
            JLabel widthLabel = new JLabel("Current width is: " + sliderWidth.getValue() + "pxs");

            sliderHeight.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    heightLabel.setText("Current height is: " + sliderHeight.getValue() + "pxs");
                    iterations.setValue(sliderHeight.getValue() - 1);
                }
            });

            sliderWidth.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    widthLabel.setText("Current width is: " + sliderWidth.getValue() + "pxs");
                }
            });

            JPanel rulePanel = new JPanel();
            rulePanel.setLayout(new BoxLayout(rulePanel, BoxLayout.Y_AXIS));

            rule = new JSlider();
            rule.setMinimum(0);
            rule.setMaximum(255);
            rule.setValue(30);

            iterations = new JSlider();
            iterations.setMinimum(0);
            iterations.setMaximum(2000);
            iterations.setValue(sliderHeight.getValue() - 1);

            JLabel iterationsLabel = new JLabel("Current iterations is: " + iterations.getValue());

            JLabel ruleLabel = new JLabel("Current rule is: " + rule.getValue());

            rule.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ruleLabel.setText("Current rule is: " + rule.getValue());
                }
            });

            iterations.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    iterationsLabel.setText("Current iterations is: " + iterations.getValue());
                }
            });

            rulePanel.add(ruleLabel);
            rulePanel.add(rule);
            rulePanel.add(Box.createRigidArea(new Dimension(50, 50)));
            rulePanel.add(iterationsLabel);
            rulePanel.add(iterations);

            sliderHPanel.add(heightLabel);
            sliderHPanel.add(sliderHeight);

            sliderWPanel.add(widthLabel);
            sliderWPanel.add(sliderWidth);

            helpPanel.add(sliderHPanel);
            helpPanel.add(sliderWPanel);

            button = new JButton("Start Cellular Automata");
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    OneDFrame oneFrame = new OneDFrame(sliderWidth.getValue(), sliderHeight.getValue(), iterations.getValue(), rule.getValue());
                }
            });

            add(helpPanel);
            add(rulePanel);
            add(button);
        }
    }

    class choose2DPanel extends JPanel {

        private Image image;

        public choose2DPanel() throws IOException {

            BufferedImage background = ImageIO.read(new File("2dPanels.png"));
            setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
            image = background;

            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            ImageIcon LifeOfGame = new ImageIcon("life.png");
            lifeButton = new JButton(LifeOfGame);
            lifeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "gof");
                }
            });
            ImageIcon ant = new ImageIcon("ant.png");
            antButton = new JButton(ant);
            antButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "ant");
                }
            });
            ImageIcon wire = new ImageIcon("wire.png");
            wireButton = new JButton(wire);
            wireButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "wire");
                }
            });
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(Box.createHorizontalGlue());
            add(lifeButton);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(antButton);
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(wireButton);
            add(Box.createHorizontalGlue());
            add(Box.createRigidArea(new Dimension(10, 0)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    class ImagePanel extends JPanel {

        private Image image;

        public ImagePanel(Image image) {
            this.image = image;

            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            ImageIcon oneDImage = new ImageIcon("1dButton.png");
            onedButton = new JButton(oneDImage);
            onedButton.setContentAreaFilled(false);
            onedButton.setBorderPainted(false);
            onedButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "1D");
                }
            });
            add(Box.createRigidArea(new Dimension(50, 0)));
            add(Box.createHorizontalGlue());
            add(onedButton);

            add(Box.createRigidArea(new Dimension(50, 0)));

            ImageIcon twoDImage = new ImageIcon("2dButton.png");
            twoDButton = new JButton(twoDImage);
            twoDButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) (cards.getLayout());
                    cl.show(cards, "2D");
                }
            });
            twoDButton.setContentAreaFilled(false);
            twoDButton.setBorderPainted(false);
            add(twoDButton);
            add(Box.createHorizontalGlue());
            add(Box.createRigidArea(new Dimension(50, 0)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, this);
        }
    }

    private JButton onedButton, twoDButton, lifeButton, antButton, wireButton;
    private ImagePanel backPanel;
    private choose2DPanel twoDPanel;
    private GameOfLifePanel gofPanel;
    private LangtonsAntPanel laPanel;
    private WireWorldPanel wwPanel;
    private choose1DPanel oneDPanel;

    private JPanel cards;

    public CellularAutomata() throws HeadlessException, IOException {
        super("Cellular Automata");

        cards = new JPanel(new CardLayout());

        BufferedImage background = ImageIO.read(new File("background.png"));
        setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
        setResizable(false);
        backPanel = new ImagePanel(background);
        twoDPanel = new choose2DPanel();
        gofPanel = new GameOfLifePanel();
        laPanel = new LangtonsAntPanel();
        wwPanel = new WireWorldPanel();
        oneDPanel = new choose1DPanel();
        cards.add(backPanel, "START");
        cards.add(twoDPanel, "2D");
        cards.add(gofPanel, "gof");
        cards.add(laPanel, "ant");
        cards.add(wwPanel, "wire");
        cards.add(oneDPanel, "1D");

        add(cards);

        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            CellularAutomata ca = new CellularAutomata();
        } catch (HeadlessException ex) {
            Logger.getLogger(CellularAutomata.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CellularAutomata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
