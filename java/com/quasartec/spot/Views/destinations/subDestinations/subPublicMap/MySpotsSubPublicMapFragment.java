package com.quasartec.spot.Views.destinations.subDestinations.subPublicMap;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.quasartec.spot.LiveDatas.DataOrException;
import com.quasartec.spot.R;
import com.quasartec.spot.adapters.listAdapters.SpotsListAdapter;
import com.quasartec.spot.models.SpotModel;
import com.quasartec.spot.viewmodels.SpotListViewModels.PublicSpotsListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MySpotsSubPublicMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    ArrayList<SpotModel> spots;
    PublicSpotsListViewModel publicSpotsListViewModel;

    public static MySpotsSubPublicMapFragment newInstance(String param1, String param2) {
        MySpotsSubPublicMapFragment fragment = new MySpotsSubPublicMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        publicSpotsListViewModel = new ViewModelProvider(this.getParentFragment()).get(PublicSpotsListViewModel.class);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_spots_sub_public_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SpotsListAdapter listAdapter = new SpotsListAdapter(new SpotsListAdapter.WordDiff(), getContext());
        RecyclerView recyclerView = view.findViewById(R.id.publicMapSubMySpotsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);


        publicSpotsListViewModel.getPublicSpots().observe(getViewLifecycleOwner(), new Observer<DataOrException<List<DocumentSnapshot>>>() {
            @Override
            public void onChanged(DataOrException<List<DocumentSnapshot>> e) {
                System.out.println("e.getData() "+e.getData()+" e.getException()"+e.getException());
                if (e.getData() != null && e.getException() == null) {
                    List<SpotModel> spots = new ArrayList<>();

                    for (DocumentSnapshot snap : e.getData()) {
                        spots.add(snap.toObject(SpotModel.class));
                    }
                    listAdapter.submitList(spots);

                } else {

                    Log.i("LazyMVVMFireStoreQuery", e.getException().getMessage());
                }

            }
        });





     /*   publicLisViewModel.getSpots(CollectionPath,QueryField,QueryValue,RequestCode).observe(this, new Observer<DataOrException<List<QueryDocumentSnapshot>>>() {
            @Override
            public void onChanged(DataOrException<List<QueryDocumentSnapshot>> e) {
                if (e.getData()!=null || e.getException()!=null){
                    for (QueryDocumentSnapshot snap:e.getData()){
                        Log.i("LazyMVVMFireStoreQuery",snap.toObject(UsersModel.class).getFirstName()+snap.toObject(UsersModel.class).getLastName());
                    }
                }else {

                    Log.i("LazyMVVMFireStoreQuery", e.getException().getMessage());
                }

            }
        });*/

    }
}
