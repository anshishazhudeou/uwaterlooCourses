package com.example.cs349.switchpages;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void goNextPage(View view){

        // get selected value from the Radio Group
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int radio_id = radioGroup.getCheckedRadioButtonId();

        int version = 0;
        switch (radio_id){
            case R.id.radioButton1:
                version = 6;
                break;
            case R.id.radioButton2:
                version = 7;
                break;
            case R.id.radioButton3:
                version = 8;
                break;
        }


        // Set the selected value
        if(version > 0){
            Intent intent = new Intent(this, VersionActivity.class);
            intent.putExtra("version", version);
            startActivity(intent);
        }

    }
}
