package by.skriom.cdsports2.foto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import by.skriom.cdsports2.usuario.Usuario;

public class daoFoto {
    SQLiteDatabase cx;
    ArrayList<Foto> lista = new ArrayList<Foto>();
    Foto c;
    Context ct;
    String nombreBD = "CDSports";
    String tabla = "create table if not exists Fotos2(id_foto integer primary key autoincrement, referencia text,id_usuario integer)";

    public daoFoto(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, c.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }

    public boolean insertar(Foto c) {
        ContentValues contenedor;
        int res = 0;
        if(getFotoById(c.getId_usuario()).getId_foto()>0){
            c.setId_foto(getFotoById(c.getId_usuario()).getId_foto());
            editar(c);
            return res>0;
        }else {

            try {
                contenedor = new ContentValues();
                contenedor.put("referencia", c.getReferencia());
                contenedor.put("id_usuario", c.getId_usuario());
                res = (int) cx.insert("Fotos2", null, contenedor);
                // Log.i("Click", "SE AGREGO Foto CORRECTAMENTE");
            } catch (Exception e) {
                //  Log.i("Click", "ERROR AL AGREGAR Foto");
            }
            return res > 0;
/*
        String sql = "INSERT INTO Foto (angulo, jugadora, referencia, escena, id_sesion) VALUES(?,?,?,?,?)";
        SQLiteStatement insert = cx.compileStatement(sql);
        insert.clearBindings();
        insert.bindString(1, c.getAngulo());
        insert.bindString(2, c.getJugadora());
        insert.bindBlob(3, c.getRefencia());
        insert.bindBlob(4, c.getRefencia());
        insert.bindLong(5,c.getId_sesion());
        insert.executeInsert();
        cx.close();
        */
        }
    }

    public ArrayList<Foto> selectFoto() {
        ArrayList<Foto> lista = new ArrayList<Foto>();
        lista.clear();
        Cursor cr = cx.rawQuery("select * from Fotos2", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Foto f = new Foto();

                f.setId_foto(cr.getInt(0));
                f.setReferencia(cr.getString(1));
                f.setId_usuario(cr.getInt(2));
                Log.v(" ITEM -> ", f.toString());
                lista.add(f);
            } while (cr.moveToNext());
        }
        return lista;
    }

    public Foto getFotoById(int id) {
        lista = selectFoto();
        for (Foto f : lista) {
            if (f.getId_usuario()==id) {
                return f;
            }
        }
        return null;
    }

    public boolean editar(Foto c) {
        ContentValues contenedor = new ContentValues();
        contenedor.put("referencia", c.getReferencia());
        contenedor.put("id_usuario", c.getId_usuario());
        return (cx.update("Fotos2", contenedor, "id_foto=" + c.getId_foto(), null)) > 0;
    }

    public boolean eliminar(int id) {
        return (cx.delete("Fotos2", "id_foto=" + id, null)) > 0;
    }

    public Foto verUno(int posicion) {
        Cursor cursor = cx.rawQuery("select * from Fotos2", null);
        cursor.moveToPosition(posicion);
        c = new Foto(cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2));
        return c;
    }
}

