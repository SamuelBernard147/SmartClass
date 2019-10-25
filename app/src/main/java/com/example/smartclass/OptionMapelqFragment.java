package com.example.smartclass;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class OptionMapelqFragment extends DialogFragment implements View.OnClickListener  {

    Button btnClose, btnPilih;
    RadioGroup rgOption;
    RadioButton mapel1, mapel2;
    OnOptionMapelqListener optionMapelListener;


    public OptionMapelqFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_mapelq, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        btnPilih = view.findViewById(R.id.btn_pilih);
        btnPilih.setOnClickListener(this);
        rgOption = view.findViewById(R.id.rg_option);
        mapel1 = view.findViewById(R.id.mapel1);
        mapel2 = view.findViewById(R.id.mapel2);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Fragment fragment = getParentFragment();

        if (fragment instanceof HomeSiswaFragment){
            HomeSiswaFragment homeSiswaFragment = (HomeSiswaFragment) fragment;
            this.optionMapelListener = homeSiswaFragment.optionMapelqListener;
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        this.optionMapelListener = null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_close:
            getDialog().cancel();
            break;

            case R.id.btn_pilih:
                int checkedRadioButtonId = rgOption.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1){
                    String coach = null;
                    switch (checkedRadioButtonId){
                        case R.id.mapel1:
                        coach = mapel1.getText().toString().trim();
                        break;

                        case R.id.mapel2:
                        coach = mapel2.getText().toString().trim();
                        break;
                    }
                    if (optionMapelListener != null){
                        optionMapelListener.onOptionChosen(coach);
                    }
                    getDialog().dismiss();
                }
                break;
        }
    }

    public interface OnOptionMapelqListener{
        void onOptionChosen(String text);
    }
}
