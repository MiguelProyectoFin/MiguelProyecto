package by.skriom.cdsports2.ui.reservar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class AdaptadorReserva extends BaseAdapter { //adapatador para rellenar listview
    ArrayList<Reserva> lista;
    daoReserva dao;
    Reserva c;
    Activity a;
    int id = 0;
    String usuario = "";
    daoUsuario daoUser;


    //METODO CONSTRUCTOR DE ADAPTADOR
    public AdaptadorReserva(Activity a, ArrayList<Reserva> lista, daoReserva dao, daoUsuario daoUser, String usuario) {
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
    public Reserva getItem(int i) {
        c = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = lista.get(i);
        return c.getIdreserva();
    }

    //METODO PARA GESTIÓN DE LA VISTA DENTRO DEL LISTVIEW
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null) {
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.ireserva, null);
        }
        //INTERFAZ DEL CADA ELEMENTO DE LA LISTA DENTRO DEL LISTVIEW

        c = lista.get(posicion);
        TextView clase = (TextView) v.findViewById(R.id.r_clase);
        TextView horario = (TextView) v.findViewById(R.id.r_horario);
        TextView usuario = (TextView) v.findViewById(R.id.r_usuario);
        final Button editar = (Button) v.findViewById(R.id.editar_reserva);
        Button eliminar = (Button) v.findViewById(R.id.eliminar_reserva);
        clase.setText(c.getClase());
        horario.setText(c.getHorario());
        Usuario u=daoUser.getUsuarioById(Integer.parseInt(c.getUsuario()));
        usuario.setText(u.getNombre()+" "+u.getApellidos());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

//EDITAR UNA RESERVA CON UN DIALOGO
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo de editar dialogo.xml
                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialogo = new Dialog(a, android.R.style.Theme_DeviceDefault_Light_Dialog);
                dialogo.setTitle("Editar Reserva");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dreserva);
                dialogo.show();
                final Spinner clase = (Spinner) dialogo.findViewById(R.id.d_clase);
                final Spinner horario = (Spinner) dialogo.findViewById(R.id.d_horario);
                Button guardar = (Button) dialogo.findViewById(R.id.d_agregar_reserva);
                guardar.setText("Guardar");
                Button cancelar = (Button) dialogo.findViewById(R.id.d_cancelar_reserva);
                c = lista.get(pos);
                setId(c.getIdreserva());

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            c = new Reserva(getId(), clase.getSelectedItem().toString(), horario.getSelectedItem().toString(), getUsuario());
                            dao.editar(c);
                            lista = dao.verTodos(getUsuario());
                            notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            // Toast.makeText(a,"ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogo.dismiss();
                    }
                });


            }
        });
        // DIALOGO PARA ELIMINAR RESERVA
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogo para confirma SI/NO
                int pos = Integer.parseInt(view.getTag().toString());
                c = lista.get(pos);
                setId(c.getIdreserva());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("Estas seguro de eliminar Reserva?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dao.eliminar(getId());
                        lista = dao.verTodos(getUsuario());
                        notifyDataSetChanged();
                    }
                });
                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });


        return v;
    }
}
