package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;

import java.lang.ref.WeakReference;

import io.keepcoding.madridshops.domain.managers.db.ShopDAO;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;

public class SaveAllShopsIntoCacheManagerDAOImpl implements SaveAllShopsIntoCacheManager {

    private WeakReference<Context> contextWeakReference;

    public SaveAllShopsIntoCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(Shops shops, Runnable completion) {
        ShopDAO dao = new ShopDAO(contextWeakReference.get());
        for (Shop shop : shops.allShops()) {
            dao.insert(shop);
        }

        completion.run();
    }
}
