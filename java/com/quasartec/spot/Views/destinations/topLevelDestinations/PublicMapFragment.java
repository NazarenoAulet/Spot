package com.quasartec.spot.Views.destinations.topLevelDestinations;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.quasartec.spot.LiveDatas.DataOrException;
import com.quasartec.spot.LiveDatas.SpotListLiveData;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subPublicMap.MapSubPublicMapFragment;
import com.quasartec.spot.adapters.listAdapters.pagerAdapters.PublicMapPagerAdapter;
import com.quasartec.spot.models.SpotModel;
import com.quasartec.spot.viewmodels.SpotListViewModels.PublicSpotsListViewModel;
import com.quasartec.spot.viewmodels.SpotViewModel;

import java.util.List;

public class PublicMapFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    PublicSpotsListViewModel viewModel;
    LiveData<DataOrException<List<SpotModel>, FirebaseFirestoreException>> liveData;
    List<SpotModel> spots;
    ViewPager viewPager;
TabLayout tabLayout;
    public PublicMapFragment() {
        // Required empty public constructor
    }

    public static PublicMapFragment newInstance(String param1, String param2) {
        PublicMapFragment fragment = new PublicMapFragment();
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

        viewModel = new ViewModelProvider(requireActivity()).get(PublicSpotsListViewModel.class);
        liveData = viewModel.getPublicSpots();
        liveData.observe(getViewLifecycleOwner(), new Observer<DataOrException<List<SpotModel>, FirebaseFirestoreException>>() {
            @Override
            public void onChanged(DataOrException<List<SpotModel>, FirebaseFirestoreException> listFirebaseFirestoreExceptionDataOrException) {
                int currentPage = tabLayout.getSelectedTabPosition();

                if (listFirebaseFirestoreExceptionDataOrException.getData() != null) {
                    spots=listFirebaseFirestoreExceptionDataOrException.getData();
                    if (currentPage == 0) {

                            ((MapSubPublicMapFragment) ((PublicMapPagerAdapter) viewPager.getAdapter()).getItem(0)).
                    }
                    //   ((PublicMapPagerAdapter)viewPager.getAdapter()).getItem(tabLayout.g)

                } else if (listFirebaseFirestoreExceptionDataOrException.getException() != null) {

                }
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_public_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
          viewPager = view.findViewById(R.id.viewPagesPublicMap);
        viewPager.setAdapter(new PublicMapPagerAdapter(getChildFragmentManager()));
        tabLayout=view.findViewById(R.id.tabLayoutPublicMap));
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

                } else {
                    ((MainActivity) getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.spots_toolbar_menu);
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
