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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {//clase principal para INICIAR SESION
EditText user,pass;
Button btnEntrar,btnRegistrar, btnSalir;
daoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.User);
        pass=(EditText)findViewById(R.id.Pass);
        btnEntrar=(Button)findViewById(R.id.btnEntrar);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);
        btnEntrar.setOnClickListener(this);
        btnRegistrar.setOnClickListener(this);
        dao=new daoUsuario(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnEntrar://BOTON PARA INICIAR SESION CON EL CORREO Y CONTRASEÑA
                String u=user.getText().toString();
                String p=pass.getText().toString();
                if(u.equals("")&&p.equals("")){
                    Toast.makeText(this,"Error: campos vacios", Toast.LENGTH_LONG).show();
                }else if (dao.login(u,p)==1){
                    Usuario ux=dao.getUsuario(u,p);
                    Toast.makeText(this, "Datos correctos", Toast.LENGTH_LONG).show();
                    Intent i2=new Intent(MainActivity.this,Inicio.class);
                    i2.putExtra("Id", ux.getId());
                    startActivity(i2);
                    finish();
                }else{
                    Toast.makeText(this, "Usuario y/o Password incorrectos", Toast.LENGTH_LONG).show();
                }
        break;
            case R.id.btnRegistrar://IR A LA INTERFAZ DE REGISTRAR
                Intent i=new Intent(MainActivity.this,Registrar.class);
                startActivity(i);
                break;

                
        }

    }
}
