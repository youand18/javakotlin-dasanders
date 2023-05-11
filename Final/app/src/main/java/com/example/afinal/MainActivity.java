package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvBindByName;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Random;
import com.example.afinal.TonyAwardWinner;

public class MainActivity extends AppCompatActivity {



    private List<TonyAwardWinner> winners;
    private TonyAwardWinner currentWinner;
    private Random rand;
    private int correctGuesses = 0;
    private int incorrectGuesses = 0;
    private TextView questionTextView;
    private Button[] options = new Button[4];
    private Button submitButton;
    private TextView resultTextView;
    private TextView correctTextView;
    private TextView incorrectTextView;

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
        resultTextView = findViewById(R.id.resultText);
        correctTextView = findViewById(R.id.correctText);
        correctTextView.setText("Correct Guesses: " + correctGuesses);
        incorrectTextView = findViewById(R.id.incorrectText);
        incorrectTextView.setText("Incorrect Guesses: " + incorrectGuesses);
        Button backToHomeButton = findViewById(R.id.backToHomeButton);
        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        options[0] = findViewById(R.id.option1);
        options[1] = findViewById(R.id.option2);
        options[2] = findViewById(R.id.option3);
        options[3] = findViewById(R.id.option4);

        for (Button option : options) {
            option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(((Button) v).getText().toString());
                }
            });
        }
        generateQuestion();
    }

    private void generateQuestion(){
        int winnerIndex = rand.nextInt(winners.size());
        currentWinner = winners.get(winnerIndex);
        questionTextView.setText(String.format("Who won the Tony for '%s' in %s?", currentWinner.getCategory(), currentWinner.getYear()));

        int correctOption = rand.nextInt(4);
        Set<String> incorrectOptions = new HashSet<>();
        while (incorrectOptions.size() < 3) {
            int incorrectIndex = rand.nextInt(winners.size());
            TonyAwardWinner incorrectWinner = winners.get(incorrectIndex);
            if (!incorrectWinner.getWinner().equals(currentWinner.getWinner()) && incorrectWinner.getCategory().equals(currentWinner.getCategory())){
                incorrectOptions.add(incorrectWinner.getWinner());
            }
        }

        List<String> allOptions = new ArrayList<>(incorrectOptions);
        allOptions.add(correctOption, currentWinner.getWinner());

        for (int i = 0; i < 4; i++){
            options[i].setText(allOptions.get(i));
        }

    }

    private void checkAnswer(String answer){
        if (answer.equals(currentWinner.getWinner()) && !currentWinner.getCategory().equals("Best Musical") && !currentWinner.getCategory().equals("Best Play") && !currentWinner.getCategory().equals("Best Revival of a Musical") && !currentWinner.getCategory().equals("Best Revival of a Play")) {
            resultTextView.setText(currentWinner.getWinner() + " is Correct! They won for the show " + currentWinner.getShow() + " in the year " + currentWinner.getYear() + ".");
            correctGuesses++;
            correctTextView.setText("Correct Guesses: " + correctGuesses);

        } else if (answer.equals(currentWinner.getWinner())) {
            resultTextView.setText(currentWinner.getWinner() + " is Correct!");
            correctGuesses++;
            correctTextView.setText("Correct Guesses: " + correctGuesses);

        } else if (!currentWinner.getCategory().equals("Best Play") && !currentWinner.getCategory().equals("Best Revival of a Musical") && !currentWinner.getCategory().equals("Best Revival of a Play")){
            incorrectGuesses++;
            resultTextView.setText("Sorry, the correct answer was " + currentWinner.getWinner() + " for the show " + currentWinner.getShow());
            incorrectTextView.setText("Incorrect Guesses: " + incorrectGuesses);

        } else {
            incorrectGuesses++;
            resultTextView.setText("Sorry, the correct answer was " + currentWinner.getWinner());
            incorrectTextView.setText("Incorrect Guesses: " + incorrectGuesses);

        }
        generateQuestion();
    }
}