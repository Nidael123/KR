package com.example.kr.baseManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class create extends SQLiteOpenHelper {
    //String t_kanji = "create table kanji(id_kanji int ,kanji varchar(25),significado varchar(25),significado_ingles varchar(50),id_tipo int,level int)";
    String t_kanji = "create table kanji(id_kanji int ,kanji varchar(25),nivel int default 0,sig_ingle varchar(100),sig_español varchar(100),id_tipo int, estado bool default 0)";
    //tipo 12345678910  de kanji nivel 1 - 5 vacobulario de nivel 6 - 10
    public create(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase datebase) {datebase.execSQL(t_kanji);}

    @Override
    public void onUpgrade(SQLiteDatabase datebase, int versionanterior, int vesionnueva) {
        datebase.execSQL("drop table if exists kanji ");//borro las tablas en el caso de existir
        onCreate(datebase);
    }


}