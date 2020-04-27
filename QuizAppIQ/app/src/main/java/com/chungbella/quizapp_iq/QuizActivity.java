package com.chungbella.quizapp_iq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    List<String> questions;
    List<String> choices;
    List<String> answers;

    TextView que;
    TextView timer;

    RadioButton choice1;
    RadioButton choice2;
    RadioButton choice3;
    RadioButton choice4;

    RadioGroup rbg;

    int current_question;
    int correct;
    int time_left;

    SharedPreferences myPreferences;
    SharedPreferences.Editor prefsEditor;

    CountDownTimer count_down_timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question1);

        myPreferences = getSharedPreferences("com.chungbella.lab05_lifecycle.sharedprefs",
                MODE_PRIVATE);
        prefsEditor = myPreferences.edit();

        que = findViewById(R.id.question);

        rbg = findViewById(R.id.rbg);

        choice1 = findViewById(R.id.radio1);
        choice2 = findViewById(R.id.radio2);
        choice3 = findViewById(R.id.radio3);
        choice4 = findViewById(R.id.radio4);

        current_question = 0;
        correct = 0;

        questions = new ArrayList<String>();
        questions.add("What is the next number in the series: 7, 10, 16, 28, 52, ___");
        questions.add("None of the runners is a teacher.\n" +
                "All of the attendees are runners.");
        questions.add("Afraid is to scared as brave is to _______.");

        choices = new ArrayList<String>();
        choices.add("88-100-66-76");
        choices.add("some drones are teachers-no runners are attendees-teachers are not attendees-" +
                "all runners are teachers");
        choices.add("courageous-cowardly-timid-trustworthy");

        answers = new ArrayList<String>();
        answers.add("Continent");
        answers.add("100");
        answers.add("teachers are not attendees");
        answers.add("courageous");

        timer = findViewById(R.id.timer);
        time_left = 20;

        count_down_timer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                time_left -=1;
                String time = "00:" + time_left;
                timer.setText(time);
            }

            public void onFinish() {
                timer.setText("Time's Up!");
                nextQuestion(true);
            }
        }.start();

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion(false);
            }
        });
    }

    private void nextQuestion(boolean time) {
        if(!time && (choice1.isChecked() || choice2.isChecked() || choice3.isChecked() || choice4.isChecked())) {
            RadioButton selected = findViewById(rbg.getCheckedRadioButtonId());
            String chosenAnswer = "" + selected.getText();
            if(chosenAnswer.equals(answers.get(current_question))){
                correct += 1;
            }

            current_question += 1;
            if (current_question > 3) {
                prefsEditor.putInt("score", correct);
                prefsEditor.apply();

                Intent intent = new Intent(QuizActivity.this, EndActivity.class);
                startActivity(intent);
            } else {
                que.setText(questions.get(current_question - 1));
                String[] four_choices = choices.get(current_question - 1).split("-");
                choice1.setText(four_choices[0]);
                choice2.setText(four_choices[1]);
                choice3.setText(four_choices[2]);
                choice4.setText(four_choices[3]);

                count_down_timer.cancel();
                timer.setText("00:20");
                time_left = 20;
                count_down_timer = new CountDownTimer(20000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time_left -=1;
                        String time = "00:" + time_left;
                        timer.setText(time);
                    }

                    public void onFinish() {
                        timer.setText("Time's Up!");
                        nextQuestion(true);
                    }
                }.start();
            }
        }
        else if(time){
            current_question += 1;
            if (current_question > 3) {
                prefsEditor.putInt("score", correct);
                prefsEditor.apply();

                Intent intent = new Intent(QuizActivity.this, EndActivity.class);
                startActivity(intent);
            } else {
                que.setText(questions.get(current_question - 1));
                String[] four_choices = choices.get(current_question - 1).split("-");
                choice1.setText(four_choices[0]);
                choice2.setText(four_choices[1]);
                choice3.setText(four_choices[2]);
                choice4.setText(four_choices[3]);

                count_down_timer.cancel();
                timer.setText("00:20");
                time_left = 20;
                count_down_timer = new CountDownTimer(20000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time_left -=1;
                        String time = "00:" + time_left;
                        timer.setText(time);
                    }

                    public void onFinish() {
                        timer.setText("Time's Up!");
                        nextQuestion(true);
                    }
                }.start();
            }
        }
        else {
            Toast.makeText(QuizActivity.this, "You must select an answer.", Toast.LENGTH_SHORT).show();
        }
    }
}
