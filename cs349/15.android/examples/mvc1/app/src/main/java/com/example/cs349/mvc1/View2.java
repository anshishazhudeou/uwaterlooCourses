package com.example.cs349.mvc1;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

/**
 * CS349_AndroidSamples
 * <p>
 * Created by J. J. Hartmann on 11/19/2017.
 * Email: j3hartma@uwaterloo.ca
 * Copyright 2017
 */

public class View2 extends LinearLayout implements Observer
{
    // Private Vars
    private Model model;
    private TextView textView;

    public View2(Context context, Model m)
    {
        super(context);
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "View2: Constructor Called");

        // Get the XML view description and "inflate" it into the display (like rendering)
        View.inflate(context, R.layout.view2_layout, this);

        // Save model ref
        this.model = m;

        // Add view to model list of observers
        model.addObserver(this);

        // get reference to widgets and manipulate updates
        textView = (TextView) findViewById(R.id.view2_textView);

        // Create a controller to increment counter when clicked
        textView.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Increment counter everytime view text view is clicked.
                model.incrementCounter();
            }
        });

    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg)
    {
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "View2: Update TextView");

        // Build String based on model state
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < model.getCounter(); ++i){
            s.append("x");
        }

        // Update textView
        textView.setText(s);
    }
}
