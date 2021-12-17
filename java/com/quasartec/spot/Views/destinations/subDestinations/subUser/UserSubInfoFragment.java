package com.quasartec.spot.Views.destinations.subDestinations.subUser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.quasartec.spot.R;

public class UserSubInfoFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    TextView numberSpots;
    ImageView profilePic;
    TextView userName;

    public static UserSubInfoFragment newInstance(String param1, String param2) {
        UserSubInfoFragment fragment = new UserSubInfoFragment();
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
        return inflater.inflate(R.layout.fragment_user_sub_info, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.userName = (TextView) view.findViewById(R.id.userNameTV);
        this.numberSpots = (TextView) view.findViewById(R.id.user_spotsquant);
        this.profilePic = (ImageView) view.findViewById(R.id.userprofilePicture);
    }
}
