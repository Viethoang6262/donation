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

import ie.app.models.Donation;


public class donate extends Base {
    private      Button         donateButton;
    private      RadioGroup     paymentMethod;
    private      ProgressBar    progressBar;
    private      NumberPicker   amountPicker;
    private      EditText       amountText;
    private      TextView       amountTotal;

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
        amountTotal = findViewById(R.id.amoutTotal);

        progressBar.setMax(10000);
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        amountTotal.setText("$0");
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
        String method = paymentMethod.getCheckedRadioButtonId() == R.id.Paypal ?
                "PayPal" : "Direct";
        int donatedAmount = amountPicker.getValue();
        if (donatedAmount == 0)
        {
            String text = amountText.getText().toString();
            if (!text.equals(""))
                donatedAmount = Integer.parseInt(text);
        }
        if (donatedAmount > 0)
        {
            newDonation(new Donation(donatedAmount, method));
            totalDonated = totalDonated + donatedAmount;
            progressBar.setProgress(totalDonated);
            String totalDonatedStr = "$" + totalDonated;
            amountTotal.setText(totalDonatedStr);
        }
    }
}