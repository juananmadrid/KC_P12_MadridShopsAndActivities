package io.keepcoding.madridshops.util.map.model;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridshops.domain.model.Activity;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.util.map.MapPinnable;

public class ActivityPin implements MapPinnable<Activity> {

    private Activity activity;

    public static List<MapPinnable> activityPinsFromActivities(Activities activities) {

        List<Activity> activityList = activities.allActivities();
        List<MapPinnable> activityPinLIst = new ArrayList<>();

        for (Activity activity : activityList) {
            ActivityPin ap = new ActivityPin(activity);
            activityPinLIst.add(ap);
        }
        return activityPinLIst;
    }


    public ActivityPin(Activity activity) {
        this.activity = activity;
    }

    @Override
    public float getLatitude() {
        return activity.getLatitud();
    }

    @Override
    public float getLongitude() {
        return activity.getLongitud();
    }

    @Override
    public String getPinDescription() {
        return activity.getName();
    }

    @Override
    public String getPinImageUrl() {
        return activity.imgUrl;
    }

    @Override
    public Activity getRelatedModelObject() {
        return activity;
    }
}

