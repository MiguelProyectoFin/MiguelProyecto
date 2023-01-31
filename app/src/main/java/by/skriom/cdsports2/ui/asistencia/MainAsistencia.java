package by.skriom.cdsports2.ui.asistencia;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class MainAsistencia extends AppCompatActivity { //CLASE PRINCIPAL PARA GESTION DE RESERVAS
    daoAsistencia dao;
    AdaptadorAsistencia adapter;
    ArrayList<Asistencia> lista;
    Asistencia c;
    Usuario u;
    String usuario;
    int id=0;
    daoUsuario daoUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lreserva);
        this.setTitle("GESTIÓN DE RESERVAS");
        //RECIBE EL ID DE USUARIO QUE INICIO SESION
        id =getIntent().getExtras().getInt("Id");
        daoUser = new daoUsuario(this);
        u = daoUser.getUsuarioById(id);
        usuario=u.getNombre()+" "+u.getApellidos();
        Log.i("Click", "usuario" + usuario);
        dao=new daoAsistencia(MainAsistencia.this);
        lista=dao.verTodos();
        adapter=new AdaptadorAsistencia(this,lista,dao,daoUser, usuario);
        ListView list=(ListView)findViewById(R.id.lista_jugadora);
        Button agregar=(Button)findViewById(R.id.agregar_reserva);
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
                final Dialog dialogo=new Dialog(MainAsistencia.this,android.R.style.Theme_DeviceDefault_Light_Dialog);
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
                            c=new Asistencia(usuario);
                            dao.insertar(c);
                            lista=dao.verTodos();
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


    }
}
