package com.quasartec.spot.viewmodels.SpotListViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.quasartec.spot.LiveDatas.DataOrException;

import com.quasartec.spot.LiveDatas.SpotListLiveData;
import com.quasartec.spot.LiveDatas.SpotLiveData;
import com.quasartec.spot.Repository;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotFilter;
import com.quasartec.spot.models.SpotModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicSpotsListViewModel extends ViewModel {

    static Repository repo;
    static SpotFilter filter;
    public int liveDatasQuant;
    List<SpotListLiveData> _spotList;
    public List<LiveData<DataOrException<List<SpotModel>, FirebaseFirestoreException>>> spotList;
    //  private Map<Integer, LiveData<DataOrException<List<DocumentSnapshot>>>> MainModelMap= new HashMap<>();

    public PublicSpotsListViewModel() {
        repo = Repository.getInstance();

        //
        //      public SpotFilter(int distance, boolean withoutEnd, boolean happeningNow, boolean notStarted, Category category, String orderBy, String group) {
        ArrayList<String> keywords = new ArrayList<>();
        filter = new SpotFilter(100, true, true, true, Category.SOCIAL, "distance", "00");


        _spotList = repo.getSpotsFromGroup(filter);
        liveDatasQuant = _spotList.size();

    }

    public LiveData<DataOrException<List<SpotModel>, FirebaseFirestoreException>> getPublicSpots() {

        MediatorLiveData<DataOrException<List<SpotModel>, FirebaseFirestoreException>> result = new MediatorLiveData<>();
        List<SpotModel> spotModelList = new ArrayList<>();

        Observer<SpotListLiveData> observer = new Observer<SpotListLiveData>() {
            @Override
            public void onChanged(SpotListLiveData spotListLiveData) {
                DataOrException data = new DataOrException<>();

                if (spotListLiveData.getValue().getData() != null) {
                    List<DocumentSnapshot> list = spotListLiveData.getValue().getData();
                    spotModelList.clear();
                    for (DocumentSnapshot doc : list) {

                        spotModelList.add(new SpotModel(
                                doc.getId(),
                                doc.getString("category"),
                                doc.getString("subcategory"),
                                (int) doc.get("favouritesAmount"),
                                (int) doc.get("commentsAmount"),
                                doc.getLong("creationDate"),
                                doc.getLong("startingDate"),
                                doc.getLong("durationInMinutes"),
                                doc.getDouble("lat"),
                                doc.getDouble("lon"),
                                doc.getString("title"),
                                doc.getString("picURL"),
                                doc.getString("userID"),
                                doc.getString("userPicURL"),
                                doc.getString("groupName"),
                                doc.getString("groupID"),
                                doc.getBoolean("mine"),
                                doc.getBoolean("inAdminGroup"),
                                doc.getBoolean("inBelongGroup"),
                                doc.getBoolean("showsUser"),
                                doc.getBoolean("isFavourited"),
                                doc.getBoolean("inPublicGroup"),
                                doc.getBoolean("inFavourites")


                        ));
                    }
                    data.setData(spotModelList);
                    result.setValue(data);
                } else if (spotListLiveData.getValue().getException() != null) {
                    data.setException(spotListLiveData.getValue().getException());
                    result.setValue(data);

                }
            }
        };
        for (SpotListLiveData spotListLiveData : _spotList) {
            result.addSource((LiveData)spotListLiveData,observer);
        }
        return result;
    }
}
