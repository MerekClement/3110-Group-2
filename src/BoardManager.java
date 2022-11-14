import Developer.Utils;

import java.util.Random;

public class BoardManager {
    private Utils _dev;
    private String[][] _slots;
    public int _width;
    public int _height;

    DictionaryManager dictionaryManager;

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
        dictionaryManager = new DictionaryManager(new Utils(false));
    }

    /**
     * The following will build a box with random indexes
     */
    public void initalizeIndex(){
        for (int i = 0; i < this._width; i++) {
            for (int j = 0; j < this._height; j++) {
                this._slots[i][j] = "";
            }
        }
    }

    /**
     * The following will print the current board
     */
    public void viewBoard() {
        for (int i = 0; i < this._width; i++) {
            if (i == 0){
                for (int k = 0; k < this._width; k++) {
                    if (k == 0){
                        System.out.print("\t");
                    }
                    System.out.print("\t" + k);
                }
                System.out.print("\n\n\n");
            }
            for (int j = 0; j < this._height; j++) {
                if (j == 0){
                    System.out.print(i + "\t");
                }
                System.out.print("\t" + this._slots[i][j] + "");
                if (j + 1 == this._height){
                    System.out.print("\n");
                }
            }
        }
    }

    public boolean validateMove(String move) {
        String start = move.split("]")[0];
        String word = move.split("]")[1];
        int left = Integer.parseInt(start.split(",")[0].replace("[", ""));
        int top = Integer.parseInt(start.split(",")[1]);
        String direction = start.split(",")[2].replace("]", "");

        if (direction.equals("up")){
            if (top - word.length() < 0){
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (!this._slots[top - i][left].equals("") && !this._slots[top - i][left].equals(String.valueOf(word.charAt(i)))){
                    return false;
                }
            }
        } else if (direction.equals("down")){
            if (top + word.length() > this._height){
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (!this._slots[top + i][left].equals("") && !this._slots[top + i][left].equals(String.valueOf(word.charAt(i)))){
                    return false;
                }
            }
        } else if (direction.equals("left")){
            if (left - word.length() < 0){
                return false;
            }
            StringBuilder wordReverse = new StringBuilder(word);
            wordReverse.reverse();
            for (int i = 0; i < wordReverse.length(); i++) {
                if (!this._slots[top][left - i].equals("") && !this._slots[top][left - i].equals(String.valueOf(wordReverse.charAt(i)))){
                    return false;
                }
            }
        } else if (direction.equals("right")){
            if (left + word.length() > this._width){
                return false;
            }
            for (int i = 0; i < word.length(); i++) {
                if (!this._slots[top][left + i].equals("") && !this._slots[top][left + i].equals(String.valueOf(word.charAt(i)))){
                    return false;
                }
            }
        } else {
            return false;
        }
        updateBoard(word, top, left, direction);
        return true;
    }

    public void updateBoard(String word, int top, int left, String direction){
        if (direction.equals("up")){
            for (int i = 0; i < word.length(); i++) {
                this._slots[top - i][left] = word.substring(i, i + 1);
            }
        } else if (direction.equals("down")){
            for (int i = 0; i < word.length(); i++) {
                this._slots[top + i][left] = word.substring(i, i + 1);
            }
        } else if (direction.equals("left")){
            StringBuilder wordReverse = new StringBuilder(word);
            wordReverse.reverse();
            for (int i = 0; i < wordReverse.length(); i++) {
                this._slots[top][left - i] = wordReverse.substring(i, i + 1);
            }
        } else if (direction.equals("right")){
            for (int i = 0; i < word.length(); i++) {
                this._slots[top][left + i] = word.substring(i, i + 1);
            }
        }
    }

    public char getRandomAlphabets() {
        Random rnd = new Random();
        return (char) ('A' + rnd.nextInt(26));
    }
}
