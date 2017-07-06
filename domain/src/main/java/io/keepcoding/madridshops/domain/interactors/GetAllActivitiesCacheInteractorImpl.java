package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class GetAllActivitiesCacheInteractorImpl implements GetAllActivitiesCacheInteractor {

    private WeakReference<Context> context;

    public GetAllActivitiesCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }
    @Override
    public void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean activitiresSaved = preferences.getBoolean(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED, false);

        if (activitiresSaved) {
            onAllActivitiesAreCached.run();
        } else {
            onAllActivitiesAreNotCached.run();
        }
    }

}
