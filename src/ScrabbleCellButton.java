import javax.swing.*;

public class ScrabbleCellButton extends JButton {
    private Coordinates top, right, down, left, self;

    public ScrabbleCellButton(String text, int x, int y) {
        super(text);
        if(x<0 || x > 15){return;}
        this.self = new Coordinates(x,y);
        left = (x==0)?null: new Coordinates(x-1,y);
        right = (x==14)?null: new Coordinates(x+1, y);
        top = (y==0)?null: new Coordinates(x, y - 1);
        down = (y == 14)?null:new Coordinates(x, y + 1);
    }
    public Coordinates[] getAdjacentCells(){
        Coordinates coordinates[] = new Coordinates[4];
        coordinates[0] = this.top;
        coordinates[1] = this.right;
        coordinates[2] = this.down;
        coordinates[3] = this.left;
        return coordinates;
    }

}
