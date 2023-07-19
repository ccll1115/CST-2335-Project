package algonquin.cst2335.cst2335_finalproject.bearimage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.cst2335_finalproject.R;

import algonquin.cst2335.cst2335_finalproject.databinding.ActivityBearImageBinding;


public class BearImage extends AppCompatActivity {
    ActivityBearImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bear_image);

        binding = ActivityBearImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences("BearImageData", Context.MODE_PRIVATE);

        String widthValue = prefs.getString("width", "");
        binding.width.setText(widthValue);

        String heightValue = prefs.getString("height", "");
        binding.height.setText(heightValue);

        binding.setButton.setOnClickListener(clk -> {

            EditText widthText = binding.width;
            String width = widthText.getText().toString();
            SharedPreferences.Editor editor1 = prefs.edit();
            editor1.putString("width", width);
            editor1.apply();

            EditText heightText = binding.height;
            String height = heightText.getText().toString();
            SharedPreferences.Editor editor2 = prefs.edit();
            editor2.putString("height", height);
            editor2.apply();

            TextView messageText = null;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to generate the image: " + messageText.getText())
                    .setTitle("Select:")
                    .setNegativeButton("No", (dialog, cl) -> {
                        Toast.makeText(this, "Cancel Image generated!", Toast.LENGTH_SHORT).show();
                    })
                    .setPositiveButton("Yes", (dialog, cl) -> {
                        Snackbar.make(messageText, "The image size is: width = " + width + "height = " + height, Snackbar.LENGTH_LONG)
                                .setAction("Undo", click -> {

                                    }).show();
                                }).create().show();
                            });
                        }

                    }