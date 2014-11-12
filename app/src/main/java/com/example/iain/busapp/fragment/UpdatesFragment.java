package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;
import com.example.iain.busapp.adapter.favListAdapter;


/**
 * Created by iain on 11/11/14.
 */
public class UpdatesFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
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

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
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

    public void populateAllList(View rootView){
        String[] favsTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] favsDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), favsTitles, favsDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesAllList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));

                Toast.makeText(UpdatesFragment.this.getActivity(), tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void populateBookedList(View rootView){
        String[] recTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] recDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesBookedList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));

                Toast.makeText(UpdatesFragment.this.getActivity(), tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void populateFavList(View rootView){
        String[] favsTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] favsDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), favsTitles, favsDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesFavouritesList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));

                Toast.makeText(UpdatesFragment.this.getActivity(), tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void populateRecentList(View rootView){
        String[] recTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] recDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesRecentList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));

                Toast.makeText(UpdatesFragment.this.getActivity(), tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void populateCloseList(View rootView){
        String[] recTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] recDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.updatesCloseList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String tvShowPicked = "You selected " +
                        String.valueOf(adapterView.getItemAtPosition(i));

                Toast.makeText(UpdatesFragment.this.getActivity(), tvShowPicked, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
