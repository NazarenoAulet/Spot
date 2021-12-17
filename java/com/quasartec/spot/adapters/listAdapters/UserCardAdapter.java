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
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.models.UserModel;
import java.util.List;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private LayoutInflater inflater;
    private List<UserModel> list;

    public UserCardAdapter(List<UserModel> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.usercard, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.list.get(position));
    }

    public void setItems(List<UserModel> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView body;
        TextView menu;
        TextView user;
        Bitmap userBM;
        ImageView userPhoto;

        ViewHolder(View view) {
            super(view);
            this.userPhoto = (ImageView) view.findViewById(R.id.usercard_profile_picture);
            this.user = (TextView) view.findViewById(R.id.userCard_user);
            this.body = (CardView) view.findViewById(R.id.usercard_body);
            this.menu = (TextView) view.findViewById(R.id.usercard_menuIcon);
        }

        /* access modifiers changed from: package-private */
        public void bindData(UserModel UserModel) {
            this.user.setText(UserModel.userId);
            this.body.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(1)));
            PopupMenu popup = new PopupMenu(UserCardAdapter.this.context, this.menu);
            if (UserModel.request) {
                popup.inflate(R.menu.user_request_popup_menu);
                popup.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_block_user) {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                        return false;
                    } else if (itemId != R.id.menu_report_user) {
                        return false;
                    } else {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                        return false;
                    }
                });
            } else {
                popup.inflate(R.menu.user_ingroup_popup_menu);
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menu_block_user:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(5));
                            return false;
                        case R.id.menu_remove_user:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(6));
                            return false;
                        case R.id.menu_report_user:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(3));
                            return false;
                        default:
                            return false;
                    }
                });
            }
            this.menu.setOnClickListener(view -> popup.show());
        }



    }
}
