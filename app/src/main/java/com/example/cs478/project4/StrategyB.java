package com.example.cs478.project4;

//Ayokunle Olugboyo
// Strategy : It randomly places the pieces anywhere on the board
import android.util.Log;



import java.util.Random;

public class StrategyB {
    Position[] pieces ;
    int numPieces ;

    StrategyB(Position[] pieces, int numPieces){
        this.numPieces = numPieces;
        this.pieces = pieces;
    }

    void makeMoveB() {
        //if not all pieces placed, place a new piece
        if(numPieces < 3) {
            if(pieces == null)
                pieces = new Position[3];
            pieces[numPieces] = getRandomPointB();
            GameLogic.SetMove(new Position(-1,-1,2), pieces[numPieces]);
            numPieces++;
            return;
        }
        //move a random piece to a random location
        int pieceNumber = new Random().nextInt(3);
        Position randomPoint = getRandomPointB();
        //move the piece
        GameLogic.SetMove(pieces[pieceNumber], randomPoint);
    }



    private Position getRandomPointB() {
        // make sure it only plays in the first and last column
        int[] pos = {0,2,0};
        int posX = new Random().nextInt(3);
        int posY = new Random().nextInt(3);
        //int posY = pos[posX];

        //make sure point is open
        while(!(GameLogic.getBoard()[posX][posY] == 0)) {
            Log.i("randompointB" , "I am here");
            Log.i("pointXB",Integer.toString(posX));
            Log.i("pointYB",Integer.toString(posY));
            posX = new Random().nextInt(3);
            posY = new Random().nextInt(3);
            //posY = pos[posX];
        }
        Log.i("POSXA",Integer.toString(posX));
        Log.i("POSYA",Integer.toString(posY));

        return new Position(posX, posY, 2);
    }


    // getters so each thread can have the update moves and piece
    public Position[] getPostion(){
        return this.pieces;
    }
    public int getNumPieces(){
        return this.numPieces;
    }
}
