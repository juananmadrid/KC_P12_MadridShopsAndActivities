package io.keepcoding.madridshops.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.adapters.ActivitiesAdapter;
import io.keepcoding.madridshops.adapters.ShopsAdapter;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;
import io.keepcoding.madridshops.views.OnElementClick;


public class ActivitiesFragment extends Fragment {

    private OnElementClick<Activity> listener;

    private RecyclerView activitiesRecyclerView;
    private ActivitiesAdapter adapter;
    private Activities activities;

    // Constructor usado para instanciar fragment desde xml
    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);

        activitiesRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_activities__recycler_view);
        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Estrategia: la actividad obtiene los datos y los pasa a sus fragment (recycler y maps)

        // No puedo crear aquí el Adapter, pq aquí aún no se han creado activities.
        // Lo pongo en setActivities, justo cuando se crea activities y me lo pasa la actividad

        return view;
    }


    // Inyección de dependencias (properties)
    public void setActivities(final Activities activities) {
        this.activities = activities;

        adapter = new ActivitiesAdapter(activities, getActivity());
        activitiesRecyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new OnElementClick<Activity>() {
            @Override
            public void clickedOn(@NonNull Activity activity, int position) {
                Log.d("Click", activity.getName());
                if (listener != null) {
                    listener.clickedOn(activity, position);
                }
            }
        });

    }

    public void setOnElementClickListener (OnElementClick<Activity> listener) {
        this.listener = listener;
    }



}
