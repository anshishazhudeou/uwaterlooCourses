package com.example.cs349.switchpages;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class VersionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        // Get the intent that started this activity
        Intent intent = getIntent();
        // extract the intent value in int
        int v = intent.getIntExtra("version", 0);
        SetVersion(v);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    void SetVersion(int v){
        TextView label = findViewById(R.id.version_txt);
        ImageView image_view = findViewById(R.id.imageView);

        // set version code and image
        switch(v){
            case 6:
                label.setText(R.string.v6);
                image_view.setImageResource(R.drawable.marshmallow);
                break;
            case 7:
                label.setText(R.string.v7);
                image_view.setImageResource(R.drawable.nougat);
                break;
            case 8:
                label.setText(R.string.v6);
                image_view.setImageResource(R.drawable.oreo);
                break;
        }

    }

}
