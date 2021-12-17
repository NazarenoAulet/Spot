package com.quasartec.spot.Views.destinations.subDestinations.subMySpots;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.adapters.listAdapters.SpotsListAdapter;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MySpotsSubSpotsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    List<SpotModel> spots;

    public static MySpotsSubSpotsFragment newInstance(String param1, String param2) {
        MySpotsSubSpotsFragment fragment = new MySpotsSubSpotsFragment();
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
        return inflater.inflate(R.layout.fragment_my_spots_sub_spots, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SpotsListAdapter listAdapter = new SpotsListAdapter(new SpotsListAdapter.WordDiff(),getContext());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mySpotsSubMySpotsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }
}
