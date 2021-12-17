package com.quasartec.spot.Views.destinations.topLevelDestinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.ChatCardAdapter;
import com.quasartec.spot.models.ChatCardModel;
import java.util.ArrayList;
import java.util.List;

public class MessagesFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<ChatCardModel> chats;
    private String mParam1;
    private String mParam2;

    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).toolbar.getMenu().clear();
        ((MainActivity) getActivity()).toolbar.inflateMenu(R.menu.onlynotifs_toolbar_menu);
        ((MainActivity) getActivity()).toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_notifications) {
                ( MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalNotificationsFragment());
                return false;
            }
            return false;
        });
        ArrayList arrayList = new ArrayList();
        this.chats = arrayList;
        arrayList.add(new ChatCardModel("LastMEsaaje", "LAejuhfiehihaka", "eeee"));
        this.chats.add(new ChatCardModel("LastMEsaaje", "LAejuhfiehihaka", "eeee"));
        this.chats.add(new ChatCardModel("LastMEsaaje", "LAejuhfiehihaka", "eeee"));
        this.chats.add(new ChatCardModel("LastMEsaaje", "LAejuhfiehihaka", "eeee"));
        this.chats.add(new ChatCardModel("LastMEsaaje", "LAejuhfiehihaka", "eeee"));
        ChatCardAdapter listAdapter = new ChatCardAdapter(this.chats, getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.messagesRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }


}
