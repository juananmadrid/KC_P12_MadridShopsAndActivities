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


    // lazy getter (para crear Lista cuando vaya a usarse, no antes)

    private List<Activity> getActivities() {
        if (activities == null) {
            activities =  new LinkedList<>();
        }
        return activities;
    }



    @Override
    public void add(Activity activity) {
        getActivities().add(activity);
    }

    @Override
    public void delete(Activity activity) {
        getActivities().remove(activity);
    }

    @Override
    public void update(Activity newActivity, long index) {
        getActivities().set((int) index, newActivity);
    }

    @Override
    public long size() {
        return getActivities().size();
    }

    @Override
    public Activity get(long index) {
        return getActivities().get((int) index);
    }

    @Override
    public List<Activity> allActivities() {
        // No usamos return getActivities() para evitar que puedan añadirme
        // activities sin pasar por método add. Creo copia inmutable de la lista

        List<Activity> activityListCopy = new LinkedList<>();
        for (Activity activity : getActivities()) {
            activityListCopy.add(activity);
        }
        return activityListCopy;
    }
}
