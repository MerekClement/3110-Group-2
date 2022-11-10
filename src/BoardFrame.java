import Developer.Utils;

import javax.swing.*;
import java.awt.*;

public class BoardFrame extends JFrame implements BoardView {
    private JButton[][] cells = new JButton[15][15];
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
        boardPanel  = new JPanel(new GridLayout(16,16,3,3));
        bagOfPlayer2 = new JPanel(new GridLayout(8,1));

        bagOfPlayer1.add(new JLabel("Player 1"));
        bagOfPlayer2.add(new JLabel("Player 2"));

        alphabetButtonsPlayer1 = new JButton[7];
        alphabetButtonsPlayer2 = new JButton[7];


        char a = 'a';

        for(int i = 0; i < 16; i++){
            if(i>0) {
                boardPanel.add(new JLabel((char) (a + i - 1) + ""));
            }else{
                boardPanel.add(new JLabel("R/C"));
            }
            for(int j = 1; j < 16; j++){
                if(i == 0){
                    boardPanel.add(new JLabel((char)(a+j-1) + ""));
                    continue;
                }
                cells[i-1][j-1] = new JButton(" ");
                cells[i-1][j-1].setActionCommand(i +""+ j);
                cells[i-1][j-1].addActionListener(boardController);
                boardPanel.add(cells[i-1][j-1]);
            }
        }
        String player1Alphabet, player2Alphabet;
        for(int i = 0; i < 7; i++){
            player1Alphabet = ""+boardManager.getRandomAlphabets();
            player2Alphabet = ""+boardManager.getRandomAlphabets();

            alphabetButtonsPlayer1[i] = new JButton(player1Alphabet);
            bagOfPlayer1.add(alphabetButtonsPlayer1[i]);
            alphabetButtonsPlayer1[i].addActionListener(boardController);
            alphabetButtonsPlayer1[i].setActionCommand(player1Alphabet);

            alphabetButtonsPlayer2[i] = new JButton(player2Alphabet);
            bagOfPlayer2.add(alphabetButtonsPlayer2[i]);
            alphabetButtonsPlayer2[i].addActionListener(boardController);
            alphabetButtonsPlayer2[i].setActionCommand(player2Alphabet);
        }



        JLabel label = new JLabel("S C R A B B L E");

        c.ipadx = 50;
        c.ipady = 50;
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

        setSize(new Dimension(950,630));
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
