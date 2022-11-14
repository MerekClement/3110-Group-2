import Developer.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BoardFrame extends JFrame implements BoardView {
    BagController bagController;

    JButton submitButtonPlayer1, submitButtonPlayer2;
    protected ScrabbleCellButton[][] cells = new ScrabbleCellButton[15][15];

    private JButton passPlayer1btn, passPlayer2btn, clearPlayer1, clearPlayer2;
    BoardController boardController;

    BoardManager boardManager = new BoardManager(new Utils(false));
    JPanel bagOfPlayer1, bagOfPlayer2, boardPanel;
    private JLabel scoreTextLabel1, scoreTextLabel2;
    private JLabel player1score, player2score;
    JButton[] alphabetButtonsPlayer1, alphabetButtonsPlayer2;

    String currentSelectedCharacter;
    int currentTurn = 1;


    public BoardFrame(){
        boardController = new BoardController(this);
        bagController = new BagController(this);


        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gb);

        bagOfPlayer1 = new JPanel(new GridLayout(13,3,10,10));
        bagOfPlayer1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        boardPanel  = new JPanel(new GridLayout(16,16,3,3));
        boardPanel.setBorder(BorderFactory.createEtchedBorder());
        bagOfPlayer2 = new JPanel(new GridLayout(13,3,10,10));
        bagOfPlayer2.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JLabel player1 = new JLabel("Player 1",SwingConstants.CENTER);
        JLabel player2 = new JLabel("Player 2",SwingConstants.CENTER);
        player1.setBorder(BorderFactory.createEtchedBorder());
        player2.setBorder(BorderFactory.createEtchedBorder());
        bagOfPlayer1.add(player1);
        bagOfPlayer2.add(player2);

        alphabetButtonsPlayer1 = new JButton[7];
        alphabetButtonsPlayer2 = new JButton[7];

        scoreTextLabel1 = new JLabel("SCORE",SwingConstants.CENTER);
        bagOfPlayer1.add(scoreTextLabel1);
        scoreTextLabel2 = new JLabel("SCORE",SwingConstants.CENTER);
        bagOfPlayer2.add(scoreTextLabel2);

        player1score = new JLabel("0", SwingConstants.CENTER);
        player2score = new JLabel("0",SwingConstants.CENTER);
        bagOfPlayer1.add(player1score);
        bagOfPlayer2.add(player2score);

        char a = 'a';

        for(int i = 0; i < 16; i++){
            if(i>0) {
                boardPanel.add(new JLabel( ( i ) + "",SwingConstants.CENTER));
            }else{
                boardPanel.add(new JLabel("  ",SwingConstants.CENTER));
            }
            for(int j = 1; j < 16; j++){
                if(i == 0){
                    boardPanel.add(new JLabel((char)(a+j-1) + "",SwingConstants.CENTER));
                    continue;
                }
                cells[i-1][j-1] = new ScrabbleCellButton("", i-1,j-1);
                cells[i-1][j-1].setActionCommand(i +" "+ j);
                cells[i-1][j-1].addActionListener(boardController);
                cells[i-1][j-1].setEnabled(false);
                if(i == 8 && j == 8){
                    cells[i-1][j-1].setEnabled(true);
                }
                boardPanel.add(cells[i-1][j-1], CENTER_ALIGNMENT);
            }
        }
        String player1Alphabet, player2Alphabet;
        for(int i = 0; i < 7; i++){
            player1Alphabet = ""+boardManager.getRandomAlphabets();
            player2Alphabet = ""+boardManager.getRandomAlphabets();

            alphabetButtonsPlayer1[i] = new JButton(player1Alphabet);
            bagOfPlayer1.add(alphabetButtonsPlayer1[i]);
            alphabetButtonsPlayer1[i].addActionListener(bagController);
            alphabetButtonsPlayer1[i].setActionCommand(1+","+i+","+player1Alphabet);

            alphabetButtonsPlayer2[i] = new JButton(player2Alphabet);
            bagOfPlayer2.add(alphabetButtonsPlayer2[i]);
            alphabetButtonsPlayer2[i].addActionListener(bagController);
            alphabetButtonsPlayer2[i].setActionCommand(2+","+i+","+player2Alphabet);
        }

        passPlayer1btn = new JButton("Pass");
        passPlayer2btn = new JButton("Pass");
        clearPlayer1 = new JButton("Clear");
        clearPlayer2 = new JButton("Clear");
        submitButtonPlayer1 = new JButton("SUBMIT");
        submitButtonPlayer2 = new JButton("SUBMIT");
        bagOfPlayer2.add(passPlayer2btn);
        bagOfPlayer1.add(clearPlayer1);
        bagOfPlayer2.add(clearPlayer2);
        bagOfPlayer1.add(passPlayer1btn);
        bagOfPlayer1.add(submitButtonPlayer1);
        bagOfPlayer2.add(submitButtonPlayer2);
        submitButtonPlayer2.addActionListener(bagController);
        submitButtonPlayer1.addActionListener(bagController);
        passPlayer1btn.addActionListener(bagController);
        passPlayer2btn.addActionListener(bagController);
        clearPlayer2.addActionListener(bagController);
        clearPlayer1.addActionListener(bagController);
        submitButtonPlayer1.addActionListener(bagController);
        submitButtonPlayer2.addActionListener(bagController);
        passPlayer1btn.setActionCommand(1+","+"pass");
        passPlayer2btn.setActionCommand(2+","+"pass");
        clearPlayer2.setActionCommand(1+","+"clear");
        clearPlayer1.setActionCommand(2+","+"clear");
        submitButtonPlayer1.setActionCommand(1+","+"submit");
        submitButtonPlayer2.setActionCommand(2+","+"submit");

        JLabel label = new JLabel("S C R A B B L E");
        label.setFont(new Font("Copperplate Gothic Bold", Font.ROMAN_BASELINE, 50));

        c.ipadx = 10;
        c.ipady = 10;
        c.fill = GridBagConstraints.VERTICAL;
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

        setSize(new Dimension(975,630));
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
