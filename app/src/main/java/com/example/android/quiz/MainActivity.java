package com.example.android.quiz;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int score;
    String validationSummary = "";
    String scoreSummary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitAnswers(View view) {

        checkName();
        checkQuestionOne();
        checkQuestionTwo();
        checkQuestionThree();
        checkQuestionFour();

        Context context = getApplicationContext();

        if (validationSummary.length() > 0) {
            // If there's text in the validationSummary string, toast message with details of what to fix instead of displaying score.
            Toast toast = Toast.makeText(context, validationSummary, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
            toast.show();

        } else {
            // If all answers have been filled in correctly, display toast message with score as well as a summary at the bottom of screen.
            String scoreMessage = "You scored " + score + " out of 5!";
            Toast toast = Toast.makeText(context, scoreMessage, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 0);
            toast.show();

            TextView displayScoreSummary = (TextView) findViewById(R.id.score_text_view);
            displayScoreSummary.setText(scoreSummary);
        }

        resetScore();
    }

    public void checkName() {
        EditText name = (EditText) findViewById(R.id.input_name);
        String getName = name.getText().toString();

        // Check if user has entered name. If they haven't entered name, add to validationSummary.
        if (getName.length() == 0) {
            validationSummary += "Please enter your name and submit again to find out your score.";
        } else {
            scoreSummary += "Hi " + getName + "! Here's how you did:";
        }
    }

    public void checkQuestionOne() {
        RadioButton questionOneCorrect = (RadioButton) findViewById(R.id.radiobutton_lion);
        boolean hasQuestionOneCorrect = questionOneCorrect.isChecked();

        // Check if user has chosen correct answer for question one.
        if(hasQuestionOneCorrect) {
            score++;
            scoreSummary += "\nQuestion one, correct!";
        } else {
            scoreSummary += "\nYou didn\'t get question one correct this time. Try again!";
        }
    }

    public void checkQuestionTwo() {

        // Variable to keep track of the number of boxes that have been checked.
        int checkboxCounter = 0;

        CheckBox questionTwoCorrect = (CheckBox) findViewById(R.id.checkbox_flourish);
        boolean hasQuestionTwoCorrect = questionTwoCorrect.isChecked();

        if (hasQuestionTwoCorrect) {
            checkboxCounter++;
        }

        CheckBox questionTwoCorrectTwo = (CheckBox) findViewById(R.id.checkbox_ollivanders);
        boolean hasQuestionTwoCorrectTwo = questionTwoCorrectTwo.isChecked();

        if (hasQuestionTwoCorrectTwo) {
            checkboxCounter++;
        }

        CheckBox questionTwoIncorrect = (CheckBox) findViewById(R.id.checkbox_whistle_bells);
        boolean hasQuestionTwoIncorrect = questionTwoIncorrect.isChecked();

        if (hasQuestionTwoIncorrect) {
            checkboxCounter++;
        }

        CheckBox questionTwoIncorrectTwo = (CheckBox) findViewById(R.id.checkbox_trelawney);
        boolean hasQuestionTwoIncorrectTwo = questionTwoIncorrectTwo.isChecked();

        if (hasQuestionTwoIncorrectTwo) {
            checkboxCounter++;
        }

        // Check if user has chosen correct answers for question two. If more than two checkboxes have been checked, add to validationSummary.
        if (checkboxCounter != 2) {
            validationSummary += "\nMake sure you check two answers for question two before submitting your answers again.";
        } else if ((hasQuestionTwoCorrect) && (hasQuestionTwoCorrectTwo)) {
            score += 2;
            scoreSummary += "\nYou got both parts of question two correct. 2 points to your house!";
        } else if ((hasQuestionTwoCorrect) || (hasQuestionTwoCorrectTwo)) {
            score++;
            scoreSummary += "\nYou got one part of question two correct! Try again to get both points.";
        } else {
            scoreSummary += "\nYou got both parts of question two wrong. Why not try again?";
        }
    }

    public void checkQuestionThree() {
        RadioButton questionThreeCorrect = (RadioButton) findViewById(R.id.radiobutton_lumos);
        boolean hasQuestionThreeCorrect = questionThreeCorrect.isChecked();

        // Check if user has chosen correct answer for question three.
        if(hasQuestionThreeCorrect) {
            score++;
            scoreSummary += "\nYou got question three correct! Professor Flitwick will be pleased.";
        } else {
            scoreSummary += "\nGive question three another go.";
        }
    }

    public void checkQuestionFour() {
        RadioButton questionFourCorrect = (RadioButton) findViewById(R.id.radiobutton_james);
        boolean hasQuestionFourCorrect = questionFourCorrect.isChecked();

        // Check if user has chosen correct answer for question four.
        if(hasQuestionFourCorrect) {
            score++;
            scoreSummary += "\nQuestion four was correct, James is Harry's middle name.";
        } else {
            scoreSummary += "\nQuestion four was wrong.";
        }
    }

    public void resetScore() {
        score = 0;
        scoreSummary = "";
        validationSummary = "";
    }
}