import Developer.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    // Managers and utils
    private DictionaryManager _dictionary;
    private BoardManager _board;
    private Utils _utils;
    private PlayerManager _players;

    private boolean _gameInProgress = false;
    // Instance variables

    // Constructor (Requires the utils and managers)
    public Game(DictionaryManager dm, BoardManager bm, Utils u, PlayerManager pm) {
        this._dictionary = dm;
        this._board = bm;
        this._utils = u;
        this._players = pm;
    }

    public boolean isRunning(){
        return this._gameInProgress;
    }

    public void terminate(){
        this._gameInProgress = false;
    }

    private boolean setProgress(boolean state){
        this._gameInProgress = state;
        return this._gameInProgress;
    }
    private void welcome() {
        System.out.print("Welcome to the game:\n");
        this._players.getPlayers().forEach((player) -> {
            System.out.println(player.name + " [" + player.get_id() + "]");
        });
    }

    private boolean requestStart() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (this._utils.isDev()) {
            return true;
        }
        System.out.println("Are you ready to begin? (y/n)");
        String res = reader.readLine();

        if (res.equals("y")){
            return true;
        } else {
            return false;
        }
    }

    public boolean mainLoop() {
        this.welcome();
        try {
            if (!this.requestStart()) {
                return false;
            } else {
                this.setProgress(true);
            }

            while (this._gameInProgress) {
                Player next = this._players.getNextMove();
                // vmakeMove(next);


            }

            return true;
        } catch (Exception e) {
            System.out.println("[ERROR] Error during main loop or io input. Terminating...");
            return false;
        }
    }
}
