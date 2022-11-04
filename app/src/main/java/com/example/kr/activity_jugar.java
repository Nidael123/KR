package com.example.kr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kr.baseManage.create;
import com.example.kr.clases.objetoskanji;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class activity_jugar extends AppCompatActivity {

    Button btn_next,btn_preview,btn_finish;
    TextView txtkanji;
    create nuevabase;
    ArrayList<objetoskanji> list_kanjis;

    int place;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        list_kanjis = new ArrayList<objetoskanji>();

        nuevabase = new create(this,"bd_kanji",null,2);
        btn_next = findViewById(R.id.btn_sgt);
        btn_preview = findViewById(R.id.btn_ant);
        btn_finish = findViewById(R.id.btn_finish);
        txtkanji = findViewById(R.id.txt_prueba);
        carga_kanjis();
        //generar las funciones para que cambien entre los diferentes kanjis
        //txtkanji.setText(list_kanjis.get(0).getKanji());
        place = 0;
        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place == 0)
                {
                    place = list_kanjis.size() - 1;
                    txtkanji.setText(list_kanjis.get(place).getKanji());
                    Log.d("preview",list_kanjis.get(place).getKanji()+":"+place);
                }
                else
                {
                    place = place - 1;
                    txtkanji.setText(list_kanjis.get(place).getKanji());
                    Log.d("preview",list_kanjis.get(place).getKanji()+":"+place);
                }

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(place == list_kanjis.size()-1 )
                {
                    place = 0;
                    txtkanji.setText(list_kanjis.get(place).getKanji());
                    Log.d("next",list_kanjis.get(place).getKanji()+":"+place);
                }
                else
                {
                    place = place+1;
                    txtkanji.setText(list_kanjis.get(place).getKanji());
                    Log.d("next",list_kanjis.get(place).getKanji()+":"+place);
                }
            }
        });
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_jugar.this,Menu.class));
                finish();
            }
        });
    }


    public void carga_kanjis()
    {
        int r=1;
        objetoskanji ingresokanji;
        ingresokanji = new objetoskanji();
        SQLiteDatabase db = nuevabase.getReadableDatabase();
        Log.d("for","kanji:LLL");
        try {
            //hacer el bucle para llenbar el arraylist
            Cursor cursor = db.rawQuery("select id_kanji,kanji from kanji", null);

            Log.d("for","cantidad:" + cursor.getCount());
            //Nos aseguramos de que existe al menos un registro
            if (cursor.move(r)) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    r++;
                    ingresokanji.setId_kanji(cursor.getInt(0));
                    ingresokanji.setKanji(cursor.getString(1));
                    //list_kanjis.add(ingresokanji);
                    llenararray(cursor.getInt(0),cursor.getString(1));
                    Log.d("dowhiole",cursor.getString(1));
                } while(cursor.moveToNext());
            }
            else Log.d("dowhiole","nmo hay data");
            /*while(cursor.moveToNext())
            {
                ingresokanji.setId_kanji(cursor.getInt(0));
                ingresokanji.setKanji(cursor.getString(1));
                Log.d("kanjisguardados",cursor.getString(1));
            }/*
            for (int i = 0;i >= cursor.getCount()-1;i++) {

                Log.d("for","kanji:" + cursor.getString(i));
                list_kanjis.add(ingresokanji);
                Log.d("i","i:" + i);
                cursor.move(i);
            }*/
            //Log.d("kanji","kanji:" + cursor.getCount());

            //Toast.makeText(getApplicationContext(), "kanji:" + cursor.getString(0), Toast.LENGTH_LONG).show();
            //Log.d("kanji","kanji:" + cursor.getString(0));

        }
        catch (Exception e){
            Log.d("V","V" + e.toString());
            Toast.makeText(getApplicationContext(), "error"+e.toString(), Toast.LENGTH_LONG).show();
        }


    }

    public  void  llenararray(int id, String kanji)
    {
        objetoskanji kj = new objetoskanji();
        kj.setId_kanji(id);
        kj.setKanji(kanji);
        list_kanjis.add(kj);
    }
}