package algonquin.cst2335.cst2335_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import algonquin.cst2335.cst2335_finalproject.bearimage.BearImage;
import algonquin.cst2335.cst2335_finalproject.currencyconverter.CurrencyConverter;
import algonquin.cst2335.cst2335_finalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.cst2335_finalproject.flighttracker.FlightTracker;
import algonquin.cst2335.cst2335_finalproject.triviaquestion.TriviaQuestion;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.flightTracker.setOnClickListener( click ->
                startActivity(new Intent(MainActivity.this, FlightTracker.class))
        );

        mainBinding.currencyConverter.setOnClickListener( click ->
                startActivity(new Intent(MainActivity.this, CurrencyConverter.class))
        );

        mainBinding.triviaQuestion.setOnClickListener( click ->
                startActivity(new Intent(MainActivity.this, TriviaQuestion.class))
        );

        mainBinding.bearImage.setOnClickListener( click ->
                startActivity(new Intent(MainActivity.this, BearImage.class))
        );

    }
}