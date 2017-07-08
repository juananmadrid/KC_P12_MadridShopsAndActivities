package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.keepcoding.madridshops.domain.managers.db.ActivityDAO;
import io.keepcoding.madridshops.domain.managers.db.ShopDAO;

public class ClearCacheManagerDAOImpl implements ClearCacheManager {
    private WeakReference<Context> contextWeakReference;

    public ClearCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }


    @Override
    public void execute(Runnable completion) {

        // Delete shops
        ShopDAO shopDao = new ShopDAO(contextWeakReference.get());
        shopDao.deleteAll();

        // Delete Activities
        ActivityDAO activityDao = new ActivityDAO(contextWeakReference.get());
        activityDao.deleteAll();

        completion.run();

    }
}
