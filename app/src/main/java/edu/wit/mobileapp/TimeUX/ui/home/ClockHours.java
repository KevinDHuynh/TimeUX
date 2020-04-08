package edu.wit.mobileapp.TimeUX.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.wit.mobileapp.TimeUX.R;

public class ClockHours extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Parent call (All state function must have it)
        super.onCreate(savedInstanceState);

        // The XML layout located in res>layout>activity_main.xml
        // will be inflated.
        setContentView(R.layout.activity_clockhours);

    }
}
