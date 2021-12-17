package com.quasartec.spot.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.quasartec.spot.LiveDatas.SpotLiveData;
import com.quasartec.spot.Repository;
import com.quasartec.spot.models.SpotModel;

import java.util.HashMap;

public class SpotViewModel extends ViewModel {

    private Repository repository;
    private HashMap<String, SpotLiveData> cache;

    public SpotViewModel() {
        repository = Repository.getInstance();

    }

    private SpotLiveData liveData;

    public SpotLiveData getSpotLiveData(String spotID) {
        liveData = Repository.getSpot(spotID);

        return liveData;
    }


    public void removeSpotFromFavourites(String spotID) {
        Repository.removeSpotFromFavourites(  spotID) ;
    }

    public void addSpotFromFavourites(String spotID) {
        Repository.addSpotFromFavourites(  spotID) ;

    }
}
