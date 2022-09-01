package com.example.kr.baseManage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class create extends SQLiteOpenHelper {
    //String t_kanji = "create table kanji(id_kanji int ,kanji varchar(25),significado varchar(25),significado_ingles varchar(50),id_tipo int,level int)";
    String t_kanji = "create table kanji(id_kanji int ,kanji varchar(25))";

    public create(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase datebase) {datebase.execSQL(t_kanji);}

    @Override
    public void onUpgrade(SQLiteDatabase datebase, int versionanterior, int vesionnueva) {
        datebase.execSQL("drop table if exists table_kanji ");//borro las tablas en el caso de existir
        onCreate(datebase);
    }
}
