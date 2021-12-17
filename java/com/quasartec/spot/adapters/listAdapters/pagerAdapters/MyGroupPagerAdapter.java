package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubInfoFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubMySpotsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubSpotsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubUserRequestsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subMyGroup.MyGroupSubUsersFragment;

public class MyGroupPagerAdapter extends FragmentStatePagerAdapter {
    int pageCount = 6;
    String[] titles = {MainActivity.info, MainActivity.map, MainActivity.spots, MainActivity.myspots, MainActivity.members, MainActivity.memberRequests};

    public MyGroupPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new MyGroupSubInfoFragment();
        }
        if (position == 1) {
            return new MyGroupSubMapFragment();
        }
        if (position == 2) {
            return new MyGroupSubSpotsFragment();
        }
        if (position == 3) {
            return new MyGroupSubMySpotsFragment();
        }
        if (position == 4) {
            return new MyGroupSubUsersFragment();
        }
        if (position != 5) {
            return null;
        }
        return new MyGroupSubUserRequestsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
