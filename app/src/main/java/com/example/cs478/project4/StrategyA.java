package com.example.cs478.project4;
//Ayokunle Olugboyo
// Strategy : It randomly places the pieces anywhere on the board
import android.util.Log;


import java.util.Random;

public class StrategyA {
    Position[] pieces = null;
    int numPieces = 0;

    StrategyA(Position[] pieces, int numPieces){
        this.numPieces = numPieces;
        this.pieces = pieces;
    }

    void makeMoveA() {
        //if not all pieces placed, place a new piece
        if(numPieces < 3) {
            if(pieces == null)
                pieces = new Position[3];
            pieces[numPieces] = getRandomPointA();
            GameLogic.SetMove(new Position(-1,-1,1), pieces[numPieces]);
            numPieces++;
            return;
        }
        //move a random piece to a random location
        int pieceNumber = new Random().nextInt(3);
        Position randomPoint = getRandomPointA();
        //move the piece
        GameLogic.SetMove(pieces[pieceNumber], randomPoint);
    }



    private Position getRandomPointA() {
        int posX = new Random().nextInt(3);
        int posY = new Random().nextInt(3);
        //make sure point is open
        while(!(GameLogic.getBoard()[posX][posY] == 0)) {
            posX = new Random().nextInt(3);
            posY = new Random().nextInt(3);
        }
        Log.i("POSXA",Integer.toString(posX));
        Log.i("POSYA",Integer.toString(posY));
        return new Position(posX, posY, 1);
    }

    // getters so each thread can have the update moves and piece
    public Position[] getPostion(){
        return this.pieces;
    }
    public int getNumPieces(){
        return this.numPieces;
    }
}
