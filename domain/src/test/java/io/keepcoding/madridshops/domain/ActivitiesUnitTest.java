package io.keepcoding.madridshops.domain;

import org.junit.Test;

import io.keepcoding.madridshops.domain.model.Activities;
import io.keepcoding.madridshops.domain.model.Activity;

import static org.junit.Assert.*;

public class ActivitiesUnitTest {

    // Test de inicialización
    @Test
    public void after_creation_activities_size_is_zero() throws Exception {
        Activities sut = new Activities();
        assertEquals(0, sut.size());
    }

    // Test de método add()
    @Test
    public void activities_adding_two_activity_size_is_two() throws Exception {
        Activities sut = new Activities();
        sut.add(Activity.of(1, "Activity1"));
        sut.add(Activity.of(2, "Activity2"));
        assertEquals(2, sut.size());
    }

    // Test de método delete()
    @Test
    public void activities_adding_one_activity_and_deleting_size_is_zero() throws Exception {
        Activities sut = new Activities();
        Activity activity = Activity.of(1, "Activity");
        sut.add(activity);
        sut.delete(activity);
        assertEquals(0, sut.size());
    }

    // Test de método get()
    @Test
    public void activities_adding_one_activity_and_getting_returns_that_activity() throws Exception {
        Activities sut = new Activities();

        Activity activity1 = Activity.of(1, "activity1");
        Activity activity2 = Activity.of(2, "activity2");

        sut.add(activity1);
        sut.add(activity2);

        Activity activityReturn1 = sut.get(0);
        Activity activityReturn2 = sut.get(1);

        assertEquals(activity1, activityReturn1);
        assertNotEquals(activity1, activityReturn2);
        // assertEquals(activity.getName(), activityReturned.getName());
        // assertEquals(activity.getId(), activityReturned.getId());
    }

    // Test de método allActivities()
    @Test
    public void activities_adding_several_activities_return_all_activites() throws Exception {
        Activities sut = new Activities();

        final int size = 15;

        for (int i = 0; i < size ; i++) {
            Activity activity = Activity.of(i, "Activity" + i);
            sut.add(activity);
        }

        assertEquals(size, sut.size());
        assertEquals(size, sut.allActivities().size());
        assertEquals(2, sut.allActivities().get(2).getId());
        assertEquals("Activity4", sut.allActivities().get(4).getName());
        assertNotEquals(1, sut.allActivities().get(2).getId());
    }

    // Test de método update()
    @Test
    public void activities_updating_one_activity_and_getting_returns_that_activity() throws Exception {
        Activities sut = new Activities();

        sut.add(Activity.of(1, "MyActivity"));
        Activity newActivity = Activity.of(1, "NewActivity");
        sut.update(newActivity, 0);
        assertEquals(newActivity, sut.allActivities().get(0));
    }

}
