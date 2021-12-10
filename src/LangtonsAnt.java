

/**
 *
 * @author Win7
 */
public class LangtonsAnt extends Cell implements Runnable {

    private int width;
    private int height;

    private int[][] board;

    private Thread t;

    private int generation = 0;

    private int timeOut = 10;

    private int[] antPoint = new int[2];
    private int[] direction = new int[2];

    public LangtonsAnt(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new int[this.width][this.height];
        antPoint[0] = width / 2;
        antPoint[1] = height / 2;
        direction[0] = 1;
        direction[1] = 0;

        t = new Thread(this);
    }

    public void step() throws InterruptedException {
        int color = board[antPoint[0]][antPoint[1]];

        int[][] newBoard = new int[this.width][this.height];

        if (color == 0) {
            int temp = direction[0];
            direction[0] = -direction[1];
            direction[1] = temp;
            board[antPoint[0]][antPoint[1]] = 1;
            //System.out.println(newBoard[antPoint[0]][antPoint[1]]);
            antPoint[0] = antPoint[0] + direction[0];
            antPoint[1] = antPoint[1] + direction[1];
        } else {
            int temp = direction[0];
            direction[0] = direction[1];
            direction[1] = -temp;
            board[antPoint[0]][antPoint[1]] = 0;
            antPoint[0] = antPoint[0] + direction[0];
            antPoint[1] = antPoint[1] + direction[1];
        }

        //newBoard[antPoint[0]][antPoint[1]] += 2;
        pcs.firePropertyChange("Langtons Ant", 0, 1);
        Thread.sleep(timeOut);
    }

    @Override
    public int[][] getBoard() {
        return this.board;
    }

    @Override
    public void start(int startType) {
        t.start();
    }

    @Override
    public void run() {
        System.out.println("Starting thread...");
        while (!Thread.interrupted()) {
            try {
                step();
                generation++;
            } catch (InterruptedException ex) {
                break;
            } catch (java.lang.ArrayIndexOutOfBoundsException e){
                
            }
        }
    }

    @Override
    public void setBoard(int[][] board) {
        this.board = board;
    }

    @Override
    public void pause() {
        t.interrupt();
    }

    @Override
    public void resume() {
        t = new Thread(this);
        t.start();
    }

    @Override
    public void setTile(int x, int y, int value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
