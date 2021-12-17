package com.quasartec.spot;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.location.Location;
import android.location.LocationManager;

import com.firebase.geofire.GeoFireUtils;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQueryBounds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.quasartec.spot.LiveDatas.SpotListLiveData;
import com.quasartec.spot.LiveDatas.SpotLiveData;
import com.quasartec.spot.Views.MainActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.quasartec.spot.LiveDatas.DataOrException;

import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.GroupFilter;
import com.quasartec.spot.models.SpotFilter;
import com.quasartec.spot.models.SpotModel;
import com.quasartec.spot.models.UserModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Repository {

    static Repository INSTANCE;
//apply plugin: 'kotlin-android'
    //  classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    static CollectionReference spots, users, groups, reported;//TopLevel
    CollectionReference spot_inside, spot_cardView, spot_inside_comments;
    CollectionReference user_followedSpots, user_privateInfo, user_chats, user_belongGroups, user_admingGroups, user_notifications, user_chat_allMessages, user_admingGroup_users, getUser_admingGroup_userRequests;
    CollectionReference group_cardView, group_inside;

    UserModel loggedUser;


    public Repository() {
        spots = db.collection("spots");


        //....
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();

        }
        return INSTANCE;
    }

  /*  public LiveData<DataOrException<List<DocumentSnapshot>>> getSpotsGroup(SpotFilter filter) {

    // Find cities within 50km of London
    final GeoLocation center = new GeoLocation(51.5074, 0.1278);
    final double radiusInM = 50 * 1000;

    // Each item in 'bounds' represents a startAt/endAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
    List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
    final List<Task<QuerySnapshot>> tasks = new ArrayList<>();
for(
    GeoQueryBounds b :bounds)

    {
        Query q = db.collection("cities")
                .orderBy("geohash")
                .startAt(b.startHash)
                .endAt(b.endHash);

        tasks.add(q.get());
    }

// Collect all the query results together into a single list
Tasks.whenAllComplete(tasks)
            .

    addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
        @Override
        public void onComplete (@NonNull Task < List < Task < ? >>> t){
            List<DocumentSnapshot> matchingDocs = new ArrayList<>();

            for (Task<QuerySnapshot> task : tasks) {
                QuerySnapshot snap = task.getResult();
                for (DocumentSnapshot doc : snap.getDocuments()) {
                    double lat = doc.getDouble("lat");
                    double lng = doc.getDouble("lng");

                    // We have to filter out a few false positives due to GeoHash
                    // accuracy, but most will match
                    GeoLocation docLocation = new GeoLocation(lat, lng);
                    double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                    if (distanceInM <= radiusInM) {
                        matchingDocs.add(doc);
                    }
                }
            }

            // matchingDocs contains the results
            // ...
        }
    });

}
  /*  public LiveData<DataOrException<List<DocumentSnapshot>>> getSpotsFromGroup(SpotFilter filter) {



        Location centerLocation = new Location(LocationManager.GPS_PROVIDER);
        Distance distanceForRadius = new Distance(filter.getDistance(), BoundingBoxUtils.DistanceUnit.KILOMETERS); // or you can set unit as DistanceUnit.MILES if you want to find for 1 mile

        *//*GeoQuery geoQuery = new GeoQuery()
                .collection("users")
                .whereEqualTo("status","approved")
                .whereEqualTo("country","IN")
                .whereNearToLocation(centerLocation, distanceForRadius, fieldName)
                //fieldName if you have passed at time of setLocation else it will take default as "g" if you do not pass
                .startAfter(lastDocument) //optinal (for pagination)
                .limit(10); // If you requires only 10 data per query
 /*


        GeoQuery geoQuery;
        if (filter.getCategory() == Category.ALL) {
            if (filter.isHappeningNow() && filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd());
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords());
                }
            } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                }
            } else {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));

                }
            }
        } else if (filter.getCategory() == Category.SOCIAL || filter.getCategory() == Category.MATERIAL || filter.getCategory() == Category.ENVIRONMENT) {
            String category = null;
            if (filter.getCategory() == Category.SOCIAL)
                category = "social";
            else if (filter.getCategory() == Category.MATERIAL)
                category = "resource";
            else if (filter.getCategory() == Category.ENVIRONMENT)
                category = "environment";

            if (filter.isHappeningNow() && filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy("loc")//filter.getOrderBy()
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd());

                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords());
                }
            } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                }
            } else {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("category", category)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                }
            }
        } else {
            String subcategory = null;
            if (filter.getCategory() == Category.S_GROUP_ACTIVITY)
                subcategory = "group_activity";
            else if (filter.getCategory() == Category.S_HELP_NEEDED)
                subcategory = "help_needed";
            else if (filter.getCategory() == Category.S_HUMANITARIAN_ACTIVITY)
                subcategory = "human_activity";
            else if (filter.getCategory() == Category.S_PARTY)
                subcategory = "party";
            else if (filter.getCategory() == Category.M_BUY)
                subcategory = "buy";
            else if (filter.getCategory() == Category.M_SELL)
                subcategory = "sell";
            else if (filter.getCategory() == Category.M_DONATION)
                subcategory = "donation";
            else if (filter.getCategory() == Category.M_RECYCLING)
                subcategory = "recycling";
            else if (filter.getCategory() == Category.E_POINT_OF_INTEREST)
                subcategory = "point_interes";
            else if (filter.getCategory() == Category.E_WARNING)
                subcategory = "warning";

            if (filter.isHappeningNow() && filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd());
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords());
                }
            } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                }
            } else {
                if (filter.getKeywords().size() == 0) {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                } else {
                    geoQuery = new GeoQuery().collection("spots")
                            .orderBy(filter.getOrderBy())
                            .whereEqualTo("groupID", filter.getGroup())
                            .whereEqualTo("subcategory", subcategory)
                            .whereNearToLocation(centerLocation, distanceForRadius, "loc")
                            .whereEqualTo("indefinite", filter.isWithoutEnd())
                            .whereArrayContains("title", filter.getKeywords())
                            .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                }
            }
        }

        DataOrException <List<DocumentSnapshot>> dataOrException= new DataOrException<>();

        geoQuery.addSnapshotListener(new GeoQueryEventListener() {
            @Override
            public void onEvent(@Nullable FirebaseFirestoreException fireStoreException, @NonNull List<DocumentSnapshot> addedAndModifiedData, @NonNull List<DocumentSnapshot> removedData) {
                if (fireStoreException == null) {
                    System.out.println("added" + addedAndModifiedData.size());
                    System.out.println("removedData" + removedData.size());
                    for (DocumentSnapshot doc : addedAndModifiedData) {

                        String spotID = doc.getString("spotID");
                        if (!(!loggedUser.getHiddenSpots().contains(spotID) && !loggedUser.getBlockedUsers().contains(doc.getString("userID")))) {
                            addedAndModifiedData.remove(doc);
                            removedData.add(doc);

                        }
                    }
                    dataOrException.setData(addedAndModifiedData);
                } else {
//error
                }
            }
        }); *//*{ firebaseFirestoreException, addedOrModifiedDataList, removedList  ->
	...
            //Do your stuff here
            //If exception occurs, firebaseFirestoreException will not be null else
            //In addedOrModifiedDataList, you will get all data that falls within distance and given query
            //as either ADDED for first time in query or has MODIFIED as was in the query from first.
            //In removedList, you will get data which is not relevent to your query or out of distance
        }*//*

       MutableLiveData<DataOrException<List<DocumentSnapshot>>> mutableLiveData= new MutableLiveData<>();
       mutableLiveData.setValue(dataOrException);
        return (LiveData<DataOrException<List<DocumentSnapshot>>>) mutableLiveData;
    }
*/






  /*  public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getPublicSpotsFromUser(SpotFilter filter, String userID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getPublicGroupsFromUser(GroupFilter filter, String userID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getFavouriteSpots(SpotFilter filter) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getMySpots(SpotFilter filter) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getGroupSpots(String groupID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getGroupUsers(String groupID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getGroupUserRequests(String groupID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getSpotInfo(String spotID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getSpotComments(String spotID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getBelongGroups(GroupFilter filter) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getAdminGroups(String userID, GroupFilter filter) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getPublicGroups(String userID, GroupFilter filter) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getChats(String userID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getMessagesFromChat(String chatID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getNotifications(String userID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getUserPublicInfo(String userID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getUserPrivateInfo(String userID) {

    }

    public LiveData<DataOrException<QueryDocumentSnapshot>> getReporteds(String userID) {

    }

    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getSpots(SpotFilter filter) {
        GeoLocation center = new GeoLocation(filter.lat, filter.lon);
        double radiusInM = filter.getDistance() * 1000;

        // Each item in 'bounds' represents a startAt/endAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
        List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
        final List<Task<QuerySnapshot>> tasks = new ArrayList<>();
//  this.distance = distance;
//        this.withoutEnd = withoutEnd;
//        this.happeningNow = happeningNow;
//        this.notStarted = notStarted;
//        this.category = category;
//        this.keywords=keywords;
        for (GeoQueryBounds b : bounds) {
            Query q = null;
            if (filter.getUser() == null) {
                if (filter.getCategory() == Category.ALL) {
                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                } else if (filter.getCategory() == Category.SOCIAL || filter.getCategory() == Category.MATERIAL || filter.getCategory() == Category.ENVIRONMENT) {
                    String category = null;
                    if (filter.getCategory() == Category.SOCIAL)
                        category = "social";
                    else if (filter.getCategory() == Category.MATERIAL)
                        category = "resource";
                    else if (filter.getCategory() == Category.ENVIRONMENT)
                        category = "environment";

                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                } else {
                    String subcategory = null;
                    if (filter.getCategory() == Category.S_GROUP_ACTIVITY)
                        subcategory = "group_activity";
                    else if (filter.getCategory() == Category.S_HELP_NEEDED)
                        subcategory = "help_needed";
                    else if (filter.getCategory() == Category.S_HUMANITARIAN_ACTIVITY)
                        subcategory = "human_activity";
                    else if (filter.getCategory() == Category.S_PARTY)
                        subcategory = "party";
                    else if (filter.getCategory() == Category.M_BUY)
                        subcategory = "buy";
                    else if (filter.getCategory() == Category.M_SELL)
                        subcategory = "sell";
                    else if (filter.getCategory() == Category.M_DONATION)
                        subcategory = "donation";
                    else if (filter.getCategory() == Category.M_RECYCLING)
                        subcategory = "recycling";
                    else if (filter.getCategory() == Category.E_POINT_OF_INTEREST)
                        subcategory = "point_interes";
                    else if (filter.getCategory() == Category.E_WARNING)
                        subcategory = "warning";

                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                }
                tasks.add(q.get());
            } else {
                if (filter.getCategory() == Category.ALL) {
                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                } else if (filter.getCategory() == Category.SOCIAL || filter.getCategory() == Category.MATERIAL || filter.getCategory() == Category.ENVIRONMENT) {
                    String category = null;
                    if (filter.getCategory() == Category.SOCIAL)
                        category = "social";
                    else if (filter.getCategory() == Category.MATERIAL)
                        category = "resource";
                    else if (filter.getCategory() == Category.ENVIRONMENT)
                        category = "environment";

                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("category", category)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                } else {
                    String subcategory = null;
                    if (filter.getCategory() == Category.S_GROUP_ACTIVITY)
                        subcategory = "group_activity";
                    else if (filter.getCategory() == Category.S_HELP_NEEDED)
                        subcategory = "help_needed";
                    else if (filter.getCategory() == Category.S_HUMANITARIAN_ACTIVITY)
                        subcategory = "human_activity";
                    else if (filter.getCategory() == Category.S_PARTY)
                        subcategory = "party";
                    else if (filter.getCategory() == Category.M_BUY)
                        subcategory = "buy";
                    else if (filter.getCategory() == Category.M_SELL)
                        subcategory = "sell";
                    else if (filter.getCategory() == Category.M_DONATION)
                        subcategory = "donation";
                    else if (filter.getCategory() == Category.M_RECYCLING)
                        subcategory = "recycling";
                    else if (filter.getCategory() == Category.E_POINT_OF_INTEREST)
                        subcategory = "point_interes";
                    else if (filter.getCategory() == Category.E_WARNING)
                        subcategory = "warning";

                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    } else if (filter.isHappeningNow() && !filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereLessThanOrEqualTo("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    } else {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("userID", filter.getUser())
                                    .whereEqualTo("group", "public")
                                    .whereEqualTo("subcategory", subcategory)
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords())
                                    .whereGreaterThan("date", (int) (Calendar.getInstance().getTimeInMillis() / 1000));
                        }
                    }
                }
                tasks.add(q.get());
            }
        }
// Collect all the query results together into a single list
        Tasks.whenAllComplete(tasks)
                .addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Task<?>>> t) {
                        ArrayList<SpotModel> matchingDocs = new ArrayList<>();

                        for (Task<QuerySnapshot> task : tasks) {
                            QuerySnapshot snap = task.getResult();
                            if (!snap.isEmpty())
                                for (DocumentSnapshot doc : snap.getDocuments()) {
                                    if (!doc.exists()) continue;
                                    double lat = doc.getDouble("lat");
                                    double lng = doc.getDouble("lng");

                                    // We have to filter out a few false positives due to GeoHash
                                    // accuracy, but most will match
                                    GeoLocation docLocation = new GeoLocation(lat, lng);
                                    double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                                    String spotID = doc.getString("spotID");
                                    if (!loggedUser.getHiddenSpots().contains(spotID) && !loggedUser.getBlockedUsers().contains(doc.getString("userID")) && distanceInM <= filter.getDistance()) {

                                        if (filter.isSeen() && filter.isNotSeen())
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isSeen() && loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isNotSeen() && !loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));

                                    }
                                }
                        }


                        //    for(DocumentSnapshot doc :matchingDocs){

                        //  }

                        _spotsLiveData.setValue(matchingDocs);

                        ArrayList<SpotModel> spots = new ArrayList();
                        Date date = new Date();


                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));

                        _spotsLiveData.setValue(spots);


                        // matchingDocs contains the results
                        // ... populate recyclerView


                        //recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        //        @Override
                        //        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        //            super.onScrolled(recyclerView, dx, dy);
                        //            int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                        //            if (lastItem == ArrayList.size() - 1) {
                        //            start=start+10;
                        //            //now hit you api
                        //            hitapi(start);
                        //
                        //        }
                        //    });
                        //    hitapi(int start){
                        //         //in your api set this start parameter value
                        //         //weher you recive the response add your new list to the bottom of new list and call adapter.notifydatasetchanged
                        //        responseArrayList.clear();
                        //        responseArrayList.addAll(flaersPOJO.flares);
                        //        ArrayList<Flare> newList = new ArrayList<Flare>(OrignalArrayList);
                        //        newList.addAll(responseArrayList);
                        //        OrignalArrayList.clear();
                        //        OrignalArrayList.addAll(newList);
                        //        adapter.notifyDataSetChanged();
                        //      //try somtinhg like this
                        //     }
                    }
                });

        return spotsLiveData;
    }



    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getSpots(SpotFilter filter) {
        GeoLocation center = new GeoLocation(filter.lat, filter.lon);
        double radiusInM = filter.getDistance() * 1000;

        // Each item in 'bounds' represents a startAt/endAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
        List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
        final List<Task<QuerySnapshot>> tasks = new ArrayList<>();

        for (GeoQueryBounds b : bounds) {
            Query q = null;
            if (filter.getUser() == null) {
                if (filter.getCategory() == Category.ALL) {
                    if (filter.isHappeningNow() && filter.isNotStarted()) {
                        if (filter.getKeywords().size() == 0) {
                            q = db.collection("spots")
                                   // .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd());
                        } else {
                            q = db.collection("spots")
                                    .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash)
                                    .whereEqualTo("indefinite", filter.isWithoutEnd())
                                    .whereArrayContains("title", filter.getKeywords());
                        }
                    }
                }


                }


        tasks.add(q.get());
        }
// Collect all the query results together into a single list
        Tasks.whenAllComplete(tasks)
                .addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Task<?>>> t) {
                        ArrayList<SpotModel> matchingDocs = new ArrayList<>();

                        for (Task<QuerySnapshot> task : tasks) {
                            QuerySnapshot snap = task.getResult();
                            if (!snap.isEmpty())
                                for (DocumentSnapshot doc : snap.getDocuments()) {
                                    if (!doc.exists()) continue;
                                    double lat = doc.getDouble("lat");
                                    double lng = doc.getDouble("lng");

                                    // We have to filter out a few false positives due to GeoHash
                                    // accuracy, but most will match
                                    GeoLocation docLocation = new GeoLocation(lat, lng);
                                    double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                                    String spotID = doc.getString("spotID");
                                    if (!loggedUser.getHiddenSpots().contains(spotID) && !loggedUser.getBlockedUsers().contains(doc.getString("userID")) && distanceInM <= filter.getDistance()) {

                                        if (filter.isSeen() && filter.isNotSeen())
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isSeen() && loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isNotSeen() && !loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));

                                    }
                                }
                        }


                        //    for(DocumentSnapshot doc :matchingDocs){

                        //  }

                        _spotsLiveData.setValue(matchingDocs);

                        ArrayList<SpotModel> spots = new ArrayList();
                        Date date = new Date();


                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));

                        _spotsLiveData.setValue(spots);


                        // matchingDocs contains the results
                        // ... populate recyclerView


                        //recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        //        @Override
                        //        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        //            super.onScrolled(recyclerView, dx, dy);
                        //            int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                        //            if (lastItem == ArrayList.size() - 1) {
                        //            start=start+10;
                        //            //now hit you api
                        //            hitapi(start);
                        //
                        //        }
                        //    });
                        //    hitapi(int start){
                        //         //in your api set this start parameter value
                        //         //weher you recive the response add your new list to the bottom of new list and call adapter.notifydatasetchanged
                        //        responseArrayList.clear();
                        //        responseArrayList.addAll(flaersPOJO.flares);
                        //        ArrayList<Flare> newList = new ArrayList<Flare>(OrignalArrayList);
                        //        newList.addAll(responseArrayList);
                        //        OrignalArrayList.clear();
                        //        OrignalArrayList.addAll(newList);
                        //        adapter.notifyDataSetChanged();
                        //      //try somtinhg like this
                        //     }
                    }
                });

        return spotsLiveData;
    }







    public LiveData<DataOrException<List<QueryDocumentSnapshot>>> getSpots(SpotFilter filter) {
        GeoLocation center = new GeoLocation(filter.lat, filter.lon);
        double radiusInM = filter.getDistance() * 1000;

        // Each item in 'bounds' represents a startAt/endAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
        List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
        final List<Task<QuerySnapshot>> tasks = new ArrayList<>();

        for (GeoQueryBounds b : bounds) {
            Query q = null;
            if (filter.getUser() == null) {



                            q = db.collection("spots")
                                    // .orderBy(filter.getOrderBy())
                                    .whereEqualTo("groupID", filter.getGroup())
                                    .startAt(b.startHash)
                                    .endAt(b.endHash);






            }else{
                q = db.collection("users")
                        // .orderBy(filter.getOrderBy())
                        .document(filter.getUser()).collection("userSpots")
                        .startAt(b.startHash)
                        .endAt(b.endHash);


            }


            tasks.add(q.get());
        }
// Collect all the query results together into a single list
        Tasks.whenAllComplete(tasks)
                .addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
                    @Override
                    public void onComplete(@NonNull Task<List<Task<?>>> t) {
                        ArrayList<SpotModel> matchingDocs = new ArrayList<>();

                        for (Task<QuerySnapshot> task : tasks) {
                            QuerySnapshot snap = task.getResult();
                            if (!snap.isEmpty())
                                for (DocumentSnapshot doc : snap.getDocuments()) {
                                    if (!doc.exists()) continue;
                                    double lat = doc.getDouble("lat");
                                    double lng = doc.getDouble("lng");

                                    // We have to filter out a few false positives due to GeoHash
                                    // accuracy, but most will match
                                    GeoLocation docLocation = new GeoLocation(lat, lng);
                                    double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                                    String spotID = doc.getString("spotID");
                                    if (!loggedUser.getHiddenSpots().contains(spotID) && !loggedUser.getBlockedUsers().contains(doc.getString("userID")) && distanceInM <= filter.getDistance()) {

                                        if (filter.isSeen() && filter.isNotSeen())
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isSeen() && loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));
                                        else if (filter.isNotSeen() && !loggedUser.getSeenSpots().contains(spotID))
                                            matchingDocs.add(doc.toObject(SpotModel.class));

                                    }
                                }
                        }


                        //    for(DocumentSnapshot doc :matchingDocs){

                        //  }

                        _spotsLiveData.setValue(matchingDocs);

                        ArrayList<SpotModel> spots = new ArrayList();
                        Date date = new Date();


                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));
                        spots.add(new SpotModel("titlea", "abc", "Groupname", date, "groupid", "spotID", "imageurl", "profilepicURL", 6, 5, 1890, true, date, Category.SOCIAL, Category.S_GROUP_ACTIVITY));

                        _spotsLiveData.setValue(spots);


                        // matchingDocs contains the results
                        // ... populate recyclerView


                        //recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                        //        @Override
                        //        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        //            super.onScrolled(recyclerView, dx, dy);
                        //            int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                        //            if (lastItem == ArrayList.size() - 1) {
                        //            start=start+10;
                        //            //now hit you api
                        //            hitapi(start);
                        //
                        //        }
                        //    });
                        //    hitapi(int start){
                        //         //in your api set this start parameter value
                        //         //weher you recive the response add your new list to the bottom of new list and call adapter.notifydatasetchanged
                        //        responseArrayList.clear();
                        //        responseArrayList.addAll(flaersPOJO.flares);
                        //        ArrayList<Flare> newList = new ArrayList<Flare>(OrignalArrayList);
                        //        newList.addAll(responseArrayList);
                        //        OrignalArrayList.clear();
                        //        OrignalArrayList.addAll(newList);
                        //        adapter.notifyDataSetChanged();
                        //      //try somtinhg like this
                        //     }
                    }
                });

        return spotsLiveData;
    }*/


    public List<SpotListLiveData> getSpotsFromGroup(SpotFilter filter) {

        // Find cities within 50km of London
        final GeoLocation center = new GeoLocation(filter.lat, filter.lon);
        final double radiusInM = filter.getDistance()  ;

        // Each item in 'bounds' represents a startAt/endAt pair. We have to issue
// a separate query for each pair. There can be up to 9 pairs of bounds
// depending on overlap, but in most cases there are 4.
        List<GeoQueryBounds> bounds = GeoFireUtils.getGeoHashQueryBounds(center, radiusInM);
      //  final List<Task<QuerySnapshot>> tasks = new ArrayList<>();
        List<SpotListLiveData> spotListLiveDatas  = new ArrayList<>();
        for(GeoQueryBounds b :bounds) {
            Query q = db.collection("cities")
                    .orderBy("geohash")
                    .whereEqualTo("groupID", filter.getGroup())
                    .startAt(b.startHash)
                    .endAt(b.endHash)
                    ;

            spotListLiveDatas.add(new SpotListLiveData(q));
        }

// Collect all the query results together into a single list
      /*  Tasks.whenAllComplete(tasks)
                .

                        addOnCompleteListener(new OnCompleteListener<List<Task<?>>>() {
                            @Override
                            public void onComplete (@NonNull Task < List < Task < ? >>> t){
                                List<DocumentSnapshot> matchingDocs = new ArrayList<>();

                                for (Task<QuerySnapshot> task : tasks) {
                                    QuerySnapshot snap = task.getResult();
                                    for (DocumentSnapshot doc : snap.getDocuments()) {
                                        double lat = doc.getDouble("lat");
                                        double lng = doc.getDouble("lng");

                                        // We have to filter out a few false positives due to GeoHash
                                        // accuracy, but most will match
                                        GeoLocation docLocation = new GeoLocation(lat, lng);
                                        double distanceInM = GeoFireUtils.getDistanceBetween(docLocation, center);
                                        if (distanceInM <= radiusInM) {
                                            matchingDocs.add(doc);
                                        }
                                    }
                                }

                                // matchingDocs contains the results
                                // ...
                            }
                        });*/
        return spotListLiveDatas;

    }

    public static SpotLiveData getSpot(String spotID){
       DocumentReference docRef = spots.document(spotID).collection("inside").document();

        return new SpotLiveData(docRef);
    }

    public static void removeSpotFromFavourites(String spotID) {
    }

    public static void addSpotFromFavourites(String spotID) {
    }

    public void deleteSpot(String spotID) {
        DocumentReference docRef = spots.document(spotID);

        docRef.delete();
    }


}
