package com.example.iain.busapp.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iain.busapp.MainActivity;
import com.example.iain.busapp.R;

import java.util.StringTokenizer;


public class LoginFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LoginFragment newInstance(int sectionNumber) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;

    }


    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);


        final SharedPreferences loginSettings = getActivity().getSharedPreferences("login", 0);
        String strLogin = loginSettings.getString("login", "0");
        if(strLogin.equalsIgnoreCase("1")){
            LinearLayout notLogged = (LinearLayout) rootView.findViewById(R.id.notLogged);
            notLogged.setVisibility(LinearLayout.GONE);
            LinearLayout Logged = (LinearLayout) rootView.findViewById(R.id.Logged);
            Logged.setVisibility(LinearLayout.VISIBLE);
        }else{
            LinearLayout notLogged = (LinearLayout) rootView.findViewById(R.id.notLogged);
            notLogged.setVisibility(LinearLayout.VISIBLE);
            LinearLayout Logged = (LinearLayout) rootView.findViewById(R.id.Logged);
            Logged.setVisibility(LinearLayout.GONE);

        }
        eventHandle(rootView);

        return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
    public void eventHandle(View rView){
        final View rootView = rView;
        final SharedPreferences loginSettings = getActivity().getSharedPreferences("login", 0);
        Button loginButton = (Button) rootView.findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = loginSettings.edit();
                editor.putString("login", "1");
                editor.commit();

                LinearLayout notLogged = (LinearLayout) rootView.findViewById(R.id.notLogged);
                notLogged.setVisibility(LinearLayout.GONE);
                LinearLayout Logged = (LinearLayout) rootView.findViewById(R.id.Logged);
                Logged.setVisibility(LinearLayout.VISIBLE);
            }
        });
        Button logoutButton = (Button) rootView.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = loginSettings.edit();
                editor.putString("login", "0");
                editor.commit();
                LinearLayout notLogged = (LinearLayout) rootView.findViewById(R.id.notLogged);
                notLogged.setVisibility(LinearLayout.VISIBLE);
                LinearLayout Logged = (LinearLayout) rootView.findViewById(R.id.Logged);
                Logged.setVisibility(LinearLayout.GONE);
            }
        });
    }

}
