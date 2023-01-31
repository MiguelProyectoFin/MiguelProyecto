package by.skriom.cdsports2.ui.asistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class daoAsistencia {//CLASE PARA GESTIÓN DE LA BD Y TABLA RESERVA CON LOS 4 METODOS BASICOS(INSERTAR, ELIMINAR, SELECCCIONAR Y EDITAR)
    SQLiteDatabase cx;
    ArrayList<Asistencia> lista=new ArrayList<Asistencia>();
    Asistencia c;
    Context ct;
    String nombreBD = "CDSports";
    String tabla = "create table if not exists Asistencia(idasistencia integer primary key autoincrement, usuario text, fecha text)";

    public daoAsistencia(Context c) {//METODO CONSTRUCTOR PARA CREAR BASE DE DATOS SINO EXISTE O ABRIR SI EXISTE
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, c.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }

    public String obtenerFechaHora(){
        Date date = new Date();
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy");
        Log.i("Click", "idasistencia" + hourdateFormat.format(date));
        return hourdateFormat.format(date);
    }

    public boolean insertar(Asistencia c) {//insertar reserva de bd
        ContentValues contenedor=new ContentValues();
        contenedor.put("usuario",c.getUsuario());
        contenedor.put("fecha", obtenerFechaHora());
        Log.i("Click", "SE INSERTO RESERVA" );
        return (cx.insert("Asistencia",null,contenedor))>0;

    }

    public boolean editar(Asistencia c) {// EDITAR RESERVA EN BD
        ContentValues contenedor=new ContentValues();
        contenedor.put("usuario",c.getUsuario());
        contenedor.put("fecha", c.getFecha());
        return (cx.update("Asistencia",contenedor,"idasistencia="+c.getIdasistencia(),null))>0;
    }
    //ELIMINAR RESERVA EN BD
    public boolean eliminar(int id) {
        return (cx.delete("Asistencia","idasistencia="+id,null))>0;
    }

    //OBTENER EN UN ARRAY TODAS LAS RESERVAS DE LA TABLAS RESERVA
    public ArrayList<Asistencia> verTodos() {
        lista.clear();
        Cursor cursor=cx.rawQuery("select * from Asistencia",null);
        if(cursor!=null &&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Asistencia(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
                Log.i("Click", "REGISTRO RESERVA" );
            }while (cursor.moveToNext());
        }
        return lista;
    }

    //OBTENER SOLO UNA RESERVA DE ACUERDO A SU  POSICION
    public Asistencia verUno(int posicion) {
        Cursor cursor=cx.rawQuery("select * from Asistencia",null);
        cursor.moveToPosition(posicion);
        c=new Asistencia(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2));
        return c;
    }
}
