package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subPublicMap.MapSubPublicMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subPublicMap.MySpotsSubPublicMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subPublicMap.SpotsSubPublicMapFragment;

public class PublicMapPagerAdapter extends FragmentStatePagerAdapter {
    int pageCount = 3;
    String[] titles = {MainActivity.map, MainActivity.spots, MainActivity.myspots};

    public PublicMapPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new MapSubPublicMapFragment();
        }
        if (position == 1) {
            return new SpotsSubPublicMapFragment();
        }
        if (position != 2) {
            return null;
        }
        return new MySpotsSubPublicMapFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
