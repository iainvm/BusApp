package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;
import com.example.iain.busapp.adapter.favListAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class UpdatesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    OnBusSelectedListener mCallback;
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static UpdatesFragment newInstance(int sectionNumber) {
        UpdatesFragment fragment = new UpdatesFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public UpdatesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_updates, container, false);

        createTabs(rootView);
        populateAllList(rootView);
        populateBookedList(rootView);
        populateFavList(rootView);
        populateRecentList(rootView);
        populateCloseList(rootView);
        eventHandle(rootView);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnBusSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBusSelectedListener");
        }
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    // Container Activity must implement this interface
    public interface OnBusSelectedListener {
        public void onBusSelected(int position);
        public void onBusSelected(String title);
    }

    public void createTabs(View rootView){
        TabHost tabHost = (TabHost) rootView.findViewById(R.id.updatesTabHost);
        tabHost.setup();

        TabHost.TabSpec spec1, spec2, spec3, spec4, spec5;

        spec1 = tabHost.newTabSpec("spec1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator(getString(R.string.updates_tab1_text));
        tabHost.addTab(spec1);

        spec2 = tabHost.newTabSpec("spec2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator(getString(R.string.updates_tab2_text));
        tabHost.addTab(spec2);

        spec3 = tabHost.newTabSpec("spec3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator(getString(R.string.updates_tab3_text));
        tabHost.addTab(spec3);

        spec4 = tabHost.newTabSpec("spec4");
        spec4.setContent(R.id.tab4);
        spec4.setIndicator(getString(R.string.updates_tab4_text));
        tabHost.addTab(spec4);

        spec5 = tabHost.newTabSpec("spec5");
        spec5.setContent(R.id.tab5);
        spec5.setIndicator(getString(R.string.updates_tab5_text));
        tabHost.addTab(spec5);

    }

    public void eventHandle(View rootView){
        ListView updatesAllListView = (ListView) rootView.findViewById(R.id.updatesAllList);
        updatesAllListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });


        ListView updatesBookedListView = (ListView) rootView.findViewById(R.id.updatesBookedList);
        updatesBookedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });

        ListView updatesFavouritesListView = (ListView) rootView.findViewById(R.id.updatesFavouritesList);
        updatesFavouritesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String title = ((TextView)view.findViewById(R.id.favTitle)).getText().toString();

                mCallback.onBusSelected(title);
            }
        });

        ListView updatesRecentListView = (ListView) rootView.findViewById(R.id.updatesRecentList);
        updatesRecentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });

        ListView updatesCloseListView = (ListView) rootView.findViewById(R.id.updatesCloseList);
        updatesCloseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });
    }

    public void populateAllList(View rootView){
        Resources res = getResources();
        String[] recTitles = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] recDescs = res.getStringArray(R.array.testStatus);
        favListAdapter recAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs, testCost);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesAllList);

        // Tells the ListView what data to use
        theListView.setAdapter(recAdapter);
    }
    public void populateBookedList(View rootView){
        Resources res = getResources();
        String[] recTitles = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] recDescs = res.getStringArray(R.array.testStatus);
        favListAdapter recAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs, testCost);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesBookedList);

        // Tells the ListView what data to use
        theListView.setAdapter(recAdapter);
    }
    public void populateFavList(final View rootView){

        Resources res = getResources();
        String[] testRoutes = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] testStatus = res.getStringArray(R.array.testStatus);

        SharedPreferences settings = getActivity().getSharedPreferences("favs", 0);
        String str = settings.getString("favs", "0,0,0,0,0,0,0,0,0,0");
        StringTokenizer tokenizer = new StringTokenizer(str, ",");
        int n = tokenizer.countTokens();
        int[] favourites = new int[n];
        for (int i = 0; i < n; i++) {
            String token = tokenizer.nextToken();
            favourites[i] = Integer.parseInt(token);
        }

        List<String> titles = new ArrayList<String>();
        List<String> descs = new ArrayList<String>();
        for (int i=0; i < favourites.length; i++){

            if(favourites[i] == 1){
                titles.add(testRoutes[i]);
                descs.add(testStatus[i]);
            }
        }
        String[] favsTitles = new String[ titles.size() ];
        titles.toArray( favsTitles );
        String[] favsDescs = new String[ titles.size() ];
        descs.toArray( favsDescs );


        favListAdapter favAdapter;

        if(favsTitles.length > 0){
            favAdapter = new favListAdapter(this.getActivity(), favsTitles, favsDescs, testCost);
            // ListViews display data in a scrollable list
            ListView favListView = (ListView) rootView.findViewById(R.id.favListView);

            // Tells the ListView what data to use
            favListView.setAdapter(favAdapter);

            TextView textV = (TextView) rootView.findViewById(R.id.errorText);
            textV.setText("");
        }else{
            TextView textV = (TextView) rootView.findViewById(R.id.errorText);
            textV.setText(R.string.favError);
        }
    }
    public void populateRecentList(View rootView){
        Resources res = getResources();
        String[] recTitles = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] recDescs = res.getStringArray(R.array.testStatus);
        favListAdapter recAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs, testCost);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesRecentList);

        // Tells the ListView what data to use
        theListView.setAdapter(recAdapter);
    }
    public void populateCloseList(View rootView){
        Resources res = getResources();
        String[] recTitles = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] recDescs = res.getStringArray(R.array.testStatus);
        favListAdapter recAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs, testCost);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesCloseList);

        // Tells the ListView what data to use
        theListView.setAdapter(recAdapter);
    }
}
