package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManager;
import io.keepcoding.madridshops.domain.managers.cache.GetAllShopsFromCacheManagerCompletion;
import io.keepcoding.madridshops.domain.model.Shops;

public class GetAllShopsFromCacheInteractorImpl implements GetAllShopsFromCacheInteractor {

    private GetAllShopsFromCacheManager cacheManager;

    public GetAllShopsFromCacheInteractorImpl(final GetAllShopsFromCacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public void execute(@NonNull final GetAllShopsInteractorCompletion completion) {
        cacheManager.execute(new GetAllShopsFromCacheManagerCompletion() {
            @Override
            public void completion(@NonNull Shops shops) {
                completion.completion(shops);
            }
        });
    }
}
