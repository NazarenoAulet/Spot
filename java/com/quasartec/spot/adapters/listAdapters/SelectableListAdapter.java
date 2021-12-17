package com.quasartec.spot.adapters.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.Views.MainActivity;
import java.util.List;

public class SelectableListAdapter extends RecyclerView.Adapter<SelectableListAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<String> list;
    int type;

    public SelectableListAdapter(List<String> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.list_dialog_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.list.get(position));
    }

    public void setItems(List<String> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout body;
        TextView text;

        ViewHolder(View view) {
            super(view);
            this.text = (TextView) view.findViewById(R.id.list_item_text);
            this.body = (LinearLayout) view.findViewById(R.id.list_item_body);
        }

        /* access modifiers changed from: package-private */
        public void bindData(String string) {
            this.text.setText(string);
            this.body.setOnClickListener(v -> {
                int i = SelectableListAdapter.this.type;
                ((MainActivity) MainActivity.mn.get()).onBackPressed();
            });
        }
    }
}
