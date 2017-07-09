package io.keepcoding.madridshops.domain.interactors;

public interface SetAllActivitiesAreCachedInteractor {

    public static final String ACTIVITIES_SAVED = "ACTIVITIES_SAVED";
    public static final String ACTIVITIES_SAVED_DATE = "ACTIVITIES_SAVED_DATE";

    void execute(boolean activitiesSaved);

}
