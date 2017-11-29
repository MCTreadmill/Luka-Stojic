package com.example.androiddevelopment.tourguide.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.androiddevelopment.tourguide.R;
import com.example.androiddevelopment.tourguide.adapters.DrawerAdapter;
import com.example.androiddevelopment.tourguide.db.ORMLightHelper;
import com.example.androiddevelopment.tourguide.model.NavigationItem;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.ArrayList;

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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sight);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        // Manages NavigationDrawer

        // Populates a list of NavigationDrawer items
        //drawerItems.add(new NavigationItem("Settings", "Adjust some Preferences", R.drawable.ic_settings));
        //drawerItems.add(new NavigationItem("About", "Learn about the author", R.drawable.ic_about));

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
                if (position == 0) {/*
                    Intent settings = new Intent(ActorActivity.this, PreferenceClass.class);
                    startActivity(settings);*/
                }
                if (position == 1) {/*
                    android.support.v7.app.AlertDialog alertDialog = new AboutDialog(ActorActivity.this).prepareDialog();
                    alertDialog.show();*/
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
}
