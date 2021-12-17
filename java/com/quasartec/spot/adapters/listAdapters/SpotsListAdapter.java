package com.quasartec.spot.adapters.listAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavAction;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.SpotModel;

import java.util.ArrayList;

public class SpotsListAdapter extends ListAdapter<SpotModel, SpotsListAdapter.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private LayoutInflater inflater;
    private ArrayList<SpotModel> spots;
    NavAction navAction;

    public SpotsListAdapter(@NonNull DiffUtil.ItemCallback<SpotModel> diffCallback, Context context2 ) {
        super(diffCallback);
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

   /* public SpotsListAdapter(ArrayList<SpotModel> list2, Context context2) {
        this.spots = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }*/

    public int getItemCount() {
        return getCurrentList().size();
    }


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spot_view, parent, false);
        context= inflater.getContext();
        return new SpotsListAdapter.ViewHolder(view);
     //   return new ViewHolder(this.inflater.inflate(R.layout.spot_view, parent, false));
    }

   /* public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.spots.get(position));
    }*/

    @Override
    public void onBindViewHolder(SpotsListAdapter.ViewHolder holder, int position) {
        SpotModel current = getItem(position);
        holder.bindData(current);
    }

    public void setItems(ArrayList<SpotModel> newList) {
        this.spots = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView body;
        ImageView categoryIcon;
        TextView commentsTV;
        TextView distanceTV;
        TextView groupTV;
        TextView menu;
        Bitmap profilePhotoBM;
        ImageView profilePicture;
        CardView roundProfile;
        TextView savesTV;
        Bitmap spotBM;
        ImageView spotPhoto;
        TextView timeTV;
        TextView titleTV;
        LinearLayout topBar;

        ViewHolder(View view) {
            super(view);
            this.titleTV = (TextView) view.findViewById(R.id.spotView_title);
            this.distanceTV = (TextView) view.findViewById(R.id.spotView_distanceTV);
            this.timeTV = (TextView) view.findViewById(R.id.spotView_timeTV);
            this.savesTV = (TextView) view.findViewById(R.id.spotView_savedsTV);
            this.commentsTV = (TextView) view.findViewById(R.id.spotView_commentsTV);
            this.roundProfile = (CardView) view.findViewById(R.id.card_viewProfile);
            this.profilePicture = (ImageView) view.findViewById(R.id.spotView_profile_picture);
            this.spotPhoto = (ImageView) view.findViewById(R.id.spotView_image);
            this.body = (CardView) view.findViewById(R.id.card_view_spotView);
            this.topBar = (LinearLayout) view.findViewById(R.id.spotView_linearLayout);
            this.categoryIcon = (ImageView) view.findViewById(R.id.categoryIcon_SpotView);
            this.menu = (TextView) view.findViewById(R.id.spotview_menuIcon);
            this.groupTV = (TextView) view.findViewById(R.id.spotView_group);
        }

        /* access modifiers changed from: package-private */
        public void bindData(SpotModel spotModel) {
            this.titleTV.setText(spotModel.title);
            if (!spotModel.showsUser) {
                this.profilePicture.setVisibility(View.GONE);
                this.roundProfile.setVisibility(View.GONE);
            } else {
                this.profilePicture.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalUserFragment(1)));
                this.profilePicture.setVisibility(View.VISIBLE);
                this.roundProfile.setVisibility(View.VISIBLE);
            }
            this.groupTV.setText(spotModel.groupName);
            this.groupTV.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalBelongGroupFragment(1)));
            this.distanceTV.setText(20 + MainActivity.km);
            this.timeTV.setText("20 OCT");
            this.savesTV.setText(String.valueOf(spotModel.favouritesAmmount));
            this.commentsTV.setText(String.valueOf(spotModel.commentsAmmount));

            if (spotModel.category == Category.SOCIAL) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_social);
            } else if (spotModel.category == Category.MATERIAL) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_material);
            } else if (spotModel.category == Category.ENVIRONMENT) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_environment);
            }
            this.body.setOnClickListener(v -> ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalSpotFragment(1)));
            PopupMenu popup = new PopupMenu( context, this.menu);
            if (spotModel.mine) {
                popup.inflate(R.menu.ownspot_menu_popup);
                popup.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_ownspot_edit) {
                        ((MainActivity) MainActivity.mn.get()).toolbar.setTitle((CharSequence) MainActivity.modifySpot);
                        NavGraphDirections.ActionGlobalModifySpotFragment action = NavGraphDirections.actionGlobalModifySpotFragment();
                        action.setSpotId(1);
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) action);
                    } else if (itemId == R.id.menu_spot_delete) {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(0));
                    }
                    return false;
                });
            } else if (spotModel.insideMyGroup) {
                popup.inflate(R.menu.ownspot_menu_popup);
                popup.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.menu_spot_delete) {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(0));
                    }
                    return false;
                });
            } else {
                popup.inflate(R.menu.spotview_menu);
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menu_spot_Hide:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(10));
                            return false;
                        case R.id.menu_spot_Report:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(4));
                            return false;
                        default:
                            return false;
                    }
                });
            }
            this.menu.setOnClickListener(view -> popup.show());
        }




    }

  public static class WordDiff extends DiffUtil.ItemCallback<SpotModel> {

        @Override
        public boolean areItemsTheSame(@NonNull SpotModel oldItem, @NonNull SpotModel newItem) {
            return oldItem.getSpotId().equals(newItem.getSpotId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull SpotModel oldItem, @NonNull SpotModel newItem) {
            return oldItem.getSpotId().equals(newItem.getSpotId());//contents
        }
    }

}
