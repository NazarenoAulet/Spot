package com.quasartec.spot.LiveDatas;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.quasartec.spot.models.SpotModel;

import java.util.List;
import java.util.Map;

public class SpotLiveData extends LiveData<DataOrException<SpotModel,FirebaseFirestoreException>> implements EventListener<DocumentSnapshot> {

    private DocumentReference docRef;

    private ListenerRegistration listenerRegistration = null;


    public SpotLiveData(DocumentReference docRef) {
        this.docRef = docRef;
    }


    @Override
    protected void onActive() {
        listenerRegistration = docRef.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot snap, @Nullable FirebaseFirestoreException error) {
        DataOrException dataOrException=new DataOrException();
        if (snap != null && snap.exists()) {
            //    public SpotModel(String title2, String userId2, String groupName2, Date creationdate, String groupId2, String spotId2, String imageURL2, String profilePicURL2, int commentsAmmount2, int savings2, int durationInMinutes2, boolean showsUser2, Date startdate, Category category2, Category subCategory2) {
            SpotModel spotModel = new SpotModel(
                    snap.getString("userName"),
                    snap.getString("description"),
                    snap.getString("address"),
                    snap.getId(),
                    ((List<Map<String,Object>>) snap.get("comments"))
                    );//snap.id, snap.getDouble().toFloat());
dataOrException.setData(snap);
            setValue(dataOrException);//notify observers, retains value
        } else if (error != null) {
            dataOrException.setException(error);

        }
    }
}
