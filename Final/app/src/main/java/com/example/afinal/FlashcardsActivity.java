package com.example.afinal;

import android.os.Bundle;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.InputStreamReader;
import java.util.HashSet;
import com.example.afinal.TonyAwardWinner;
import java.util.List;
import java.util.Set;
import androidx.appcompat.app.AppCompatActivity;

public class FlashcardsActivity extends AppCompatActivity {
    List<TonyAwardWinner> winners;
    Set<String> categories = new HashSet<>();
    //winners = new CsvToBeanBuilder<TonyAwardWinner>(new InputStreamReader(getResources().openRawResource(R.raw.tonys)))
            //.withType(TonyAwardWinner.class).build().parse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
