package com.quasartec.spot.Views.destinations.otherDestinations.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quasartec.spot.R;

public class FilterGroupByDialog extends BottomSheetDialogFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.filter_group_dialog, container, false);
    }
}
