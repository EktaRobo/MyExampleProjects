package com.example.ekta.myexampleprojects.manager;

import android.os.Bundle;

import com.example.ekta.myexampleprojects.constant.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class NotificationListenerManager {

    private static NotificationListenerManager mNotifier = null;
    private HashMap<Constants.NotificationEnum, ArrayList<Observer>> mObservablesMap = null;
    private NotificationListenerManager() {
        mObservablesMap = new HashMap<Constants.NotificationEnum, ArrayList<Observer>>();
    }

    /**
     * Returns an instance of the {@link NotificationListenerManager}.
     *
     * @return
     */
    public static NotificationListenerManager getInstance() {
        return NotificationManagerHolder.mInstance;
    }

    /**
     * Registers an Observer for the specified notification.
     *
     * @param notificationName
     * @param observer
     */
    public void addObserver(Constants.NotificationEnum notificationName, Observer observer) {
        if (observer == null) {
            throw new IllegalArgumentException("The observer is null.");
        }

        if (notificationName == null) {
            throw new IllegalArgumentException("The notificationName is null.");
        }

        ArrayList<Observer> observerList = null;
        synchronized (mObservablesMap) {
            if (mObservablesMap.containsKey(notificationName)) {
                observerList = mObservablesMap.get(notificationName);
                if (!observerList.contains(observer))
                    observerList.add(observer);
            } else {
                observerList = new ArrayList<Observer>();
                if (!observerList.contains(observer))
                    observerList.add(observer);
                mObservablesMap.put(notificationName, observerList);
            }
        }
    }

    /**
     * Unregisters the observer from a particular notification.
     *
     * @param notificationName
     * @param observer
     */
    public void removeObserver(Constants.NotificationEnum notificationName, Observer observer) {
        ArrayList<Observer> observerList = null;
        synchronized (mObservablesMap) {
            if (mObservablesMap.containsKey(notificationName)) {
                observerList = mObservablesMap.get(notificationName);

                //Remove the observer
                if (observerList.contains(observer))
                    observerList.remove(observer);

				/* Remove the key mapping if there are no other observers for the
                particular notification */
                if (observerList.isEmpty())
                    mObservablesMap.remove(notificationName);
            }
        }
    }

    /**
     * Removes a particular observer from all the notifications.
     * Ideally used for cleanup when an Observer closes or goes out of scope.
     *
     * @param observer
     */
    public void removeObserver(Observer observer) {
        synchronized (mObservablesMap) {
            Set<Constants.NotificationEnum> keys = mObservablesMap.keySet();
            for (Constants.NotificationEnum key : keys) {
                ArrayList<Observer> observers = mObservablesMap.get(key);
                if (observers.contains(observer))
                    observers.remove(observer);
            }
        }
    }

    /**
     * Notifies all the observers on occurance of an event.
     * Optional data can also be sent.
     *
     * @param notificationName
     * @param data
     */
    public void notifyObservers(Constants.NotificationEnum notificationName, Bundle data) {
        int size = 0;
        Observer[] observersToNotify = null;
        synchronized (mObservablesMap) {
            if (mObservablesMap.containsKey(notificationName)) {
                ArrayList<Observer> observers = mObservablesMap.get(notificationName);
                size = observers.size();
                observersToNotify = new Observer[size];
                observers.toArray(observersToNotify);
            }
        }

        //Notify all observers
        if (observersToNotify != null) {
            for (int i = 0; i < observersToNotify.length; i++) {
                observersToNotify[i].update(notificationName, data);
            }
        }

    }

    public interface Observer {
        public void update(Constants.NotificationEnum notificationName, Bundle data);
    }

    private static class NotificationManagerHolder {
        public static final NotificationListenerManager mInstance = new
                NotificationListenerManager();
    }
}

