package Developer;

public class Utils {
    private boolean _dev = false;

    public Utils(boolean devMode) {
        this._dev = devMode;
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
