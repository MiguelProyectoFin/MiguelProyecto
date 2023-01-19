package by.skriom.cdsports2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class Registrar extends AppCompatActivity implements View.OnClickListener {//CLASE PARA REGISTRAR UN NUEVO USUARIO
    EditText us, pas, nom, ap, fec, tel, dir;
    Button reg, can;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar);
        us = (EditText) findViewById(R.id.RegUser);
        pas = (EditText) findViewById(R.id.RegPass);
        nom = (EditText) findViewById(R.id.RegNombre);
        ap = (EditText) findViewById(R.id.RegApellido);
        fec = (EditText) findViewById(R.id.RegFecha);
        tel = (EditText) findViewById(R.id.RegTelefono);
        dir = (EditText) findViewById(R.id.RegDireccion);
        reg = (Button) findViewById(R.id.btnRegRegistrar);
        can = (Button) findViewById(R.id.btnRegCancelar);
        reg.setOnClickListener(this);
        can.setOnClickListener(this);
        dao = new daoUsuario(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegRegistrar://BOTON QUE OBTIENE LOS DATOS DEL FORMULARIO Y LOS ENVIA A LA BD
                Usuario u = new Usuario();
                u.setUsuario(us.getText().toString());
                u.setPassword(pas.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellidos(ap.getText().toString());
                u.setFecha(fec.getText().toString());
                u.setTelefono(tel.getText().toString());
                u.setDireccion(dir.getText().toString());
                if (!u.isNull()) {
                    Toast.makeText(this, "Error: campos vacios", Toast.LENGTH_LONG).show();
                } else if (dao.insertUsuario(u)) {
                    Toast.makeText(this, "Registro exitoso!!!", Toast.LENGTH_LONG).show();
                    Intent i2 = new Intent(Registrar.this, MainActivity.class);
                    startActivity(i2);
                    finish();
                } else {
                    Toast.makeText(this, "Usuario ya registrado!!!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btnRegCancelar://CANCELAR, REGRESA A LA ANTERIOR INTERFAZ
                Intent i = new Intent(Registrar.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
