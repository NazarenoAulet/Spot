package com.quasartec.spot.viewmodels.SpotListViewModels;

import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.quasartec.spot.LiveDatas.DataOrException;
import com.quasartec.spot.Repository;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotFilter;
import com.quasartec.spot.models.SpotModel;

import java.util.ArrayList;
import java.util.List;

public class MySpotsListViewModel {
    static Repository repo;
    static SpotFilter filter;
    LiveData<DataOrException<List<DocumentSnapshot>>> spotList;

    public MySpotsListViewModel() {
        repo = Repository.getInstance();

        //      public SpotFilter(int distance, boolean withoutEnd, boolean happeningNow, boolean notStarted, Category category, String orderBy, String group) {
        ArrayList<String> keywords = new ArrayList<>();
        filter = new SpotFilter(100, true, true, true, Category.SOCIAL, "distance", "00");
        spotList = repo.getSpotsFromGroup(filter);

    }

    public   LiveData<DataOrException<List<DocumentSnapshot>>> getFavouriteSpots() {
        return spotList;
    }
}


