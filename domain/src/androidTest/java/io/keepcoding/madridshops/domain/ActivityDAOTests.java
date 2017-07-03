package io.keepcoding.madridshops.domain;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import io.keepcoding.madridshops.domain.managers.db.ActivityDAO;
import io.keepcoding.madridshops.domain.managers.db.ShopDAO;
import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ActivityDAOTests {

    final static Context appContext = InstrumentationRegistry.getTargetContext();

    // Tes del método "insert()"
    @Test
    public void given_activity_DAO_inserts_activity() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);
        Activity activity = Activity.of(1, "Activity1").
                setAdress("address0").
                setLatitud(5).
                setLongitud(10);
        long id = sut.insert(activity);
        assertTrue(id > 0);
        // assertEquals(1, id);
    }

    // Tes del método "query()"
    @Test
    public void given_inserted_activities_DAO_query_all_activities() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);
        insertActivity(sut, 1, "Activity1", "address", 20, 30);

        List<Activity> activities = sut.query();

        assertNotNull(activities);
        assertTrue(activities.size() >= 1);
    }

    // Tes del método "deleteAll()"
    @Test
    public void given_inserted_activities_delete_all_returns_empty_table() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);

        insertActivities();

        sut.deleteAll();

        List<Activity> query = sut.query();

        assertNull(query);

    }

    // Tes del método "delete(long id)"
    @Test
    public void given_one_inserted_activities_I_can_delete_that_activity_by_Id() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);

        sut.deleteAll();
        Activity activity = insertActivity(sut, 1, "Activity1", "address", 20, 30);
        Activity activityQuery = sut.query(activity.getId());
        sut.delete(activityQuery.getId());

        List<Activity> query = sut.query();

        assertNull(query);
    }

    // Tes del método delete(Activity element)
    @Test
    public void given_one_inserted_activity_I_can_delete_that_activity_by_activity() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);

        sut.deleteAll();
        Activity activity = insertActivity(sut, 1, "Activity1", "address", 20, 30);
        Activity activityQuery = sut.query(activity.getId());
        sut.delete(activityQuery);

        List<Activity> query = sut.query();

        assertNull(query);
    }

    // Tes del método update()
    @Test
    public void given_one_inserted_activity_I_can_update_that_activity() throws Exception {

        ActivityDAO sut = new ActivityDAO(appContext);

        sut.deleteAll();
        Activity activity = insertActivity(sut, 1, "Activity1", "address", 20, 30);

        Activity newActivity = Activity.of(2, "NewActivity").setAdress("newAddress");
        sut.update(activity.getId(), newActivity);

        Activity query = sut.query(activity.getId());

        assertEquals(query.getName(), newActivity.getName());
        assertNotEquals(query.getName(), activity.getName());

    }


    private Activity insertActivity(ActivityDAO sut, long id, String name, String address, float latitude, float longitude) {
        Activity activity = Activity.of(id, name).
                setAdress(address).
                setLatitud(latitude).
                setLongitud(latitude);

        long insertedId = sut.insert(activity);
        return activity;
    }

    private void insertActivities() {
        // Context appContext = InstrumentationRegistry.getTargetContext();
        ActivityDAO sut = new ActivityDAO(appContext);
        for (int i = 0; i < 20; i++) {
            insertActivity(sut, i, "Activity" + i, "Address " + i, i + 1, i);
        }
    }

}
