package com.example.nckhproject.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nckhproject.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<Room_Class> list_room;
    int layout;

    public CustomAdapter(Context context, List<Room_Class> list_room, int layout) {
        this.context = context;
        this.list_room = list_room;
        this.layout = layout;
    }
    class ViewHolder{
        public TextView txtNameofRoom, txtPriceofRoom, txtPerson;
    }
    @Override
    public int getCount() {
        return list_room.size();
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
            viewHolder.txtNameofRoom = view.findViewById(R.id.txtNameofRoom);
            viewHolder.txtPriceofRoom = view.findViewById(R.id.txtPriceofRoom);
            viewHolder.txtPerson = view.findViewById(R.id.txtNumofRoom);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }
        String price = list_room.get(position).getPrice() + " VNĐ/ " + list_room.get(position).getDate() + " tháng";
        String person = list_room.get(position).getPerson_Now().size() + "/" + list_room.get(position).getPerson_Max();
        viewHolder.txtNameofRoom.setText(list_room.get(position).getName());
        viewHolder.txtPriceofRoom.setText(price);
        viewHolder.txtPerson.setText(person);
        return view;
    }
}
