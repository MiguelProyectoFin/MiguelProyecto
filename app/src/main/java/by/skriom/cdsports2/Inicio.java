package by.skriom.cdsports2;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import by.skriom.cdsports2.foto.AdaptadorFoto;
import by.skriom.cdsports2.foto.Foto;
import by.skriom.cdsports2.foto.daoFoto;
import by.skriom.cdsports2.usuario.Usuario;
import by.skriom.cdsports2.usuario.daoUsuario;

public class Inicio extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    int id;
    Usuario u;
    daoUsuario dao;
    String usuario;

    daoFoto daoFoto;
    AdaptadorFoto adapter;
    Foto c;
    ImageView head_image;
    ImageView d_foto;
    MediaController mediaController;
    private static final String VIDEO_DIRECTORY = "/fotos";
    private int GALLERY = 1, CAMERA = 2;
    //File video_refencia_blob, video_escena_blob;
    String ruta_referencia = "", ruta_escena = "";
    private  static final int PERMISSION_REQUEST_CODE = 7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        daoFoto = new daoFoto(this);
        Bundle b = getIntent().getExtras();
        id = b.getInt("Id");
        dao = new daoUsuario(this);
        u = dao.getUsuarioById(id);
        usuario = u.getNombre() + " " + u.getApellidos();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_editar, R.id.nav_reservar, R.id.nav_clase, R.id.nav_horario)
                .setDrawerLayout(drawer)
                .build();

        View headerView = navigationView.getHeaderView(0);
//        head_image = (ImageView) headerView.findViewById(R.id.head_foto);

        Foto x = daoFoto.getFotoById(id);
        if (x != null) {
            Uri path = Uri.parse(x.getReferencia());
            head_image.setImageURI(path);
            head_image.requestFocus();
        }


        TextView name = (TextView) headerView.findViewById(R.id.head_nombre);
        TextView email = (TextView) headerView.findViewById(R.id.head_correo);
        name.setText(u.getNombre() + " " + u.getApellidos());
        email.setText(u.getUsuario());

        adapter = new AdaptadorFoto(this, daoFoto, id);

/*
        head_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFotoFromGallary();

                //Dialogo de agregar  dialogo.xml
                adapter.setRuta_escena("");
                adapter.setRuta_referencia("");
                final Dialog dialogo=new Dialog(Inicio.this,android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
                dialogo.setTitle("Nuevo registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dfoto);
                dialogo.show();

                d_foto=(ImageView)dialogo.findViewById(R.id.d_foto_usuario);
                final Button referencia=(Button)dialogo.findViewById(R.id.d_referencia_toma);
                Button ver_referencia=(Button) dialogo.findViewById(R.id.d_ver_referencia);
                Button guardar=(Button)dialogo.findViewById(R.id.d_agregar_toma);
                guardar.setText("Agregar");
                Button cancelar=(Button)dialogo.findViewById(R.id.d_cancelar_toma);
                ver_referencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ruta_referencia.equals("") ) {
                            AlertDialog.Builder del = new AlertDialog.Builder(Inicio.this);
                            del.setMessage("No hay vídeo almacenado");
                            del.setCancelable(true);
                            del.show();
                        } else if(!ruta_referencia.equals("")) {
                            Uri path = Uri.parse(ruta_referencia);
                            d_foto.setImageURI(path);
                           /*
                            d_foto.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                        @Override
                                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                            mediaController = new MediaController(MainToma.this);
                                            video.setMediaController(mediaController);
                                            mediaController.setAnchorView(video);
                                            Log.v("MEDIA CONTROLLER ", "SE EJECUTA");
                                        }
                                    });
                                }
                            });
                            video.start();
                            video.requestFocus();
                        }
            }
        });


 */
/*
                referencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //showPictureDialog();
                        chooseFotoFromGallary();

                    }
                });

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            //Log.i("Click", "AGREGAR TOMA" );
                            c=new Foto(ruta_referencia,id);
                            daoFoto.insertar(c);
                            adapter.notifyDataSetChanged();
                            dialogo.dismiss();
                        }catch (Exception e){
                            // Toast.makeText(getApplication(),"ERROR", Toast.LENGTH_SHORT).show();
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
        });*/


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cerrar_sesion:
                this.salir();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void salir() {
        this.finish();
        Intent a = new Intent(Inicio.this, MainActivity.class);
        startActivity(a);
        this.overridePendingTransition(R.anim.nav_default_enter_anim, R.anim.nav_default_exit_anim);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("SELECCIONAR FOTO");
        String[] pictureDialogItems = {
                "GALERÍA",
                "ABRIR CÁMARA"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                chooseFotoFromGallary();
                                break;
                            case 1:
                                takeFotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void chooseFotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takeFotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Log.d("CODIGO RESULTADO",""+resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            //Log.d("what","CANCELAR GALERIA");
            return;
        }
        if (requestCode == GALLERY) {
            String selectedVideoPath = "";
            try {
                Log.d("RESULTADO->", "GALERIA");
                if (data != null) {
                    Uri contentURI = data.getData();
                    selectedVideoPath = getPath(contentURI);
                    adapter.setRuta_referencia(selectedVideoPath);
                    Log.d("RUTA DEL VÍDEO ", selectedVideoPath);
                    ruta_referencia = saveImageToInternalStorage(selectedVideoPath);
                    head_image.setImageURI(contentURI);
                    head_image.requestFocus();
                    Foto f = new Foto(ruta_referencia, id);
                    daoFoto.insertar(c);
                }
            } catch (Exception e) {
                adapter.setRuta_referencia(selectedVideoPath);
            }

        } else if (requestCode == CAMERA) {
            String recordedVideoPath = "";
            try {
                Uri contentURI = data.getData();
                recordedVideoPath = getPath(contentURI);
                adapter.setRuta_escena(recordedVideoPath);
                ruta_escena = saveImageToInternalStorage(recordedVideoPath);
                head_image.setImageURI(contentURI);
                head_image.requestFocus();
                Foto f = new Foto(ruta_escena, id);
                daoFoto.insertar(c);
            } catch (Exception e) {
                adapter.setRuta_escena(recordedVideoPath);
            }
        }
    }

    public byte[] convertirFileToArray(File file) {
        byte[] b = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
        } catch (FileNotFoundException e) {
            Log.v("ERROR", "ERROR 1 AL CONVETIR VIDEO A BYTES");
        } catch (IOException e1) {
            Log.v("ERROR", "ERROR 2 AL CONVETIR VIDEO A BYTES");
        }
        return b;
    }

    private String saveImageToInternalStorage(String filePath) {
        String ruta = "";
        File newfile = null;
        try {
            File currentFile = new File(filePath);

            File wallpaperDirectory = new File(Environment.getExternalStorageDirectory() + VIDEO_DIRECTORY);
            newfile = new File(wallpaperDirectory, Calendar.getInstance().getTimeInMillis() + ".jpg");
            ruta = newfile.getPath();
            Log.v("RUTA ABSOLUTA-> ", newfile.getAbsolutePath());
            Log.v("RUTA-> ", newfile.getPath());
/*
            if (ContextCompat.checkSelfPermission(Inicio.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                createDirectory(VIDEO_DIRECTORY);

            } else {

                askPermission();
            }
            Log.v("SE CREO CARPETA ", wallpaperDirectory.getPath());

 */

        File path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);


            if (!path.isDirectory()) {
                path.mkdirs();
              /*
                String newFolder = "/fotos"; //cualquierCarpeta es el nombre de la Carpeta que vamos a crear
                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                File myNewFolder = new File(extStorageDirectory + newFolder);
                myNewFolder.mkdirs();
                //wallpaperDirectory.mkdir();
                Log.v("SE CREO CARPETA ", wallpaperDirectory.getPath());

               */
            }


            if (currentFile.exists()) {
                InputStream in = new FileInputStream(currentFile);
                OutputStream out = new FileOutputStream(newfile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                Log.v("VIDEO", "IMAGEN ALMACENADO CORRECTAMENTE");
            } else {
                Log.v("VIDEO", "ERROR AL ALMACENAR VÍDEO.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ruta;
    }

    private void askPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                createDirectory(VIDEO_DIRECTORY);
            } else {
                Toast.makeText(Inicio.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void createDirectory(String folderName) {

        File file = new File(Environment.getExternalStorageDirectory(), folderName);

        if (!file.exists()) {

            file.mkdir();

            Toast.makeText(Inicio.this, "Successful", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(Inicio.this, "Folder Already Exists", Toast.LENGTH_SHORT).show();


        }


    }


    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


}