package by.skriom.cdsports2.ui.editar;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import by.skriom.cdsports2.Home;
import by.skriom.cdsports2.Inicio;
import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class EditarFragment extends Fragment {

    EditText ediUser,ediPass,ediNombre,ediApellido, ediFec,ediTel, ediDir;
    Button btnActualizar,btnCancelar;
    int id=0;
    Usuario u;
    daoUsuario dao;
    Intent x;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_editar, container, false);
        ediUser=(EditText) root.findViewById(R.id.EdiUser);
        ediPass=(EditText) root.findViewById(R.id.EdiPass);
        ediNombre=(EditText) root.findViewById(R.id.EdiNombre);
        ediApellido=(EditText) root.findViewById(R.id.EdiApellido);
        ediFec=(EditText) root.findViewById(R.id.EdiFecha);
        ediTel=(EditText) root.findViewById(R.id.EdiTelefono);
        ediDir=(EditText) root.findViewById(R.id.EdiDireccion);
        btnActualizar=(Button)root.findViewById(R.id.btnEdiActualizar);
        btnCancelar=(Button)root.findViewById(R.id.btnEdiCancelar);
        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(getActivity());
        u = dao.getUsuarioById(id);
        ediUser.setText(u.getUsuario());
        ediPass.setText(u.getPassword());
        ediNombre.setText(u.getNombre());
        ediApellido.setText(u.getApellidos());
        ediFec.setText(u.getFecha());
        ediTel.setText(u.getTelefono());
        ediDir.setText(u.getDireccion());
        btnActualizar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                u.setUsuario(ediUser.getText().toString());
                u.setPassword(ediPass.getText().toString());
                u.setNombre(ediNombre.getText().toString());
                u.setApellidos(ediApellido.getText().toString());
                u.setFecha(ediFec.getText().toString());
                u.setTelefono(ediTel.getText().toString());
                u.setDireccion(ediDir.getText().toString());
                if (!u.isNull()){
                    Toast.makeText(getActivity(),"Error: campos vacios", Toast.LENGTH_LONG).show();
                }else if (dao.updateUsuario(u)){
                    Toast.makeText(getActivity(),"Actualizacion exitosa!!!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),"No se puede actualizar", Toast.LENGTH_LONG).show();
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment,).commit();


            }
        });


//RECIBE ID DE USUARIO PARA FILTRAR LOS DATOS Y MOSTRAR EN EL FORMULARIO


        return root;

    }
}