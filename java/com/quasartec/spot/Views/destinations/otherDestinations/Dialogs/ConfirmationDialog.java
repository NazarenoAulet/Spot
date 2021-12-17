package com.quasartec.spot.Views.destinations.otherDestinations.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.DialogFragment;
import com.quasartec.spot.R;
import com.quasartec.spot.Repository;
import com.quasartec.spot.Views.MainActivity;
import com.quasartec.spot.Views.destinations.otherDestinations.SpotFragment;

public class ConfirmationDialog extends DialogFragment {
    int type;
Repository repository;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        repository=Repository.getInstance();
        return inflater.inflate(R.layout.confirmation_dialog, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView title = (TextView) view.findViewById(R.id.confirmation_title);
        EditText editText = (EditText) view.findViewById(R.id.confirmation_editText);
        AppCompatButton accept = (AppCompatButton) view.findViewById(R.id.confirmation_accept);
        AppCompatButton cancel = (AppCompatButton) view.findViewById(R.id.confirmation_cancel);
        editText.setVisibility(View.GONE);
        if (getArguments().getInt("type") == 0) {
            title.setText(MainActivity.AreYouSureYouWantToDeleteThisSpot);
        } else if (getArguments().getInt("type") == 1) {
            title.setText(MainActivity.AreYouSureYouWantToDeleteThisGroup);
        } else if (getArguments().getInt("type") == 2) {
            title.setText(MainActivity.StateTheReasonsYouWantToReportThisGroup);
            editText.setVisibility(View.VISIBLE);
        } else if (getArguments().getInt("type") == 3) {
            title.setText(MainActivity.StateTheReasonsYouWantToReportThisUser);
            editText.setVisibility(View.VISIBLE);
        } else if (getArguments().getInt("type") == 4) {
            title.setText(MainActivity.StateTheReasonsYouWantToReportThisSpot);
            editText.setVisibility(View.VISIBLE);
        } else if (getArguments().getInt("type") == 5) {
            title.setText(MainActivity.AreYouSureYouWantToBlockThisUser);
        } else if (getArguments().getInt("type") == 6) {
            title.setText(MainActivity.AreYouSureRemoveUser);
        } else if (getArguments().getInt("type") == 7) {
            title.setText(MainActivity.AreYouSureAbandonGroup);
        } else if (getArguments().getInt("type") == 8) {
            title.setText(MainActivity.AreYouSureClearChat);
        } else if (getArguments().getInt("type") == 9) {
            title.setText(MainActivity.AreYouSureDeleteChat);
        } else if (getArguments().getInt("type") == 10) {
            title.setText(MainActivity.AreYouSureHideSpot);
        } else if (getArguments().getInt("type") == 11) {
            title.setText(MainActivity.AreYouSureHideGroup);
        } else if (getArguments().getInt("type") == 12) {
            title.setText(MainActivity.AreYouSureHideNotif);
        } else if (getArguments().getInt("type") == 13) {
            title.setText(MainActivity.AreYouSureDeleteComment);
        }
        accept.setOnClickListener(v -> {
            ((MainActivity) MainActivity.mn.get()).onBackPressed() ;
            ((MainActivity) MainActivity.mn.get()).onBackPressed() ;
            if (type == 0) repository.deleteSpot(SpotFragment.spotID);
        } );
        cancel.setOnClickListener(v->        ((MainActivity) MainActivity.mn.get()).onBackPressed());
    }
}
