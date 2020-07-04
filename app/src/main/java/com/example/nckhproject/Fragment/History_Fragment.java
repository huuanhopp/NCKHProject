package com.example.nckhproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nckhproject.Class.Bill_Class;
import com.example.nckhproject.Class.CustomAdapter_Bill;
import com.example.nckhproject.R;

import java.util.ArrayList;

public class History_Fragment extends Fragment {
    View view;
    ListView lvBill;
    ArrayList<Bill_Class> list_Bill;
    CustomAdapter_Bill customAdapter_bill;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.history_fragment, container, false);
        Anhxa();
        init();
        return view;

    }

    private void init() {
        list_Bill = new ArrayList<>();
        customAdapter_bill = new CustomAdapter_Bill(view.getContext(), list_Bill, R.layout.customlistviewbill);
        lvBill.setAdapter(customAdapter_bill);

        list_Bill.add(new Bill_Class("20/06/2019", "20/11/2019", 1200000, "enoughtodie99@gmail.com"));
        customAdapter_bill.notifyDataSetChanged();
    }

    private void Anhxa() {
        lvBill = (ListView) view.findViewById(R.id.lvBill);

    }
}
