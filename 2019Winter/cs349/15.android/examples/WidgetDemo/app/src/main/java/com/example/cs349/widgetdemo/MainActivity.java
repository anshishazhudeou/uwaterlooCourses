package com.example.cs349.widgetdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set spinner value
        Spinner spinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.week_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    // For the checkbox
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkbox_morning:
                if (checked) {
                    // Add morning session
                } else {
                    // Remove morning session
                }
                break;
            case R.id.checkbox_afternoon:
                if (checked) {
                    // Add afternoon session
                } else {
                    // Remove afternoon session
                }
                break;
        }

    }

    // For attendance
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_yes:
                if (checked)
                    // Count on me
                    break;
            case R.id.radio_no:
                if (checked)
                    // Forget about me
                    break;
            case R.id.radio_maybe:
                if (checked)
                    // Maybe not...
                    break;
        }
    }

    // For CONFIRM button
    public void SendMessage(View view) {
        EditText nameView = findViewById(R.id.name);
        String name = nameView.getText().toString();
    }
}

