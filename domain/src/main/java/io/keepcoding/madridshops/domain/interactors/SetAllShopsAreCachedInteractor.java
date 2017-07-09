package io.keepcoding.madridshops.domain.interactors;

public interface SetAllShopsAreCachedInteractor {

    public static final String SHOPS_SAVED = "SHOPS_SAVED";
    public static final String SHOPS_SAVED_DATE = "SHOPS_SAVED_DATE";

    void execute(boolean shopsSaved);
}
