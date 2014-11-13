package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;
import com.example.iain.busapp.adapter.favListAdapter;


public class TicketFragment extends Fragment {
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
    public static TicketFragment newInstance(int sectionNumber) {
        TicketFragment fragment = new TicketFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public TicketFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ticket, container, false);

        createTabs(rootView);
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
    }

    public void createTabs(View rootView){
        TabHost tabHost = (TabHost) rootView.findViewById(R.id.ticketTabHost);
        tabHost.setup();

        TabHost.TabSpec spec1, spec2, spec3;

        spec1 = tabHost.newTabSpec("spec1");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator(getString(R.string.ticket_tab1_text));
        tabHost.addTab(spec1);

        spec2 = tabHost.newTabSpec("spec2");
        spec2.setContent(R.id.tab2);
        spec2.setIndicator(getString(R.string.ticket_tab2_text));
        tabHost.addTab(spec2);

        spec3 = tabHost.newTabSpec("spec3");
        spec3.setContent(R.id.tab3);
        spec3.setIndicator(getString(R.string.ticket_tab3_text));
        tabHost.addTab(spec3);


    }
    public void eventHandle(View rootView){
        ListView ticketFavListView = (ListView) rootView.findViewById(R.id.ticketFavList);
        ticketFavListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });


        ListView ticketRecListView = (ListView) rootView.findViewById(R.id.ticketRecentList);
        ticketRecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });

        ListView ticketCloseView = (ListView) rootView.findViewById(R.id.ticketCloseList);
        ticketCloseView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
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
        ListView theListView = (ListView) rootView.findViewById(R.id.ticketFavList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);
    }
    public void populateRecentList(View rootView){
        String[] recTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] recDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.ticketRecentList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);
    }
    public void populateCloseList(View rootView){
        String[] recTitles = {"Bus Number 1", "Bus Number 2", "Bus Number 3", "Bus Number 4",
                "Bus Number 5" , "Bus Number 6" , "Bus Number 7" , "Bus Number 8" };
        String[] recDescs = {"Bus1 Status", "Bus2 Status", "Bus3 Status", "Bus Doesn\'t Exist",
                "Bus5 Status", "Bus6 Status", "Bus7 Status", "Bus Doesn\'t Exist"};

        favListAdapter theAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs);

        // ListViews display data in a scrollable list
        ListView theListView = (ListView) rootView.findViewById(R.id.ticketCloseList);

        // Tells the ListView what data to use
        theListView.setAdapter(theAdapter);
    }
}
