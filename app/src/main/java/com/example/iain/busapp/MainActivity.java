package com.example.iain.busapp;


import android.app.ActionBar;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.iain.busapp.fragment.BusFragment;
import com.example.iain.busapp.fragment.HomeFragment;
import com.example.iain.busapp.fragment.MapFragment;
import com.example.iain.busapp.fragment.NavigationDrawerFragment;
import com.example.iain.busapp.fragment.SettingsFragment;
import com.example.iain.busapp.fragment.TicketFragment;
import com.example.iain.busapp.fragment.SearchFragment;
import com.example.iain.busapp.fragment.UpdatesFragment;

import java.util.Arrays;
import java.util.List;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
                   HomeFragment.OnBusSelectedListener,
                   TicketFragment.OnBusSelectedListener,
                  UpdatesFragment.OnBusSelectedListener,
                  SearchFragment.OnBusSelectedListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static final String PREFS_NAME = "favs";

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.clear();
//        editor.commit();


        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        switch (position){
            case 0:
                fragment = HomeFragment.newInstance(position + 1);
                break;
            case 1:
                fragment = UpdatesFragment.newInstance(position + 1);
                break;
            case 2:
                fragment = TicketFragment.newInstance(position + 1);
                break;
            case 3:
                fragment = MapFragment.newInstance(position + 1);
                break;
            case 4:
                fragment = SettingsFragment.newInstance(position + 1);
                break;
        }

        if(fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void onBusSelected(String title) {
        Resources res = getResources();
        String[] testTitles = res.getStringArray(R.array.testRoute);

        List<String> listTitles = Arrays.asList(testTitles);
        int position = listTitles.indexOf(title);
        Fragment newFragment = BusFragment.newInstance(6);
        Bundle args = new Bundle();
        args.putInt("busID", position);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void onBusSelected(int position) {
        Fragment newFragment = BusFragment.newInstance(6);
        Bundle args = new Bundle();
        args.putInt("busID", position);
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.search:

                Fragment newFragment = SearchFragment.newInstance(6);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();

                return true;
            default:
                return false;
        }
    }


    public void onFavourited(View view){
    }
}
