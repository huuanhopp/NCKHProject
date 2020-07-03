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

public class CustomAdapter_Bill extends BaseAdapter {
    Context context;
    List<Bill_Class> list_Bill;
    int layout;

    public CustomAdapter_Bill(Context context, List<Bill_Class> list_room, int layout) {
        this.context = context;
        this.list_Bill = list_room;
        this.layout = layout;
    }
    class ViewHolder{
        public TextView txtDORofBill, txtPriceofBill, txtEDofBill;
    }
    @Override
    public int getCount() {
        return list_Bill.size();
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
            viewHolder.txtDORofBill = view.findViewById(R.id.txtDORofBill);
            viewHolder.txtPriceofBill = view.findViewById(R.id.txtPriceofBill);
            viewHolder.txtEDofBill = view.findViewById(R.id.txtEDofBill);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }

        String price = getStringPrice(list_Bill.get(position).getPrice()) + " VNĐ";
        viewHolder.txtEDofBill.setText(list_Bill.get(position).getExpiration_Date());
        viewHolder.txtPriceofBill.setText(price);
        viewHolder.txtDORofBill.setText(list_Bill.get(position).getDate_of_Registration());

        return view;
    }
}
