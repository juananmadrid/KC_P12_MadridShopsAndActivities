package io.keepcoding.madridshops.domain.managers.network.mappers;

import android.util.Log;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ShopEntity;
import io.keepcoding.madridshops.domain.model.Shop;
import io.keepcoding.madridshops.domain.model.Shops;

public class ShopEntityIntoShopsMapper {

    /**
     *
     * @param shopEntities
     * @return null is shopEntities is null or shopEntities is empty else a Shops aggregate
     */
    public static Shops map(final List<ShopEntity> shopEntities) {
        Shops shops = new Shops();

        for (ShopEntity shopEntity : shopEntities) {
            Shop shop = Shop.of(shopEntity.getId(), shopEntity.getName());

            shop.setDescription(shopEntity.getDescription_es());
            shop.setLatitude(getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            shop.setLongitude(getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            shop.setAddress(shopEntity.getAddress());
            shop.setUrl(shopEntity.getUrl());
            shop.setImageUrl(shopEntity.getImg());
            shop.setLogoUrl(shopEntity.getLogo_img());

            shops.add(shop);
        }

        return shops;
    }

    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // problem: JSON has this errors "-3.9018104,275"
        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");
        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }
        return coordinate;
    }
}