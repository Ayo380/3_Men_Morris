package com.example.cs478.project4;

import android.annotation.SuppressLint;
import android.os.Looper;

import java.util.Random;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;


public class ThreadB implements Runnable{

    private int pieces = 0;
    public static Handler ThreadBhandler;
    // keep track of all the moves made
    // able to move pieces across the board
    private Position [] move = null;
    //  add loopers to the threads as part of the project

    public void run() {
        // preparing looper
        Looper.prepare();
        // createHander();
        HandlerFunction();
        GameLogic.handler +=1;
        // start the loop
        Looper.loop();

    }

    private void HandlerFunction() {
        ThreadBhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case 0:
                        StrategyB B = new StrategyB(move,pieces);
                        B.makeMoveB();
                        pieces = B.getNumPieces();
                        move= B.getPostion();
                        break;
                    default: break;
                }
            }
        };
    }

    public static void quit() {
        ThreadBhandler.removeCallbacksAndMessages(null);
        ThreadBhandler.getLooper().quit();
    }


}
