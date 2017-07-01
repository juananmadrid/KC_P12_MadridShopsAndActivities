package io.keepcoding.madridshops.domain.managers.network.jsonparser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import io.keepcoding.madridshops.domain.managers.network.entities.ShopEntity;
import io.keepcoding.madridshops.domain.managers.network.entities.ShopsResponseEntity;

public class ShopsJsonParser {
    public List<ShopEntity> parse(@NonNull final String response) {
        if (response == null) {
            return null;
        }

        List<ShopEntity> shopEntities = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = new StringReader(response);
            ShopsResponseEntity shopsResponseEntity = gson.fromJson(reader, ShopsResponseEntity.class);
            shopEntities = shopsResponseEntity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shopEntities;
    }
}
