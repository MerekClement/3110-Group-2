import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class PlayerManager {
    private ArrayList<Player> _players;

    public PlayerManager(ArrayList<Player> players){
        this._players = players;
    }

    public boolean addScore(Player addedPlayer, ArrayList<String> letters, DictionaryManager dm){
        AtomicBoolean setReturn = new AtomicBoolean(false);
        AtomicReference<String> word = new AtomicReference<>("");
        int points = 0;
        letters.forEach((letter) -> {
            if (letter.length() > 1){
                setReturn.set(false);
            } else {
                word.set(word + letter);
            }
        });
        if (dm.isWord(word.get())){
            addedPlayer.setScore(1);
            return true;
        }

        return setReturn.get();
    }

    public ArrayList<Player> getPlayers(){
        return this._players;
    }

    // TODO: THIS FUNCTION ONLY SUPPORTS 2 PLAYERS. OPTIMIZE FOR MORE?
    public Player getNextMove(){
        if (this._players.size() > 2){
            throw new UnsupportedOperationException("More than 2 players not supported yet");
        }
        int player = 0;
        int previous = this._players.get(0).getMoves();
        for (int i = 1; i < this._players.size(); i++){
            if (previous == this._players.get(i).getMoves()){
                player = i - 1;
                break;
            } else {
                previous = this._players.get(i).getMoves();
                player = i;
            }
        }

        return this._players.get(player);
    }
}
