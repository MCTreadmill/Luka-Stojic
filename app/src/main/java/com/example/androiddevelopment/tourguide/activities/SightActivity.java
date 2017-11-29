package com.example.androiddevelopment.tourguide.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.androiddevelopment.tourguide.R;
import com.example.androiddevelopment.tourguide.adapters.DrawerAdapter;
import com.example.androiddevelopment.tourguide.db.ORMLightHelper;
import com.example.androiddevelopment.tourguide.db.model.Sight;
import com.example.androiddevelopment.tourguide.dialogs.AboutDialog;
import com.example.androiddevelopment.tourguide.model.NavigationItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by androiddevelopment on 29.11.17..
 */

public class SightActivity extends AppCompatActivity {

    // Attributes used by NavigationDrawer
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;
    private RelativeLayout drawerPane;
    private CharSequence drawerTitle;
    private ArrayList<NavigationItem> drawerItems = new ArrayList<>();

    private ORMLightHelper databaseHelper;

    private SharedPreferences prefs;

    public static String NOTIF_TOAST = "notif_toast";

    public static String SIGHT_KEY = "SIGHT_KEY";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sight);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // Manages NavigationDrawer

        // Populates a list of NavigationDrawer items
        drawerItems.add(new NavigationItem("Settings"));
        drawerItems.add(new NavigationItem("About"));

        drawerTitle = getTitle();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerList = (ListView) findViewById(R.id.navList);

        //Populates NavigtionDrawer with options
        drawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        DrawerAdapter adapter = new DrawerAdapter(this, drawerItems);

        drawerList.setAdapter(adapter);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent settings = new Intent(SightActivity.this, SettingsActivity.class);
                    startActivity(settings);
                }
                if (position == 1) {
                    AlertDialog alertDialog = new AboutDialog(SightActivity.this).prepareDialog();
                    alertDialog.show();
                }
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();    // Creates call to onPrepareOptionsMenu()
            }
        };

        final ListView listView = (ListView) findViewById(R.id.lv_activity_sight);

        try {
            List<Sight> sights = getDatabaseHelper().getSightDao().queryForAll();

            ListAdapter listAdapter = new ArrayAdapter<>(SightActivity.this, R.layout.list_item, sights);
            listView.setAdapter(listAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Sight sight = (Sight) listView.getItemAtPosition(position);

                    Intent intent = new Intent(SightActivity.this, DetailActivity.class);
                    intent.putExtra(SIGHT_KEY, sight.getmId());
                    startActivity(intent);
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_new_sight:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_add_sight);

                Button add = (Button) dialog.findViewById(R.id.btn_dialog_sight_add);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText name = (EditText) dialog.findViewById(R.id.et_dialog_sight_name);
                        EditText description = (EditText) dialog.findViewById(R.id.et_dialog_sight_description);

                        Sight sight = new Sight();
                        sight.setmName(name.getText().toString());
                        sight.setmDescription(description.getText().toString());

                        try {
                            getDatabaseHelper().getSightDao().create(sight);

                            //checking preferences
                            boolean toast = prefs.getBoolean(NOTIF_TOAST, false);
                            if (toast){
                                Toast.makeText(SightActivity.this, "Added new Sight", Toast.LENGTH_SHORT).show();
                            }

                            refresh();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sight_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Method which communicates with the database
    public ORMLightHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(SightActivity.this, ORMLightHelper.class);
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
