package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.managers.cache.GetAllActivitiesFromCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.GetAllActivitiesFromCacheManagerCompletion;
import io.keepcoding.madridshops.domain.model.Activities;

public class GetAllActivitiesFromCacheInteractorImpl implements GetAllActivitiesFromCacheInteractor {

    private GetAllActivitiesFromCacheManager cacheManager;

    public GetAllActivitiesFromCacheInteractorImpl(final GetAllActivitiesFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion) {
        cacheManager.execute(new GetAllActivitiesFromCacheManagerCompletion() {
            @Override
            public void completion(@NonNull Activities activities) {
                completion.completion(activities);
            }
        });
    }

}
