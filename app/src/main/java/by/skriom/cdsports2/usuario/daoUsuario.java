package by.skriom.cdsports2.usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {//CLASE PARA GESTIÓN DE LA BD Y TABLA USUARIO CON LOS 4 METODOS BASICOS(INSERTAR, ELIMINAR, SELECCCIONAR Y EDITAR)
    Context c;
    Usuario u;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    String bd = "CDSports";
    String tabla = "create table if not exists usuario(id integer primary key autoincrement, usuario txt, pass text, nombre text, ap text, fecha text, phone text, dir text, foto text)";

    //crea o abre la bd
    public daoUsuario(Context c) {
        this.c = c;
        sql = c.openOrCreateDatabase(bd, c.MODE_PRIVATE, null);
        sql.execSQL(tabla);
        u = new Usuario();
    }

    //registra un nuevo usuario en la BD
    public boolean insertUsuario(Usuario u) {
        if (buscar(u.getUsuario()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("usuario", u.getUsuario());
            cv.put("pass", u.getPassword());
            cv.put("nombre", u.getNombre());
            cv.put("ap", u.getApellidos());
            cv.put("fecha", u.getFecha());
            cv.put("phone", u.getTelefono());
            cv.put("dir", u.getDireccion());
            return (sql.insert("usuario", null, cv) > 0);

        } else {
            return false;
        }
    }

    //BUSCAR UN USUARIO
    public int buscar(String u) {
        int x = 0;
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u)) {
                x++;
            }
        }
        return x;
    }
//DEVUELVE UN ARRAY CON TODOS LOS USUARIOS EN BD
    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setUsuario(cr.getString(1));
                u.setPassword(cr.getString(2));
                u.setNombre(cr.getString(3));
                u.setApellidos(cr.getString(4));
                u.setFecha(cr.getString(5));
                u.setTelefono(cr.getString(6));
                u.setDireccion(cr.getString(7));
                lista.add(u);
            } while (cr.moveToNext());
        }
        return lista;
    }

    //VERIFICA UN USUARIO PARA INICIAR SESION
    public int login(String u, String p) {
        int a = 0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(1).equals(u) && cr.getString(2).equals(p)) {
                    a++;

                }

            } while (cr.moveToNext());
        }
        return a;
    }

    //DEVUELVE UN USUARIO
    public Usuario getUsuario(String u, String p) {
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getUsuario().equals(u) && us.getPassword().equals(p)) {
                return us;
            }
        }
            return null;
        }

        //DEVUELVE UN USUARIO POR ID
    public Usuario getUsuarioById(int id) {
        lista = selectUsuarios();
        for (Usuario us : lista) {
            if (us.getId()==id) {
                return us;
            }
        }
        return null;
    }

    //ACTUALIZAR UN USUARIO
    public boolean updateUsuario(Usuario u) {
        ContentValues cv = new ContentValues();
        cv.put("usuario", u.getUsuario());
        cv.put("pass", u.getPassword());
        cv.put("nombre", u.getNombre());
        cv.put("ap", u.getApellidos());
        cv.put("fecha", u.getFecha());
        cv.put("phone", u.getTelefono());
        cv.put("dir", u.getDireccion());
        return (sql.update("usuario",cv,"id="+u.getId(),null) > 0);
    }

    //ELIMINA UN USUARIO
    public boolean deleteUsuario(int id){
        return (sql.delete("usuario", "id=" + id, null) > 0);
    }
    }

