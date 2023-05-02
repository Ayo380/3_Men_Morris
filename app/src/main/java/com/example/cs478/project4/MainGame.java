package com.example.cs478.project4;
////Ayokunle Olugboyo



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Message;

public class MainGame extends Activity  {

    private static ImageView[][] board;
    private Button startbutton;
    GridLayout gridLayout;
    private static Thread threadA ;
    private static Thread threadB;
    // This handler, running on the UI thread, will be our server
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
            int what = msg.what ;
            switch (what) {
                case 0:
                    //update the board
                    Log.i("updateBoard function", "I am here");
                    UpdateBoard((Position)msg.obj, msg.arg1, msg.arg2);
                    break;
                default: break;
            }

        }
    }	; // Handler is associated with UI Thread





    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        gridLayout = findViewById(R.id.Board);
        threadA = null;
        threadB = null;
        board = new ImageView[3][3];
        board[0][0] = gridLayout.findViewById(R.id.colA0);
        board[0][1] = gridLayout.findViewById(R.id.colA1);
        board[0][2] = gridLayout.findViewById(R.id.colA2);
        board[1][0] = gridLayout.findViewById(R.id.colB0);
        board[1][1] = gridLayout.findViewById(R.id.colB1);
        board[1][2] = gridLayout.findViewById(R.id.colB2);
        board[2][0] = gridLayout.findViewById(R.id.colC0);
        board[2][1] = gridLayout.findViewById(R.id.colC1);
        board[2][2] = gridLayout.findViewById(R.id.colC2);

        startbutton = findViewById(R.id.startGameBtn);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(threadA != null || threadB != null){
                    closeThreads();
                }

                Log.i("starting ", "i am here");

                new GameLogic(mHandler);

                // CREATE THE WORKER THREADS
                threadA = new Thread(new ThreadA());
                threadB = new Thread(new ThreadB());


                //clear the board
                for(int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++)
                        board[i][j].setAlpha(0.0f);
                }

                // start the game
                game();

            }
        });
    }

    public void UpdateBoard(Position P, int oldrow, int oldcol) {
        //get the new position
        int newrow = P.getRow();
        int newcol = P.getCol();

        // make the old postion invisible
        if(oldrow != -1 || oldcol != -1)
            board[oldrow][oldcol].setAlpha(0.0f);


        // update the UI color based on the ID of player
        if (P.getID() == 1) {
            board[newrow][newcol].setImageResource(R.drawable.white);
        } else {
            board[newrow][newcol].setImageResource(R.drawable.black);
        }

        board[newrow][newcol].setAlpha(1.0f);

        // check for win
        int win = GameLogic.checkWIn();


        /// if someone wins you wanna end the threads
        // and print the appropriate result
        if (win != 0) {
            // end the threads before
            closeThreads();
            if (P.getID() == 1)
                Toast.makeText(this, "White is the Winner!!", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Black is the Winner!!", Toast.LENGTH_LONG).show();

            return;
        } else {// make next move
            if (P.getID() == 1) {
                Log.i("make move", "ThreadB");
                Message msg = ThreadB.ThreadBhandler.obtainMessage(0, oldrow, oldcol, P);
                ThreadB.ThreadBhandler.sendMessage(msg);
                Log.i("make move", "ThreadB");
            }
            else {
                Log.i("make move", "ThreadA");
                Message msg = ThreadA.ThreadAhandler.obtainMessage(0, oldrow, oldcol, P);
                ThreadA.ThreadAhandler.sendMessage(msg);
                Log.i("make move", "ThreadA");
            }
        }
    }



    public void game(){
        GameLogic.handler = 0;
        // start thread
        threadA.start();
        threadB.start();

        // started the handler
        Log.i("start" , "thread are started");

        // waiting to make sure the number of handler is 2
        while(GameLogic.handler < 2);

        // the first worker thread always starts the game
       // Log.i("start1" , "thread are started");
        ThreadA.ThreadAhandler.sendMessage(ThreadA.ThreadAhandler.obtainMessage(0));
        Log.i("start1" , "thread are started");
    }

    public void closeThreads(){
        //post runnables to exit threads
        ThreadB.ThreadBhandler.post(new Runnable() {
            @Override
            public void run() {
                ThreadB.quit();
            }
        });
        ThreadA.ThreadAhandler.post(new Runnable() {
            @Override
            public void run() {
                ThreadA.quit();
            }
        });

        //make sure threads are dead
        while(threadA.isAlive());
        while(threadB.isAlive());

        //reset threads to null
        threadB = null;
        threadA = null;
        mHandler.removeCallbacksAndMessages(null);
    }

}
