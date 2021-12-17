package com.quasartec.spot.Views.destinations.topLevelDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;

public class OwnProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static OwnProfileFragment newInstance(String param1, String param2) {
        OwnProfileFragment fragment = new OwnProfileFragment();
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
        return inflater.inflate(R.layout.fragment_own_profile, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatButton) view.findViewById(R.id.logoutButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Logout
            }
        });
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.toolbar_menu_profilevariant);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            public final boolean onMenuItemClick(MenuItem menuItem) {

                int id = menuItem.getItemId();
                if (id == R.id.menu_notifications) {
                    (MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                    return false;
                } else if (id == R.id.menu_toolbar_profile_edit) {

                    ((MainActivity) getActivity()).navController.navigate(R.id.action_ownProfileFragment_to_ownProfileEditorFragment);
                    return false;
                }
                return false;
            }
        });
    }


}
