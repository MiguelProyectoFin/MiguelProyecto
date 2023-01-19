package by.skriom.cdsports2.ui.reservar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class daoReserva {//CLASE PARA GESTIÓN DE LA BD Y TABLA RESERVA CON LOS 4 METODOS BASICOS(INSERTAR, ELIMINAR, SELECCCIONAR Y EDITAR)
    SQLiteDatabase cx;
    ArrayList<Reserva> lista=new ArrayList<Reserva>();
    Reserva c;
    Context ct;
    String nombreBD = "CDSports";
    String tabla = "create table if not exists Reserva(idreserva integer primary key autoincrement, clase text, horario text, usuario text)";

    public daoReserva(Context c) {//METODO CONSTRUCTOR PARA CREAR BASE DE DATOS SINO EXISTE O ABRIR SI EXISTE
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, c.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }



    public boolean insertar(Reserva c) {//insertar reserva de bd
        ContentValues contenedor=new ContentValues();
        contenedor.put("clase",c.getClase());
        contenedor.put("horario", c.getHorario());
        contenedor.put("usuario",c.getUsuario());
        Log.i("Click", "SE INSERTO RESERVA" );
        return (cx.insert("Reserva",null,contenedor))>0;

    }

    public boolean editar(Reserva c) {// EDITAR RESERVA EN BD
        ContentValues contenedor=new ContentValues();
        contenedor.put("clase",c.getClase());
        contenedor.put("horario", c.getHorario());
        contenedor.put("usuario",c.getUsuario());
        return (cx.update("Reserva",contenedor,"idreserva="+c.getIdreserva(),null))>0;
    }
    //ELIMINAR RESERVA EN BD
    public boolean eliminar(int id) {
        return (cx.delete("Reserva","idreserva="+id,null))>0;
    }

    //OBTENER EN UN ARRAY TODAS LAS RESERVAS DE LA TABLAS RESERVA
    public ArrayList<Reserva> verTodos(String usuario) {
        lista.clear();
        Cursor cursor=cx.rawQuery("select * from Reserva where usuario='"+usuario+"'",null);
        if(cursor!=null &&cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Reserva(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
                Log.i("Click", "REGISTRO RESERVA" );
            }while (cursor.moveToNext());
        }
        return lista;
    }

    //OBTENER SOLO UNA RESERVA DE ACUERDO A SU  POSICION
    public Reserva verUno(int posicion) {
        Cursor cursor=cx.rawQuery("select * from Reserva",null);
        cursor.moveToPosition(posicion);
        c=new Reserva(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return c;
    }
}
