package com.quasartec.spot.Views.destinations.subDestinations.subGroups.subBelongGroup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;

import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;

public class BelongGroupSubInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public static BelongGroupSubInfoFragment newInstance(String param1, String param2) {
        BelongGroupSubInfoFragment fragment = new BelongGroupSubInfoFragment();
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
        return inflater.inflate(R.layout.fragment_belong_group_sub_info, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        (  view.findViewById(R.id.belongGroupAdmin_profile_picture)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                (MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalUserFragment(1));
            }
        });
        ( view.findViewById(R.id.belongGroupAdmin_user)).setOnClickListener(new View.OnClickListener() {
            @Override
            public final void onClick(View view) {
                ( MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalUserFragment(1));
            }
        });
    }
}
