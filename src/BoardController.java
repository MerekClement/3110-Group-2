import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BoardController implements ActionListener {
    BoardFrame frame;
    BoardManager manager;

    String word = "";
    ArrayList<Coordinates> characterStack;

    ArrayList<Coordinates> activeButtonsForThisMove;
    public BoardController(BoardFrame boardFrame) {
        frame = boardFrame;
        manager = frame.boardManager;
        characterStack = new ArrayList<>();
        activeButtonsForThisMove = new ArrayList<>();
    }

    public ArrayList getCharacterStack(){
        return this.characterStack;
    }
    public void clearStack(){
        //clear character stack
        //clear adjacent active cells for this move
        for(Coordinates c : activeButtonsForThisMove){
            frame.cells[c.getX()][c.getY()].setText("");
            if(activeButtonsForThisMove.indexOf(c) == 0) {

            }else{
                System.out.println("unabling button at x:" + c.getX() + " y:"+c.getY());
                setEnableFalse(c);
            }
        }
        for(Coordinates c: characterStack){
            frame.cells[c.getX()][c.getY()].setText("");
            if(characterStack.indexOf(c)==0){
                frame.cells[c.getX()][c.getY()].setEnabled(true);
            }
        }
        activeButtonsForThisMove.clear();
        characterStack.clear();
    }
    private void setEnableFalse(Coordinates c){
        frame.cells[c.getX()][c.getY()].setEnabled(false);
    }
    private void setEnableTrue(Coordinates c){
        if(activeButtonsForThisMove.contains(c)){return;}
        frame.cells[c.getX()][c.getY()].setEnabled(true);
        activeButtonsForThisMove.add(c);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand().split(" ")[0]);
        int j = Integer.parseInt(e.getActionCommand().split(" ")[1]);
        Coordinates self = new Coordinates(i-1,j-1);
        if (i < 15 && j < 15) {
            frame.cells[i - 1][j - 1].setText(frame.currentSelectedCharacter);
            characterStack.add(self);
            activeButtonsForThisMove.add(self);
            Coordinates coordinates[] = frame.cells[i - 1][j - 1].getAdjacentCells();
            Coordinates top = coordinates[0];
            Coordinates right = coordinates[1];
            Coordinates down = coordinates[2];
            Coordinates left = coordinates[3];

            if (characterStack.size() == 1) {
                setEnableTrue(top);
                setEnableTrue(right);
                setEnableTrue(down);
                setEnableTrue(left);
            }else{
                Coordinates character_0 = characterStack.get(0);
                Coordinates character_1 = characterStack.get(1);
                if(character_0.getY() - character_1.getY() == 0){
                    setEnableTrue(left);
                    setEnableTrue(right);
                }
                if(character_0.getX() - character_1.getX() == 0){
                    setEnableTrue(top);
                    setEnableTrue(down);
                }
            }
        }
    }
}
