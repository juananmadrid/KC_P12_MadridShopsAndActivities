package io.keepcoding.madridshops.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Activity;
import io.keepcoding.madridshops.util.Constants;
import io.keepcoding.madridshops.util.StaticMapImage;

public class ActivitiesDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_activities_detail__activity_image) ImageView activityImage;
    @BindView(R.id.activity_activities_detail__activity_name) TextView activityName;
    @BindView(R.id.activity_activities_detail__activity_address) TextView activityAddress;
    @BindView(R.id.activity_activities_detail__activity_description) TextView activityDescription;
    @BindView(R.id.activitiy_activities_detail__map_image) ImageView activityMapImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent != null) {
            Activity activity = (Activity) intent.getSerializableExtra(Constants.INTENT_ACTIVITY_DETAIL);

            activityName.setText(activity.getName());
            activityAddress.setText(activity.getAdress());
            activityDescription.setText(activity.getDescription());
            Picasso.with(this).
                    load(activity.getImgUrl()).
                    placeholder(R.drawable.activity_placeholder).
                    into(activityImage);

            String staticMapUrl = StaticMapImage.getMapImageUrl(activity.getLatitud(), activity.getLongitud());
            Picasso.with(this).
                    load(staticMapUrl).
                    placeholder(R.drawable.map2_placeholder).
                    into(activityMapImage);
        }



    }
}
