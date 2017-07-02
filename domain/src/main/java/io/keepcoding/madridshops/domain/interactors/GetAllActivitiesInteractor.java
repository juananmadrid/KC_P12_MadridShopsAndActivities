package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface GetAllActivitiesInteractor {

    public void execute(@NonNull final GetAllActivitiesInteractorCompletion completion, @Nullable final InteractorErrorCompletion onError);
}
