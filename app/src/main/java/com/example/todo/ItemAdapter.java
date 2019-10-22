package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todo.model.Item;

import java.util.List;

public class ItemAdapter extends ArrayAdapter {
    public ItemAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

            ImageView ivItem = convertView.findViewById(R.id.ivItem);
            TextView tvItem = convertView.findViewById(R.id.tvItem);

            viewHolder = new ViewHolder();
            viewHolder.hIvItem = ivItem;
            viewHolder.hTvItem = tvItem;

            viewHolder.hIvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Item item = (Item) getItem(viewHolder.position);
                    item.setDone(!item.isDone());
                    notifyDataSetChanged();
                }
            });

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.position = position;

        final Item item = (Item) getItem(position);

        viewHolder.hIvItem.setImageResource(item.isDone() ? R.drawable.done : R.drawable.notdone);
        viewHolder.hTvItem.setText(item.getText());

        return convertView;
    }

    private static class ViewHolder {
        private ImageView hIvItem;
        private TextView hTvItem;
        private int position;
    }
}
