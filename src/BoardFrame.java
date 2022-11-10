import Developer.Utils;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame implements BoardView {
    private JButton[][] cells = new JButton[10][10];

    private BoardController boardController;

    private BoardManager boardManager = new BoardManager(new Utils(false));
    private JPanel bagOfPlayer1, bagOfPlayer2, boardPanel;

    private JButton[] alphabetButtonsPlayer1, alphabetButtonsPlayer2;

    public BoardFrame(){
        boardController = new BoardController(this);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gb);

        bagOfPlayer1 = new JPanel(new GridLayout(8,1));
        boardPanel  = new JPanel(new GridLayout(10,10,3,3));
        bagOfPlayer2 = new JPanel(new GridLayout(8,1));

        bagOfPlayer1.add(new JLabel("Player 1"));
        bagOfPlayer2.add(new JLabel("Player 2"));

        alphabetButtonsPlayer1 = new JButton[7];
        alphabetButtonsPlayer2 = new JButton[7];

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                cells[i][j] = new JButton(i + " " + j);
                cells[i][j].addActionListener(boardController);
                boardPanel.add(cells[i][j]);
            }
        }

        for(int i = 0; i < 7; i++){
            alphabetButtonsPlayer1[i] = new JButton(""+boardManager.getRandomAlphabets());
            bagOfPlayer1.add(alphabetButtonsPlayer1[i]);
            alphabetButtonsPlayer2[i] = new JButton(""+boardManager.getRandomAlphabets());
            bagOfPlayer2.add(alphabetButtonsPlayer2[i]);
        }



        JLabel label = new JLabel("S C R A B B L E");

        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.gridheight = 1;
        add(label,c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets = new Insets(2,5,2,5);
        add(bagOfPlayer1, c);

        c.gridx = 1;
        c.gridwidth = 1;
        c.gridheight = 2;
        add(boardPanel, c);

        c.gridx = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        add(bagOfPlayer2, c);

        setSize(new Dimension(800,400));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void update() {

    }

    public static void main(String[] args) {
        BoardFrame frame = new BoardFrame();
    }
}
