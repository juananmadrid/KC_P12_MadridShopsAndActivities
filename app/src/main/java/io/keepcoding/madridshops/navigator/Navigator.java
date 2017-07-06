package io.keepcoding.madridshops.navigator;

import android.content.Intent;
import android.support.annotation.NonNull;

import io.keepcoding.madridshops.activities.ActivitiesDetailActivity;
import io.keepcoding.madridshops.activities.ActivitiesListActivity;
import io.keepcoding.madridshops.activities.MainActivity;
import io.keepcoding.madridshops.activities.ShopDetailActivity;
import io.keepcoding.madridshops.activities.ShopListActivity;
import io.keepcoding.madridshops.domain.model.Activity;
import io.keepcoding.madridshops.domain.model.Shop;

import static io.keepcoding.madridshops.util.Constants.INTENT_ACTIVITY_DETAIL;
import static io.keepcoding.madridshops.util.Constants.INTENT_SHOP_DETAIL;

public class Navigator {
    public static Intent navigateFromMainActivityToShopListActivity(@NonNull final MainActivity mainActivity) {
        assert(mainActivity != null);

        final Intent intent = new Intent(mainActivity, ShopListActivity.class);
        mainActivity.startActivity(intent);

        return intent;
    }


    public static Intent navigateFromMainActivityToActivitiesListActivity(@NonNull final MainActivity mainActivity){
        assert(mainActivity != null);

        final Intent intent = new Intent(mainActivity, ActivitiesListActivity.class);
        mainActivity.startActivity(intent);

        return intent;
    }


    public static Intent navigateFromShopListActivityToShopDetailActivity(@NonNull final ShopListActivity shopListActivity, final Shop shop, final int position) {
        final Intent intent = new Intent(shopListActivity, ShopDetailActivity.class);
        intent.putExtra(INTENT_SHOP_DETAIL, shop);

        shopListActivity.startActivity(intent);

        return intent;
    }


    public static Intent navigateFromActivitiesListActivityToActivityDetailActivity(@NonNull final ActivitiesListActivity activitiesListActivity, final Activity activity, final int position) {
        final Intent intent = new Intent(activitiesListActivity, ActivitiesDetailActivity.class);
        intent.putExtra(INTENT_ACTIVITY_DETAIL, activity);

        activitiesListActivity.startActivity(intent);

        return intent;
    }


}
