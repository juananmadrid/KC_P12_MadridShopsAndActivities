package io.keepcoding.madridshops.domain.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Shop implements Serializable {
    private long id;
    private String name;
    private String imageUrl;
    private String logoUrl;
    private String address;
    private String url;
    private String description;
    private float latitude;
    private float longitude;

    public static Shop of(long id, String name) {
        Shop shop = new Shop();

        shop.setId(id);
        shop.setName(name);
        return shop;
    }

    private Shop() {}

    public long getId() {
        return id;
    }

    public Shop setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Shop setName(@NonNull final String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Shop setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public Shop setLogoUrl(final String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shop setAddress(final String address) {
        this.address = address;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Shop setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Shop setDescription(final String description) {
        this.description = description;
        return this;
    }

    public float getLatitude() {
        return latitude;
    }

    public Shop setLatitude(final float latitude) {
        this.latitude = latitude;
        return this;
    }

    public float getLongitude() {
        return longitude;
    }

    public Shop setLongitude(final float longitude) {
        this.longitude = longitude;
        return this;
    }
}
