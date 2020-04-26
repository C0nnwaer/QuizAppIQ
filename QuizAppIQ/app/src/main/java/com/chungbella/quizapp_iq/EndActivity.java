package com.chungbella.quizapp_iq;

import android.content.SharedPreferences;
import android.os.Bundle;
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

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
    }
}
