JAVA:

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

XML:

<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/winnerTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="180dp"
        android:layout_marginLeft="180dp"
        android:layout_marginTop="30dp"
        android:layout_marginEnd="180dp"
        android:layout_marginRight="180dp"
        android:text="TextView"
        android:textSize="22sp"
        android:visibility="invisible"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/playAgainButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="140dp"
        android:layout_marginLeft="140dp"
        android:layout_marginTop="5dp"
        android:layout_marginEnd="140dp"
        android:layout_marginRight="140dp"
        android:onClick="playAgain"
        android:text="Play again!"
        android:visibility="invisible"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/winnerTextView" />

    <android.support.v7.widget.GridLayout
        android:id="@+id/gridLayout"
        android:layout_width="380dp"
        android:layout_height="380dp"
        android:layout_marginStart="0dp"
        android:layout_marginLeft="0dp"
        android:layout_marginTop="10dp"
        android:layout_marginEnd="10dp"
        android:layout_marginRight="0dp"
        android:layout_marginBottom="10dp"
        android:background="@drawable/board"
        app:columnCount="3"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.478"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.457"
        app:rowCount="3">

        <ImageView
            android:id="@+id/imageView1"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="17dp"
            android:onClick="dropin"
            android:tag="0"
            app:layout_column="0"
            app:layout_row="0" />

        <ImageView
            android:id="@+id/imageView2"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="17dp"
            android:tag="1"
            app:layout_column="1"
            app:layout_row="0"
            android:onClick="dropin" />

        <ImageView
            android:id="@+id/imageView3"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="12dp"
            android:tag="2"
            app:layout_column="2"
            app:layout_row="0"
            android:onClick="dropin" />

        <ImageView
            android:id="@+id/imageView4"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="17dp"
            android:layout_marginRight="13dp"
            android:tag="3"
            app:layout_column="0"
            app:layout_row="1"
            android:onClick="dropin" />
        <ImageView
            android:id="@+id/imageView5"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="17dp"
            android:layout_marginRight="13dp"
            android:tag="4"
            app:layout_column="1"
            app:layout_row="1"
            android:onClick="dropin" />
        <ImageView
            android:id="@+id/imageView6"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginLeft="12dp"
            android:layout_marginRight="13dp"
            android:tag="5"
            app:layout_column="2"
            app:layout_row="1"
            android:onClick="dropin" />

        <ImageView
            android:id="@+id/imageView7"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginTop="21dp"
            android:tag="6"
            app:layout_column="0"
            app:layout_row="2"
            android:onClick="dropin"/>

        <ImageView
            android:id="@+id/imageView8"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginTop="21dp"
            android:tag="7"
            app:layout_column="1"
            app:layout_row="2"
            android:onClick="dropin" />
        <ImageView
            android:id="@+id/imageView9"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_margin="10dp"
            android:layout_marginTop="21dp"
            android:tag="8"
            app:layout_column="2"
            app:layout_row="2"
            android:onClick="dropin" />
    </android.support.v7.widget.GridLayout>

</android.support.constraint.ConstraintLayout>
