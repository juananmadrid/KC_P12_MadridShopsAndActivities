package io.keepcoding.madridshops.domain.interactors;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class SetAllActivitiesAreCachedInteractorImpl implements SetAllActivitiesAreCachedInteractor {

    private WeakReference<Context> context;

    public SetAllActivitiesAreCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }


    @Override
    public void execute(boolean activitiesSaved) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(SetAllActivitiesAreCachedInteractorImpl.ACTIVITIES_SAVED, activitiesSaved);

        editor.commit();
    }
}
