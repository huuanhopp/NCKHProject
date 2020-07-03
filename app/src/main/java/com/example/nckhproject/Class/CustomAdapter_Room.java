package com.example.nckhproject.Class;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nckhproject.R;

import java.util.List;



public class CustomAdapter_Room extends BaseAdapter {
    Context context;
    List<Room_Class> list_room;
    int layout;

    public CustomAdapter_Room(Context context, List<Room_Class> list_room, int layout) {
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
    public String getStringPrice(long Price)
    {
        StringBuilder args = new StringBuilder(String.valueOf(Price)).reverse();
        String res = "";
        for(int i = 0; i < args.length(); i ++)
        {
            res += args.charAt(i);
            if((i+1)%3 == 0 && i!= args.length()-1)
                res += ",";
        }
        return new StringBuffer(res).reverse().toString();
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

        String price = getStringPrice(list_room.get(position).getPrice()) + " VNĐ/ " + list_room.get(position).getDate() + " tháng";
        String person = list_room.get(position).getPerson_Now().size() + "/" + list_room.get(position).getPerson_Max();
        viewHolder.txtNameofRoom.setText(list_room.get(position).getName());
        viewHolder.txtPriceofRoom.setText(price);
        viewHolder.txtPerson.setText(person);
        int size = list_room.get(position).getPerson_Now().size();
        if(size <= 2)
        {
            viewHolder.txtPerson.setBackgroundColor(Color.GRAY);
        }
        else if(size <= 4)
        {
            viewHolder.txtPerson.setBackgroundColor(Color.GREEN);
        }
        else if(size <= 6)
        {
            viewHolder.txtPerson.setBackgroundColor(Color.BLUE);
        }
        else
        {
            viewHolder.txtPerson.setBackgroundColor(Color.RED);
        }

        return view;
    }
}
