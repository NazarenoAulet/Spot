package com.quasartec.spot.Views.destinations.otherDestinations;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;

public class CreateGroupFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static CreateGroupFragment newInstance(String param1, String param2) {
        CreateGroupFragment fragment = new CreateGroupFragment();
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
        return inflater.inflate(R.layout.fragment_create_group, container, false);
    }

    public void onViewCreated(View view2, Bundle savedInstanceState) {

        super.onViewCreated(view2, savedInstanceState);
        EditText titleET = (EditText) view2.findViewById(R.id.title_createGroup_et);
        EditText descrET = (EditText) view2.findViewById(R.id.descr_createGroup_et);
        EditText topicET = (EditText) view2.findViewById(R.id.category_createGroup_et);
        FrameLayout selectedPicFL = (FrameLayout) view2.findViewById(R.id.selectedPhotoFrame);
        ImageView imageView = (ImageView) view2.findViewById(R.id.spotPhoto_createGroup);
        ImageView imageView2 = (ImageView) view2.findViewById(R.id.selectedPhoto_createGroupmini);
        ImageView nonSelectedPicCameraIV = (ImageView) view2.findViewById(R.id.selectePhoto_createGroup);
        Button button = (Button) view2.findViewById(R.id.acceptCreateGroupBtn);
        TextView publicInfoTV = (TextView) view2.findViewById(R.id.createGroup_publicTVdescr);
        TextView publicTV = (TextView) view2.findViewById(R.id.createGroup_publicTV);
        TextView lockedInfoTV = (TextView) view2.findViewById(R.id.createGroup_lockedTVdescr);
        TextView lockedTV = (TextView) view2.findViewById(R.id.createGroup_lockedTV);
        TextView shownInfoTV = (TextView) view2.findViewById(R.id.createGroup_shownTVdescr);
        TextView shownTV = (TextView) view2.findViewById(R.id.createGroup_shownTV);

        RadioGroup publicRG = view2.findViewById(R.id.createGroup_publicRG);
        RadioGroup lockedRG = view2.findViewById(R.id.createGroup_lockedRG);
        RadioGroup shownRG = view2.findViewById(R.id.createGroup_shownRG);

        CheckBox acceptNewMembersCB= view2.findViewById(R.id.createGroup_acceptMembersCB);
        CheckBox showtNewMembersRequestsCB= view2.findViewById(R.id.createGroup_showRequestsCB);
        CheckBox showNewSpotsCB= view2.findViewById(R.id.createGroup_showSpotsNotsCB);


        publicRG.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.createGroup_publicRB) {
                publicTV.setText(getString(R.string.publics));
                publicInfoTV.setText(getString(R.string.public_descr));
                publicTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_public_24,0,0,0);
                acceptNewMembersCB.setVisibility(VISIBLE);
                showtNewMembersRequestsCB.setVisibility(VISIBLE);


            }else{
                publicTV.setText(getString(R.string.privates));
                publicTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_public_off_24,0,0,0);

                publicInfoTV.setText(getString(R.string.privat_descr));
                acceptNewMembersCB.setVisibility(GONE);
                showtNewMembersRequestsCB.setVisibility(GONE);
            }
        });

        lockedRG.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.createGroup_lockedRB) {
                lockedTV.setText(getString(R.string.locked));
                lockedInfoTV.setText(getString(R.string.locked_descr));
                showNewSpotsCB.setVisibility(GONE);
                lockedTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_24,0,0,0);


            }else{
                lockedTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_lock_open_24,0,0,0);

                lockedTV.setText(getString(R.string.unlocked));
                lockedInfoTV.setText(getString(R.string.unlocked_descr));
                showNewSpotsCB.setVisibility(VISIBLE);
            }
        });

        shownRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.createGroup_shownRB) {
                    shownTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_remove_red_eye_24,0,0,0);

                    shownTV.setText(getString(R.string.shown));
                    shownInfoTV.setText(getString(R.string.shown_descr));

                }else{
                    shownTV.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_visibility_off_24,0,0,0);

                    shownTV.setText(getString(R.string.hidden));
                    shownInfoTV.setText(getString(R.string.hidden_descr));
                }
            }
        });
        if (getArguments().getInt("groupId") != -1) {
            ((MainActivity) getActivity()).toolbar.setTitle((int) R.string.modifyGroup);
            titleET.setText("lalala");
            descrET.setText("lalala");
            topicET.setText("Social");
            topicET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(0)));
            selectedPicFL.setVisibility(VISIBLE);
            nonSelectedPicCameraIV.setVisibility(GONE);
            publicTV.setText(getResources().getString(R.string.publics));
            publicInfoTV.setText(getResources().getString(R.string.public_descr));
            lockedTV.setText(getResources().getString(R.string.locked));
            lockedInfoTV.setText(getResources().getString(R.string.locked_descr));
            shownTV.setText(getResources().getString(R.string.shown));
            shownInfoTV.setText(getResources().getString(R.string.shown_descr));
        } else {
            ((MainActivity) getActivity()).toolbar.setTitle((int) R.string.create_group);
            topicET.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalListDialog(0)));
            selectedPicFL.setVisibility(GONE);
            nonSelectedPicCameraIV.setVisibility(VISIBLE);
        }
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.onlynotifs_toolbar_menu);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() != R.id.menu_notifications) {
                return false;
            }
            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
            return false;
        });
    }


}
