package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;
import com.example.iain.busapp.adapter.favListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);


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

    public void eventHandle(final View rootView){
        TextView theListView = (TextView) rootView.findViewById(R.id.searchBar);
        theListView.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String searchText = s.toString();

                searchList(rootView, searchText);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }
    public void searchList(View rootView, String query){
        Resources res = getResources();
        List<String> recTitles = Arrays.asList(res.getStringArray(R.array.testRoute));
        List<String> testCost = Arrays.asList(res.getStringArray(R.array.testCost));
        List<String> recDescs = Arrays.asList(res.getStringArray(R.array.testStatus));

        List<String> searchTitle = new ArrayList<String>();
        List<String> searchDescs = new ArrayList<String>();
        List<String> searchCost = new ArrayList<String>();

        for(int i = 0; i < 10; i++){
            if(recTitles.get(i).toLowerCase().indexOf(query.toLowerCase()) > -1){
                searchTitle.add(recTitles.get(i));
                searchDescs.add(recDescs.get(i));
                searchCost.add(testCost.get(i));
            }else if(recDescs.get(i).toLowerCase().indexOf(query.toLowerCase()) > -1){
                searchTitle.add(recTitles.get(i));
                searchDescs.add(recDescs.get(i));
                searchCost.add(testCost.get(i));
            }else if(testCost.get(i).toLowerCase().indexOf(query.toLowerCase()) > -1){
                searchTitle.add(recTitles.get(i));
                searchDescs.add(recDescs.get(i));
                searchCost.add(testCost.get(i));
            }
        }
        String[] ansTitles = new String[ searchTitle.size() ];
        searchTitle.toArray( ansTitles );
        String[] ansDesc = new String[ searchDescs.size() ];
        searchDescs.toArray( ansDesc );
        String[] ansCost = new String[ searchCost.size() ];
        searchCost.toArray( ansCost );


        favListAdapter recAdapter = new favListAdapter(this.getActivity(), ansTitles, ansDesc, ansCost);

        // ListViews display data in a scrollable list
        ListView recListView = (ListView) rootView.findViewById(R.id.searchListView);

        // Tells the ListView what data to use
        recListView.setAdapter(recAdapter);
    }
}
