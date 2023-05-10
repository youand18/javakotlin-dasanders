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
    private Button[] options = new Button[4];
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
        resultTextView = findViewById(R.id.resultText);

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
            resultTextView.setText("Correct! They won for the show " + currentWinner.getShow());
        } else if (answer.equals(currentWinner.getWinner())) {
            resultTextView.setText("Correct!");
        } else if (!currentWinner.getCategory().equals("Best Play") && !currentWinner.getCategory().equals("Best Revival of a Musical") && !currentWinner.getCategory().equals("Best Revival of a Play")){
            resultTextView.setText("Sorry, the correct answer was " + currentWinner.getWinner() + " for the show " + currentWinner.getShow());
        } else {
            resultTextView.setText("Sorry, the correct answer was " + currentWinner.getWinner());
        }
        generateQuestion();
    }
}