package algonquin.cst2335.cst2335_finalproject.triviaquestion;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.cst2335_finalproject.R;
import algonquin.cst2335.cst2335_finalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.cst2335_finalproject.databinding.ActivityTriviaQuestionBinding;

public class TriviaQuestion extends AppCompatActivity {
    private ActivityTriviaQuestionBinding binding;
    private static String TAG = "TriviaQuestion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia_question);

        binding = ActivityTriviaQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("Name", Context.MODE_PRIVATE);

        String name = prefs.getString("name", "");

        //Set the name to the edited text
        binding.inputName.setText(name);

        binding.saveNameButton.setOnClickListener(clk -> {
            String inputname = binding.inputName.getText().toString();
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", inputname);
            editor.apply();
            //String whatIsTyped = binding.inputName.getText().toString();
            Toast.makeText(TriviaQuestion.this, "You input name as" + inputname, Toast.LENGTH_SHORT).show();
            // Log.w(TAG, "In onCreate() - Loading Widgets");

        });

        binding.artButton.setOnClickListener(clk -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(TriviaQuestion.this);
            builder.setMessage("Do you want to do the quiz about Art?")
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {
                        dialog.dismiss();
                    })
                    .setPositiveButton("Yes", (dialog, cl) -> {
                        // For example, you can create another method to display the records
                        //showTopScoreRecords();

                        // After showing the records, you can also dismiss the AlertDialog if needed
                        dialog.dismiss();
                    });

            // Create and show the AlertDialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        binding.historyButton.setOnClickListener(clk -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(TriviaQuestion.this);
            builder.setMessage("Do you want to do the quiz about History?")
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {
                        dialog.dismiss();
                    })
                    .setPositiveButton("Yes", (dialog, cl) -> {
                        // For example, you can create another method to display the records
                        //showTopScoreRecords();

                        // After showing the records, you can also dismiss the AlertDialog if needed
                        dialog.dismiss();
                    });

            // Create and show the AlertDialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        binding.generalButton.setOnClickListener(clk -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(TriviaQuestion.this);
            builder.setMessage("Do you want to do the General quiz?")
                    .setTitle("Question:")
                    .setNegativeButton("No", (dialog, cl) -> {
                        dialog.dismiss();
                    })
                    .setPositiveButton("Yes", (dialog, cl) -> {
                        // For example, you can create another method to display the records
                        //showTopScoreRecords();

                        // After showing the records, you can also dismiss the AlertDialog if needed
                        dialog.dismiss();
                    });

            // Create and show the AlertDialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

        binding.showRecords.setOnClickListener(clk -> {
            Snackbar.make(TriviaQuestion.this.getCurrentFocus(), "You choose to look the top5 records", Snackbar.LENGTH_LONG).show();
        });

    }
}