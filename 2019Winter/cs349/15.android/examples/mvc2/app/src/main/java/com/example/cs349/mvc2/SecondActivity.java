package com.example.cs349.mvc2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

public class SecondActivity extends AppCompatActivity implements Observer
{
    // Private Vars
    Model mModel;
    TextView mTextView;

    /**
     * OnCreate
     * -- Called when application is initially launched.
     *
     * @param savedInstanceState
     * @see <a href="https://developer.android.com/guide/components/activities/activity-lifecycle.html">Android LifeCycle</a>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "SecondActivity: OnCreate");
        super.onCreate(savedInstanceState);

        // Set Content View
        setContentView(R.layout.activity_second);

        // Get Model instance
        mModel = Model.getInstance();
        mModel.addObserver(this);

        // Get TextView Reference
        mTextView = (TextView) findViewById(R.id.view2_textView);

        // Create controller to increment counter
        mTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // Increment mModel counter
                mModel.incrementCounter();
            }
        });

        // Init observers
        mModel.initObservers();
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d(String.valueOf(R.string.DEBUG_MVC_ID), "MainActivity: onDestory");

        // Remove observer when activity is destroyed.
        mModel.deleteObserver(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Options Menu
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.view2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle interaction based on item selection
        switch (item.getItemId())
        {
            case R.id.menu2_GoToMainActivity:
                // Create Intent to launch second activity
                Intent intent = new Intent(this, MainActivity.class);

                // Start activity
                startActivity(intent);
                finish();   // don't place this on the stack, can't use back button
                return true;
        }

        return super.onOptionsItemSelected(item);
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
        for (int i = 0; i < mModel.getCounter(); ++i)
        {
            s.append("x");
        }

        // Update textView
        mTextView.setText(s);
    }
}