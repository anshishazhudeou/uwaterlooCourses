package com.example.fotag;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

public class MainActivity extends Activity {
    private GridView gridView;
    private ImageAdapter imageAdapter;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init gridView
        gridView = (GridView) this.findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        Button loadBnt = (Button) this.findViewById(R.id.load_img);
        loadBnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("load bnt click", "click");
                if(!isLoading) {
                    // clear image list
                    imageAdapter.clearBitmap();
                    // asynchronous download image
                    getBitmap(imageAdapter);
                    isLoading = true;
                }
                else {
                    tips();
                }
            }
        });

        Button clean_bnt = (Button) this.findViewById(R.id.clean_img);
        clean_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLoading) {
                    imageAdapter.clearBitmap();
                }
                else {
                    tips();
                }
            }
        });

        RatingBar ratingBar = (RatingBar) findViewById(R.id.filter_rate_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                imageAdapter.filter(rating);
            }
        });

        // Setting Views Based on Screen
        Configuration config = getResources().getConfiguration();
        setViewByOrientation(config);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Setting Views Based on Screen
        setViewByOrientation(newConfig);
    }

    private void tips() {
        AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Tips" ) ;
        builder.setMessage("Pictures are loading" ) ;
        builder.setPositiveButton("yes" ,  null );
        builder.show();
    }

    private void setViewByOrientation (Configuration config) {
        TextView title = (TextView) findViewById(R.id.title);
        if (config.orientation == ORIENTATION_LANDSCAPE){
            // Horizontal orientation
            gridView.setNumColumns(2);
            title.setVisibility(View.VISIBLE);
        }
        else{
            // Vertical orientation
            gridView.setNumColumns(1);
            title.setVisibility(View.GONE);
        }
    }

    private void getBitmap(final ImageAdapter imageAdapter){
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bitmap bitmap = (Bitmap) msg.obj;
                imageAdapter.addBitmap(bitmap);
                if (imageAdapter.getCount() == 10 ) {
                    isLoading = false;
                }
                Log.i("handle", "get one message");
            }
        };

        String [] imageNames = {
                "bunny.jpg","chinchilla.jpg",
                "doggo.jpg","fox.jpg","hamster.jpg",
                "husky.jpg","kitten.png","loris.jpg",
                "puppy.jpg	","sleepy.png"
        };
        // start thread download image
        for (final String imageName:imageNames) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("download thread", "download:" + imageName);
                    Bitmap bitmap = getImageFromUrl("https://www.student.cs.uwaterloo.ca/~cs349/w19/assignments/images/" + imageName);
                    if (bitmap != null) {
                        Message msg = new Message();
                        msg.obj = bitmap;
                        Log.i("download thread", "download success:" + imageName);
                        handler.sendMessage(msg);
                    }
                    else {
                        Log.i("download thread","download error:" + imageName);
                    }
                }
            }).start();
        }
    }

    private Bitmap getImageFromUrl(String url)
    {
        // generate bitmap from url
        URL imageURL = null;
        Bitmap bitmap = null;

        try {
            imageURL = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(300000);
            conn.setReadTimeout(300000);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
