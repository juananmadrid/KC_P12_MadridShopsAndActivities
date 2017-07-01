package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;

public class GetAllShopsInteractorFakeImpl implements GetAllShopsInteractor {

    @Override
    public void execute(@NonNull GetAllShopsInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Shops shops = new Shops();

        for (int i = 0; i < 10; i++) {
            Shop shop = Shop.of(i, "My shop " + i).
                    setLogoUrl("http://icons.iconarchive.com/icons/custom-icon-design/pretty-office-11/512/shop-icon.png");
            shops.add(shop);
        }

        if (completion != null) {
            completion.completion(shops);
        }
    }
}
