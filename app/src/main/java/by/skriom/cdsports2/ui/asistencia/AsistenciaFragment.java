package by.skriom.cdsports2.ui.asistencia;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class AsistenciaFragment extends Fragment {

    daoAsistencia dao;
    AdaptadorAsistencia adapter;
    ArrayList<Asistencia> lista;
    Asistencia c;
    Usuario u;
    String usuario;
    int id=0;
    daoUsuario daoUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=  inflater.inflate(R.layout.fragment_asistencia, container, false);
       // root.setTitle("GESTIÓN DE RESERVAS");
        //RECIBE EL ID DE USUARIO QUE INICIO SESION
        id =getActivity().getIntent().getExtras().getInt("Id");
        daoUser = new daoUsuario(getActivity());
        u = daoUser.getUsuarioById(id);
        usuario=""+id;
        Log.i("Click", "usuario" + usuario);
        dao=new daoAsistencia(getActivity());
        lista=dao.verTodos();
        adapter=new AdaptadorAsistencia(getActivity(),lista,dao,daoUser, usuario);
        ListView list=(ListView)root.findViewById(R.id.lista_asistencia);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Click", "CLICK ELEMENTO SESSIONX|" );
            }
        });


        return root;
    }
}