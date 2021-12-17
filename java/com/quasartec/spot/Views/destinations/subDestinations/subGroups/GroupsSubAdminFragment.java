package com.quasartec.spot.Views.destinations.subDestinations.subGroups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.topLevelDestinations.GroupsFragmentDirections;
import com.quasartec.spot.adapters.listAdapters.GroupsCardsListAdapater;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.GroupModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupsSubAdminFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<GroupModel> groups;
    private String mParam1;
    private String mParam2;

    public static GroupsSubAdminFragment newInstance(String param1, String param2) {
        GroupsSubAdminFragment fragment = new GroupsSubAdminFragment();
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
        return inflater.inflate(R.layout.fragment_groups_sub_my_groups, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        View view2 = view;
        super.onViewCreated(view, savedInstanceState);
        this.groups = new ArrayList();
        Date date = new Date();
        List<GroupModel> list = this.groups;
        this.groups.add(new GroupModel("groupid", "adminid", "title", "imageurl", Category.SOCIAL, Category.S_HELP_NEEDED, date, true, true, true, 5, 3));
       groups.get(0).admin=true;
        GroupsCardsListAdapater listAdapter = new GroupsCardsListAdapater(this.groups, getContext());

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.groupsSubMyGroupsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        GroupsFragmentDirections.ActionGroupsFragment2ToCreateGroupFragment action = GroupsFragmentDirections.actionGroupsFragment2ToCreateGroupFragment();
        action.setGroupId(1);
        ( view.findViewById(R.id.myGroups_addGroupFab)).setOnClickListener(view1 -> (  MainActivity.mn.get()).navController.navigate(action));
    }
}
