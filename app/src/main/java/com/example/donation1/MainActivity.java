package com.example.donation1;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donateButton = findViewById(R.id.donateButton);

        if(donateButton != null)
        {
            Log.v("Donate", "Relly got the donate button");
        }
        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donateButtonPressed(v);
            }
        });
        //khai bao cac giao dien thanh toan
        paymentMethod = (RadioGroup)findViewById(R.id.paymentMethod);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(10000);

        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
            return true;
        //}return super.onOptionsItemSelected(item);
    }

    public void donateButtonPressed(View view)
    {
        int amount = amountPicker.getValue();
        int radioId = paymentMethod.getCheckedRadioButtonId();
        String method = "";
        if(radioId == R.id.Paypal) {
            method = "PayPal";
        } else {
            method = "Direct";
        }
        totalDonated = totalDonated + amount;
        progressBar.setProgress(totalDonated);
        Log.v("Donate", "Donate Pressed, with amount: " + amount + ", Method: " + method);
        Log.v("Donate","Current total" + totalDonated);
    }
}