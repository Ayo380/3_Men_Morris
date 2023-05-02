# 3_Men_Morris

This is a Andoird App that implements the 3 Men Morris strategy game.

This is an Android app that implements the Three Men Morris (TMM) strategy game, where two Java worker threads play against each other. The game is played on a 3x3 board, with two players starting with three pieces each. The objective is to arrange three pieces in a line either horizontally or vertically. If there is no winner after placing the pieces, the players take turns moving one piece to an empty location on the board. The app have a UI thread that maintains and updates the display, and two worker threads that take turns making moves, waiting for the opponent to complete their move, and communicating with the UI thread.

The UI thread is responsible for displaying the initial empty board, updating the board after each move, showing a button to start the game, receiving notifications of moves by the worker threads, informing the worker threads of their opponent's move, checking the status of the game, displaying an appropriate message to indicate the outcome of each game, and signaling the worker threads that the game is over. The worker threads use different strategies for winning the game, and the game is played at a speed that allows a human user to understand each move.

--Video Link
https://drive.google.com/drive/folders/1LUEzwO_z68o2ncVgujJkAhoKoh9E1d8t?usp=sharing


