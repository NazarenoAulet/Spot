package com.quasartec.spot.Views.destinations.subDestinations.subMySpots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;

public class MySpotsSubMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static MySpotsSubMapFragment newInstance(String param1, String param2) {
        MySpotsSubMapFragment fragment = new MySpotsSubMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_spots_sub_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ( view.findViewById(R.id.mySpots_addSpotFab)).setOnClickListener(view1 -> (  MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalModifySpotFragment()));
    }
}
