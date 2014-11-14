package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;
import com.example.iain.busapp.adapter.favListAdapter;


public class SearchFragment extends Fragment{
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
    public static SearchFragment newInstance(int sectionNumber) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }


    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);


        eventHandle(rootView);
        //TestFRContract.FeedEntry.TestFRDbHelper mDbHelper = new TestFRContract.FeedEntry.TestFRDbHelper(rootView.getContext());

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
        public void onBusSelected(int i);
        public void onBusSelected(String title);
    }

    public void eventHandle(View rootView){
        ListView theListView = (ListView) rootView.findViewById(R.id.favListView);
        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String title = ((TextView) view.findViewById(R.id.favTitle)).getText().toString();

                mCallback.onBusSelected(title);
            }
        });


        ListView recListView = (ListView) rootView.findViewById(R.id.recListView);
        recListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mCallback.onBusSelected(i);
            }
        });
    }
    public void populateRecentList(View rootView){
        Resources res = getResources();
        String[] recTitles = res.getStringArray(R.array.testRoute);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] recDescs = res.getStringArray(R.array.testStatus);
        favListAdapter recAdapter = new favListAdapter(this.getActivity(), recTitles, recDescs, testCost);

        // ListViews display data in a scrollable list
        ListView recListView = (ListView) rootView.findViewById(R.id.recListView);

        // Tells the ListView what data to use
        recListView.setAdapter(recAdapter);
    }
}
