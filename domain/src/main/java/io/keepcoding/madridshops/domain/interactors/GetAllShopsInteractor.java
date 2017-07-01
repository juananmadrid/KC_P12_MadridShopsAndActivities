package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface GetAllShopsInteractor {
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion,
                        @Nullable final InteractorErrorCompletion onError);
}
