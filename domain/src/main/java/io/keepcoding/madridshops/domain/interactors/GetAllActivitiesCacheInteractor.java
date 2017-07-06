package io.keepcoding.madridshops.domain.interactors;

public interface GetAllActivitiesCacheInteractor {

    void execute(Runnable onAllShopsAreCached, Runnable onAllShopsAreNotCache);
}
