package com.example.kr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kr.baseManage.create;
import com.example.kr.clases.objetoskanji;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    //BASE DE DATOS
    SQLiteDatabase bd;
    create nuevabase;
    ContentValues campos;
    //instancia de objetos del activity
    Button start;
    //objetos externos
    ArrayList<objetoskanji> lisdkanji;
    //propio de la clase
    String urlusurio;
    RequestQueue n_requeriminto;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        campos = new ContentValues();
        nuevabase = new create(this,"bd_kanji",null,2);
        bd = nuevabase.getWritableDatabase();
        urlusurio = getString(R.string.api_kanjis);
        lisdkanji = new ArrayList<>();

        start = (Button) findViewById(R.id.btn_start);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                cargar_kanji();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Menu.class));
            }
        });
    }

    public void cargar_kanji()
    {
        objetoskanji kj = new objetoskanji();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlusurio, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("datos");
                    Log.d("main activity",response.getJSONArray("datos").toString() );
                    for(int i=0;i<=jsonArray.length()-1;i++) {
                        jsonObject = new JSONObject(jsonArray.getJSONObject(i).toString());
                        kj.setKanji(jsonObject.getString("kanji").toString());
                        kj.setId_kanji(Integer.parseInt(jsonObject.getString("id_kanji").toString()));
                        lisdkanji.add(kj);
                        /*llenado de la base de datos */
                        campos.put("id_kanji",Integer.parseInt(jsonObject.getString("id_kanji").toString()));
                        campos.put("kanji",jsonObject.getString("kanji").toString());
                        campos.put("nivel",0);
                        campos.put("sig_ingle",jsonObject.getString("ingles").toString());
                        campos.put("sig_español",jsonObject.getString("significado").toString());
                        campos.put("id_tipo",jsonObject.getString("id_tipo").toString());

                        Log.d("error al consultar",jsonObject.getString("kanji").toString());
                        Long ingreso = bd.insert("kanji","id_kanji",campos);
                        Toast.makeText(getApplicationContext(), "ingreso = "+lisdkanji.get(i).getId_kanji(), Toast.LENGTH_SHORT).show();
                        Log.d("main activity",kj.getKanji()+":"+kj.getId_kanji() );
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("tamaño de lista","es:"+lisdkanji.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //verifico el error
                Log.d("error al consultar",error.toString());
            }
        });
        n_requeriminto = Volley.newRequestQueue(this);
        n_requeriminto.add(jsonObjectRequest);
    }
}