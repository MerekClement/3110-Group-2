import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BagController implements ActionListener {

    BoardFrame frame;
    BoardManager manager;
    BoardController boardController;
    ArrayList<Player>players = new ArrayList<>();
    PlayerManager playerManager;

    ArrayList<JButton> alphabetStack = new ArrayList<>();
    String move = "";


    public BagController(BoardFrame frame) {
        this.frame = frame;
        manager = frame.boardManager;
        manager.buildBoard(15,15);
        manager.initalizeIndex();
        boardController = frame.boardController;
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));
        playerManager = new PlayerManager(players);
    }

    private String getMove(){
        ArrayList<Coordinates> characterStack = boardController.getCharacterStack();
        String move = "";
        Coordinates wordStart = new Coordinates(15,15);
        Coordinates wordEnd = new Coordinates(0,0);
        for(Coordinates character : characterStack){
            int y = character.getY();
            int x = character.getX();
            if(x < wordStart.getX() || y < wordStart.getY()){
                wordStart = character;
            }
            if(x > wordEnd.getX() || y > wordEnd.getY()){
                wordEnd = character;
            }
        }
        move += "["+wordStart.getX()+","+wordStart.getY();
        String word = "";
        if(wordStart.getX() - wordEnd.getX() == 0){
            for(int i = wordStart.getX(); i == wordStart.getX(); i++){
                word+=frame.cells[i][wordStart.getY()].getText();
            }
            this.move = word;
            move += ",down]";
        }
        if(wordStart.getY() - wordEnd.getY() == 0){
            for(int i = wordStart.getY() ; i ==wordStart.getY(); i++){
                word+=frame.cells[wordStart.getX()][wordStart.getY()+i].getText();
            System.out.println(word + " is word");
            }
            move += ",left]";
        }

        System.out.println(move+this.move);
        return move + this.move;
    }
    public void pass(){
        System.out.println("Now in pass");
        for(JButton b : alphabetStack){
            char ch = manager.getRandomAlphabets();
            if(b.getText() == ""+ch){
                ch++;
            }
            b.setText(ch+"");
            String currentActionCommand = b.getActionCommand();
            String player = currentActionCommand.split(",")[0];
            String buttonNumber = currentActionCommand.split(",")[1];
            b.setActionCommand(player+","+buttonNumber+","+ch);
            b.setEnabled(false);
        }
        if(frame.currentTurn == 1){
            for(Component c : frame.bagOfPlayer1.getComponents()){
                c.setEnabled(false);
            }
            for(Component c : frame.bagOfPlayer2.getComponents()){
                c.setEnabled(true);
            }
            System.out.println("Passed to player 2");
            frame.currentTurn --;
        }else{
            for(Component c : frame.bagOfPlayer2.getComponents()){
                c.setEnabled(false);
            }
            for(Component c : frame.bagOfPlayer1.getComponents()){
                c.setEnabled(true);
            }
            System.out.println("Passed to player 1");
            frame.currentTurn++;
        }
        alphabetStack.clear();
        System.out.println("Stack Cleared");
        this.move = "";
        System.out.println(frame.currentTurn + " turn");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int player = Integer.parseInt(e.getActionCommand().split(",")[0]);
        if (e.getActionCommand().split(",")[1].equals("pass")){
           pass();
           return;
        }
        if(e.getActionCommand().split(",")[1].equals("clear")){
            clear();
            return;
        }
        if(e.getActionCommand().split(",")[1].equals("submit")){
            submit();
            return;
        }
        int i = Integer.parseInt(e.getActionCommand().split(",")[1]);
        frame.currentSelectedCharacter = e.getActionCommand().split(",")[2];
        move += frame.currentSelectedCharacter;
        if(player == 1){
            frame.alphabetButtonsPlayer1[i].setEnabled(false);
            alphabetStack.add(frame.alphabetButtonsPlayer1[i]);
        }
        if(player == 2){
            frame.alphabetButtonsPlayer2[i].setEnabled(false);
            alphabetStack.add(frame.alphabetButtonsPlayer2[i]);
        }

    }

    /**
     * updateSurroundingNodes is responsible for enabling nodes surrounding a letter
     * after a successful try.
     */
    private void updateSurroundingNodes() {
        for (int x = 1; x < 15; x++) {
            for (int y = 1; y < 15; y++) {
                if (boardController.frame.cells[x][y].getText() != "") {
                    if (boardController.frame.cells[x - 1][y].getText() == "") {
                        boardController.frame.cells[x - 1][y].setEnabled(true);
                    }
                    if (boardController.frame.cells[x + 1][y].getText() == "") {
                        boardController.frame.cells[x + 1][y].setEnabled(true);
                    }
                    if (boardController.frame.cells[x][y - 1].getText() == "") {
                        boardController.frame.cells[x][y - 1].setEnabled(true);
                    }
                    if (boardController.frame.cells[x][y + 1].getText() == "") {
                        boardController.frame.cells[x][y + 1].setEnabled(true);
                    }
                }
            }
        }
    }

    private void submit() {
        System.out.println(move);

        if(move.equals("")){
            JOptionPane.showMessageDialog(frame,"No word selected","Select a word",JOptionPane.ERROR_MESSAGE);
        }else if(manager.dictionaryManager.isWord(move)) {
            manager.validateMove(getMove());
            System.out.println("Word passed");
            boardController.updateCorrectWords();
            updateSurroundingNodes();
            pass();

        }else{
            JOptionPane.showMessageDialog(frame, "Invalid Word", "Select a word that makes a sense", JOptionPane.ERROR_MESSAGE);
            clear();
        }
    }

    private void clear() {
        boardController.clearStack();
        for(JButton alphabet : alphabetStack){
            alphabet.setEnabled(true);
        }
        updateSurroundingNodes();
        move = "";
    }
}
