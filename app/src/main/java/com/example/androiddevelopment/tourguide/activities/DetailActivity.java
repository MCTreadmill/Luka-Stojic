package com.example.androiddevelopment.tourguide.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.androiddevelopment.tourguide.R;
import com.example.androiddevelopment.tourguide.db.ORMLightHelper;
import com.example.androiddevelopment.tourguide.db.model.Sight;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class DetailActivity extends AppCompatActivity {

    private ORMLightHelper databaseHelper;
    private Sight sight;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        int key = getIntent().getExtras().getInt(SightActivity.SIGHT_KEY);

        try {
            sight = getDatabaseHelper().getSightDao().queryForId(key);

            TextView name = (TextView) findViewById(R.id.tv_detail_name);
            TextView description = (TextView) findViewById(R.id.tv_detail_description);
            TextView address = (TextView) findViewById(R.id.tv_detail_address);
            TextView phone = (TextView) findViewById(R.id.tv_detail_phone);
            TextView website = (TextView) findViewById(R.id.tv_detail_website);
            TextView worktime = (TextView) findViewById(R.id.tv_detail_worktime);
            TextView price = (TextView) findViewById(R.id.tv_detail_price);
            TextView comment = (TextView) findViewById(R.id.tv_detail_comment);

            name.setText(sight.getmName());
            description.setText(sight.getmDescription());
            address.setText(sight.getmAdress());
            phone.setText(sight.getmPhone());
            website.setText(sight.getmWebsite());
            worktime.setText(sight.getmWorktime());
            price.setText(sight.getmPrice());
            comment.setText(sight.getmComment());


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Method which communicates with the database
    public ORMLightHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(DetailActivity.this, ORMLightHelper.class);
        }
        return databaseHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Free resources after done with database work
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    private void refresh() {
        ListView listView = (ListView) findViewById(R.id.lv_activity_sight);

        if (listView != null) {
            ArrayAdapter<Sight> adapter = (ArrayAdapter<Sight>) listView.getAdapter();

            if (adapter != null) {
                try {
                    adapter.clear();

                    List<Sight> sights = getDatabaseHelper().getSightDao().queryForAll();

                    adapter.addAll(sights);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
