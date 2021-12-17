package com.quasartec.spot.adapters.listAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.topLevelDestinations.MessagesFragmentDirections;
import com.quasartec.spot.models.ChatCardModel;

import java.util.List;

public class ChatCardAdapter extends RecyclerView.Adapter<ChatCardAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<ChatCardModel> list;

    public ChatCardAdapter(List<ChatCardModel> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.chatcard, parent, false));
    }


    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.list.get(position));
    }

    public void setItems(List<ChatCardModel> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView body;
        TextView menu;
        TextView messageTV;
        TextView user;
        Bitmap userBM;
        ImageView userPhoto;

        ViewHolder(View view) {
            super(view);
            this.userPhoto = view.findViewById(R.id.chatcard_profile_picture);
            this.user = view.findViewById(R.id.chatCard_user);
            this.messageTV = view.findViewById(R.id.chatcard_text);
            this.body = view.findViewById(R.id.chatcard_body);
            this.menu = view.findViewById(R.id.chatcard_menuIcon);
        }


          void bindData(ChatCardModel cardModel) {
            this.messageTV.setText(cardModel.lastMessage);
            this.user.setText(cardModel.userName);
            this.userPhoto.setOnClickListener(v -> (  MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalUserFragment("1")));
            this.user.setOnClickListener(v -> (  MainActivity.mn.get()).navController.navigate(NavGraphDirections.actionGlobalUserFragment("1")));
            this.body.setOnClickListener(v -> (  MainActivity.mn.get()).navController.navigate(MessagesFragmentDirections.actionMessagesFragment2ToChatFragment("1")));
            PopupMenu popup = new PopupMenu(ChatCardAdapter.this.context, this.menu);
            popup.inflate(R.menu.chatcardmenu);
            popup.setOnMenuItemClickListener(item -> {
                int itemid = item.getItemId();
                if (itemid == R.id.menu_chat_clear) {
                    (MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(8));
                    return false;
                } else if (itemid == R.id.menu_chat_delete) {
                    (MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(9));
                    return false;
                } else if (itemid == R.id.menu_block_user) {
                    (MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                    return false;
                } else if (itemid != R.id.menu_report_user) {
                    return false;
                } else {
                    (MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                    return false;
                }
            });
            this.menu.setOnClickListener(view -> popup.show());
        }


    }
}
