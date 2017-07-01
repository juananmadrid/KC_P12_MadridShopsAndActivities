package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Shops;

public interface GetAllShopsInteractorCompletion {
    void completion(@NonNull final Shops shops);
}
