package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.keepcoding.madridshops.domain.interactors.SaveAllActivitiesIntoCacheInteractor;
import io.keepcoding.madridshops.domain.managers.db.ActivityDAO;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;


public class SaveAllActivitiesIntoCacheManagerDAOImpl implements SaveAllActivitiesIntoCacheManager {

    private WeakReference<Context> contextWeakReference;

    public SaveAllActivitiesIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Activities activities, Runnable completion) {

        ActivityDAO dao = new ActivityDAO(contextWeakReference.get());
        for (Activity activity : activities.allActivities()) {
            dao.insert(activity);
        }
        completion.run();

    }
}
