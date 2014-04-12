P4Arcade
By: Kevin Robins, Dennis Law
Version 1.0

Welcome to the arcade! This program allows you to play either Tic Tac Toe or Tetris. To play, simply press the Tetris button or Tic Tac Toe button to play each game. They set up the settings according to your liking, and you are ready to have a blast!

Tic Tac Toe rules are simple. Just get 3 like pieces in a row and you win! You can undo your last move by pressing the undo button on the top.

Tetris plays like it always does: stack bricks as long as you can! Use the directional keys to navigate the block. Pressing the Up key will make the block drop instantly, while pressing the Down key will rotate the block. Good luck!

To compile the program, simply type in this command:
	make compile

To run the program, type in this command:
	make run

If the images aren't loading in for the games, you may override the file paths maually by going into the .java files for TetrisForm.java, TetrisFormFP.java, TetrisFormNP.java, and TTTBoard.java respectively and changing the filepath to "<filepath>"/art/<images>.png.

<filepath> is what you put in for String projPath. (i.e. String projPath = <filepath>)