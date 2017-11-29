package com.example.androiddevelopment.tourguide.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.androiddevelopment.tourguide.db.model.Sight;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class ORMLightHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME    = "Sights.db";
    private static final int    DATABASE_VERSION = 1;

    private Dao<Sight, Integer> mSightDao = null;

    public ORMLightHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Sight.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Sight.class, true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Dao<Sight, Integer> getSightDao() throws SQLException {
        if (mSightDao == null) {
            mSightDao = getDao(Sight.class);
        }
        return mSightDao;
    }

    //Close the Base after work is done to free resources
    @Override
    public void close() {
        mSightDao = null;

        super.close();
    }
}
