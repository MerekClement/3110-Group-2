import Developer.Utils;

public class BoardManager {
    private Utils _dev;
    private String[][] _slots;
    private int _width;
    private int _height;

    public BoardManager(Utils _devMode) {
        this._dev = _devMode;
    }

    /**
     * buildBoard is used for building a board of a specified width and height.
     * @param width {int} The width of the board.
     * @param height {int} The height of the board.
     */
    public void buildBoard(int width, int height){
        this._height = height;
        this._width = width;
        this._slots = new String[this._width][this._height];
    }

    /**
     * The following will build a box with random indexes
     */
    public void initalizeIndex(){
        for (int i = 0; i < this._width; i++) {
            for (int j = 0; j < this._height; j++) {
                this._slots[i][j] = String.valueOf(i+j);
            }
        }
    }

    /**
     * The following will print the current board
     */
    public void viewBoard() {
        for (int i = 0; i < this._width; i++) {
            if (i == 0){
                for (int k = 1; k <= this._width; k++) {
                    System.out.print("\t" + k);
                }
                System.out.print("\n\n");
            }
            for (int j = 0; j < this._height; j++) {
                System.out.print("\t" + this._slots[i][j]);
                if (j + 1 == this._height){
                    System.out.print("\n");
                }
            }
        }
    }
}
