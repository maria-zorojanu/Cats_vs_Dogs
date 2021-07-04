package com.example.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 - cat; 1 -dog; 2 - empty
    int activePlayer = 0;

    boolean gameActive = true;

    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropin(View view){

        ImageView counter= (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.cat);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.dog);
                activePlayer = 0;
            }

            gameActive = false;

            for (int i=0; i<9; i++){
                if(gameState[i]==2)
                {
                    gameActive = true;
                }
            }
            if(!gameActive){
                Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                TextView textViewWinner = (TextView) findViewById(R.id.winnerTextView);

                textViewWinner.setText("Draw");

                playAgainButton.setVisibility(View.VISIBLE);
                textViewWinner.setVisibility(View.VISIBLE);
            }
            counter.animate().translationY(0).setDuration(30);

            String winner = "";

            for (int[] index : winningPositions)
                if (gameState[index[0]] == gameState[index[1]] && gameState[index[0]] == gameState[index[2]] && gameState[index[0]] != 2) {
                    //Someone has won!
                    if (activePlayer == 1) {
                        winner += "Cat";
                    } else {
                        winner += "Dog";
                    }
                    gameActive = false;
                    Log.i("Winner", winner);

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    TextView textViewWinner = (TextView) findViewById(R.id.winnerTextView);

                    textViewWinner.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);
                    textViewWinner.setVisibility(View.VISIBLE);
                    break;
                }


        }
        if(!gameActive){
            Log.i("Game state", "Somebody has won!");
        }

    }

    public void playAgain(View view){

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        TextView textViewWinner = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        textViewWinner.setVisibility(View.INVISIBLE);

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i<9; i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        activePlayer = 0;

        gameActive = true;

        for(int i=0; i<9; i++){
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}