package io.keepcoding.madridshops.domain.interactors;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SetAllActivitiesAreCachedInteractorImpl implements SetAllActivitiesAreCachedInteractor {

    private WeakReference<Context> context;

    public SetAllActivitiesAreCachedInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }


    @Override
    public void execute(boolean activitiesSaved) {

        // Activamos flag de activities grabadas a true
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        SharedPreferences.Editor editor = preferences.edit();

        editor.putBoolean(SetAllActivitiesAreCachedInteractorImpl.ACTIVITIES_SAVED, activitiesSaved);


        // Guardamos fecha de caducidad de los datos
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date toDay = new Date(); // Fecha de hoy
        String toDayString = formatter.format(toDay);

        editor.putString(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED_DATE, toDayString);


        editor.commit();
    }
}
