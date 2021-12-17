package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subUser.UserSubGroupsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subUser.UserSubInfoFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subUser.UserSubSpotsFragment;

public class UserPagerAdapter extends FragmentStatePagerAdapter {
    public int pageCount = 3;
    String[] titles = {MainActivity.info, MainActivity.spots, MainActivity.groups};

    public UserPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new UserSubInfoFragment();
        }
        if (position == 1) {
            return new UserSubSpotsFragment();
        }
        if (position != 2) {
            return null;
        }
        return new UserSubGroupsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
