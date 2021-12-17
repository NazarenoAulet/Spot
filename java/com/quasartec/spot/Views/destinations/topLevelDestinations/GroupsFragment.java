package com.quasartec.spot.Views.destinations.topLevelDestinations;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.pagerAdapters.GroupsPagerAdapter;

public class GroupsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static GroupsFragment newInstance(String param1, String param2) {
        GroupsFragment fragment = new GroupsFragment();
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
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.toolbar_grouptop_menu);
        ((SearchView) ((MainActivity) getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                TextUtils.isEmpty(newText);
                return true;
            }
        });
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {

            int itemid = item.getItemId();
            if (itemid == R.id.menu_notifications) {
                (MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
            } else if (itemid == R.id.menu_orderGroupBy) {
                (MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalOrderGroupByDialog());
            } else if (itemid == R.id.menu_filterGroupBy) {
                NavGraphDirections.ActionGlobalFilterGroupByDialog action = NavGraphDirections.actionGlobalFilterGroupByDialog();
                action.setMaintopic(0);
                (MainActivity.mn.get()).navController.navigate( action);
            }
            return false;

        });


        ViewPager viewPager = view.findViewById(R.id.viewPagesGroups);
        viewPager.setAdapter(new GroupsPagerAdapter(getChildFragmentManager()));
        ((TabLayout) view.findViewById(R.id.tabLayoutGroups)).setupWithViewPager(viewPager);


    }


}
