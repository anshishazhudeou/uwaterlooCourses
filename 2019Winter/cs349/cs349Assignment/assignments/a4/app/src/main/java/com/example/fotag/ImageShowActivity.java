package com.example.fotag;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class ImageShowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_image_show);

        ImageView imageView = (ImageView) findViewById(R.id.image_detail);
        // get bitmap index
        int bitmapIndex = getIntent().getIntExtra("bitmap", 0);
        imageView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        // get bitmap data from ImageAdapter
        imageView.setImageBitmap(ImageAdapter.getBitmap(bitmapIndex));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
