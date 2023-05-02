package com.example.cs478.project4;

//Ayokunle Olugboyo
//This class contains most of the game logic and also passes the thread moves to the UI thread
//to update the UI

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class GameLogic {

    //instance variables
    private static int[][] gameBoard; // to create the board
    public static int handler;
    private static Handler Handle;


    //public constructor to create the game
    // recieves the UI hander as a parameter
    // so the handler can update the UI
    public GameLogic(Handler h){
        handler = 0;


        //create game board
        gameBoard = new int[3][3];
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                gameBoard[i][j] = 0;


        //create the handler
        this.Handle = h;
    }


    // make a static method to return the same gameboard at anypoint in the game
    public synchronized static int[][] getBoard(){
        return gameBoard;
    }

    //create static methods to movepiece and validate moves

    // function to set a piece, takes in the new and old postion
    public synchronized static void SetMove(Position Old, Position New){
        //find a way to make the user see the move ???
        Log.i("SetMove","I am here");
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException i){

        }
        Log.i("SetMove1","I am here");
        // get the old postion
        Log.i("Debug", Integer.toString(Old.getRow()));
        int oldrow = Old.getRow();

        int oldcol = Old.getCol();

        //get the new position
        int newrow = New.getRow();
        int newcol = New.getCol();

        //check if it is a new position
        if(oldrow != -1|| oldcol != -1)
            gameBoard[oldrow][oldcol] = 0;

        //set playerid at new location
        gameBoard[newrow][newcol] = New.getID();

        //store the position into the point of the piece
        Old.setRow(newrow);
        Old.setCol(newcol);
        Log.i("SetMove2","I am here");
        //post move
        // send it to the mainHandler which is the
        // UI handler
        Message msg = Handle.obtainMessage(0, oldrow, oldcol, Old);
        Log.i("Game logic","I am here");
        Handle.sendMessage(msg);
        Log.i("Game logic1","I am here");

    }


    public static int checkWIn(){
        // Check for horizontal wins
        for (int i = 0; i < 3; i++) {
            if (gameBoard[i][0] != 0 && gameBoard[i][0] == gameBoard[i][1] && gameBoard[i][1] == gameBoard[i][2]) {
                return gameBoard[i][0];
            }
        }

        // Check for vertical wins
        for (int j = 0; j < 3; j++) {
            if (gameBoard[0][j] != 0 && gameBoard[0][j] == gameBoard[1][j] && gameBoard[1][j] == gameBoard[2][j]) {
                return gameBoard[0][j];
            }
        }

        // If no winner is found, return 0
        return 0;
    }


}
