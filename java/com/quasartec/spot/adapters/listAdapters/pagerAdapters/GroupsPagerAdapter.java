package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.GroupsPublicGroupsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.GroupsSubAdminFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.GroupsSubBelongFragment;

public class GroupsPagerAdapter extends FragmentStatePagerAdapter {
    int pageCount = 3;
    String[] titles = {MainActivity.mygroups, MainActivity.createdbyme, MainActivity.publicgroups};

    public GroupsPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new GroupsSubBelongFragment();
        }
        if (position == 1) {
            return new GroupsSubAdminFragment();
        }
        if (position != 2) {
            return null;
        }
        return new GroupsPublicGroupsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
