// This applcation is used to show a simple version of the game scrabble.
// It has been created for the purposes of SYSC 3110 - Software Design.
//
// @author: Merek Clement (101138443)
// @author: Kathan Patel (101146368)
// @author: Jatin Kumar (PUT YOUR STUDENT ID HERE)
// @author: Faiaz Ahsan (PUT YOUR STUDENT ID HERE)
//
// While this repo is public, please do not copy this code for your own assignments.

// Imports

// Constants
// PUT CONSTANTS HERE

import Developer.Utils;

public class Main {
    // Instance variables
    private static DictionaryManager _dictionaryManager;
    private static boolean _devMode = false;
    private static Utils _devUtils;


    /**
     * The following setup method is used to setup a game instance
     * @return {boolean} - Returns true if the game was setup successfully
     */
    private static boolean setup() {
        // Setup dev utils
        _devUtils = new Utils(_devMode);

        // Initalize the DictionaryManager
        _dictionaryManager = new DictionaryManager(_devUtils);
        _devUtils.printDev("DictionaryManager loaded with " + _dictionaryManager.getWords().size() + " words");
        return testSetup();
    }

    /**
     * Stubbed test function to validate the setup.
     * @return
     */
    private static boolean testSetup() {
        // TODO: Stubbed for now until testing.
        return true;
    }

    /**
     * The main method for the application.
     * @param args {String[]} - The arguments passed to the application.
     *              -dev -> This is used to enable dev mode.
     */
    public static void main(String[] args) {
        // Runtime args
        if (args.length > 0) {
            for(int i = 0; i < args.length; ++i) {
                if (args[i].toString().equals("-dev")) {
                    _devMode = true;
                }
            }
        }

        // Setup game
        setup();
        _devUtils.printDev(String.valueOf(_dictionaryManager.isWord("tEstg")));
        _devUtils.printDev(String.valueOf(_dictionaryManager.isWord("tEst")));
    }
}