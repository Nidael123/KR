package com.example.kr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kr.baseManage.create;

public class MainActivity extends AppCompatActivity {
    //BASE DE DATOS
    SQLiteDatabase bd;
    create nuevabase;
    ContentValues campos;
    //instancia de objetos del activity
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campos = new ContentValues();
        nuevabase = new create(this,"bd_kanji",null,2);
        bd = nuevabase.getWritableDatabase();
        //llenar la base desde la pagina web
        campos.put("id_kanji",1);
        campos.put("kanji","ー");
        Long ingreso = bd.insert("kanji","id_kanji",campos);
        Toast.makeText(getApplicationContext(), "ingreso = "+ingreso, Toast.LENGTH_SHORT).show();
        campos.put("id_kanji",2);
        campos.put("kanji","二");
        ingreso = bd.insert("kanji","id_kanji",campos);
        Toast.makeText(getApplicationContext(), "ingreso = "+ingreso, Toast.LENGTH_SHORT).show();
        //cabiar arriba por los datos de llegada de la base


        start = (Button) findViewById(R.id.btn_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Menu.class));
            }
        });
    }
}