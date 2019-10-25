package com.example.smartclass;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeSiswaFragment extends Fragment implements View.OnClickListener {

    CardView absenSeluruh, pilihMapel;


    public HomeSiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_siswa, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        absenSeluruh = view.findViewById(R.id.absen_seluruh);
        pilihMapel = view.findViewById(R.id.pilih_mapel);
        absenSeluruh.setOnClickListener(this);
        pilihMapel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.absen_seluruh:
                break;

            case R.id.pilih_mapel:
                OptionMapelqFragment mOptionMapelqFragment = new OptionMapelqFragment();

                FragmentManager mFragmentManager = getChildFragmentManager();
                mOptionMapelqFragment.show(mFragmentManager, OptionMapelqFragment.class.getSimpleName());
                break;
        }
    }

    OptionMapelqFragment.OnOptionMapelqListener optionMapelqListener = new OptionMapelqFragment.OnOptionMapelqListener() {
        @Override
        public void onOptionChosen(String text) {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }
    };
}
