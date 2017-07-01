package io.keepcoding.madridshops.domain.managers.cache;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Shops;

public interface GetAllShopsFromCacheManagerCompletion {
    void completion(@NonNull final Shops shops);
}
