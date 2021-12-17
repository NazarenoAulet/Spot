package com.quasartec.spot.Views;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.quasartec.spot.R;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotModel;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    public static String AreYouSureAbandonGroup;
    public static String AreYouSureClearChat;
    public static String AreYouSureDeleteChat;
    public static String AreYouSureDeleteComment;
    public static String AreYouSureHideGroup;
    public static String AreYouSureHideNotif;
    public static String AreYouSureHideSpot;
    public static String AreYouSureRemoveUser;
    public static String AreYouSureYouWantToBlockThisUser;
    public static String AreYouSureYouWantToDeleteThisGroup;
    public static String AreYouSureYouWantToDeleteThisSpot;
    public static String StateTheReasonsYouWantToReportThisGroup;
    public static String StateTheReasonsYouWantToReportThisSpot;
    public static String StateTheReasonsYouWantToReportThisUser;
    public static String and;
    public static String createSpot;
    public static String createdbyme;
    public static int density;
    public static String groups;
    public static String hasBeenReported;
    public static String hasBeenReportedBy;
    public static String hasBeenUpdated;
    public static String hasCommentedYourSpot;
    public static String hasCreatedSpotIn;
    public static String hasRequestedToJoin;

    /* renamed from: in */
    public static String in;
    public static String info;

    /* renamed from: km */
    public static String km;
    public static boolean loginPhase;
    public static String map;
    public static String memberRequests;
    public static String members;

    /* renamed from: mn */
    public static WeakReference<MainActivity> mn;
    public static String modifySpot;
    public static String mygroups;
    public static String myspots;
    public static String people;
    public static String peopleHaveCommentedYourSpot;
    public static String peopleHaveRequestedToJoin;
    public static String publicMap;
    public static String publicgroups;
    public static String spots;
    public static HashMap<Integer, SpotModel> spotsMap;
    public static String theFollowedSpot;
    public static String theSpot;
    AppBarConfiguration appBarConfiguration;
    public DrawerLayout drawerLayout;
    public InputMethodManager imm;
    public NavController navController;
    public NavigationView navView;
    public Toolbar toolbar;
    static String loggedUserID;
    static String loggedUserName;
    static String loggedUserPicURL;
   static  public String getLoggedUserID(){
    return loggedUserID;
}
    public static void updateAct(MainActivity act) {
        mn = new WeakReference<>(act);
    }

    /* access modifiers changed from: protected */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        updateAct(this);

        map = getResources().getString(R.string.map);
        spots = getResources().getString(R.string.spots_viewpager);
        myspots = getResources().getString(R.string.my_spots);
        publicMap = getResources().getString(R.string.public_map);
        createSpot = getResources().getString(R.string.create_spot);
        groups = getResources().getString(R.string.groups_viewpager);
        mygroups = getResources().getString(R.string.mygroups);
        createdbyme = getResources().getString(R.string.createdbyme);
        publicgroups = getResources().getString(R.string.publicgroups);
        info = getResources().getString(R.string.info);
        members = getResources().getString(R.string.members_viewPager);
        memberRequests = getResources().getString(R.string.memberRequests);
        hasCommentedYourSpot = getResources().getString(R.string.hascommented);
        peopleHaveCommentedYourSpot = getResources().getString(R.string.peoplehavecommentedSpot);
        hasCreatedSpotIn = getResources().getString(R.string.hasCreatedSpotIn);
        theFollowedSpot = getResources().getString(R.string.theFollowedSpot);
        hasBeenUpdated = getResources().getString(R.string.hasBeenUpdated);
        theSpot = getResources().getString(R.string.theSpot);
        in = getResources().getString(R.string.in);
        hasRequestedToJoin = getResources().getString(R.string.hasRequestedToJoin);
        and = getResources().getString(R.string.and);
        peopleHaveRequestedToJoin = getResources().getString(R.string.peopleHaveRequestedToJoin);
        hasBeenReported = getResources().getString(R.string.hasBeenReported);
        hasBeenReportedBy = getResources().getString(R.string.hasBeenReportedBy);
        people = getResources().getString(R.string.people);
        km = getResources().getString(R.string.km);
        modifySpot = getResources().getString(R.string.modifySpot);
        AreYouSureYouWantToDeleteThisGroup = getResources().getString(R.string.AreYouSureYouWantToDeleteThisGroup);
        AreYouSureYouWantToDeleteThisSpot = getResources().getString(R.string.AreYouSureYouWantToDeleteThisSpot);
        StateTheReasonsYouWantToReportThisGroup = getResources().getString(R.string.StateTheReasonsYouWantToReportThisGroup);
        StateTheReasonsYouWantToReportThisUser = getResources().getString(R.string.StateTheReasonsYouWantToReportThisUser);
        StateTheReasonsYouWantToReportThisSpot = getResources().getString(R.string.StateTheReasonsYouWantToReportThisSpot);
        AreYouSureYouWantToBlockThisUser = getResources().getString(R.string.AreYouSureYouWantToBlockThisUser);
        AreYouSureRemoveUser = getResources().getString(R.string.AreYouSureRemoveUser);
        AreYouSureAbandonGroup = getResources().getString(R.string.AreYouSureAbandonGroup);
        AreYouSureClearChat = getResources().getString(R.string.AreYouSureClearChat);
        AreYouSureDeleteChat = getResources().getString(R.string.AreYouSureDeleteChat);
        AreYouSureHideSpot = getResources().getString(R.string.AreYouSureHideSpot);
        AreYouSureHideGroup = getResources().getString(R.string.AreYouSureHideGroup);
        AreYouSureHideNotif = getResources().getString(R.string.AreYouSureHideNotif);
        AreYouSureDeleteComment = getResources().getString(R.string.AreYouSureDeleteComment);
        spotsMap = new HashMap<>();

        setSupportActionBar(this.toolbar);
        this.toolbar.setBackgroundResource(R.drawable.main_gradient);
        this.navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        density = (int) getResources().getDisplayMetrics().density;
        imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        HashSet<Integer> topLevelDestinations = new HashSet<>(6);
        topLevelDestinations.add(Integer.valueOf(R.id.publicMapFragment));
        topLevelDestinations.add(Integer.valueOf(R.id.ownProfileFragment));
        topLevelDestinations.add(Integer.valueOf(R.id.groupsFragment2));
        topLevelDestinations.add(Integer.valueOf(R.id.favouritesFragment));
        topLevelDestinations.add(Integer.valueOf(R.id.messagesFragment2));
        topLevelDestinations.add(Integer.valueOf(R.id.mySpotsFragment2));
        this.appBarConfiguration = new AppBarConfiguration.Builder((Set<Integer>) topLevelDestinations).setOpenableLayout(this.drawerLayout).build();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.navView = navigationView;
        NavigationUI.setupWithNavController(navigationView, this.navController);
        this.navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            public void onDestinationChanged(NavController controller, NavDestination destination, Bundle arguments) {
                MainActivity.this.hideKeyboard();
            }
        });
        this.drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            public void onDrawerSlide(View drawerView, float slideOffset) {
            }

            public void onDrawerOpened(View drawerView) {
                MainActivity.this.hideKeyboard();
            }

            public void onDrawerClosed(View drawerView) {
            }

            public void onDrawerStateChanged(int newState) {
                MainActivity.this.hideKeyboard();
            }
        });
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) this, this.navController, this.appBarConfiguration);
        this.navView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MainActivity.this.navController.navigate((int) R.id.ownProfileFragment);
                MainActivity.this.drawerLayout.close();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(this.navController, this.appBarConfiguration);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_toolbar_menu, menu);
        ((SearchView) menu.findItem(R.id.menu_search).getActionView()).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            public boolean onQueryTextChange(String newText) {
                TextUtils.isEmpty(newText);
                return true;
            }
        });
        return true;
    }

    public void hideKeyboard() {
        this.imm.hideSoftInputFromWindow(this.drawerLayout.getWindowToken(), 0);
    }
}
