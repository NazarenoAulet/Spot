package com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup;

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

public class MyGroupSubMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static MyGroupSubMapFragment newInstance(String param1, String param2) {
        MyGroupSubMapFragment fragment = new MyGroupSubMapFragment();
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
        return inflater.inflate(R.layout.fragment_my_group_sub_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ( view.findViewById(R.id.myGroup_addSpotFab)).setOnClickListener(view1 -> (  MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalModifySpotFragment()));
    }
}
