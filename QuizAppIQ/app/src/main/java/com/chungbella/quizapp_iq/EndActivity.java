package com.chungbella.quizapp_iq;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class EndActivity extends AppCompatActivity {
    SharedPreferences myPreferences;
    SharedPreferences.Editor prefsEditor;

    TextView text1;
    TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_screen);

        myPreferences = getSharedPreferences("com.chungbella.lab05_lifecycle.sharedprefs",
                MODE_PRIVATE);
        prefsEditor = myPreferences.edit();

        String name = myPreferences.getString("name", "oops");
        Integer score = myPreferences.getInt("score", -1);
        Log.d("score", "" + score);

        text1 = findViewById(R.id.text1);
        String first_text = name + ", your IQ is:";
        text1.setText(first_text);
        text2 = findViewById(R.id.text2);

        String iq = "GENIUS";
        if(score == 1){
            iq = "LOW";
        }
        if(score == 2){
            iq = "BELOW\nAVERAGE";
        }
        if(score == 3){
            iq = "AVERAGE";
        }

        text2.setText(iq);
    }
}
