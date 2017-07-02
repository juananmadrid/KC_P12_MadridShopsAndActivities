package io.keepcoding.madridshops.domain.model;

import java.util.List;

public interface ActivitiesIterable {

    long size();
    Activity get(long index);
    List<Activity> allActivities();

}
