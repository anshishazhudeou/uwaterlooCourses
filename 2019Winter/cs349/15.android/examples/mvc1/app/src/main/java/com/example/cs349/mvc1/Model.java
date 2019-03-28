package com.example.cs349.mvc1;

import android.util.Log;
import java.util.Observable;
import java.util.Observer;

/**
 * CS349_AndroidSamples
 * <p>
 * Created by J. J. Hartmann on 11/19/2017.
 * Email: j3hartma@uwaterloo.ca
 * Copyright 2017
 */


/**
 * Class Model
 * - Stores a persistent state for the application.
 */
public class Model extends Observable
{
    // Private Variables
    private int mCounter;

    /**
     * Model Constructor:
     * - Init member variables
     */
    Model() {
        mCounter = 0;
    }

    /**
     * Get mCounter Values
     * @return Current value mCounter
     */
    public int getCounter()
    {
        return mCounter;
    }

    /**
     * Set mCounter Value
     * @param i
     * -- Value to set Counter
     */
    public void setCounter(int i)
    {
        Log.d("DEMO", "Model: set counter to " + mCounter);
        this.mCounter = i;
    }

    /**
     * Increment mCounter by 1
     */
    public void incrementCounter()
    {
        mCounter++;
        Log.d("DEMO", "Model: increment counter to " + mCounter);

        // Observable API
        setChanged();
        notifyObservers();
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Observable Methods
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Helper method to make it easier to initialize all observers
     */
    public void initObservers()
    {
        setChanged();
        notifyObservers();
    }

    /**
     * Deletes an observer from the set of observers of this object.
     * Passing <CODE>null</CODE> to this method will have no effect.
     *
     * @param o the observer to be deleted.
     */
    @Override
    public synchronized void deleteObserver(Observer o)
    {
        super.deleteObserver(o);
    }

    /**
     * Adds an observer to the set of observers for this object, provided
     * that it is not the same as some observer already in the set.
     * The order in which notifications will be delivered to multiple
     * observers is not specified. See the class comment.
     *
     * @param o an observer to be added.
     * @throws NullPointerException if the parameter o is null.
     */
    @Override
    public synchronized void addObserver(Observer o)
    {
        super.addObserver(o);
    }

    /**
     * Clears the observer list so that this object no longer has any observers.
     */
    @Override
    public synchronized void deleteObservers()
    {
        super.deleteObservers();
    }

    /**
     * If this object has changed, as indicated by the
     * <code>hasChanged</code> method, then notify all of its observers
     * and then call the <code>clearChanged</code> method to
     * indicate that this object has no longer changed.
     * <p>
     * Each observer has its <code>update</code> method called with two
     * arguments: this observable object and <code>null</code>. In other
     * words, this method is equivalent to:
     * <blockquote><tt>
     * notifyObservers(null)</tt></blockquote>
     *
     * @see Observable#clearChanged()
     * @see Observable#hasChanged()
     * @see Observer#update(Observable, Object)
     */
    @Override
    public void notifyObservers()
    {
        super.notifyObservers();
    }
}
