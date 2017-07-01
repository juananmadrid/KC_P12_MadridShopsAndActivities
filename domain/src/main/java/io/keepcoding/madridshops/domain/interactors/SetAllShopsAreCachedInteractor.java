package io.keepcoding.madridshops.domain.interactors;

public interface SetAllShopsAreCachedInteractor {
    public static final String SHOPS_SAVED = "SHOPS_SAVED";

    void execute(boolean shopsSaved);
}
