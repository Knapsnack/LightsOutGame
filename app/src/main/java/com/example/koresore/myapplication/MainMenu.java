package com.example.koresore.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        final Button buttonIncreaseHeight = (Button)findViewById(R.id.buttonIncreaseHeight);
        final Button buttonIncreaseWidth = (Button)findViewById(R.id.buttonIncreaseWidth);
        final Button buttonDecreaseHeight = (Button)findViewById(R.id.buttonDecreaseHeight);
        final Button buttonDecreaseWidth = (Button)findViewById(R.id.buttonDecreaseWidth);
        final Button buttonBuildPuzzle = (Button)findViewById(R.id.buttonBuildPuzzle);
        buttonIncreaseWidth.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        //change value of gameWidth
                        TextView textViewWidth = (TextView) findViewById(R.id.textWidthValue);
                        int gameWidth = Integer.parseInt(textViewWidth.getText().toString());
                        gameWidth++;
                        textViewWidth.setText("" + gameWidth);

                        //Button change ability to click buttons
                        buttonDecreaseWidth.setClickable(true);
                        if(gameWidth == 25){
                            buttonIncreaseWidth.setClickable(false);
                        }
                    }
                }
        );

        buttonDecreaseWidth.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        //change value of gameWidth
                        TextView textViewWidth = (TextView) findViewById(R.id.textWidthValue);
                        int gameWidth = Integer.parseInt(textViewWidth.getText().toString());
                        gameWidth--;
                        textViewWidth.setText("" + gameWidth);

                        //change ability to click buttons
                        buttonIncreaseWidth.setClickable(true);
                        if(gameWidth == 1){
                            buttonDecreaseWidth.setClickable(false);
                        }
                    }
                }
        );

        buttonIncreaseHeight.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v) {
                        //change value of gameHeight
                        TextView textViewHeight = (TextView) findViewById(R.id.textHeightValue);
                        int gameHeight = Integer.parseInt(textViewHeight.getText().toString());
                        gameHeight++;
                        textViewHeight.setText("" + gameHeight);

                        //change ability to click buttons
                        buttonDecreaseHeight.setClickable(true);
                        if(gameHeight == 25){
                            buttonIncreaseHeight.setClickable(false);
                        }
                    }
                }
        );

        buttonDecreaseHeight.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        //change value of gameHeight
                        TextView textViewHeight = (TextView) findViewById(R.id.textHeightValue);
                        int gameHeight = Integer.parseInt(textViewHeight.getText().toString());
                        gameHeight--;
                        textViewHeight.setText("" + gameHeight);

                        //change ability to click buttons
                        buttonIncreaseHeight.setClickable(true);
                        if (gameHeight == 1) {
                            buttonDecreaseHeight.setClickable(false);
                        }
                    }
                }
        );

        buttonBuildPuzzle.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(MainMenu.this, GameScreen.class);

                        TextView textViewHeight = (TextView) findViewById(R.id.textHeightValue);
                        intent.putExtra("gameHeight", Integer.parseInt(textViewHeight.getText().toString()));

                        TextView textViewWidth = (TextView) findViewById(R.id.textWidthValue);
                        intent.putExtra("gameWidth", Integer.parseInt(textViewWidth.getText().toString()));
                        startActivity(intent);

                    }
                }
        );

    }
}
