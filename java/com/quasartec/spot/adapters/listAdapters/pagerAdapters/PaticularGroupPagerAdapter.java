package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subParticularGroup.ParticularGroupSubInfoFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subParticularGroup.ParticularGroupSubMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subGroups.subParticularGroup.ParticularGroupSubSpotsFragment;

public class PaticularGroupPagerAdapter extends FragmentStatePagerAdapter {
    public int pageCount = 3;
    String[] titles = {MainActivity.info, MainActivity.map, MainActivity.spots};

    public PaticularGroupPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new ParticularGroupSubInfoFragment();
        }
        if (position == 1) {
            return new ParticularGroupSubMapFragment();
        }
        if (position != 2) {
            return null;
        }
        return new ParticularGroupSubSpotsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
