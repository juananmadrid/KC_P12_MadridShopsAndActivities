package io.keepcoding.madridshops.domain.managers.network.mappers;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ShopEntity;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;

import static io.keepcoding.madridshops.domain.managers.network.mappers.ShopEntityIntoShopsMapper.getCorrectCoordinateComponent;

public class ShopEntityIntoActivitiesMapper {

    /**
     * @param shopEntities
     * @return null is shopEntities is null or shopEntities is empty else a Activities aggregate
     */

    public static Activities map(final List<ShopEntity> shopEntities) {

        Activities activities = new Activities();

        for (ShopEntity shopEntity : shopEntities) {

            Activity activity = Activity.of(shopEntity.getId(), shopEntity.getName());

            activity.setDescription(shopEntity.getDescription_es());
            activity.setLatitud(getCorrectCoordinateComponent(shopEntity.getGps_lat()));
            activity.setLongitud(getCorrectCoordinateComponent(shopEntity.getGps_lon()));
            activity.setAdress(shopEntity.getAddress());
            activity.setUrl(shopEntity.getUrl());
            activity.setImgUrl(shopEntity.getImg());
            activity.setLogoUrl(shopEntity.getLogo_img());

            activities.add(activity);
        }

        return activities;
    }

}
