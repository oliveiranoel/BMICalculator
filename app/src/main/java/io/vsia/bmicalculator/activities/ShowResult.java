package io.vsia.bmicalculator.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import io.vsia.bmicalculator.R;

public class ShowResult extends AppCompatActivity {

    TextView textView;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        double bmi = intent.getDoubleExtra("bmi", 0);

        textView = findViewById(R.id.textView7_result);
        textView.setText(result);

        ratingBar = findViewById(R.id.ratingBar);

        ratingBar.setNumStars(5);

        if (bmi < 16 || bmi > 40) {
            ratingBar.setRating(1);
        } else if (bmi >= 18.5 && bmi < 25) {
            ratingBar.setRating(5);
        } else if  (bmi >= 17 && bmi < 18.5 || bmi >= 25 && bmi < 30) {
            ratingBar.setRating(4);
        } else if (bmi >= 30 && bmi < 35) {
            ratingBar.setRating(3);
        } else if (bmi >= 16 && bmi < 17 || bmi >= 35 && bmi < 40) {
            ratingBar.setRating(1);
        } else {
            ratingBar.setRating(0);
        }
    }
}
