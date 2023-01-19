package by.skriom.cdsports2.ui.reservar;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class ReservarFragment extends Fragment {

    daoReserva dao;
    AdaptadorReserva adapter;
    ArrayList<Reserva> lista;
    Reserva c;
    Usuario u;
    String usuario;
    int id=0;
    daoUsuario daoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_reservar, container, false);
       // root.setTitle("GESTIÓN DE RESERVAS");
        //RECIBE EL ID DE USUARIO QUE INICIO SESION
        id =getActivity().getIntent().getExtras().getInt("Id");
        daoUser = new daoUsuario(getActivity());
        u = daoUser.getUsuarioById(id);
        usuario=""+id;
        Log.i("Click", "usuario" + usuario);
        dao=new daoReserva(getActivity());
        lista=dao.verTodos(usuario);
        adapter=new AdaptadorReserva(getActivity(),lista,dao,daoUser, usuario);
        ListView list=(ListView)root.findViewById(R.id.lista_jugadora);
        Button agregar=(Button)root.findViewById(R.id.agregar_reserva);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Click", "CLICK ELEMENTO SESSIONX|" );
            }
        });
        //DIALOGO PARA AGREGAR UNA NUEVA RESERVA
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialogo de agregar  dialogo.xml
                final Dialog dialogo=new Dialog(getActivity(),android.R.style.Theme_DeviceDefault_Light_Dialog);
                dialogo.setTitle("Nueva Reserva");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dreserva);
                dialogo.show();
                final Spinner clase=(Spinner)dialogo.findViewById(R.id.d_clase);
                final Spinner horario=(Spinner)dialogo.findViewById(R.id.d_horario);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar_reserva);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_cancelar_reserva);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            c=new Reserva(clase.getSelectedItem().toString(),horario.getSelectedItem().toString(),usuario);
                            dao.insertar(c);
                            lista=dao.verTodos(usuario);
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            //  t.makeText(getApplication(),"ERROR", Toast.LENGTH_SHORT).show();
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

        return root;
    }
}