package com.example.nckhproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.nckhproject.Activity.A3Activity;
import com.example.nckhproject.R;

public class Home_Fragment extends Fragment {
    Button btnA1, btnA3,btnA4, btnA5, btnA6;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        Anhxa();
        Listener();
        return view;

    }

    private void Listener() {
        btnA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "Tòa nhà này không dành cho sinh viên!", Toast.LENGTH_SHORT).show();
            }
        });
        btnA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), A3Activity.class);
                startActivity(intent);
            }
        });
    }

    private void Anhxa() {
        btnA6 = view.findViewById(R.id.btnA6);
        btnA1 = view.findViewById(R.id.btnA1);
        btnA3 = view.findViewById(R.id.btnA3);
        btnA4 = view.findViewById(R.id.btnA4);
        btnA5 = view.findViewById(R.id.btnA5);
    }


}
