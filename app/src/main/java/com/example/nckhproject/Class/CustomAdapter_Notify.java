package com.example.nckhproject.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nckhproject.R;

import java.util.Collections;
import java.util.List;

public class CustomAdapter_Notify extends BaseAdapter {
    Context context;
    List<Notify_Class> list_Notify;
    int layout;

    public CustomAdapter_Notify(Context context, List<Notify_Class> list_Notify, int layout) {
        this.context = context;
        this.list_Notify = list_Notify;
        this.layout = layout;
    }

    class ViewHolder{
        public TextView txtNotify_Date, txtNotify_Title;
    }
    @Override
    public int getCount() {
        return list_Notify.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null)
        {
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout, null);
            viewHolder.txtNotify_Date = view.findViewById(R.id.txt_notify_date);
            viewHolder.txtNotify_Title = view.findViewById(R.id.txt_notify_title);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }

        viewHolder.txtNotify_Title.setText(list_Notify.get(position).getTitle());
        viewHolder.txtNotify_Date.setText(list_Notify.get(position).getDate());

        return view;
    }
}
