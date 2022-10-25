package Developer;

import java.util.ArrayList;

public class Utils {
    private boolean _dev = false;

    public Utils(boolean devMode) {
        this._dev = devMode;
    }

    public static ArrayList<String> makeList(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Jim");
        names.add("Bob");
        names.add("Austin");
        names.add("Joe");
        names.add("Alex");
        names.add("Linus");
        names.add("Tom");
        names.add("Derek");
        names.add("Chris");
        return names;
    }

    public boolean isDev(){
        return this._dev;
    }

    /**
     * The following method is to print a dev message
     * @param message {String} - The message to print
     */
    public void printDev(String message) {
        if (this._dev) {
            System.out.println("[DEV] " + message);
        }
    }
}
