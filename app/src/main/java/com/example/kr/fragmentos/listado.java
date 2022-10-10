package com.example.kr.fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.kr.R;
import com.example.kr.kanjis;


public class listado extends Fragment {

    Button btn_kanji1,btn_kanji2,btn_kanji3,btn_kanji4,btn_kanji5;
    View vistaFragment;
    Intent intent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(getActivity(),kanjis.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vistaFragment = inflater.inflate(R.layout.fragment_listado, container, false);
        btn_kanji1 = vistaFragment.findViewById(R.id.btn_kanji1);
        btn_kanji2 = vistaFragment.findViewById(R.id.btn_kanji2);
        btn_kanji3 = vistaFragment.findViewById(R.id.btn_kanji3);
        btn_kanji4 = vistaFragment.findViewById(R.id.btn_kanji4);
        btn_kanji5 = vistaFragment.findViewById(R.id.btn_kanji5);
        //enviar en cada fragment en nivel de jaji que estoy pidiedno
        btn_kanji1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        btn_kanji2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        btn_kanji3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        btn_kanji4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        btn_kanji5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
        //vistaFragment.findViewById(R.id.listaKanji);
        return vistaFragment;
    }
}