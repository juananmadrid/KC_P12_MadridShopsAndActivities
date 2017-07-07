package io.keepcoding.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Activities;


public interface GetAllActivitiesFromCacheManagerCompletion {
    void completion(@NonNull final Activities activities);
}
