package by.skriom.cdsports2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class Home extends AppCompatActivity implements View.OnClickListener {
    Button btnEditar, btnReserva, btnHorario, btnSalir, btnEjercicio;
    TextView nombre;
    int id = 0;
    Usuario u;
    daoUsuario dao;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        nombre = (TextView) findViewById(R.id.nombreUsuario);
        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(this);
        u = dao.getUsuarioById(id);
        nombre.setText(u.getNombre() + " " + u.getApellidos());
    }

    @Override
    public void onClick(View v) {//GESTIÓN DE MENU PRINCIPAL DE INICIO PARA IR A DIFERENTES INTERFACES
      /*  switch (v.getId()) {
            case R.id.btnEditar:
                Intent a = new Intent(Home.this, Editar.class);
                a.putExtra("Id",id);
                startActivity(a);
                break;
            case R.id.btnReserva:
                Intent c3 = new Intent(Home.this, MainReserva.class);
                c3.putExtra("Id",id);
                startActivity(c3);

                break;
            case R.id.btnEjercicio:
                Intent c2 = new Intent(Home.this, Ejercicios.class);
                startActivity(c2);
                break;
            case R.id.btnSalir:
                Intent i2 = new Intent(Home.this, MainActivity.class);
                startActivity(i2);
                finish();
                break;
        }*/
    }
}
