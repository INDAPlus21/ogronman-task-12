

import java.util.Random;

/**
 *
 * @author Win7
 */
public class GameOfLife extends Cell implements Runnable {

    private int width;
    private int height;
    private int[][] board;

    private Thread t;

    private int generation;

    private int timeOut = 50;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new int[this.width][this.height];

        t = new Thread(this);

    }

    public void setAlive(int x, int y) {
        this.board[x][y] = 1;
    }

    private int aliveNeighbours(int x, int y) {
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
        /**
         * Random r = new Random(); for (int k = 0; k < 50; k++) { Random xRand
         * = new Random(r.nextLong()); int x = xRand.nextInt(width - 50); int y
         * = xRand.nextInt(height - 50); spawnCluster(x, y, xRand.nextInt(3) +
         * 1); }
         */

        if (startType == 0) {
            setStartingBoard();
        } else if (startType == 1) {
            Random r = new Random();
            for (int k = 0; k < 50; k++) {
                Random xRand = new Random(r.nextLong());
                int x = xRand.nextInt(width);
                int y = xRand.nextInt(height);
                try{
                spawnCluster(x, y, xRand.nextInt(3) + 1);
                }catch(java.lang.ArrayIndexOutOfBoundsException e){
                    setStartingBoard();
                }
            }
        } else if (startType == 2) {
            setStartingBoardColumn();
        } else {
            createRandomGlider();
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

            return this.board[Math.abs(newX)][Math.abs(newY)];
        }

        /**
         * if (x < 0 || x >= width) { return 0; } if (y < 0 || y >= height) {
         * return 0; } return this.board[x][y];
         *
         */
        return this.board[x][y];
    }

    public void step() throws InterruptedException {
        int[][] newBoard = new int[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int neighbours = aliveNeighbours(x, y);

                if (this.board[x][y] == 1) {
                    if (neighbours < 2) {
                        newBoard[x][y] = 0;
                    } else if (neighbours > 3) {
                        newBoard[x][y] = 0;
                    } else if (neighbours == 2 || neighbours == 3) {
                        newBoard[x][y] = 1;
                    }
                } else {
                    if (neighbours == 3) {
                        newBoard[x][y] = 1;
                    }
                }

            }
        }

        board = newBoard;

        pcs.firePropertyChange("GameOfLifeGen", 0, 1);
        Thread.sleep(timeOut);
    }

    private void setStartingBoard() {
        Random seedR = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Random r = new Random(seedR.nextLong());
                if (r.nextInt() % 2 == 0) {
                    this.board[i][j] = 1;
                } else {
                    this.board[i][j] = 0;
                }
            }
        }
    }

    private void setStartingBoardColumn() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (i % 2 == 0) {
                    this.board[i][j] = 1;
                }
            }
        }
    }

    private void createGlider(int x, int y) {
        setAlive(x, y);
        setAlive(x + 1, y + 1);
        setAlive(x - 1, y + 2);
        setAlive(x, y + 2);
        setAlive(x + 1, y + 2);
    }

    private void createRandomGlider() {
        Random xRand = new Random();
        int x = xRand.nextInt(width - 7) + 5;
        int y = xRand.nextInt(height - 7) + 5;
        createGlider(x, y);
    }

    private void spawnCluster(int x, int y, int type) {

        System.out.println(type);

        if (type == 1) {
            for (int k = x; k < x + 10; k++) {
                for (int l = y; l < y + 10; l++) {
                    if (k % type != 0 || l % type != 0) {
                        setAlive(k, l);
                    }
                }
            }
        } else if (type == 2) {
            for (int l = y; l < y + 10; l++) {
                if (l % type != 0) {
                    setAlive(x, l);
                }
            }
        } else if (type == 3) {
            setAlive(x, y);
            setAlive(x + 1, y);
            setAlive(x + 2, y);
            setAlive(x + 3, y);
            setAlive(x + 4, y);
            setAlive(x + 5, y);

            setAlive(x, y + 2);
            setAlive(x + 1, y + 4);
            setAlive(x + 2, y + 6);

            setAlive(x + 5, y + 5);
            setAlive(x + 5, y + 6);
            setAlive(x + 6, y + 7);
        }
    }

    public void setDead(int x, int y) {
        this.board[x][y] = 0;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                step();
                generation++;
                if (generation % 100 == 0) {
                    //createRandomGlider();
                }
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
