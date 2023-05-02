package com.example.cs478.project4;

import android.annotation.SuppressLint;
import android.os.Looper;

import java.util.Random;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



public class ThreadA implements Runnable{

    private int pieces = 0;
    public static Handler ThreadAhandler;
    // keep track of all the moves made
    // able to move pieces across the board
    private Position [] move = null;
    //  add loopers to the threads as part of the project
    @SuppressLint("HandlerLeak")
    @Override
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
        ThreadAhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int what = msg.what;
                switch(what) {
                    case 0:
                        StrategyA A = new StrategyA(move,pieces);
                        A.makeMoveA();
                        pieces = A.getNumPieces();
                        move= A.getPostion();
                        break;
                    default: break;
                }
            }
        };
    }

    public static void quit() {
        ThreadAhandler.removeCallbacksAndMessages(null);
        ThreadAhandler.getLooper().quit();
    }


}
