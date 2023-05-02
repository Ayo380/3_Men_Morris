package com.example.cs478.project4;


// this class is being sent around from the threads// containing the players position
// and the players ID to identify the player
public class Position {

    private int row;
    private int col;
    private int ID;

    // constructor
    Position(int r, int c, int player){
        row = r;
        col = c;
        ID = player;
    }

    // make getters and setter
    public int getRow(){return this.row;}
    public int getCol(){return this.col;}
    public int getID(){return this.ID;}



    public void setRow(int r){this.row = r;}
    public void setCol(int c){this.col = c;}



    // this method was not used.
    //public void setID(int player){ this.ID= player; }



}
