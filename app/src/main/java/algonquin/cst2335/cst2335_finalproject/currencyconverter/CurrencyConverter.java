package algonquin.cst2335.cst2335_finalproject.currencyconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import algonquin.cst2335.cst2335_finalproject.R;
import algonquin.cst2335.cst2335_finalproject.databinding.ActivityCurrencyConverterBinding;
import algonquin.cst2335.cst2335_finalproject.databinding.CurrencyConversionDetailBinding;

public class CurrencyConverter extends AppCompatActivity {
    ActivityCurrencyConverterBinding binding;

    private RecyclerView.Adapter myAdapter;

    private double dCurrencyRate = 5.0012;
    private String strSharedPreferencesName = "CurrencyConverter";
    ArrayList<String> conversionLog = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyConverterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_currency_converter);

        loadFromPrefs();

        binding.btnSwitch.setOnClickListener(clk -> {
            String strForm = binding.editFromCurrency.getText().toString();
            String strTo = binding.editToCurrency.getText().toString();

            binding.editFromCurrency.setText(strTo);
            binding.editToCurrency.setText(strForm);
        });

        binding.btnConvert.setOnClickListener(clk -> {
            String strAmount = binding.editAmount.getText().toString();
            String strForm = binding.editFromCurrency.getText().toString();
            String strTo = binding.editToCurrency.getText().toString();
            String strNewAmount = binding.editNewAmount.getText().toString();

            String strLog = strAmount + " " + strForm + " = " + strNewAmount + " " + strTo;

            conversionLog.add(strLog);
            myAdapter.notifyDataSetChanged();
            saveToPrefs();
        });

        binding.editAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String strAmount = s.toString();
                double dNewAmount = dCurrencyRate * Double.parseDouble(strAmount);

                String strNewAmount = String.format("%.4f",dNewAmount);
                Log.d("===Currency Converter===", strAmount);
                Log.d("===Currency Converter===", String.valueOf(dNewAmount) );

                binding.editNewAmount.setText(strNewAmount);
            }
        });

        binding.convertHistory.setLayoutManager(new LinearLayoutManager(this));
        binding.convertHistory.setAdapter(myAdapter = new RecyclerView.Adapter<convertHistoryRowHolder>() {
            @NonNull
            @Override
            public convertHistoryRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                CurrencyConversionDetailBinding detailBinding = CurrencyConversionDetailBinding.inflate(getLayoutInflater(), parent, false);
                return new convertHistoryRowHolder(detailBinding.getRoot());
                //return null;
            }

            @Override
            public void onBindViewHolder(@NonNull convertHistoryRowHolder holder, int position) {
                String  strDetail = conversionLog.get(position);
                holder.textConverDetail.setText(strDetail);
                holder.textConverTime.setText("12:12:12pm");
            }

            @Override
            public int getItemCount() {
                return conversionLog.size();
            }
        });
    }

    private void saveToPrefs() {
        SharedPreferences prefs = getSharedPreferences(strSharedPreferencesName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String strAmount = binding.editAmount.getText().toString();
        String strForm = binding.editFromCurrency.getText().toString();
        String strTo = binding.editToCurrency.getText().toString();
        String strNewAmount = binding.editNewAmount.getText().toString();

        editor.putString("Amount", strAmount);
        editor.putString("FromCurrency", strForm);
        editor.putString("ToCurrency", strTo);
        editor.putString("NewAmount", strNewAmount);
        editor.apply();
    }

    private void loadFromPrefs(){
        SharedPreferences prefs = getSharedPreferences(strSharedPreferencesName, Context.MODE_PRIVATE);

        String strAmount = prefs.getString("Amount", "1");
        String strForm = prefs.getString("FromCurrency", "CAD");
        String strTo = prefs.getString("ToCurrency", "CNY");
        //String strNewAmount = prefs.getString("NewAmount", "");

        binding.editAmount.setText(strAmount);
        binding.editFromCurrency.setText(strForm);
        binding.editToCurrency.setText(strTo);
    }

    public class convertHistoryRowHolder extends RecyclerView.ViewHolder{
        TextView textConverDetail;
        TextView textConverTime;


        public convertHistoryRowHolder(@NonNull View itemView) {
            super(itemView);

            textConverDetail = itemView.findViewById(R.id.txtConverDetail);
            textConverTime = itemView.findViewById(R.id.txtConverTime);
        }
    }
}