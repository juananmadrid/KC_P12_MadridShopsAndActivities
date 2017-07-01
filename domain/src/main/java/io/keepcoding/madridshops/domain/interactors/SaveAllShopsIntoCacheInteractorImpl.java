package io.keepcoding.madridshops.domain.interactors;

import io.keepcoding.madridshops.domain.managers.cache.SaveAllShopsIntoCacheManager;
import io.keepcoding.madridshops.domain.model.Shops;

public class SaveAllShopsIntoCacheInteractorImpl implements SaveAllShopsIntoCacheInteractor {

    private SaveAllShopsIntoCacheManager manager;

    public SaveAllShopsIntoCacheInteractorImpl(SaveAllShopsIntoCacheManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        manager.execute(shops, completion);
    }
}
