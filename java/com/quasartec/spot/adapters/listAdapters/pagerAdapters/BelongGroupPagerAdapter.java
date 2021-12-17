package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subBelongGroup.BelongGroupSubInfoFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subBelongGroup.BelongGroupSubMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subBelongGroup.BelongGroupSubMySpotsFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subBelongGroup.BelongGroupSubSpotsFragment;

public class BelongGroupPagerAdapter extends FragmentStateAdapter {
    int pageCount = 4;
    String[] titles = {MainActivity.info, MainActivity.map, MainActivity.spots, MainActivity.myspots};

    public BelongGroupPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new BelongGroupSubInfoFragment();
        }
        if (position == 1) {
            return new BelongGroupSubMapFragment();
        }
        if (position == 2) {
            return new BelongGroupSubSpotsFragment();
        }
        if (position != 3) {
            return null;
        }
        return new BelongGroupSubMySpotsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
