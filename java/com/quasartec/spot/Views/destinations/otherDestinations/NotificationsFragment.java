package com.quasartec.spot.Views.destinations.otherDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.NotificationsAdapter;
import com.quasartec.spot.models.NotificationModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    List<NotificationModel> notifs;
    RecyclerView recyclerView;

    public static NotificationsFragment newInstance(String param1, String param2) {
        NotificationsFragment fragment = new NotificationsFragment();
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
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.notifs = new ArrayList();
        new Date();
        this.notifs.add(new NotificationModel("User123", "GroupX", true, 0, 7));
        this.notifs.add(new NotificationModel("User123", "GroupX", true, 1, ""));
        this.notifs.add(new NotificationModel("", "", false, 2, "Dog found"));
        this.notifs.add(new NotificationModel("", "GroupX", false, 3, "Metals found"));
        this.notifs.add(new NotificationModel("User123", "GroupX", true, 4, 5));
        this.notifs.add(new NotificationModel("", "GroupX", false, 5, "Reusablematerial2"));
        NotificationsAdapter listAdapter = new NotificationsAdapter(this.notifs, getContext());
        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.notificationRecyclerView);
        this.recyclerView = recyclerView2;
        recyclerView2.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setAdapter(listAdapter);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
    }
}
