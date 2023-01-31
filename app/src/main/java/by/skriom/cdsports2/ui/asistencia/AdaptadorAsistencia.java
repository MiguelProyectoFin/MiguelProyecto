package by.skriom.cdsports2.ui.asistencia;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class AdaptadorAsistencia extends BaseAdapter { //adapatador para rellenar listview
    ArrayList<Asistencia> lista;
    daoAsistencia dao;
    Asistencia c;
    Activity a;
    int id = 0;
    String usuario = "";
    daoUsuario daoUser;


    //METODO CONSTRUCTOR DE ADAPTADOR
    public AdaptadorAsistencia(Activity a, ArrayList<Asistencia> lista, daoAsistencia dao, daoUsuario daoUser, String usuario) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
        this.daoUser=daoUser;
        this.usuario = usuario;
    }

    //METODOS GETTERS AND SETTER DE ADAPTADOR
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Asistencia getItem(int i) {
        c = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = lista.get(i);
        return c.getIdasistencia();
    }

    //METODO PARA GESTIÓN DE LA VISTA DENTRO DEL LISTVIEW
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.iasistencia, null);
        }
        //INTERFAZ DEL CADA ELEMENTO DE LA LISTA DENTRO DEL LISTVIEW

        c = lista.get(posicion);
        TextView usuario = (TextView) v.findViewById(R.id.a_usuario);
        TextView fecha = (TextView) v.findViewById(R.id.a_fecha);
        usuario.setText(c.getUsuario());
        fecha.setText(c.getFecha());
       // usuario.setText(u.getNombre()+" "+u.getApellidos());



        return v;
    }
}
