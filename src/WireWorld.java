

/**
 *
 * @author Win7
 */
public class WireWorld extends Cell {

    private int width;
    private int height;
    private int[][] board;

    private Thread t;

    private int generation;

    private int timeOut = 75;

    public WireWorld(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new int[this.width][this.height];

        t = new Thread(this);

    }

    public void setConductor(int x, int y) {

        this.board[x][y] = 3;
    }

    public void setElectron(int x, int y) {
        if (this.board[x][y] == 3) {
            this.board[x][y] = 1;
        }
    }

    public int getPoint(int x, int y) {
        return this.board[x][y];
    }

    private int neighbourElectron(int x, int y) {
        int count = 0;

        count += isAlive(x - 1, y - 1);
        count += isAlive(x, y - 1);
        count += isAlive(x + 1, y - 1);

        count += isAlive(x - 1, y);
        count += isAlive(x + 1, y);

        count += isAlive(x - 1, y + 1);
        count += isAlive(x, y + 1);
        count += isAlive(x + 1, y + 1);
        return count;
    }

    @Override
    public void start(int startType) {

        if (startType == 0) {

        } else if (startType == 0) {

        } else if (startType == 0) {

        } else {

        }

        t.start();
    }

    public int isAlive(int x, int y) {
        int newX = x;
        int newY = y;
        if (x < 0 || x >= width || y < 0 || y >= height) {
            if (x < 0) {
                newX = x + width;
            } else {
                newX = width - x - 1;
            }
            if (y < 0) {
                newY = y + height;
            } else {
                newY = height - y - 1;
            }

            if (this.board[Math.abs(newX)][Math.abs(newY)] == 1) {
                return 1;
            } else {
                return 0;
            }
        }

        if (this.board[x][y] == 1) {
            return 1;
        } else {
            return 0;
        }

    }

    public void step() throws InterruptedException {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbours = neighbourElectron(x, y);

                if (this.board[x][y] == 1) {
                    newBoard[x][y] = 2;
                } else if (this.board[x][y] == 2) {
                    newBoard[x][y] = 3;
                } else if (this.board[x][y] == 3) {
                    if (neighbours == 1 || neighbours == 2) {
                        newBoard[x][y] = 1;
                    } else {
                        newBoard[x][y] = 3;
                    }
                } else {
                    newBoard[x][y] = 0;
                }

            }
        }

        board = newBoard;

        pcs.firePropertyChange("WireWorld", 0, 1);
        Thread.sleep(timeOut);
    }

    public void setEmpty(int x, int y) {
        this.board[x][y] = 0;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                step();
                generation++;
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

    @Override
    public int[][] getBoard() {
        return this.board;
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
        try{
        this.board[x][y] = value;
        pcs.firePropertyChange("Changed board", 0, 1);
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            
        }
    }

}
