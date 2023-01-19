package by.skriom.cdsports2.foto;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.File;
import java.util.ArrayList;

public class AdaptadorFoto extends BaseAdapter {
    ArrayList<Toma> lista;
    daoFoto dao;
    Toma c;
    Activity a;
    int id = 0;
    int id_usuario = 0;
    MediaController mc;
    VideoView editar_video;
    MediaController mediaController;
    private static final String VIDEO_DIRECTORY = "/fotos";
    private int GALLERY = 1, CAMERA = 2;
    File video_refencia_blob, video_escena_blob;
    String ruta_referencia="", ruta_escena="";

    public AdaptadorFoto(Activity a, daoFoto dao, int id_usuario) {
        this.lista = lista;
        this.a = a;
        this.dao = dao;
        this.id_usuario = id_usuario;
    }

    public String getRuta_referencia() {
        return ruta_referencia;
    }

    public void setRuta_referencia(String ruta_referencia) {
        this.ruta_referencia = ruta_referencia;
    }

    public String getRuta_escena() {
        return ruta_escena;
    }

    public void setRuta_escena(String ruta_escena) {
        this.ruta_escena = ruta_escena;
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
    public Toma getItem(int i) {
        c = lista.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = lista.get(i);
        return c.getId_toma();
    }


    public void videoGaleria() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        a.startActivityForResult(galleryIntent, GALLERY);
    }

    private void videoCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       a.startActivityForResult(intent, CAMERA);
    }
    public static int obtenerPosicionItem(Spinner spinner, String texto) {
        //Creamos la variable posicion y lo inicializamos en 0
        int posicion = 0;
        //Recorre el spinner en busca del ítem que coincida con el parametro `String fruta`
        //que lo pasaremos posteriormente
        for (int i = 0; i < spinner.getCount(); i++) {
            //Almacena la posición del ítem que coincida con la búsqueda
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(texto)) {
                posicion = i;
            }
        }
        //Devuelve un valor entero (si encontro una coincidencia devuelve la
        // posición 0 o N, de lo contrario devuelve 0 = posición inicial)
        return posicion;
    }


    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;



        return v;
    }
}
