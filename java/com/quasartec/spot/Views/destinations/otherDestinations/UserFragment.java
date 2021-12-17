package com.quasartec.spot.Views.destinations.otherDestinations;

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
import com.quasartec.spot.adapters.listAdapters.pagerAdapters.UserPagerAdapter;

public class UserFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagesUser);
        UserPagerAdapter pagerAdapter = new UserPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        ((TabLayout) view.findViewById(R.id.tabLayoutUser)).setupWithViewPager(viewPager);
        pagerAdapter.pageCount = 3;
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.user_toolbar_menu);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            int itemid = item.getItemId();
            if (itemid == R.id.menu_notifications) {
                ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                return false;
            } else if (itemid == R.id.menu_block_user) {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                return false;
            } else if (itemid == R.id.menu_usermessage) {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) UserFragmentDirections.actionUserFragmentToChatFragment(1));
                return false;
            } else if (itemid != R.id.menu_report_user) {
                return false;
            } else {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                if (position == 0) {
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.inflateMenu(R.menu.user_toolbar_menu);
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        int itemid = item.getItemId();
                        if (itemid == R.id.menu_notifications) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                            return false;
                        } else if (itemid == R.id.menu_block_user) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                            return false;
                        } else if (itemid != R.id.menu_report_user) {
                            return false;
                        } else {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                            return false;
                        }
                    });
                } else if (position == 1) {
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.inflateMenu(R.menu.spots_toolbar_menu);
                    ((SearchView) ((MainActivity) UserFragment.this.getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        int itemid = item.getItemId();
                        if (itemid == R.id.menu_notifications) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                            return false;
                        } else if (itemid == R.id.menu_orderBy) {
                            NavGraphDirections.ActionGlobalOrderByDialog action = NavGraphDirections.actionGlobalOrderByDialog();
                            action.setAsc(1);
                            action.setProp(1);
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) action);
                            return false;
                        } else if (itemid != R.id.menu_filterBy) {
                            return false;
                        } else {
                            NavGraphDirections.ActionGlobalFilterByDialog action2 = NavGraphDirections.actionGlobalFilterByDialog();
                            action2.setCat(1);
                            action2.setStateTime(1);
                            action2.setDistance(-1);
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) action2);
                            return false;
                        }
                    });
                } else {
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.inflateMenu(R.menu.toolbar_grouptop_menu);
                    ((SearchView) ((MainActivity) UserFragment.this.getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });
                    ((MainActivity) UserFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        int itemid = item.getItemId();
                        if (itemid == R.id.menu_notifications) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                        } else if (itemid == R.id.menu_orderGroupBy) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalOrderGroupByDialog());
                        } else if (itemid == R.id.menu_filterGroupBy) {
                            NavGraphDirections.ActionGlobalFilterGroupByDialog action = NavGraphDirections.actionGlobalFilterGroupByDialog();
                            action.setMaintopic(0);
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) action);
                        }
                        return false;
                    });
                }
            }




            public void onPageScrollStateChanged(int state) {
            }
        });
    }

}
