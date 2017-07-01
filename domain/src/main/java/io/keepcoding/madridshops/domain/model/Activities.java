package io.keepcoding.madridshops.domain.model;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

public class Activities implements ActivitiesIterable, ActivitiesUpdatable {

    private List<Activity> activities;


    public static Activities from (@NonNull final List<Activity> activityList) {

        final Activities activities = new Activities();

        for (final Activity activity : activityList){
            activities.add(activity);
        }
        return activities;
    }

    public Activities() {
    }


    // lazy getter

    private List<Activity> getActivity() {
        if (activities == null) {
            activities =  new LinkedList<>();
        }
        return activities;
    }




    @Override
    public void add(Activity activity) {

    }

    @Override
    public void delete(Activity activity) {

    }

    @Override
    public void update(Activity newActivity, long index) {

    }

    @Override
    public long size() {
        return 0;
    }

    @Override
    public Activity get(long index) {
        return null;
    }

    @Override
    public List<Activity> AllActivities() {
        return null;
    }
}
