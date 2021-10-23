package com.example.donation1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class donate extends Base {
    private      Button         donateButton;
    private      RadioGroup     paymentMethod;
    private      ProgressBar    progressBar;
    private      NumberPicker   amountPicker;
    private      EditText       amountText;
    private      TextView       amoutTotal;
    private int totalDonated = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

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
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amoutTotal = findViewById(R.id.amoutTotal);

        progressBar.setMax(10000);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        amoutTotal.setText("$0");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuReport : startActivity (new Intent(this, Report.class));
            //case R.id.donateMenu: startActivity (new Intent(this, donate.class));
            break;
        }
        return super.onOptionsItemSelected(item);
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