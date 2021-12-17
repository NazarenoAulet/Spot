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
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.RecyclerView;

import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.adapters.listAdapters.NotificationsAdapter;
import com.quasartec.spot.models.NotificationModel;

import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private LayoutInflater inflater;
    private List<NotificationModel> list;

    public NotificationsAdapter(List<NotificationModel> list2, Context context2) {
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

    public void setItems(List<NotificationModel> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView body;
        TextView menu;
        TextView text;
        Bitmap userBM;
        ImageView userPhoto;
        CardView userRound;

        ViewHolder(View view) {
            super(view);
            this.userPhoto = (ImageView) view.findViewById(R.id.usercard_profile_picture);
            this.text = (TextView) view.findViewById(R.id.userCard_user);
            this.body = (CardView) view.findViewById(R.id.usercard_body);
            this.userRound = (CardView) view.findViewById(R.id.cardViewUser);
            this.menu = (TextView) view.findViewById(R.id.usercard_menuIcon);
        }

        /* access modifiers changed from: package-private */
        public void bindData(NotificationModel notificationModel) {
            NavController nav = ((MainActivity) MainActivity.mn.get()).navController;
            if (notificationModel.type == 0) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, false);
                    nav.navigate(NavGraphDirections.actionGlobalMySpotsFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1));
                });
                if (notificationModel.andXpeople == 0) {
                    this.text.setText(notificationModel.user + MainActivity.hasCommentedYourSpot);
                } else {
                    this.text.setText(notificationModel.user + MainActivity.and + notificationModel.andXpeople + MainActivity.peopleHaveCommentedYourSpot);
                }
            } else if (notificationModel.type == 1) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, false);
                    nav.navigate(NavGraphDirections.actionGlobalGroupsFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalMyGroupFragment(1));
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1));
                });
                this.text.setText(notificationModel.user + MainActivity.hasCreatedSpotIn + notificationModel.group);
            } else if (notificationModel.type == 2) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, false);
                    nav.navigate(NavGraphDirections.actionGlobalFavouritesFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1));
                });
                this.userRound.setVisibility(View.GONE);
                this.text.setText(MainActivity.theFollowedSpot + notificationModel.spot + MainActivity.hasBeenUpdated);
            } else if (notificationModel.type == 3) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, true);
                    nav.navigate(NavGraphDirections.actionGlobalGroupsFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalMyGroupFragment(1));
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1));
                });
                this.userRound.setVisibility(View.GONE);
                this.text.setText(MainActivity.theSpot + notificationModel.spot + MainActivity.in + notificationModel.group + MainActivity.hasBeenUpdated);
            } else if (notificationModel.type == 4) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, false);
                    nav.navigate(NavGraphDirections.actionGlobalGroupsFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalMyGroupFragment(1));
                });
                if (notificationModel.andXpeople == 0) {
                    this.text.setText(notificationModel.user + MainActivity.hasRequestedToJoin + notificationModel.group);
                } else {
                    this.text.setText(notificationModel.user + MainActivity.and + notificationModel.andXpeople + MainActivity.peopleHaveRequestedToJoin + notificationModel.group);
                }
            } else if (notificationModel.type == 5) {
                this.body.setOnClickListener(view -> {
                    ((MainActivity) MainActivity.mn.get()).navController.popBackStack(R.id.publicMapFragment, false);
                    nav.navigate(NavGraphDirections.actionGlobalGroupsFragment());
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalMyGroupFragment(1));
                    nav.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1));
                });
                this.userRound.setVisibility(View.GONE);
                if (notificationModel.andXpeople == 0) {
                    this.text.setText(MainActivity.theSpot + notificationModel.spot + MainActivity.in + notificationModel.group + MainActivity.hasBeenReported);
                } else {
                    this.text.setText(MainActivity.theSpot + notificationModel.spot + MainActivity.in + notificationModel.group + MainActivity.hasBeenReportedBy + (notificationModel.andXpeople + 1) + MainActivity.people);
                }
            }
            PopupMenu popup = new PopupMenu(NotificationsAdapter.this.context, this.menu);
            popup.inflate(R.menu.notifications_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(12));
                return false;
            });
            this.menu.setOnClickListener(view -> popup.show());
        }


    }
}
