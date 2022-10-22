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
}
