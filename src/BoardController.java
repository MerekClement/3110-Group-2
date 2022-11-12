import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController implements ActionListener {
    BoardFrame frame;
    BoardManager manager;

    String selectedCharacter;
    public BoardController(BoardFrame boardFrame) {
        frame = boardFrame;
        manager = frame.boardManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand().split(" ")[0]);
        int j = Integer.parseInt(e.getActionCommand().split(" ")[1]);

        if (i < 15 && j < 15) {
            frame.cells[i-1][j-1].setText(frame.currentSelectedCharacter);
            Coordinates coordinates[] = frame.cells[i-1][j-1].getAdjacentCells();
            Coordinates top = coordinates[0];
            Coordinates right = coordinates[1];
            Coordinates down = coordinates[2];
            Coordinates left = coordinates[3];
            frame.cells[top.getX()][top.getY()].setEnabled(true);
            frame.cells[right.getX()][right.getY()].setEnabled(true);
            frame.cells[down.getX()][down.getY()].setEnabled(true);
            frame.cells[left.getX()][left.getY()].setEnabled(true);
        }
    }
}
