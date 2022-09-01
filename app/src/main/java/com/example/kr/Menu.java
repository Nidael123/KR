package com.example.kr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.kr.fragmentos.jugar;
import com.example.kr.fragmentos.listado;

public class Menu extends AppCompatActivity {

    FragmentTransaction cambiarFragment;
    Fragment fragmentListado,fragmentJugar;//instanciar el resto de fragments
    ImageButton buttonListado,buttonJugar;//revisar si se quedan como image button o button normal



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        fragmentListado = new listado();
        fragmentJugar = new jugar();

        buttonJugar = findViewById(R.id.buttonJugar);
        buttonListado = findViewById(R.id.buttonListado);



        getSupportFragmentManager().beginTransaction().add(R.id.contenedor_fragment,fragmentJugar).commit();

        buttonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarFragment = getSupportFragmentManager().beginTransaction();
                cambiarFragment.replace(R.id.contenedor_fragment,fragmentJugar).commit();
            }
        });
        buttonListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarFragment = getSupportFragmentManager().beginTransaction();
                cambiarFragment.replace(R.id.contenedor_fragment,fragmentListado).commit();
            }
        });

    }
    public void onClick(View view)
    {

    }


}