package io.keepcoding.madridshops.domain.interactors;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.keepcoding.madridshops.domain.managers.cache.ClearCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.ClearCacheManagerDAOImpl;

public class GetAllActivitiesCacheInteractorImpl implements GetAllActivitiesCacheInteractor {

    private WeakReference<Context> context;

    public GetAllActivitiesCacheInteractorImpl(Context context) {
        this.context = new WeakReference<Context>(context);
    }
    @Override
    public void execute(Runnable onAllActivitiesAreCached, Runnable onAllActivitiesAreNotCached) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.get());
        Boolean activitiesSaved = preferences.getBoolean(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED, false);
        String lastDateString = preferences.getString(SetAllActivitiesAreCachedInteractor.ACTIVITIES_SAVED_DATE, null);

        deleteCacheIfOneWeekHasPassed(lastDateString);

        if (activitiesSaved) {
            onAllActivitiesAreCached.run();
        } else {
            onAllActivitiesAreNotCached.run();
        }
    }


    private void deleteCacheIfOneWeekHasPassed(String lastDateString) {

        final long MILLSECS_PER_WEEK = 7 * 24 * 60 * 60 * 1000; // Milisegundos de una semana
        final Date toDay = new Date(); // Fecha de hoy
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date lastDate = (Date) formatter.parse(lastDateString);
            long difference = (toDay.getTime() - lastDate.getTime());

            if (difference > MILLSECS_PER_WEEK) {
                ClearCacheManager clearCacheManager = new ClearCacheManagerDAOImpl(context.get());
                ClearCacheInteractor clearCacheInteractor = new ClearCacheInteractorImpl(clearCacheManager);
                clearCacheInteractor.execute(new Runnable() {
                    @Override
                    public void run() {
                        Log.v("ACTIVITY", "Data Base delete after a week");
                        SetAllShopsAreCachedInteractor setAllShopsAreCachedInteractor = new SetAllShopsAreCachedInteractorImpl(context.get());
                        setAllShopsAreCachedInteractor.execute(false);
                    }
                });
            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
