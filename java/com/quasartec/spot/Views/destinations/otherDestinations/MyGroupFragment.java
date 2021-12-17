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
import com.quasartec.spot.Views.destinations.otherDestinations.MyGroupFragmentDirections;
import com.quasartec.spot.Views.destinations.topLevelDestinations.GroupsFragmentDirections;
import com.quasartec.spot.adapters.listAdapters.pagerAdapters.MyGroupPagerAdapter;

public class MyGroupFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static MyGroupFragment newInstance(String param1, String param2) {
        MyGroupFragment fragment = new MyGroupFragment();
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
            ((MainActivity) getActivity()).toolbar.setTitle((CharSequence) "Grupo de reciclaje");
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_group, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPagesMyGroup);
        viewPager.setAdapter(new MyGroupPagerAdapter(getChildFragmentManager()));
        ((TabLayout) view.findViewById(R.id.tabLayoutMyGroup)).setupWithViewPager(viewPager);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.toolbar_group_menu_admin_info);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            int itemid = item.getItemId();
            if (itemid == R.id.menu_notifications) {
                ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                return false;
            } else if (itemid == R.id.menu_admin_group_edit) {
                MyGroupFragmentDirections.ActionMyGroupFragmentToCreateGroupFragment navAction = MyGroupFragmentDirections.actionMyGroupFragmentToCreateGroupFragment();
                navAction.setGroupId(1);
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) navAction);
                return false;
            } else if (itemid != R.id.menu_toolbar_group_admindelete) {
                return false;
            } else {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(1));
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {
                if (position == 0) {
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) MyGroupFragment.this.requireActivity()).toolbar.inflateMenu(R.menu.toolbar_group_menu_admin_info);
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        int itemid = item.getItemId();
                        if (itemid == R.id.menu_notifications) {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                            return false;
                        } else if (itemid == R.id.menu_admin_group_edit) {
                            MyGroupFragmentDirections.ActionMyGroupFragmentToCreateGroupFragment navAction = MyGroupFragmentDirections.actionMyGroupFragmentToCreateGroupFragment();
                            navAction.setGroupId(1);
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) navAction);
                            return false;
                        } else if (itemid != R.id.menu_toolbar_group_admindelete) {
                            return false;
                        } else {
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(1));
                            return false;
                        }
                    });
                } else if (position == 1) {
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.inflateMenu(R.menu.map_toolbar_menu);
                    ((SearchView) ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int itemid = item.getItemId();
                            if (itemid == R.id.menu_notifications) {
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                                return false;
                            } else if (itemid != R.id.menu_filterBy) {
                                return false;
                            } else {
                                NavGraphDirections.ActionGlobalFilterByDialog action = NavGraphDirections.actionGlobalFilterByDialog();
                                action.setCat(1);
                                action.setStateTime(1);
                                action.setDistance(-1);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) action);
                                return false;
                            }
                        }
                    });
                } else if (position == 4 || position == 5) {
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.inflateMenu(R.menu.onlynotifs_toolbar_menu);
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        if (item.getItemId() != R.id.menu_notifications) {
                            return false;
                        }
                        ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                        return false;
                    });
                } else {
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().clear();
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.inflateMenu(R.menu.spots_toolbar_menu);
                    ((SearchView) ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.getMenu().findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        public boolean onQueryTextChange(String newText) {
                            TextUtils.isEmpty(newText);
                            return true;
                        }
                    });
                    ((MainActivity) MyGroupFragment.this.getActivity()).toolbar.setOnMenuItemClickListener(item -> {
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
                }
            }



            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    public void onResume() {
        super.onResume();
    }
}
