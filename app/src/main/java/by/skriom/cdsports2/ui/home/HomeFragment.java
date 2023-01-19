package by.skriom.cdsports2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import by.skriom.cdsports2.Inicio;
import by.skriom.cdsports2.R;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    int id;
    Usuario u;
    daoUsuario dao;
    String usuario;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.home, container, false);
        Bundle b = getActivity().getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(getActivity());
        u = dao.getUsuarioById(id);
        usuario=u.getNombre() + " " + u.getApellidos();

        final TextView textView = root.findViewById(R.id.nombreUsuario);
        textView.setText(usuario);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}