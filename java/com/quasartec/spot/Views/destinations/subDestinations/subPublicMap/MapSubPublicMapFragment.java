package com.quasartec.spot.Views.destinations.subDestinations.subPublicMap;

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
import com.quasartec.spot.models.SpotModel;

import java.util.ArrayList;

public class MapSubPublicMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    ArrayList<SpotModel> mapSpots;
    public static MapSubPublicMapFragment newInstance(String param1, String param2) {
        MapSubPublicMapFragment fragment = new MapSubPublicMapFragment();
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
        return inflater.inflate(R.layout.fragment_map_sub_public_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((FloatingActionButton) view.findViewById(R.id.publicMap_addSpotFab)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalModifySpotFragment());
            }
        });
    }

    public void addNewSpot(SpotModel spotModel){
mapSpots.add(spotModel);
    }

    public void removeSpot(SpotModel spotModel){
        mapSpots.remove(spotModel);
    }
}
