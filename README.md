# 3110-Group-2

This is a text based implementation of the board game called Scrabble.

Our implementation of the Scrabble game has a Developer package which has a “Utils” class containing utilities relevant to our project. The words that can be used to play the game are taken from a list of words provided by the Professor. Our version of scrabble will fail to recognize any words outside of the provided list even if the words are valid English words.

Moreover, the project consists of a Game, “Board Manager”, “Dictionary Manager”, “Player Manager”, “Player” and a “Main” class.

BoardManager.java :
To begin with, The “BoardManager” class is responsible for creating a board on which the game will be played. The board will be of a specified height and width.

DictionaryManager.java :
The “DictionaryManager” class is responsible for managing the dictionary created from the list of words provided by the Professor. It also makes sure the word implemented by the user is in the list and valid.

PlayerManager.java :
The PlayerManager manages players playing the game and which player's turn it is to implement their word. The PlayerManager does not support more than two players at this moment of time.

Player.java :
The “Player” class creates player objects that will be playing the game and they are managed by the “PlayerManager”.

Game.java :
The Game class is responsible for initializing objects of the “BoardManager”, “DictionaryManager” and “PlayerManager”. It also provides instructions to the users on how to play our version of Scrabble.

Main.java :
The Main class is used to setup an instance of Game which also sets up the Board Manager, Dictionary Manager and the player manager with the requested players.
