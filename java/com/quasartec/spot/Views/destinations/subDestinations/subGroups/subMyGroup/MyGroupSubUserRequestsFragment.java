package com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup;

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
import com.quasartec.spot.adapters.listAdapters.UserCardAdapter;
import com.quasartec.spot.models.UserModel;
import java.util.ArrayList;
import java.util.List;

public class MyGroupSubUserRequestsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    List<UserModel> users;

    public static MyGroupSubUserRequestsFragment newInstance(String param1, String param2) {
        MyGroupSubUserRequestsFragment fragment = new MyGroupSubUserRequestsFragment();
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
        return inflater.inflate(R.layout.fragment_my_group_sub_user_requests, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList arrayList = new ArrayList();
        this.users = arrayList;
        arrayList.add(new UserModel("LAejuffehihaka", "username", "picurl"));
        UserCardAdapter listAdapter = new UserCardAdapter(this.users, getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.myGroupSubUserRequestsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.adyacent_light, R.color.teal_200, R.color.green_200);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
              //  Toast.makeText((Context) MainActivity.mn.get(), "lele", 0).show();
            }
        });
    }
}
