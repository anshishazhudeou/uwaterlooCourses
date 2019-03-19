package com.example.cs349.mvc1;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity
{
    // Private Vars
    Model model;

    /**
     * OnCreate
     * -- Called when application is initially launched.
     *    @see <a href="https://developer.android.com/guide/components/activities/activity-lifecycle.html">Android LifeCycle</a>
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "onCreate called");

        // Load base UI layout from resources.
        setContentView(R.layout.activity_main);

        // Init Model
        model = new Model();

        ////////////////////////////////////////
        // Setup Views
        ////////////////////////////////////////
        View1 view1 = new View1(this, model);
        ViewGroup v1 = (ViewGroup) findViewById(R.id.mainactivity_1);
        v1.addView(view1);

        View2 view2 = new View2(this, model);
        ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
        v2.addView(view2);

        // Init Views
        model.initObservers();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Save and Restore State
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Save the state of the application
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "MainActivity: Save model state.");

        // Serialize all sateful values form model
        outState.putInt("Counter", model.getCounter());
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     * <p>
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "MainActivity: Restore model");

        // Get all values and restore them to model
        model.setCounter(savedInstanceState.getInt("Counter"));
    }
}
