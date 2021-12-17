package com.quasartec.spot.Views.destinations.otherDestinations;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.textfield.TextInputLayout;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;

public class OwnProfileEditorFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public ImageView choosePictureIV;
    public TextInputLayout cityInputLayout;
    public TextView cityTV;
    public ImageButton descrBT;
    public EditText descrET;
    public TextInputLayout descrInputLayout;
    public TextView descrTV;
    public Button doneBT;
    public ImageButton editCityBT;
    public ImageButton editUserBT;
    private String mParam1;
    private String mParam2;
    public EditText newCityET;
    public EditText newUserNameET;
    public ImageView profileIV;
    TextView.OnEditorActionListener textActionListener;
    public TextInputLayout userInputLayout;
    public TextView userNameTV;

    public static OwnProfileEditorFragment newInstance(String param1, String param2) {
        OwnProfileEditorFragment fragment = new OwnProfileEditorFragment();
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
        return inflater.inflate(R.layout.fragment_own_profile_editor, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.onlynotifs_toolbar_menu);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() != R.id.menu_notifications) {
                return false;
            }
            ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
            return false;
        });
        this.profileIV = (ImageView) view.findViewById(R.id.ownprofilePictureEditor);
        this.choosePictureIV = (ImageView) view.findViewById(R.id.profileeditor_selectnewPhoto);
        this.newUserNameET = (EditText) view.findViewById(R.id.newuser_name_et);
        this.newCityET = (EditText) view.findViewById(R.id.city_ET);
        this.userNameTV = (TextView) view.findViewById(R.id.username_holdertv);
        this.cityTV = (TextView) view.findViewById(R.id.cityname_textview_holder);
        this.userInputLayout = (TextInputLayout) view.findViewById(R.id.userinputlayout);
        this.cityInputLayout = (TextInputLayout) view.findViewById(R.id.city_inputlayout);
        this.editUserBT = (ImageButton) view.findViewById(R.id.edituserButton);
        this.editCityBT = (ImageButton) view.findViewById(R.id.editcityinput);
        this.doneBT = (Button) view.findViewById(R.id.acceptEditProfileBtn);
        this.descrBT = (ImageButton) view.findViewById(R.id.editdescrButton);
        this.descrET = (EditText) view.findViewById(R.id.newuser_descr_et);
        this.descrInputLayout = (TextInputLayout) view.findViewById(R.id.descrinputlayout);
        this.descrTV = (TextView) view.findViewById(R.id.descr_holdertv);
        this.descrET.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == 6) {
                String text = textView.getText().toString();
                if (!TextUtils.isEmpty(text) && text.length() >= 3) {
                    descrTV.setText(text);
                }
            }
          descrInputLayout.setVisibility(View.INVISIBLE);
            descrET.setVisibility(View.INVISIBLE);
           descrBT.setVisibility(View.VISIBLE);
            descrTV.setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });
        this.descrBT.setOnClickListener(view13 -> {
             descrInputLayout.setVisibility(View.VISIBLE);
            descrET.setVisibility(View.VISIBLE);
            descrBT.setVisibility(View.INVISIBLE);
            descrTV.setVisibility(View.INVISIBLE);
           descrET.setText( descrTV.getText());
            ((MainActivity) getActivity()).imm.showSoftInput( descrET, 0);
        });
        this.editUserBT.setOnClickListener(view12 -> {
            userInputLayout.setVisibility(View.VISIBLE);
             newUserNameET.setVisibility(View.VISIBLE);
            editUserBT.setVisibility(View.INVISIBLE);
            userNameTV.setVisibility(View.INVISIBLE);
            newUserNameET.setText( userNameTV.getText());
            ((MainActivity) getActivity()).imm.showSoftInput( newUserNameET, 0);
        });
        this.editCityBT.setOnClickListener(view1 -> {
            cityInputLayout.setVisibility(View.VISIBLE);
           newCityET.setVisibility(View.VISIBLE);
           editCityBT.setVisibility(View.INVISIBLE);
            cityTV.setVisibility(View.INVISIBLE);
            newCityET.setText( cityTV.getText());
            ((MainActivity) getActivity()).imm.showSoftInput( newCityET, 0);
        });
        this.newCityET.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == 6) {
                String text = textView.getText().toString();
                if (!TextUtils.isEmpty(text) && text.length() >= 3) {
                   cityTV.setText(text);
                }
            }
          cityInputLayout.setVisibility(View.INVISIBLE);
            newCityET.setVisibility(View.INVISIBLE);
            editCityBT.setVisibility(View.VISIBLE);
            cityTV.setVisibility(View.INVISIBLE);
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });
        this.newUserNameET.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == 6) {
                String text = textView.getText().toString();
                if (!TextUtils.isEmpty(text) && text.length() >= 3) {
                   userNameTV.setText(text);
                }
            }
            userInputLayout.setVisibility(View.INVISIBLE);
            newUserNameET.setVisibility(View.INVISIBLE);
         editUserBT.setVisibility(View.VISIBLE);
            userNameTV.setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).hideKeyboard();
            return false;
        });
        this.doneBT.setOnClickListener(v -> OwnProfileEditorFragment.this.getActivity().onBackPressed());
    }






    public void onPause() {
        super.onPause();
        ((MainActivity) getActivity()).hideKeyboard();
    }

    public void resetInput() {
        this.userInputLayout.setVisibility(View.INVISIBLE);
        this.newUserNameET.setVisibility(View.INVISIBLE);
        this.editUserBT.setVisibility(View.VISIBLE);
        this.userNameTV.setVisibility(View.VISIBLE);
        this.cityInputLayout.setVisibility(View.INVISIBLE);
        this.newCityET.setVisibility(View.INVISIBLE);
        this.editCityBT.setVisibility(View.VISIBLE);
        this.cityTV.setVisibility(View.VISIBLE);
    }
}
