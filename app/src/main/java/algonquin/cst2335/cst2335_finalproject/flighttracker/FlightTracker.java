package algonquin.cst2335.cst2335_finalproject.flighttracker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.cst2335_finalproject.databinding.ActivityFlightTrackerBinding;
import algonquin.cst2335.cst2335_finalproject.databinding.ActivityMainBinding;

public class FlightTracker extends AppCompatActivity {

    private ActivityFlightTrackerBinding binding;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlightTrackerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("FlightTrackerPrefs", MODE_PRIVATE);

        // Show Toast
        Toast.makeText(this, "Welcome to Flight Tracker", Toast.LENGTH_LONG).show();

        String storedAirportCode = sharedPreferences.getString("airportCode", "");
        binding.airportCodeEditText.setText(storedAirportCode);

        binding.searchFlightBtn.setOnClickListener(v -> {
            String input = binding.airportCodeEditText.getText().toString();

            if (input.length() != 3) {
                showErrorSnackbar("Error! Airport code must be 3 letters.");
            } else if (!input.matches("[a-zA-Z]+")) {
                showErrorSnackbar("Error! Airport code must only contain letters.");
            } else {
                new AlertDialog.Builder(FlightTracker.this)
                        .setTitle("Search Flights")
                        .setMessage("Are you sure you want to search flights for this airport?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("airportCode", input);
                            editor.apply();

                            // Show a Toast
                            Toast.makeText(FlightTracker.this, "Searching flights for airport code: " + input, Toast.LENGTH_LONG).show();

                            executeSearch(input);

                            // Navigate to FlightSearchResult Activity
                            Intent intent = new Intent(FlightTracker.this, FlightSearchResult.class);
                            intent.putExtra("airportCode", input); // Pass the airport code to the new Activity
                            startActivity(intent);
                        })
                        .setNegativeButton("No", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

    private void showErrorSnackbar(String message) {
        Snackbar.make(binding.getRoot(), message, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        binding.airportCodeEditText.setText("");
                    }
                })
                .show();
    }

    private void executeSearch(String airportCode) {

    }


}
