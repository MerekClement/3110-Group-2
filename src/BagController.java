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
        if(wordStart.getX() - wordEnd.getX() == 0){
            move += ",down]";
        }
        if(wordStart.getY() - wordEnd.getY() == 0){
            move += ",left]";
        }
        System.out.println(move+this.move);
        return move + this.move;
    }
    public void pass(){
        for(JButton b : alphabetStack){
            char ch = manager.getRandomAlphabets();
            if(b.getText() == ""+ch){
                ch++;
            }
            b.setText(ch+"");
            b.setEnabled(false);
        }
        if(frame.isPlayer1){
            for(Component c : frame.bagOfPlayer2.getComponents()){
                c.setEnabled(false);
            }
            for(Component c : frame.bagOfPlayer1.getComponents()){
                c.setEnabled(true);
            }
            frame.isPlayer1 = false;
        }else{
            for(Component c : frame.bagOfPlayer1.getComponents()){
                c.setEnabled(false);
            }
            for(Component c : frame.bagOfPlayer2.getComponents()){
                c.setEnabled(true);
            }
            frame.isPlayer1 = true;
        }

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

    private void submit() {
        System.out.println(move);
        if(move.equals("")){
            JOptionPane.showMessageDialog(frame,"No word selected","Select a word",JOptionPane.ERROR_MESSAGE);
        }else if(manager.dictionaryManager.isWord(move)) {
            manager.validateMove(getMove());
            System.out.println("Word passed");
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
        move = "";
    }
}
