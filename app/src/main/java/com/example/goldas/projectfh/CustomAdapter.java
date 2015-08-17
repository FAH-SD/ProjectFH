package com.example.goldas.projectfh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by roy on 2015/8/16.
 */
public class CustomAdapter extends ArrayAdapter {
    public static final int TYPE_FRESH = 0;
    public static final int TYPE_EXPIRED = 1;
    private ListViewItem[] objects;
    @Override
    public int getViewTypeCount() {
        return 2;
    }
    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }
    public CustomAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ListViewItem listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);
        if (convertView == null) {
            if (listViewItemType == TYPE_FRESH) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.teest, null);
            } else if (listViewItemType == TYPE_EXPIRED) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.foodexpired, null);
            }
            TextView txtitem = (TextView) convertView.findViewById(R.id.txtitem);
                TextView txtquan = (TextView) convertView.findViewById(R.id.txtquan);
                TextView txtunit = (TextView) convertView.findViewById(R.id.txtunit);
                TextView txtld = (TextView) convertView.findViewById(R.id.txtld);
            viewHolder = new ViewHolder(txtitem, txtquan, txtunit, txtld);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.getItem().setText(listViewItem.getItem());
        viewHolder.getQuan().setText(listViewItem.getQuan());
        viewHolder.getUnit().setText(listViewItem.getUnit());
        viewHolder.getLd().setText(listViewItem.getLimitdate());
        return convertView;
    }
}