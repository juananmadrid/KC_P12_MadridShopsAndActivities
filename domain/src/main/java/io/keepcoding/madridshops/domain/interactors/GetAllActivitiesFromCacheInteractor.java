package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

public interface GetAllActivitiesFromCacheInteractor {

    void execute(@NonNull final GetAllActivitiesInteractorCompletion completion);
}
