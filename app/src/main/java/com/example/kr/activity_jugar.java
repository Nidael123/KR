package com.example.kr;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kr.baseManage.create;
import com.example.kr.clases.objetoskanji;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class activity_jugar extends AppCompatActivity {

    Button btn_next,btn_preview;
    TextView txtkanji;
    create nuevabase;
    ArrayList<objetoskanji> list_kanjis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);
        list_kanjis = new ArrayList<objetoskanji>();
        nuevabase = new create(this,"bd_kanji",null,2);
        btn_next = findViewById(R.id.btn_sgt);
        btn_preview = findViewById(R.id.btn_ant);
        txtkanji = findViewById(R.id.txt_prueba);
        carga_kanjis();
        //generar las funciones para que cambien entre los diferentes kanjis
        txtkanji.setText(list_kanjis.get(0).getKanji());
    }


    public void carga_kanjis()
    {
        objetoskanji ingresokanji;
        ingresokanji = new objetoskanji();
        SQLiteDatabase db = nuevabase.getReadableDatabase();
        try {
            //hacer el bucle para llenbar el arraylist
            Cursor cursor = db.rawQuery("select * from kanji", null);
            cursor.moveToFirst();
            ingresokanji.setId_kanji(cursor.getInt(0));
            ingresokanji.setKanji(cursor.getString(1));

            list_kanjis.add(ingresokanji);
            Toast.makeText(getApplicationContext(), "kanji:" + cursor.getString(0), Toast.LENGTH_LONG).show();
            Log.d("kanji","kanji:" + cursor.getString(0));
            cursor.moveToNext();
        }
        catch (Exception e){

            Toast.makeText(getApplicationContext(), "error"+e.toString(), Toast.LENGTH_LONG).show();
        }
    }
}