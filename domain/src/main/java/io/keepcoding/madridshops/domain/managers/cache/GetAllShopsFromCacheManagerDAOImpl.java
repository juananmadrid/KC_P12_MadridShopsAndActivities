package io.keepcoding.madridshops.domain.managers.cache;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.List;

import io.keepcoding.madridshops.domain.managers.db.ShopDAO;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;

public class GetAllShopsFromCacheManagerDAOImpl implements GetAllShopsFromCacheManager {

    private WeakReference<Context> contextWeakReference;

    public GetAllShopsFromCacheManagerDAOImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void execute(@NonNull final GetAllShopsFromCacheManagerCompletion completion) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(contextWeakReference.get());
                List<Shop> shopList = dao.query();
                if (shopList == null) {
                    return;
                }
                final Shops shops = Shops.from(shopList);

                Handler uiHandler = new Handler(Looper.getMainLooper());
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });
            }
        }).start();
    }
}
