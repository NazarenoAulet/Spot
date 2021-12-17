package com.quasartec.spot.Views.destinations.subDestinations.subPublicMap;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.SpotsListAdapter;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpotsSubPublicMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;


    public static SpotsSubPublicMapFragment newInstance(String param1, String param2) {
        SpotsSubPublicMapFragment fragment = new SpotsSubPublicMapFragment();
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
        return inflater.inflate(R.layout.fragment_spots_sub_public_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        View view2 = view;
        super.onViewCreated(view, savedInstanceState);

        SpotsListAdapter listAdapter = new SpotsListAdapter(new SpotsListAdapter.WordDiff(),getContext());

        RecyclerView recyclerView2 = (RecyclerView) view2.findViewById(R.id.spotsSubPublicMapRecyclerView);
        this.recyclerView = recyclerView2;
        recyclerView2.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(listAdapter);
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view2.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.adyacent_light, R.color.teal_200, R.color.green_200);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
              //  Toast.makeText((Context) MainActivity.mn.get(), "lele", 0).show();
            }
        });

    }
}
