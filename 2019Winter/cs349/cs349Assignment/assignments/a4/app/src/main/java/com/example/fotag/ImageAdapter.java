package com.example.fotag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    private Context  context;
    private static List<Bitmap> bitmaps = new ArrayList<>();
    private static List<Float> ratings = new ArrayList<>();
    private static float currentFilterRate = 0;

    public ImageAdapter(Context  context) {
        this.context = context;
    }

    public void addBitmap(Bitmap bitmap) {
        // add bitmap and ratings value, Refresh gridview
        bitmaps.add(bitmap);
        ratings.add(new Float(0));
        notifyDataSetChanged();
    }

    public void filter(float rating) {
        // set filter rate and Refresh gridview
        // (when gridview show imageview, filter imageview and ratingbar)
        currentFilterRate = rating;
        notifyDataSetChanged();
    }

    public void clearBitmap() {
        // delete all imageview and ratingbar, Refresh gridview
        bitmaps.clear();
        ratings.clear();
        notifyDataSetChanged();
    }

    public static Bitmap getBitmap(int index) {
        // when image full-screen show, get bitmap data
        return bitmaps.get(index);
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // gridview show item call
        ViewHolder viewHolder = null;

        // first get ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.layout_grid_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.itemImg = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.image_bar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Set the data to the control according to the corresponding location
        viewHolder.itemImg.setImageBitmap(bitmaps.get(position));
        viewHolder.itemImg.setTag(position);

        viewHolder.ratingBar.setRating(ratings.get(position));
        viewHolder.ratingBar.setTag(position);

        // add listener event
        viewHolder.itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open full screen activity
                ImageView imageView = (ImageView) v;
                Intent intent = new Intent(context, ImageShowActivity.class);
                // Transfer parameters,the item position
                intent.putExtra("bitmap", (int)v.getTag());
                context.startActivity(intent);
            }
        });

        viewHolder.ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (fromUser) {
                    // when user click
                    // get current index
                    int index = (int)ratingBar.getTag();
                    // update rating value
                    ratings.set(index, rating);

                    Log.i("test", "get tag: " + index);
                    // sync filter display
                    notifyDataSetChanged();
                }
            }
        });

        // filter
        if (ratings.get(position) < currentFilterRate) {
            viewHolder.itemImg.setVisibility(View.GONE);
            viewHolder.ratingBar.setVisibility(View.GONE);
        }
        else {
            viewHolder.itemImg.setVisibility(View.VISIBLE);
            viewHolder.ratingBar.setVisibility(View.VISIBLE);
        }

        return convertView;
    }

    class ViewHolder {
        // Item containing components
        ImageView itemImg;
        RatingBar ratingBar;
    }
}
