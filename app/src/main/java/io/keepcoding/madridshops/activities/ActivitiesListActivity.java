package io.keepcoding.madridshops.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesCacheInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesCacheInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractor;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorCompletion;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorFakeImpl;
import io.keepcoding.madridshops.domain.interactors.GetAllActivitiesInteractorImpl;
import io.keepcoding.madridshops.domain.interactors.InteractorErrorCompletion;
import io.keepcoding.madridshops.domain.interactors.SetAllActivitiesAreCachedInteractor;
import io.keepcoding.madridshops.domain.interactors.SetAllActivitiesAreCachedInteractorImpl;
import io.keepcoding.madridshops.domain.managers.network.GetAllShopsManagerImpl;
import io.keepcoding.madridshops.domain.managers.network.NetworkManager;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;
import io.keepcoding.madridshops.fragments.ActivitiesFragment;
import io.keepcoding.madridshops.navigator.Navigator;
import io.keepcoding.madridshops.views.OnElementClick;

public class ActivitiesListActivity extends AppCompatActivity {

    @BindView(R.id.activity_activities_list__progress_bar) ProgressBar progressBar;

    ActivitiesFragment activityFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_list);

        ButterKnife.bind(this);

        activityFragment = (ActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.activity_activities__fragment_activities);

        GetAllActivitiesCacheInteractor getAllActivitiesCacheInteractor = new GetAllActivitiesCacheInteractorImpl(this);
        getAllActivitiesCacheInteractor.execute(new Runnable() {
            @Override
            public void run() {
                // cached ok, no need to download
                // TODO: Leer de Caché (DB)
            }
        }, new Runnable() {
            @Override
            public void run() {
                // no cached, download is necessary
                obtainActivitiesList();
            }
        });



    }


    private void obtainActivitiesList() {

        progressBar.setVisibility(View.VISIBLE);

        NetworkManager manager = new GetAllShopsManagerImpl(this);
        GetAllActivitiesInteractor getAllActivitiesInteractor = new GetAllActivitiesInteractorImpl(manager);
        getAllActivitiesInteractor.execute(
                new GetAllActivitiesInteractorCompletion() {
                    @Override
                    public void completion(Activities activities) {
                        System.out.println("Leemos Activities");

                        progressBar.setVisibility(View.INVISIBLE);

                        // TODO: PERSIST IN CACHE ALL ACTIVITIES

                        SetAllActivitiesAreCachedInteractor setAllActivitiesAreCachedInteractor = new SetAllActivitiesAreCachedInteractorImpl(ActivitiesListActivity.this);
                        setAllActivitiesAreCachedInteractor.execute(true);

                        activityFragment.setActivities(activities);
                        activityFragment.setOnElementClickListener(new OnElementClick<Activity>() {
                            @Override
                            public void clickedOn(@NonNull Activity activity, int position) {
                                Navigator.navigateFromActivitiesListActivityToActivityDetailActivity(ActivitiesListActivity.this, activity, position);
                            }
                        });
                    }
                },
                new InteractorErrorCompletion() {
                    @Override
                    public void onError(String errorDescription) {
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
        );
    }


}
