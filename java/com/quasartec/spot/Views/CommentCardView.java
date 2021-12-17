package com.quasartec.spot.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;

import com.quasartec.spot.NavGraphDirections;
import com.quasartec.spot.R;


public class CommentCardView extends FrameLayout {
    TextView commentText;
    TextView hoursTV;
    TextView menu;
    boolean myComment;
    boolean mySpot;
    TextView userNameTV;
    ImageView userPic;

    String userID, userName, userPicURL,message;
    long date;

    public CommentCardView(Context context, boolean mySpot2, boolean myComment2, String userID, String userName, String userPicURL, String message, long date) {
        super(context);
        this.mySpot = mySpot2;
        this.myComment = myComment2;
        this.userName=userName;
        this.userID=userID;
        this.userPicURL=userPicURL;
        this.message=message;
        this.date=date;
        init(context);
    }

    public CommentCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CommentCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CommentCardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context ct) {
        LayoutInflater.from(ct).inflate(R.layout.comment_card_layout, this, true);
        this.userPic =   findViewById(R.id.comment_profile_picture);

        this.hoursTV =   findViewById(R.id.comment_time);
        this.userNameTV =  findViewById(R.id.comment_user);
        this.commentText =  findViewById(R.id.comment_text);
        this.menu =  findViewById(R.id.comment_menuIcon);
        this.userPic.setOnClickListener(view -> ( MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalUserFragment(userID)));
        this.userNameTV.setOnClickListener(v -> (MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalUserFragment(userID)));

      userNameTV.setText(userName);
      commentText.setText(message);
      //userPic.setImageResource();
        //hoursTV.setText(date);
        PopupMenu popup = new PopupMenu(ct, this.menu);
        if (this.mySpot) {
            popup.inflate(R.menu.comment_popup_menu);
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_erase_comment) {
                    ( MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalConfirmationDialog(13));
                    return false;
                } else if (itemId == R.id.menu_block_user) {
                    ( MainActivity.mn.get()).navController.navigate(  NavGraphDirections.actionGlobalConfirmationDialog(5));
                    return false;
                } else if (itemId != R.id.menu_report_user) {
                    return false;
                } else {
                    (  MainActivity.mn.get()).navController.navigate(  NavGraphDirections.actionGlobalConfirmationDialog(3));
                    return false;
                }

            });
        } else if (this.myComment) {
            popup.inflate(R.menu.menu_mycomment);
            popup.setOnMenuItemClickListener(item -> {
                  (  MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalConfirmationDialog(13));
return false;
            });
        } else {
            popup.inflate(R.menu.comment_notmine);
            popup.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_block_user) {
                    (  MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalConfirmationDialog(5));
                    return false;
                } else if (itemId != R.id.menu_report_user) {
                    return false;
                } else {
                    (  MainActivity.mn.get()).navController.navigate( NavGraphDirections.actionGlobalConfirmationDialog(3));
                    return false;
                }
            });
        }
        this.menu.setOnClickListener(view -> popup.show());
    }



}
