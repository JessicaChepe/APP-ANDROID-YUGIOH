package com.example.proyecto.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class yugioh extends SQLiteOpenHelper {

    private static final String nombreDB = "yugioh.db";
    private static final int versionDB = 1;
    private static final String tableUser = "CREATE TABLE IF NOT EXISTS USUARIO (ID INTEGER, CORREO VARCHAR(255), CLAVE VARCHAR(255))";

    public yugioh(@Nullable Context context) {
        super(context, nombreDB, null, versionDB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVer, int newVer) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USUARIO");
        sqLiteDatabase.execSQL(tableUser);
    }

    //AGREGAR USUARIO
    public boolean agregarUsuario(int id, String correo, String clave){
        boolean agregado = false;
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("INSERT INTO USUARIO VALUES ("+id+",'"+correo+"','"+clave+"')");
            agregado = true;
        }
        return agregado;
    }

    //ELIMINAR USUARIO
    public boolean eliminarUsuario(int id){
        boolean eliminado = false;
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("DELETE FROM USUARIO WHERE ID = "+id);
            eliminado = true;
        }
        return eliminado;
    }

    //RECORDO SESION
    public boolean recordoUsuario(){
        boolean recordo = false;
        SQLiteDatabase db = getReadableDatabase();
        if(db != null){
            Cursor cursor = db.rawQuery("SELECT * FROM USUARIO", null);
            if(cursor.moveToNext()){
                recordo = true;
            }
        }
        return recordo;
    }

    //BUSCAR CAMPO
    public String buscarCampo(String campo){
        String data = null;
        SQLiteDatabase db = getReadableDatabase();
        String consulta = String.format("SELECT %s FROM USUARIO", campo);
        if(db != null){
            Cursor cursor = db.rawQuery(consulta, null);
            if(cursor.moveToNext()){
                data = cursor.getString(0);
            }
        }
        return data;
    }

    //ACTUALIZAR CAMPO
    public boolean actualizarCampo(int id, String llave, String valor){
        boolean actualizado = false;
        SQLiteDatabase db = getWritableDatabase();
        if(db != null){
            db.execSQL("UPDATE USUARIO SET "+llave+" = '"+valor+"' WHERE ID = "+id);
            db.close();
            actualizado = true;
        }
        return actualizado;
    }




}
