package io.keepcoding.madridshops.domain.managers.network;

import android.support.annotation.NonNull;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ShopEntity;

public interface GetAllShopsManagerCompletion {
    void completion(@NonNull final List<ShopEntity> shopEntities);
}
