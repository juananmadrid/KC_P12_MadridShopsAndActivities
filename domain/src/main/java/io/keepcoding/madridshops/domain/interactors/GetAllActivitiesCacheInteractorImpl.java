package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetAllActivitiesCacheInteractorImpl implements GetAllActivitiesCacheInteractor {

    private WeakReference<Context> context;

    public GetAllActivitiesCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }
    @Override
    public void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        boolean activitiesSaved = preferences.getBoolean(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED, false);

        // deleteCacheIfOneWeekHasPassed();

        if (activitiesSaved) {
            onAllActivitiesAreCached.run();
        } else {
            onAllActivitiesAreNotCached.run();
        }
    }



    // Si fecha > 1 semana borrar cache y ejecutar onAllActivitiesAreNotCached
    private void deleteCacheIfOneWeekHasPassed() {
        final long MILLSECS_PER_WEEK = 7 * 24 * 60 * 60 * 1000; //Milisegundos al día
        Date toDay = new Date(); //Fecha de hoy

        long difference = (toDay.getTime() - toDay.getTime());  // TODO: Cambiar toDay por LastDay extraido de SharedPreferences

        if (difference >= MILLSECS_PER_WEEK) {
            // Borrar cache






        } else {
            return;
        }
    }

}
