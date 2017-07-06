package io.keepcoding.madridshops.domain.interactors;

public interface SetAllActivitiesAreCachedInteractor {

    public static final String ACTIVITIES_SAVED = "ACTIVITIES_SAVED";

    void execute(boolean activitiesSaved);

}
