package com.quasartec.spot.Views.destinations.otherDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.MessagesAdapter;
import com.quasartec.spot.models.MessageModel;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    List<MessageModel> messages;

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
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
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.chat_username)).setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(1)));
        ((ImageView) view.findViewById(R.id.chat_profile_picture)).setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(1)));
        ArrayList arrayList = new ArrayList();
        this.messages = arrayList;
        arrayList.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", true));
        this.messages.add(new MessageModel("kakakaka", false));
        this.messages.add(new MessageModel("kakakaka", true));
        MessagesAdapter listAdapter = new MessagesAdapter(this.messages, getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.chat_menu_toolbar);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemid = item.getItemId();
                if (itemid == R.id.menu_notifications) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                    return false;
                } else if (itemid == R.id.menu_chat_clear) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(8));
                    return false;
                } else if (itemid == R.id.menu_chat_delete) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(9));
                    return false;
                } else if (itemid == R.id.menu_block_user) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                    return false;
                } else if (itemid != R.id.menu_report_user) {
                    return false;
                } else {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                    return false;
                }
            }
        });
    }


}
