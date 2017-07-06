package io.keepcoding.madridshops.util;

import android.support.annotation.NonNull;

import io.keepcoding.madridshops.domain.model.Shop;

public class StaticMapImage {

    public static String getMapImageUrl(@NonNull final float latitude, @NonNull final float longitude) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=320x120",
                latitude, longitude);

        return url;
    }
}
