package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvBindByName;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static class TonyAwardWinner {
        @CsvBindByName(column = "Year Won")
        private String year;

        @CsvBindByName(column = "Category Won")
        private String category;

        @CsvBindByName(column = "Who Won?")
        private String winner;

        @CsvBindByName(column = "What Show Was It? (Might Be Same as Last)")
        private String show;

        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
        public String getCategory() {
            return category;
        }
        public void setCategory(String category) {
            this.category = category;
        }
        public String getWinner() {
            return winner;
        }
        public void setWinner(String winner) {
            this.winner = winner;
        }
        public String getShow() {
            return show;
        }
        public void setShow(String show) {
            this.show = show;
        }
    }

    private List<TonyAwardWinner> winners;
    private TonyAwardWinner currentWinner;
    private Random rand;

    private TextView questionTextView;
    private EditText answerEditText;
    private Button submitButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rand = new Random();
        winners = new CsvToBeanBuilder<TonyAwardWinner>(new InputStreamReader(getResources().openRawResource(R.raw.tonys)))
                .withType(TonyAwardWinner.class).build().parse();

        questionTextView = findViewById(R.id.questionText);
        answerEditText = findViewById(R.id.answerText);
        submitButton = findViewById(R.id.guessButton);
        resultTextView = findViewById(R.id.resultText);

        Button backToHomeButton = findViewById(R.id.backToHomeButton);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
                generateQuestion();
            }
        });
        generateQuestion();
    }

    private void generateQuestion(){
        currentWinner = winners.get(rand.nextInt(winners.size()));
        questionTextView.setText(
                "Guess who won the Tony in the year " +
                        currentWinner.getYear() +
                        " for the category " +
                        currentWinner.getCategory() + ":");
        answerEditText.setText("");
    }

    private void checkAnswer(){
        String userInput = answerEditText.getText().toString();
        if (userInput.equalsIgnoreCase(currentWinner.getWinner())) {
            resultTextView.setText("Correct!");
        } else {
            resultTextView.setText("Sorry, the correct answer was " + currentWinner.getWinner());
        }
    }
}