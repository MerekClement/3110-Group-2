import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BagController implements ActionListener {

    BoardFrame frame;

    public BagController(BoardFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int player = Integer.parseInt(e.getActionCommand().split(",")[0]);
        if (e.getActionCommand().equals("pass")){
            //TODO Add functionality to pass button
        }
        if(e.getActionCommand().equals("clear")){
            //TODO Add functionality to clear button
        }
        int i = Integer.parseInt(e.getActionCommand().split(",")[1]);
        frame.currentSelectedCharacter = e.getActionCommand().split(",")[2];
        if(player == 1){
            frame.alphabetButtonsPlayer1[i].setEnabled(false);
        }
        if(player == 2){
            frame.alphabetButtonsPlayer2[i].setEnabled(false);
        }

    }
}
