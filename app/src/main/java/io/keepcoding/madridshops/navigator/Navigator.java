package io.keepcoding.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import io.keepcoding.madridshops.activities.MainActivity;
import io.keepcoding.madridshops.activities.ShopDetailActivity;
import io.keepcoding.madridshops.activities.ShopListActivity;
import io.keepcoding.madridshops.domain.model.Shop;

import static io.keepcoding.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent i = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(i);

        return i;
    }

    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent i = new Intent(shopListActivity, ShopDetailActivity.class);
        i.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(i);

        return i;
    }


}
