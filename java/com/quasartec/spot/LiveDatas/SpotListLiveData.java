package com.quasartec.spot.LiveDatas;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.firebase.geofire.GeoFireUtils;
import com.firebase.geofire.GeoLocation;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.quasartec.spot.models.SpotFilter;
import com.quasartec.spot.models.SpotModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpotListLiveData extends LiveData<DataOrException<List<DocumentSnapshot>, FirebaseFirestoreException>> implements EventListener<QuerySnapshot> {

    private Query query;

    private ListenerRegistration listenerRegistration = null;


    public SpotListLiveData(Query query) {
        this.query = query;
    }


    @Override
    protected void onActive() {
        listenerRegistration = query.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot snap, @Nullable FirebaseFirestoreException error) {
        DataOrException<List<DocumentSnapshot>, FirebaseFirestoreException> data = new DataOrException<>();

        if (snap != null) {
            List<DocumentSnapshot> documents = snap.getDocuments();

            List<DocumentSnapshot> matchingDocs = new ArrayList<>();

            final GeoLocation center = new GeoLocation(SpotFilter.lat, SpotFilter.lon);
            final double radiusInM = SpotFilter.getDistance();
            for (DocumentSnapshot doc : documents) {
                double lat = doc.getDouble("lat");
                double lng = doc.getDouble("lng");

                // We have to filter out a few false positives due to GeoHash
                // accuracy, but most will match
                GeoLocation docLocation = new GeoLocation(lat, lng);
                double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                if (distanceInM <= radiusInM) {//TODO FILTER
                    matchingDocs.add(doc);



                }
            }
            data.setData(matchingDocs);

        } else
            data.setException(error);

        postValue(data);
    }
}
