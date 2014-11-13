package com.example.iain.busapp.fragment;

        import android.app.Activity;
        import android.support.v4.app.Fragment;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.iain.busapp.MainActivity;
        import com.example.iain.busapp.R;


/**
 * Created by iain on 11/11/14.
 */
public class BusFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    public static final String ARG_POSITION = "arg_position";

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

        int busID = getArguments().getInt("busID");

        populateInfo(rootView, busID);

        return rootView;
    }

    private void populateInfo(View rootView, int busID) {
        Resources res = getResources();
        String[] busRoutes = res.getStringArray(R.array.testRoute);
        String[] testFrom = res.getStringArray(R.array.testFrom);
        String[] testTo = res.getStringArray(R.array.testTo);
        String[] testDeparture = res.getStringArray(R.array.testDeparture);
        String[] testCost = res.getStringArray(R.array.testCost);
        String[] testStatus = res.getStringArray(R.array.testStatus);

        TextView busRoutesView = (TextView) rootView.findViewById(R.id.busRoutesView);
        busRoutesView.setText(busRoutes[busID]);
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
