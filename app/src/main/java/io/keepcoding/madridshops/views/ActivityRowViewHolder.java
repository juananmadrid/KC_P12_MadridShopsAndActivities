package io.keepcoding.madridshops.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;

import io.keepcoding.madridshops.R;
import io.keepcoding.madridshops.domain.model.Activity;


public class ActivityRowViewHolder extends RecyclerView.ViewHolder {

    private TextView activityNameTextView;
    private ImageView activityLogoImageView;
    WeakReference<Context> context;


    public ActivityRowViewHolder(View rowActivity) {
        super(rowActivity);

        this.context = new WeakReference<>(rowActivity.getContext());

        activityNameTextView = (TextView) rowActivity.findViewById(R.id.row_activity__activity_name);
        activityLogoImageView = (ImageView) rowActivity.findViewById(R.id.row_activity__activity_logo);
    }


    public void setActivity(Activity activity) {
        if (activity == null) {
            return;
        }

        activityNameTextView.setText(activity.getName());
        Picasso.with(context.get()).
                load(activity.getLogoUrl()).
                placeholder(R.drawable.activity_placeholder).
                // networkPolicy(NetworkPolicy.OFFLINE).
                into(activityLogoImageView);
    }


}
