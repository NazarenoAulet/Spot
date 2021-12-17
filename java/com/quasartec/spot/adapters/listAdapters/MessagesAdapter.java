package com.quasartec.spot.adapters.listAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.quasartec.spot.R;
import com.quasartec.spot.models.MessageModel;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<MessageModel> list;

    public MessagesAdapter(List<MessageModel> list2, Context context2) {
        this.list = list2;
        this.context = context2;
        this.inflater = LayoutInflater.from(context2);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(this.inflater.inflate(R.layout.chat_item_receive, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(this.list.get(position));
    }

    public void setItems(List<MessageModel> newList) {
        this.list = newList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FrameLayout body;
        FrameLayout innerBody;
        TextView text;

        ViewHolder(View view) {
            super(view);
            this.innerBody = (FrameLayout) view.findViewById(R.id.chatinnerBody);
            this.body = (FrameLayout) view.findViewById(R.id.chatbody);
            this.text = (TextView) view.findViewById(R.id.chatText);
        }

        /* access modifiers changed from: package-private */
        public void bindData(MessageModel messageModel) {
            if (messageModel.sent) {
                this.body.getLayoutParams().width = -1;
                this.innerBody.setBackgroundResource(R.drawable.sent_drawable);
                return;
            }
            this.body.getLayoutParams().width = -2;
            this.innerBody.setBackgroundResource(R.drawable.receive_gradient);
        }
    }
}
