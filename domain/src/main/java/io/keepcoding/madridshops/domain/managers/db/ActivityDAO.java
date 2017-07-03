package io.keepcoding.madridshops.domain.managers.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import io.keepcoding.madridshops.domain.model.Activity;

import static io.keepcoding.madridshops.domain.managers.db.DBConstants.*;

public class ActivityDAO implements DAOReadable<Activity>, DAOWritable<Activity>{


    private static final long EMPTY_ACTIVITY = 0;

    private SQLiteDatabase dbReadConnection;
    private SQLiteDatabase dbWriteConnection;

    public ActivityDAO(DBHelper dbHelper) {
        dbReadConnection = dbHelper.getReadableDatabase();
        dbWriteConnection = dbHelper.getWritableDatabase();
    }

    public ActivityDAO(Context context) {
        this(new DBHelper(context));
    }


    @Override
    public @Nullable List<Activity> query(String where, String[] whereArgs, String orderBy) {

        Cursor c = dbReadConnection.query(TABLE_ACTIVITY, // table name
                ALL_COLUMNS,        // columns I want to obtain
                where,              // where
                whereArgs,          // where args
                orderBy,            // order by
                null,               // group
                null);              // having

        if (c == null || c.getCount() == 0) {
            return null;
        }

        List<Activity> shopList = new ArrayList<>();

        // Convierto objetos de la tabla en objetos de memoria
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(KEY_SHOP_ID));
            String name = c.getString(c.getColumnIndex(KEY_SHOP_NAME));
            String address = c.getString(c.getColumnIndex(KEY_SHOP_ADDRESS));
            String description = c.getString(c.getColumnIndex(KEY_SHOP_DESCRIPTION));
            String imageUrl = c.getString(c.getColumnIndex(KEY_SHOP_IMAGE_URL));
            String logoImageUrl = c.getString(c.getColumnIndex(KEY_SHOP_LOGO_IMAGE_URL));
            String url = c.getString(c.getColumnIndex(KEY_SHOP_URL));
            float latitude = c.getFloat(c.getColumnIndex(KEY_SHOP_LATITUDE));
            float longitude = c.getFloat(c.getColumnIndex(KEY_SHOP_LONGITUDE));

            Activity activity = Activity.of(id, name).setAdress(address).
                    setDescription(description).setImgUrl(imageUrl).setLogoUrl(logoImageUrl).
                    setLatitud(latitude).setLongitud(longitude).setUrl(url);
            shopList.add(activity);
        }

        return shopList;
    }

    @Override
    public @Nullable Activity query(long id) {
        String idAsString = String.format("%d", id);
        List<Activity> activity = query(KEY_SHOP_ID + " = ?", new String[]{ idAsString }, KEY_SHOP_ID);

        if (activity == null || activity.size() == 0) {
            return null;
        }
        return activity.get(0);
    }

    @Override
    public List<Activity> query() {
        return query(null, null, KEY_SHOP_ID);
    }

    @Override
    public long insert(@NonNull Activity element) {
        if (element == null) {
            return EMPTY_ACTIVITY;
        }

        dbWriteConnection.beginTransaction();
        long id = DBHelper.INVALID_ID;

        try {
            id = dbWriteConnection.insert(DBConstants.TABLE_ACTIVITY, null, getContentValues(element));
            element.setId(id);

            dbWriteConnection.setTransactionSuccessful();

        } finally {
            dbWriteConnection.endTransaction();     // Graba en DB
        }

        return id;
    }

    // mapeamos con pares clave+valor que usaremos para meter datos de memoria en la tabla
    private ContentValues getContentValues(Activity activity) {

        final ContentValues contentValues = new ContentValues();

        if (activity == null) {
            return contentValues;
        }

        if (activity.getName() == null) {
            contentValues.put(KEY_SHOP_NAME, "");
        } else {
            contentValues.put(KEY_SHOP_NAME, activity.getName());
        }

        contentValues.put(KEY_SHOP_ADDRESS, activity.getAdress());
        contentValues.put(KEY_SHOP_DESCRIPTION, activity.getDescription());
        contentValues.put(KEY_SHOP_IMAGE_URL, activity.getImgUrl());
        contentValues.put(KEY_SHOP_LOGO_IMAGE_URL, activity.getLogoUrl());
        contentValues.put(KEY_SHOP_URL, activity.getUrl());
        contentValues.put(KEY_SHOP_LATITUDE, activity.getLatitud());
        contentValues.put(KEY_SHOP_LONGITUDE, activity.getLongitud());

        return contentValues;

    }

    @Override
    public long update(long id, Activity element) {

        int updatedRegs = 0;
        dbWriteConnection.beginTransaction();

        try {
            updatedRegs = dbWriteConnection.update(DBConstants.TABLE_ACTIVITY, getContentValues(element), KEY_SHOP_ID + "=" + id, null);
            dbWriteConnection.setTransactionSuccessful();

        } finally {
            dbWriteConnection.endTransaction();     // Graba en DB
        }

        return updatedRegs;
    }

    @Override
    public long delete(long id) {
        return delete(KEY_SHOP_ID + " = ?", "" + id);
    }

    @Override
    public long delete(Activity element) {
        return delete(element.getId());
    }

    @Override
    public void deleteAll() {
        delete(null, null);
    }

    @Override
    public long delete(String where, String... whereClause) {
        int deletedRegs = 0;
        dbWriteConnection.beginTransaction();
        try {
            deletedRegs = dbWriteConnection.delete(TABLE_ACTIVITY, where, whereClause);
            dbWriteConnection.setTransactionSuccessful();
        } finally {
            dbWriteConnection.endTransaction();
        }
        return deletedRegs;
    }

    @Override
    public int numRecords() {
        List<Activity> activityList = query();

        return activityList == null ? 0 : activityList.size();
    }

}
