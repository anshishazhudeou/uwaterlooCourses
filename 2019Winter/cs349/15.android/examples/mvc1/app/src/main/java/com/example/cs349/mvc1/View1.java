package com.example.cs349.mvc1;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;

/**
 * CS349_AndroidSamples
 * <p>
 * Created by J. J. Hartmann on 11/19/2017.
 * Email: j3hartma@uwaterloo.ca
 * Copyright 2017
 */

public class View1 extends LinearLayout implements Observer
{
    // Private Vars
    private Model model;
    private Button button;

    public View1(Context context, Model m)
    {
        super(context);
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "View1: Constructor");

        // Get the XML view description and "inflate" it into the display (like rendering)
        View.inflate(context, R.layout.view1_layout, this);

        // Save model
        model = m;

        // Add view to observer
        model.addObserver(this);

        // Get reference for button
        button = (Button) findViewById(R.id.view1_button);

        // Create controller for button
        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Increment model when button is clicked
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
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "View1: Update Called");

        // Update button text the click count from model
        button.setText(String.valueOf(model.getCounter()));
    }
}
