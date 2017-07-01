package io.keepcoding.madridshops.domain.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Activity implements Serializable {

    public long id;
    public String name;
    public String imgUrl;
    public String logoUrl;
    public String adress;
    public String url;
    public String description;
    public float latitud;
    public float longitud;

    public static Activity of(long id, String name) {
        Activity activity = new Activity();

        activity.setId(id);
        activity.setName(name);
        return activity;
    }


    public long getId() {
        return id;
    }

    public Activity setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(@NonNull final String name) {
        this.name = name;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Activity setImgUrl(@NonNull final String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Activity setLogoUrl(@NonNull final String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getAdress() {
        return adress;
    }

    public Activity setAdress(@NonNull final String adress) {
        this.adress = adress;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Activity setUrl(@NonNull final String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(@NonNull final String description) {
        this.description = description;
        return this;
    }

    public float getLatitud() {
        return latitud;
    }

    public Activity setLatitud(@NonNull final float latitud) {
        this.latitud = latitud;
        return this;
    }

    public float getLongitud() {
        return longitud;
    }

    public Activity setLongitud(@NonNull final float longitud) {
        this.longitud = longitud;
        return this;
    }
}
