package com.quasartec.spot.adapters.listAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.topLevelDestinations.GroupsFragmentDirections;
import com.quasartec.spot.adapters.listAdapters.GroupsCardsListAdapater;
import com.quasartec.spot.models.Category;
import com.quasartec.spot.models.GroupModel;
import java.util.List;

public class GroupsCardsListAdapater extends RecyclerView.Adapter<GroupsCardsListAdapater.ViewHolder> {
    /* access modifiers changed from: private */
    public Context context;
    private LayoutInflater inflater;
    private List<GroupModel> list;

    public GroupsCardsListAdapater(List<GroupModel> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.groupcard, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.list.get(position));
    }

    public void setItems(List<GroupModel> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView body;
        ImageView categoryIcon;
        ImageView groupPhoto;
        Bitmap groupPhotoBM;
        TextView hiddenTV;
        TextView lockedTV;
        TextView menu;
        TextView spotsTV;
        TextView titleTV;
        LinearLayout topBar;
        TextView usersTV;

        ViewHolder(View view) {
            super(view);
            this.titleTV = (TextView) view.findViewById(R.id.groupCard_title);
            this.usersTV = (TextView) view.findViewById(R.id.groupCard_userCount);
            this.spotsTV = (TextView) view.findViewById(R.id.groupCard_spotCount);
            this.lockedTV = (TextView) view.findViewById(R.id.groupCard_locked);
            this.hiddenTV = (TextView) view.findViewById(R.id.groupCard_hidden);
            this.body = (CardView) view.findViewById(R.id.card_view_groupCard);
            this.topBar = (LinearLayout) view.findViewById(R.id.groupCard_linearLayout);
            this.categoryIcon = (ImageView) view.findViewById(R.id.categoryIcon_groupCard);
            this.menu = (TextView) view.findViewById(R.id.groupCard_menuIcon);
        }

        /* access modifiers changed from: package-private */
        public void bindData(GroupModel groupModel) {
            this.titleTV.setText(groupModel.title);
            this.usersTV.setText(String.valueOf(groupModel.usersAmount));
            this.spotsTV.setText(String.valueOf(groupModel.spotsAmount));
            if (groupModel.locked) {
                this.lockedTV.setText("");
            } else {
                this.lockedTV.setText("");
            }
            if (groupModel.shown) {
                this.hiddenTV.setText("");
            } else {
                this.hiddenTV.setText("");
            }

            if ( groupModel.category == Category.SOCIAL) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_social);
            } else if (groupModel.category == Category.MATERIAL) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_material);
            } else if (groupModel.category == Category.ENVIRONMENT) {
                this.topBar.setBackgroundResource(R.drawable.gradient_light_environment);
            }
            this.body.setOnClickListener(view -> {
                if (groupModel.belong) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalBelongGroupFragment(1));
                } else if (groupModel.admin) {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalMyGroupFragment(1));
                } else {
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalParticularGroupFragment(1));
                }
            });
            PopupMenu popup = new PopupMenu(GroupsCardsListAdapater.this.context, this.menu);
            if (groupModel.belong) {
                popup.inflate(R.menu.groupcard_mygroups_menu);
                popup.setOnMenuItemClickListener(item -> {
                    int itemId = item.getItemId();
                    if (itemId == R.id.menu_group_Report) {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(2));
                        return false;
                    } else if (itemId != R.id.menu_mygroups_Abandon) {
                        return false;
                    } else {
                        ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(7));
                        return false;
                    }
                });
            } else if (groupModel.admin) {
                popup.inflate(R.menu.groupcard_admin_menu);
                popup.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() != R.id.menu_admin_group_edit) {
                        return false;
                    }
                    GroupsFragmentDirections.ActionGroupsFragment2ToCreateGroupFragment navAction = GroupsFragmentDirections.actionGroupsFragment2ToCreateGroupFragment();
                    navAction.setGroupId(1);
                    ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) navAction);
                    return false;
                });
            } else {
                popup.inflate(R.menu.groupcard_menu);
                popup.setOnMenuItemClickListener(item -> {
                    switch (item.getItemId()) {
                        case R.id.menu_group_Hide:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(11));
                            return false;
                        case R.id.menu_group_Report:
                            ((MainActivity) MainActivity.mn.get()).navController.navigate((NavDirections) NavGraphDirections.actionGlobalConfirmationDialog(2));
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
