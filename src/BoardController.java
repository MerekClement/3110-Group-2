import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class BoardController implements ActionListener {
    BoardFrame frame;
    BoardManager manager;
    static HashMap<ScrabbleCellButton, String> correctWordCollector = new HashMap<>();
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
        for(ScrabbleCellButton cellButton: correctWordCollector.keySet()){
            cellButton.setEnabled(true);
            cellButton.setText(correctWordCollector.get(cellButton));
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
    public void updateCorrectWords(){
        for (Coordinates c: characterStack){
            correctWordCollector.put(frame.cells[c.getX()][c.getY()], frame.cells[c.getX()][c.getY()].getText());
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand().split(" ")[0]);
        int j = Integer.parseInt(e.getActionCommand().split(" ")[1]);
        Coordinates self = new Coordinates(i-1,j-1);
        if (i < 15 && j < 15) {
            if(correctWordCollector.containsKey(frame.cells[i - 1][j - 1])){
                JOptionPane.showMessageDialog(frame,"Cannot put a character on already put character", "Invalid Move", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            frame.cells[i - 1][j - 1].setText(frame.currentSelectedCharacter);
            characterStack.add(self);
            activeButtonsForThisMove.add(self);
            Coordinates coordinates[] = frame.cells[i - 1][j - 1].getAdjacentCells();
            Coordinates top = coordinates[0];
            Coordinates right = coordinates[1];
            Coordinates down = coordinates[2];
            Coordinates left = coordinates[3];

                setEnableTrue(top);
                setEnableTrue(right);
                setEnableTrue(down);
                setEnableTrue(left);

        }
    }
}
