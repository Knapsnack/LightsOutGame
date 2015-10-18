package com.example.koresore.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {


    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        //import variables
        Intent intent = getIntent();

        final int gameHeight = intent.getIntExtra("gameHeight", 0);
        final int gameWidth = intent.getIntExtra("gameWidth", 0);

        //initialize variables
        final Button checkButton = (Button) findViewById(R.id.checkButton);
        final Button newGameButton = (Button) findViewById(R.id.buttonNewGame);


        //Dimension Buttons
        int scrWidth = getWindowManager().getDefaultDisplay().getWidth();
        Resources r = getResources();
        float gameMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, r.getDisplayMetrics());

        int buttonWidth = (scrWidth -  (int)(gameMargin * 2) )/ gameWidth;
        int buttonHeight = (scrWidth) / gameHeight;

        //Dimension Grid
        GridLayout lightGridLayout = (GridLayout) findViewById(R.id.gridOfLights);
        lightGridLayout.invalidate();

        lightGridLayout.setRowCount(gameHeight);
        lightGridLayout.setColumnCount(gameWidth);


        //Build buttons
        Button lightGrid[][] = new Button[gameWidth][gameHeight];

        //row j, colunm i
        for (int j = 0; j < gameHeight; j++){
            for (int i = 0; i < gameWidth; i++){

                //Build Button
                lightGrid[i][j] = new Button(this);
                lightGrid[i][j].setLayoutParams(new LinearLayout.LayoutParams(buttonWidth, buttonHeight));

                //id Button
                lightGrid[i][j].setId((i * 100) + j);

                lightGrid[i][j].setBackgroundColor(Color.GREEN);
                lightGrid[i][j].setTag(0);

                //for OnClickListener
                final int finalI = i;
                final int finalJ = j;

                lightGrid[i][j].setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                //Flip color of button pressed
                                Button centerButton = (Button) findViewById((finalI * 100) + finalJ);
                                toggleLight(centerButton);

                                //Flip color of related buttons
                                if (finalI + 1 < gameWidth) {
                                    Button topButton = (Button) findViewById(((finalI + 1) * 100) + finalJ);
                                    toggleLight(topButton);
                                }
                                if (finalI - 1 >= 0) {
                                    Button bottomButton = (Button) findViewById(((finalI - 1) * 100) + finalJ);
                                    toggleLight(bottomButton);
                                }
                                if (finalJ - 1 >= 0) {
                                    Button leftButton = (Button) findViewById((finalI * 100) + (finalJ - 1));
                                    toggleLight(leftButton);
                                }
                                if (finalJ + 1 < gameHeight) {
                                    Button rightButton = (Button) findViewById((finalI * 100) + (finalJ + 1));
                                    toggleLight(rightButton);
                                }
                            }
                        }
                );

                lightGridLayout.addView(lightGrid[i][j]);


            }
        }

        //Randomly decide which game buttons to press and press them
        for (int j = 0; j < gameHeight; j++){
            for (int i = 0; i < gameWidth; i++){
                if (Math.random() * 2 < 1){
                    lightGrid[i][j].performClick();
                }
            }
        }

        //Click listeners
        newGameButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newGameButtonClicked();
                    }
                }
        );
        checkButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        checkButtonClicked(gameWidth, gameHeight);
                    }
                }
        );
    }

    //Toggles the color of the button passed
    public void toggleLight(Button button){
        if((int) button.getTag() == 0){
            button.setBackgroundColor(Color.RED);
            button.setTag(1);
        } else {
            button.setBackgroundColor(Color.GREEN);
            button.setTag(0);
        }
    }

    //Returns true iff all values in gameWidth x gameHeight set are equal
    public Boolean valuesAreEven(int gameWidth, int gameHeight){
        Boolean valuesAreEven = true;
        int value = (int) findViewById(0).getTag();
        for (int i = 0; i < gameWidth && valuesAreEven; i++){
            for (int j = 0; j < gameHeight && valuesAreEven; j++) {
                valuesAreEven = (value == (int) findViewById((i * 100) + j).getTag());
            }
        }
        return valuesAreEven;
    }

    //Return to TitleScreen
    public void newGameButtonClicked(){
        finish();
    }

    //Print whether or not player has succeeded
    public void checkButtonClicked(int gameWidth, int gameHeight){
        TextView textViewWidth = (TextView) findViewById(R.id.isSolved);
        if(valuesAreEven(gameWidth, gameHeight)){
            textViewWidth.setText("You did it!");
        }else{
            textViewWidth.setText("You have failed.");
        }
    }

}
