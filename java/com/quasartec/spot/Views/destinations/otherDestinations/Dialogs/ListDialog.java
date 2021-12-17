package com.quasartec.spot.Views.destinations.otherDestinations.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.adapters.listAdapters.SelectableListAdapter;
import java.util.ArrayList;

public class ListDialog extends DialogFragment {
    public void onViewCreated(View view, Bundle savedInstanceState) {
        View view2 = view;
        RecyclerView recyclerView = (RecyclerView) view2.findViewById(R.id.list_dialog_recyclerview);
        TextView textView = (TextView) view2.findViewById(R.id.list_dialog_title);
        ArrayList<String> list = new ArrayList<>();
        if (getArguments().getInt("type") == 0) {
            textView.setText(getResources().getString(R.string.Select_main_topic));
            list.clear();
            list.add(getResources().getString(R.string.resource_category));
            list.add(getResources().getString(R.string.social_category));
            list.add(getResources().getString(R.string.environment_category));
            list.add(getResources().getString(R.string.recycling));
            list.add(getResources().getString(R.string.donation));
            list.add(getResources().getString(R.string.buying));
            list.add(getResources().getString(R.string.selling));
            list.add(getResources().getString(R.string.humanitarian_activity));
            list.add(getResources().getString(R.string.group_activity));
            list.add(getResources().getString(R.string.help_needed));
            list.add(getResources().getString(R.string.party));
            list.add(getResources().getString(R.string.point_of_interest));
            list.add(getResources().getString(R.string.warning));
        } else if (getArguments().getInt("type") == 10) {
            list.clear();
            textView.setText(R.string.select_subcategory);
            list.add(getResources().getString(R.string.humanitarian_activity));
            list.add(getResources().getString(R.string.group_activity));
            list.add(getResources().getString(R.string.help_needed));
            list.add(getResources().getString(R.string.party));
        } else if (getArguments().getInt("type") == 20) {
            list.clear();
            textView.setText(R.string.select_subcategory);
            list.add(getResources().getString(R.string.recycling));
            list.add(getResources().getString(R.string.donation));
            list.add(getResources().getString(R.string.buying));
            list.add(getResources().getString(R.string.selling));
        } else if (getArguments().getInt("type") == 30) {
            list.clear();
            textView.setText(R.string.select_subcategory);
            list.add(getResources().getString(R.string.point_of_interest));
            list.add(getResources().getString(R.string.warning));
        } else if (getArguments().getInt("type") == 90) {
            list.clear();
            textView.setText(R.string.select_month);
            list.add(getString(R.string.January));
            list.add(getString(R.string.February));
            list.add(getString(R.string.March));
            list.add(getString(R.string.April));
            list.add(getString(R.string.May));
            list.add(getString(R.string.June));
            list.add(getString(R.string.July));
            list.add(getString(R.string.August));
            list.add(getString(R.string.September));
            list.add(getString(R.string.October));
            list.add(getString(R.string.November));
            list.add(getString(R.string.December));
        } else if (getArguments().getInt("type") == 91) {
            list.clear();
            textView.setText(R.string.Select_day);
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            list.add("7");
            list.add("8");
            list.add("9");
            list.add("10");
            list.add("11");
            list.add("12");
            list.add("13");
            list.add("14");
            list.add("15");
            list.add("16");
            list.add("17");
            list.add("18");
            list.add("19");
            list.add("20");
            list.add("21");
            list.add("22");
            list.add("23");
            list.add("24");
            list.add("25");
            list.add("26");
            list.add("27");
            list.add("28");
            list.add("29");
            list.add("30");
            list.add("31");
        } else if (getArguments().getInt("type") == 92) {
            list.clear();
            textView.setText(R.string.Select_hour);
            list.add("0");
            list.add("1");
            list.add("2");
            list.add("3");
            list.add("4");
            list.add("5");
            list.add("6");
            list.add("7");
            list.add("8");
            list.add("9");
            list.add("10");
            list.add("11");
            list.add("12");
            list.add("13");
            list.add("14");
            list.add("15");
            list.add("16");
            list.add("17");
            list.add("18");
            list.add("19");
            list.add("20");
            list.add("21");
            list.add("22");
            list.add("23");
        } else if (getArguments().getInt("type") == 93) {
            list.clear();
            textView.setText(R.string.Select_minutes);
            for (int i = 0; i < 60; i++) {
                list.add("" + i);
            }
        } else {
            list.clear();
            textView.setText(R.string.select_category);
            list.add(getResources().getString(R.string.resource_category));
            list.add(getResources().getString(R.string.social_category));
            list.add(getResources().getString(R.string.environment_category));
        }
        SelectableListAdapter listAdapter = new SelectableListAdapter(list, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_dialog, container, false);
    }
}
