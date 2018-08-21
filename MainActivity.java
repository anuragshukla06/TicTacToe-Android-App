package com.example.ashutosh.tictactoe;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean gameIsActive = true;
    boolean won = false;
    int k = 0;
    int[] tapped = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};




    public void moveIn(View view){

        ImageView red = (ImageView) view;

        int tag = Integer.parseInt(red.getTag().toString());


        if(tapped[tag] == 2 && gameIsActive) {
            red.setTranslationY(-1000f);
            if (k % 2 == 0) {
                red.setImageResource(R.drawable.x);
                tapped[tag] = 1;
            } else {
                red.setImageResource(R.drawable.zero);
                tapped[tag] = 0;
            }
            red.animate().translationY(0f).setDuration(300);
            k += 1;
        }
        if(!won) {
            for (int[] i : winningPositions) {
                if (tapped[i[0]] == tapped[i[1]] && tapped[i[1]] == tapped[i[2]] && tapped[i[0]] != 2) {
                    LinearLayout WonMessage = (LinearLayout) findViewById(R.id.WonMessage);
                    TextView message = (TextView) findViewById(R.id.Message);
                    String p = "";

                    if(tapped[i[0]] == 1){
                        p = "X";
                    }
                    else{
                        p = "O";
                    }
                    message.setText(p + " won!!");
                    WonMessage.setVisibility(View.VISIBLE);

                    gameIsActive = false;
                    won = true;
                }
            }
        }
        if(!won) {
            int sum = 0;

            for (int i : tapped) {
                if (i != 2) {
                    sum += 1;
                }
            }

            if (sum == 9) {
                LinearLayout Draw = (LinearLayout) findViewById(R.id.Draw);
                Draw.setVisibility(View.VISIBLE);
                gameIsActive = false;
            }
        }

    }

    public void PlayAgain(View view){
        won = false;
        gameIsActive = true;
        android.support.v7.widget.GridLayout Grid = (android.support.v7.widget.GridLayout) findViewById(R.id.Gridl);

        for(int i = 0; i<tapped.length; i++){
            tapped[i] = 2;
        }
        System.out.println(tapped[1]);

        for(int i= 0; i <  Grid.getChildCount(); i++){
            ((ImageView) Grid.getChildAt(i)).setImageResource(0);
        }
        LinearLayout Draw = (LinearLayout) findViewById(R.id.Draw);
        Draw.setVisibility(View.INVISIBLE);
        LinearLayout WonMessage = (LinearLayout) findViewById(R.id.WonMessage);
        WonMessage.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
