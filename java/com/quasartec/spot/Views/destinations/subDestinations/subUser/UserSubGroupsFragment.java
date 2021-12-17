package com.quasartec.spot.Views.destinations.subDestinations.subUser;

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
import com.quasartec.spot.adapters.listAdapters.GroupsCardsListAdapater;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.GroupModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserSubGroupsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<GroupModel> groups;
    private String mParam1;
    private String mParam2;

    public static UserSubGroupsFragment newInstance(String param1, String param2) {
        UserSubGroupsFragment fragment = new UserSubGroupsFragment();
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
        return inflater.inflate(R.layout.fragment_sub_user_groups, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        View view2 = view;
        super.onViewCreated(view, savedInstanceState);
        this.groups = new ArrayList();
        Date date = new Date();

        this.groups.add(new GroupModel("groupid", "adminid", "title", "imageurl", Category.SOCIAL, Category.S_HELP_NEEDED, date, true, true, true, 5, 3));
        GroupsCardsListAdapater listAdapter = new GroupsCardsListAdapater(this.groups, getContext());
        View view3 = view;
        RecyclerView recyclerView = (RecyclerView) view3.findViewById(R.id.UserSubGroupsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) view3.findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.adyacent_light, R.color.teal_200, R.color.green_200);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                Toast.makeText((Context) MainActivity.mn.get(), "lele", 0).show();
            }
        });
    }
}
