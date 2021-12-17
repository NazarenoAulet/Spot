package com.quasartec.spot.Views.destinations.otherDestinations;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;

import com.quasartec.spot.LiveDatas.DataOrException;
import com.quasartec.spot.LiveDatas.SpotLiveData;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.CommentCardView;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.topLevelDestinations.PublicMapFragmentDirections;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.CommentModel;
import com.quasartec.spot.models.SpotModel;
import com.quasartec.spot.viewmodels.SpotViewModel;

import java.util.ArrayList;
import java.util.List;

public class SpotFragment extends Fragment {

    ImageView categoryIcon;
    TextView categoryTV;
    TextView descriptionTV;
    TextView distanceTV;
    TextView durationTV;
    TextView groupTV;
    TextView locationTV;
    ImageView ownProfilePicIV;
    TextView ownUserTV;
    Bitmap posterProfilePic;
    LinearLayout spotLin;
    SpotModel spotModel;
    ImageView spotPhotoIV;
    TextView startTimeTV;
    TextView subcategoryTV;
    TextView titleTV;
    LinearLayout topLin;
    //TextView commentsAmountTV;
    //TextView favouritesAmountTV;
    ImageView userPosterProfilePicIV;
    TextView userPosterTV;
    LinearLayout commentsLinear;

    SpotViewModel viewModel;

    SpotLiveData liveData;

    public static String spotID;
    Category category;
    Category subcategory;
    int favouritesAmount;
    int commentsAmount;
    long date;
    float lat;
    float lon;
    String title;
    String picURL;
    String userID;
    String userPicURL;
    String groupName;
    String groupID;
    boolean mine;
    boolean inAdminGroup;
    boolean inBelongGroup;
    boolean showsUser;
    boolean inPublicGroup;
    boolean inFavourites;
    long durationInMinutes;
    boolean isFavourited;
    String userName;
    String description;
    String direction;
    List<CommentModel> comments;


    public static SpotFragment newInstance(int spotID2, String category, String subcategory, int favouritesAmount, int commentsAmount, long date,
                                           float lat, float lon, String title, String picURL, String userID, String userPicURL, String groupName,
                                           String groupID, boolean mine, boolean inAdminGroup, boolean inBelongGroup, boolean showsUser, long durationInMinutes, boolean isFavourited,
                                           boolean inPublicGroup, boolean inFavourites
            /* String userName, String description, String direction*/) {//TODO check for garbage collection
        SpotFragment fragment = new SpotFragment();
        Bundle args = new Bundle();
        args.putInt("spotID", spotID2);
        args.putString("category", category);
        args.putString("subcategory", subcategory);
        args.putInt("favouritesAmount", favouritesAmount);
        args.putInt("commentsAmount", commentsAmount);
        args.putLong("date", date);
        args.putFloat("lat", lat);
        args.putFloat("lon", lon);
        args.putString("title", title);
        args.putString("picURL", picURL);
        args.putString("userIDuserID", userID);
        args.putString("userPicURL", userPicURL);
        args.putString("groupName", groupName);
        args.putString("groupID", groupID);
        args.putBoolean("mine", mine);
        args.putBoolean("inAdminGroup", inAdminGroup);
        args.putBoolean("inBelongGroup", inBelongGroup);
        args.putBoolean("showsUser", showsUser);
        args.putLong("durationInMinutes", durationInMinutes);
        args.putBoolean("isFavourited", isFavourited);
        args.putBoolean("inPublicGroup", inPublicGroup);
        args.putBoolean("inFavourites", inFavourites);

      /*  args.putString("userName", userName);
        args.putString("description", description);
        args.putString("direction", direction);
*/

        fragment.setArguments(args);

        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.spotID = getArguments().getString("spotID");
            String cat = getArguments().getString("category");
            switch (cat) {
                case "resource":
                    category = Category.MATERIAL;
                    break;
                case "social":
                    category = Category.SOCIAL;
                    break;
                case "enironment":
                    category = Category.ENVIRONMENT;
                    break;
            }

            String subcat = getArguments().getString("subcategory");
            switch (subcat) {
                case "buy":
                    subcategory = Category.M_BUY;
                    break;
                case "sell":
                    subcategory = Category.M_SELL;
                    break;
                case "donation":
                    subcategory = Category.M_DONATION;
                    break;
                case "recycling":
                    subcategory = Category.M_RECYCLING;
                    break;

                case "party":
                    subcategory = Category.S_PARTY;
                    break;
                case "group":
                    subcategory = Category.S_GROUP_ACTIVITY;
                    break;
                case "humanitarian":
                    subcategory = Category.S_HUMANITARIAN_ACTIVITY;
                    break;
                case "help":
                    subcategory = Category.S_HELP_NEEDED;
                    break;

                case "warning":
                    subcategory = Category.E_WARNING;
                    break;
                case "interest":
                    subcategory = Category.E_POINT_OF_INTEREST;
                    break;

            }

            favouritesAmount = getArguments().getInt("favouritesAmount");
            commentsAmount = getArguments().getInt("commentsAmount");
            date = getArguments().getLong("date");
            lat = getArguments().getFloat("lat");
            lon = getArguments().getFloat("lon");
            title = getArguments().getString("title");
            picURL = getArguments().getString("picURL");
            userID = getArguments().getString("userID");
            userPicURL = getArguments().getString("userPicURL");
            groupName = getArguments().getString("groupName");
            groupID = getArguments().getString("groupID");
            mine = getArguments().getBoolean("mine");
            inAdminGroup = getArguments().getBoolean("inAdminGroup");
            inBelongGroup = getArguments().getBoolean("inBelongGroup");
            showsUser = getArguments().getBoolean("showsUser");
            durationInMinutes = getArguments().getLong("durationInMinutes");
            isFavourited = getArguments().getBoolean("isFavourited");
            inFavourites = getArguments().getBoolean("inFavourites");
            inPublicGroup = getArguments().getBoolean("inPublicGroup");

           /*   userName= getArguments().getString("userName");
              description= getArguments().getString("description");
              direction= getArguments().getString("direction");*/
        }

        comments = new ArrayList<>(commentsAmount);

        viewModel = new ViewModelProvider(requireActivity()).get(SpotViewModel.class);
        liveData = viewModel.getSpotLiveData(spotID);
        liveData.observe(getViewLifecycleOwner(),
                new Observer<DataOrException>() {
                    @Override
                    public void onChanged(DataOrException dataOrException) {

                        if (dataOrException.getData() != null) {
                            spotModel=(SpotModel)dataOrException.getData();
                            if (showsUser) {
                                userName = spotModel.getUserName();
                                userPosterTV.setText(userName);
                                //  userPosterProfilePicIV.setImageBitmap();
                            }
                            groupName = spotModel.getGroupName();
                            groupID = spotModel.getGroupId();

                            description = spotModel.getDescription();
                            descriptionTV.setText(description);
                            direction = spotModel.getDirection();
                            locationTV.setText(direction);
                            comments = spotModel.getComments();
                            commentsLinear.removeAllViews();
                            commentsAmount = comments.size();
                            for (int i = 0; i < commentsAmount; i++) {
                                CommentModel comment = comments.get(i);
                                commentsLinear.addView(new CommentCardView(getContext(), mine, comment.getUserId().equals(MainActivity.getLoggedUserID()), comment.getUserId(), comment.getUserName(), comment.getUserPicURL(), comment.getText(), comment.getDate()));
                            }

                        }else{
                            //error
                        }
                    }
                });

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spot, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.spotLin = view.findViewById(R.id.spot_linear);
        this.groupTV = view.findViewById(R.id.spot_group);
        this.titleTV = view.findViewById(R.id.spot_title);
        this.topLin = view.findViewById(R.id.spot_topbar);
        this.categoryIcon = view.findViewById(R.id.categoryIcon_SpotFragment);
        this.spotPhotoIV = view.findViewById(R.id.spot_photo);
        this.userPosterProfilePicIV = view.findViewById(R.id.spot_profile_picture);
        this.ownProfilePicIV = view.findViewById(R.id.own_spot_profile_picture);
        this.subcategoryTV = view.findViewById(R.id.spot_subcategory);
        this.categoryTV = view.findViewById(R.id.spot_category);
        this.descriptionTV = view.findViewById(R.id.description_spot);
        this.distanceTV = view.findViewById(R.id.distance_spot);
        this.startTimeTV = view.findViewById(R.id.startTime_spot);
        this.durationTV = view.findViewById(R.id.duration_spot);
        this.userPosterTV = view.findViewById(R.id.spot_user);
        this.locationTV = view.findViewById(R.id.location_name_spot);
        commentsLinear = view.findViewById(R.id.spot_comments_linearlayout);

        Menu menu = ((MainActivity) getActivity()).toolbar.getMenu();
        this.groupTV.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalBelongGroupFragment(groupID)));
        this.userPosterProfilePicIV.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(userID)));
        this.userPosterTV.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(userID)));

        this.titleTV.setText(title);
        groupTV.setText(groupName);

        descriptionTV.setText(description);
        distanceTV.setText("20km");
        // startTimeTV.setText(date);
        //  durationTV.setText(durationInMinutes);

        if (category == Category.SOCIAL) {
            categoryTV.setText(R.string.social_category);
            topLin.setBackgroundResource(R.drawable.gradient_light_social);
            if (subcategory == Category.S_HELP_NEEDED) {
                subcategoryTV.setText(R.string.help_needed);
                categoryIcon.setImageResource(R.drawable.ic_baseline_comment_24);
            }//else if...{}
        } else if (category == Category.ENVIRONMENT) {
            categoryTV.setText(R.string.environment_category);

            topLin.setBackgroundResource(R.drawable.gradient_light_environment);
            if (subcategory == Category.E_POINT_OF_INTEREST) {
                subcategoryTV.setText(R.string.help_needed);

                categoryIcon.setImageResource(R.drawable.ic_baseline_comment_24);
            }//else if...{}
        } else {
            categoryTV.setText(R.string.resource_category);

            topLin.setBackgroundResource(R.drawable.gradient_light_material);
            if (subcategory == Category.M_RECYCLING) {
                subcategoryTV.setText(R.string.help_needed);

                categoryIcon.setImageResource(R.drawable.ic_baseline_comment_24);
            }//else if...{}
        }

        //spotPhotoIV.setImageResource();
        //  ownProfilePicIV.setImageResource();


        if (mine) {
            userPosterProfilePicIV.setVisibility(View.GONE);
            userPosterTV.setVisibility(View.GONE);
            menu.clear();
            ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.ownspot_menu);
            ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                int itemid = item.getItemId();
                if (itemid == R.id.menu_notifications) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                } else if (itemid == R.id.menu_ownspot_edit) {
                    NavGraphDirections.ActionGlobalModifySpotFragment action = NavGraphDirections.actionGlobalModifySpotFragment();
                    action.setSpotId(spotID);
                    ((MainActivity) MainActivity.mn.get()).navController.navigate(action);
                } else if (itemid == R.id.menu_spot_delete) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalConfirmationDialog(0));
                } else if (itemid == R.id.spot_follow) {
                    if (isFavourited) {
                        viewModel.removeSpotFromFavourites(spotID);
                        menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
                    } else {
                        viewModel.addSpotFromFavourites(spotID);
                        menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_24);
                    }
                }
                return false;
            });
        } else if (inAdminGroup) {
            userPosterProfilePicIV.setVisibility(View.VISIBLE);
            userPosterTV.setVisibility(View.VISIBLE);
            //  userPosterProfilePicIV.setImageBitmap();
            userPosterTV.setText(userName);
            menu.clear();
            ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.inside_mygroup_spot_menu_toolbar);
            ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                int itemid = item.getItemId();
                if (itemid == R.id.menu_notifications) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                } else if (itemid == R.id.menu_spot_delete) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(0));
                } else if (itemid == R.id.menu_spot_Report) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(4));
                } else if (itemid == R.id.spot_follow) {
                    if (isFavourited) {
                        viewModel.removeSpotFromFavourites(spotID);
                        menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
                    } else {
                        viewModel.addSpotFromFavourites(spotID);
                        menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_24);
                    }
                }
                return false;
            });
        } else {
            userPosterProfilePicIV.setVisibility(View.VISIBLE);
            userPosterTV.setVisibility(View.VISIBLE);
            //  userPosterProfilePicIV.setImageBitmap();
            userPosterTV.setText(userName);
            menu.clear();
            ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.spot_toolbar_menu);
            ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
                        int itemid = item.getItemId();
                        if (itemid == R.id.menu_notifications) {
                            //   MyGroupFragmentDirections.ActionMyGroupFragmentToCreateGroupFragment navAction = MyGroupFragmentDirections.actionMyGroupFragmentToCreateGroupFragment();
                            //                navAction.setGroupId(1);
                            //                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) navAction);


                            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                            return false;
                        } else if (itemid == R.id.menu_spot_ShowInMap) {

                            if (inPublicGroup) {
                                NavGraphDirections.ActionGlobalPublicMapFragment publicAction = NavGraphDirections.actionGlobalPublicMapFragment();
                                publicAction.setSpotIDToShowInMap(spotID);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(publicAction);
                            } else if (inFavourites) {
                                NavGraphDirections.ActionGlobalFavouritesFragment favAction = NavGraphDirections.actionGlobalFavouritesFragment();
                                favAction.setSpotIDToShowInMap(spotID);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(favAction);
                            } else if (inBelongGroup) {
                                NavGraphDirections.ActionGlobalBelongGroupFragment belongAction = NavGraphDirections.actionGlobalBelongGroupFragment(groupID);
                                belongAction.setSpotIDToShowInMap(spotID);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(belongAction);
                            } else if (inAdminGroup) {
                                NavGraphDirections.ActionGlobalMyGroupFragment adminAction = NavGraphDirections.actionGlobalMyGroupFragment(groupID);
                                adminAction.setSpotIDToShowInMap(spotID);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(adminAction);
                            } else {
                                NavGraphDirections.ActionGlobalParticularGroupFragment partAction = NavGraphDirections.actionGlobalParticularGroupFragment(groupID);
                                partAction.setSpotIDToShowInMap(spotID);
                                ((MainActivity) MainActivity.mn.get()).navController.navigate(partAction);
                            }
                            return false;
                        } else {
                            if (itemid == R.id.menu_spot_Hide) {
                                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(10));
                                return false;
                            } else if (itemid == R.id.menu_spot_Report) {
                                return false;
                            } else if (itemid == R.id.spot_follow) {
                                if (isFavourited) {
                                    viewModel.removeSpotFromFavourites(spotID);
                                    menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_border_24);
                                } else {
                                    viewModel.addSpotFromFavourites(spotID);
                                    menu.getItem(1).setIcon(R.drawable.ic_baseline_favorite_24);
                                }
                            } else {
                                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(4));
                            }
                        }
                        return false;

                    }
            );
            //     this.spotLin.addView(new CommentCardView(getContext(), true, false));
            //this.spotLin.addView(new CommentCardView(getContext(), false, true));
            //.2this.spotLin.addView(new CommentCardView(getContext(), false, false));
        }


    }

    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).toolbar.setTitle((CharSequence) this.spotModel.title);
    }
}
