import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {
    BoardFrame frame;
    BoardManager manager;
    public BoardController(BoardFrame boardFrame) {
        frame = boardFrame;
        manager = frame.boardManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
