package io.keepcoding.madridshops.domain.interactors;

import io.keepcoding.madridshops.domain.model.Activities;

public interface SaveAllActivitiesIntoCacheInteractor {
    void execute(Activities activities, Runnable completion);
}
