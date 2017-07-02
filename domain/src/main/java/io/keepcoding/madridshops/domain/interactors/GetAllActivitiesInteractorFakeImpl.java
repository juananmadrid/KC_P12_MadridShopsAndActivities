package io.keepcoding.madridshops.domain.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;

public class GetAllActivitiesInteractorFakeImpl implements GetAllActivitiesInteractor {

    @Override
    public void execute(@NonNull GetAllActivitiesInteractorCompletion completion, @Nullable InteractorErrorCompletion onError) {
        Activities activities = new Activities();

        for (int i = 0; i < 20; i++) {
            Activity activity = Activity.of(i, "Activitity" + i);
            activities.add(activity);
        }

        if (completion != null) {
            completion.completion(activities);
        }

    }
}
