package com.quasartec.spot.Views.destinations.otherDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.google.android.material.textfield.TextInputLayout;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotModel;

public class ModifySpotFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CheckBox showFollowNotifsCB;
    CheckBox showcommentsNotifsCB;

    ImageView cameraIV;
    ImageView cameraMiniIV;
    EditText catET;
    TextInputLayout catTIL;
    EditText descrET;
    TextInputLayout descrTIL;
    Button doneBT;
    EditText durDaysET;
    TextInputLayout durDaysTIL;
    EditText durHoursET;
    TextInputLayout durHoursTIL;
    EditText durMinsET;
    TextInputLayout durMinsTIL;
    CheckBox happeningNowCB;

    CheckBox indefiniteCB;
    EditText locET;
    TextInputLayout locTIL;
    private String mParam1;
    private String mParam2;
    EditText modTitleET;
    TextInputLayout modTitleTIL;
    FrameLayout selectedPhotoFL;
    CheckBox showUserCB;
    ImageView spotPhotoIV;
    EditText startDayET;
    TextInputLayout startDayTIL;
    EditText startHourET;
    TextInputLayout startHourTIL;
    EditText startMinET;
    TextInputLayout startMinTIL;
    EditText startMothET;
    TextInputLayout startMothTIL;
    EditText subcatET;
    TextInputLayout subcatTIL;

    public static ModifySpotFragment newInstance(String param1, String param2) {
        ModifySpotFragment fragment = new ModifySpotFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.mParam1 = getArguments().getString(ARG_PARAM1);
            this.mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_modify_spot, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        happeningNowCB=view.findViewById(R.id.checkbox_now_modspot);

        showcommentsNotifsCB=view.findViewById(R.id.checkbox_showCommentsNotifs_modspot);
        showFollowNotifsCB=view.findViewById(R.id.checkbox_showFollowsNotifs_modspot);

        this.catTIL = (TextInputLayout) view.findViewById(R.id.category_til);
        this.subcatTIL = (TextInputLayout) view.findViewById(R.id.subcategory_til);
        this.modTitleTIL = (TextInputLayout) view.findViewById(R.id.edit_title_til);
        this.descrTIL = (TextInputLayout) view.findViewById(R.id.modedit_descr_til);
        this.locTIL = (TextInputLayout) view.findViewById(R.id.edit_location_til);
        this.startDayTIL = (TextInputLayout) view.findViewById(R.id.edit_startDay_til);
        this.startMothTIL = (TextInputLayout) view.findViewById(R.id.edit_startMonth_til);
        this.startHourTIL = (TextInputLayout) view.findViewById(R.id.edit_startHours_til);
        this.startMinTIL = (TextInputLayout) view.findViewById(R.id.edit_startMins_til);
        this.durDaysTIL = (TextInputLayout) view.findViewById(R.id.edit_days_til);
        this.durHoursTIL = (TextInputLayout) view.findViewById(R.id.edit_hours_til);
        this.durMinsTIL = (TextInputLayout) view.findViewById(R.id.edit_mins_til);
        this.catET = (EditText) view.findViewById(R.id.category_et);
        this.subcatET = (EditText) view.findViewById(R.id.subcategory_et);
        this.modTitleET = (EditText) view.findViewById(R.id.modspot_title_et);
        this.descrET = (EditText) view.findViewById(R.id.modspot_descr_et);
        this.locET = (EditText) view.findViewById(R.id.modspot_location_et);
        this.startDayET = (EditText) view.findViewById(R.id.modspot_startDay_et);
        this.startMothET = (EditText) view.findViewById(R.id.startmonth_mod_et);
        this.startHourET = (EditText) view.findViewById(R.id.modspot_startHour_et);
        this.startMinET = (EditText) view.findViewById(R.id.modspot_startMins_et);
        this.durDaysET = (EditText) view.findViewById(R.id.modspot_days_et);
        this.durHoursET = (EditText) view.findViewById(R.id.edit_hours_et);
        this.durMinsET = (EditText) view.findViewById(R.id.modspot_mins_et);
        this.indefiniteCB = (CheckBox) view.findViewById(R.id.checkbox_indefinite_modspot);
        this.showUserCB = (CheckBox) view.findViewById(R.id.checkbox_showuser_modspot);
        this.cameraIV = (ImageView) view.findViewById(R.id.selectePhoto_modspot);
        this.doneBT = (Button) view.findViewById(R.id.createspotBtn);
        this.spotPhotoIV = (ImageView) view.findViewById(R.id.spotPhoto_modspot);
        this.cameraMiniIV = (ImageView) view.findViewById(R.id.selectedPhoto_modspotmini);
        this.selectedPhotoFL = (FrameLayout) view.findViewById(R.id.selectedPhotoFrame);
        this.startMothET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(90)));
        this.startDayET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(91)));
        this.startMinET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(93)));
        this.startHourET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(92)));
        this.durMinsET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(93)));
        this.durHoursET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(92)));
        if (getArguments().getInt("spotId") != -1) {
            ((MainActivity) getActivity()).toolbar.setTitle((int) R.string.modify_spot);
            SpotModel spotModel = MainActivity.spotsMap.get(Integer.valueOf(SpotFragmentArgs.fromBundle(getArguments()).getSpotId()));
            this.catTIL.setActivated(true);
            this.subcatET.setActivated(true);
            if (spotModel.category == Category.SOCIAL) {
                this.catET.setText(getResources().getString(R.string.social_category));
                this.subcatET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(10)));
                if (spotModel.subCategory == Category.S_GROUP_ACTIVITY) {
                    this.subcatET.setText(getResources().getString(R.string.group_activity));
                } else if (spotModel.subCategory == Category.S_HUMANITARIAN_ACTIVITY) {
                    this.subcatET.setText(getResources().getString(R.string.humanitarian_activity));
                } else if (spotModel.subCategory == Category.S_PARTY) {
                    this.subcatET.setText(getResources().getString(R.string.party));
                }
            } else if (spotModel.category == Category.MATERIAL) {
                this.catET.setText(getResources().getString(R.string.resource_category));
                this.subcatET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(20)));
                if (spotModel.subCategory == Category.M_BUY) {
                    this.subcatET.setText(getResources().getString(R.string.buying));
                } else if (spotModel.subCategory == Category.M_SELL) {
                    this.subcatET.setText(getResources().getString(R.string.selling));
                } else if (spotModel.subCategory == Category.M_DONATION) {
                    this.subcatET.setText(getResources().getString(R.string.donation));
                } else if (spotModel.subCategory == Category.M_RECYCLING) {
                    this.subcatET.setText(getResources().getString(R.string.recycling));
                }
            } else if (spotModel.category == Category.ENVIRONMENT) {
                this.catET.setText(getResources().getString(R.string.environment_category));
                this.subcatET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(30)));
                if (spotModel.subCategory == Category.E_POINT_OF_INTEREST) {
                    this.subcatET.setText(getResources().getString(R.string.point_of_interest));
                } else if (spotModel.subCategory == Category.E_WARNING) {
                    this.subcatET.setText(getResources().getString(R.string.warning));
                }
            }
            this.catET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(50)));
            this.modTitleTIL.setActivated(true);
            this.modTitleET.setText(spotModel.title);
            this.descrTIL.setActivated(true);
            this.descrET.setText("descriptionlalalalala");
            this.locTIL.setActivated(true);
            this.locET.setText("Camarones 2640 b");
            this.startDayTIL.setActivated(true);
            this.startMothTIL.setActivated(true);
            this.startHourTIL.setActivated(true);
            this.startMinTIL.setActivated(true);
            this.happeningNowCB.setChecked(false);

            this.indefiniteCB.setChecked(false);
            this.durDaysTIL.setActivated(true);
            this.durHoursTIL.setActivated(true);
            this.durMinsTIL.setActivated(true);
            this.durDaysET.setText("5");
            this.durHoursET.setText("1");
            this.durMinsET.setText("0");
            this.selectedPhotoFL.setVisibility(View.VISIBLE);
            this.cameraIV.setVisibility(View.GONE);
        } else {
            ((MainActivity) getActivity()).toolbar.setTitle((CharSequence) getResources().getString(R.string.create_spot));
            this.catET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(50)));
            this.subcatET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(30)));
            this.selectedPhotoFL.setVisibility(View.GONE);
            this.cameraIV.setVisibility(View.VISIBLE);
            this.showUserCB.setChecked(true);
            this.happeningNowCB.setChecked(true);
            startHourTIL.setVisibility(View.GONE);
            startDayTIL.setVisibility(View.GONE);
            startMothTIL.setVisibility(View.GONE);
            startMinTIL.setVisibility(View.GONE);
            durDaysTIL.setVisibility(View.GONE);
            durHoursTIL.setVisibility(View.GONE);
            durMinsTIL.setVisibility(View.GONE);

        }
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.onlynotifs_toolbar_menu);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() != R.id.menu_notifications) {
                return false;
            }
            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
            return false;            });

    happeningNowCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if(isChecked){
            startHourTIL.setVisibility(View.GONE);
            startDayTIL.setVisibility(View.GONE);
            startMothTIL.setVisibility(View.GONE);
            startMinTIL.setVisibility(View.GONE);
        }else{
            startHourTIL.setVisibility(View.VISIBLE);
            startDayTIL.setVisibility(View.VISIBLE);
            startMothTIL.setVisibility(View.VISIBLE);
            startMinTIL.setVisibility(View.VISIBLE);
        }
    });

        indefiniteCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                durDaysTIL.setVisibility(View.GONE);
                durHoursTIL.setVisibility(View.GONE);
                durMinsTIL.setVisibility(View.GONE);

            }else{
                durDaysTIL.setVisibility(View.VISIBLE);
                durHoursTIL.setVisibility(View.VISIBLE);
                durMinsTIL.setVisibility(View.VISIBLE);
            }
        });
    }


}
