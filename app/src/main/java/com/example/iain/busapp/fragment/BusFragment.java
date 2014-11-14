package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;

import java.util.StringTokenizer;


public class BusFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private int busID;

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BusFragment newInstance(int sectionNumber) {
        BusFragment fragment = new BusFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;

    }


    public BusFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bus, container, false);

        eventHandle(rootView);

        ImageButton button = (ImageButton) rootView.findViewById(R.id.imageFavBus);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences settings = getActivity().getSharedPreferences("favs", 0);
                String str = settings.getString("favs", "0,0,0,0,0,0,0,0,0,0");
                StringBuilder temp = new StringBuilder(str);
                if(str.charAt(2*busID) == '1'){
                    temp.setCharAt(2*busID, '0');
                }else{
                    temp.setCharAt(2*busID, '1');
                }
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("favs", temp.toString());

                // Commit the edits!
                editor.commit();
                ImageButton button = (ImageButton) getActivity().findViewById(R.id.imageFavBus);
                if(temp.charAt(2*busID) == '1'){
                    button.setImageResource(R.drawable.ic_star);
                }else{
                    button.setImageResource(R.drawable.ic_star_outline);
                }
            }
        });

        busID = getArguments().getInt("busID");

        populateInfo(rootView, busID);

        return rootView;
    }

    private void populateInfo(View rootView, int busID) {
        Resources res = getResources();
        String[] testRoutes = res.getStringArray(R.array.testRoute);
        String[] testFrom = res.getStringArray(R.array.testFrom);
        String[] testTo = res.getStringArray(R.array.testTo);
        String[] testDeparture = res.getStringArray(R.array.testDeparture);
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


        TextView testRoutesView = (TextView) rootView.findViewById(R.id.busRoutesView);
        testRoutesView.setText(testRoutes[busID]);

        TextView testFromView = (TextView) rootView.findViewById(R.id.busFromView);
        testFromView.setText(testFrom[busID]);

        TextView testToView = (TextView) rootView.findViewById(R.id.busToView);
        testToView.setText(testTo[busID]);

        TextView testDepartureView = (TextView) rootView.findViewById(R.id.busDepartView);
        testDepartureView.setText(testDeparture[busID]);

        TextView testCostView = (TextView) rootView.findViewById(R.id.busCostView);
        testCostView.setText(testCost[busID]);

        TextView testStatusView = (TextView) rootView.findViewById(R.id.busStatusView);
        testStatusView.setText(testStatus[busID]);

        ImageButton button = (ImageButton) rootView.findViewById(R.id.imageFavBus);
        if(favourites[busID] == 1){
            button.setImageResource(R.drawable.ic_star);
        }else{
            button.setImageResource(R.drawable.ic_star_outline);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    public void eventHandle(View rootView){

    }

}
