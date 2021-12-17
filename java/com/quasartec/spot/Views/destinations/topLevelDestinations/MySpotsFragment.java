package com.quasartec.spot.Views.destinations.topLevelDestinations;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.pagerAdapters.MySpotsPagerAdapter;
import com.quasartec.spot.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MySpotsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySpotsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MySpotsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MySpotsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MySpotsFragment newInstance(String param1, String param2) {
        MySpotsFragment fragment = new MySpotsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_spots, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ViewPager viewPager=view.findViewById(R.id.viewPagesMySpots);
        MySpotsPagerAdapter pagerAdapter= new MySpotsPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout= view.findViewById(R.id.tabLayoutMySpots);
        tabLayout.setupWithViewPager(viewPager);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.map_toolbar_menu);
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
            if (item.getItemId() == R.id.menu_notifications) {
                (MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
            } else if (item.getItemId() == R.id.menu_filterBy) {
                NavGraphDirections.ActionGlobalFilterByDialog action = NavGraphDirections.actionGlobalFilterByDialog();
                action.setCat(1);
                action.setStateTime(1);
                action.setDistance(-1);
                (MainActivity.mn.get()).navController.navigate(action);
            } else if (item.getItemId() == R.id.menu_orderBy) {
                NavGraphDirections.ActionGlobalOrderByDialog action = NavGraphDirections.actionGlobalOrderByDialog();
                action.setAsc(1);
                action.setProp(1);
                (MainActivity.mn.get()).navController.navigate(action);
                return false;
            }
            return false;
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                if (position == 0) {
                    ((MainActivity)  getActivity()).toolbar.getMenu().clear();
                    ((MainActivity)  getActivity()).toolbar.inflateMenu(R.menu.map_toolbar_menu);
                    ((SearchView) ((MainActivity)  getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });

                } else {
                    ((MainActivity)  getActivity()).toolbar.getMenu().clear();
                    ((MainActivity)  getActivity()).toolbar.inflateMenu(R.menu.spots_toolbar_menu);
                    ((SearchView) ((MainActivity) getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });
                    //   ((MainActivity) PublicMapFragment.this.getActivity()).toolbar.setOnMenuItemClickListener($$Lambda$PublicMapFragment$2$Ks9k_MK8kIehV_ZaUx8U2lhse8.INSTANCE);
                }
            }

            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}