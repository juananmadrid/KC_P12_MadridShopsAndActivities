package io.keepcoding.madridshops.domain.interactors;

import io.keepcoding.madridshops.domain.managers.cache.SaveAllActivitiesIntoCacheManager;
import io.keepcoding.madridshops.domain.model.Activities;

public class SaveAllActivitiesIntoCacheInteractorImpl implements SaveAllActivitiesIntoCacheInteractor {

    private SaveAllActivitiesIntoCacheManager manager;

    public SaveAllActivitiesIntoCacheInteractorImpl(SaveAllActivitiesIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Activities activities, Runnable completion) {
        manager.execute(activities, completion);
    }
}
