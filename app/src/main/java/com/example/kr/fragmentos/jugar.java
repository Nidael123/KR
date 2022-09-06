package com.example.kr.fragmentos;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.kr.MainActivity;
import com.example.kr.Menu;
import com.example.kr.R;
import com.example.kr.activity_jugar;


public class jugar extends Fragment {

    Button play;
    View vista;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public jugar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment jugar.
     */
    // TODO: Rename and change types and number of parameters
    public static jugar newInstance(String param1, String param2) {
        jugar fragment = new jugar();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // crear la vista igualarla a return por default y desde aqui trabajo igual que activity añadiendo la vista
        //no olvidar retornar la vista
        vista = inflater.inflate(R.layout.fragment_jugar, container, false);
        play = vista.findViewById(R.id.btn_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(vista.getContext(), activity_jugar.class));
                getActivity().onBackPressed();
            }
        });
        return  vista;
    }
}