package com.quasartec.spot.adapters.listAdapters.pagerAdapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.subDestinations.subfavourites.FavouritesSubMapFragment;
import com.quasartec.spot.Views.destinations.subDestinations.subfavourites.FavouritesSubSpotsFragment;

public class FavouritesPagerAdapter extends FragmentStatePagerAdapter {
    int pageCount = 2;
    String[] titles = {MainActivity.map, MainActivity.spots};

    public FavouritesPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    public Fragment getItem(int position) {
        if (position == 0) {
            return new FavouritesSubMapFragment();
        }
        if (position != 1) {
            return null;
        }
        return new FavouritesSubSpotsFragment();
    }

    public int getCount() {
        return this.pageCount;
    }

    public CharSequence getPageTitle(int position) {
        return this.titles[position];
    }
}
