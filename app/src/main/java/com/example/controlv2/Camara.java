package com.example.controlv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class Camara extends AppCompatActivity {
    Button btncamara;
    ImageButton imageButton;
    ImageView visor;
    String ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camara);
        btncamara = findViewById(R.id.btncamara);
        visor= findViewById(R.id.iv_visor);
        if (ContextCompat.checkSelfPermission(Camara.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.
                checkSelfPermission(Camara.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Camara.
                    this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA}, 1000);
        }
            btncamara.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    camara();
                }
            });

        }
        //metodo para abrir la camara
        private  void  camara(){
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(intent.resolveActivity(getPackageManager())!=null){
                File fotoArchivo = null;
                try {
                    fotoArchivo= GuardarImagen();
                }catch (IOException ex) {
                    Log.e("error",ex.toString());
                }
                if(fotoArchivo !=null){
                    Uri uri = FileProvider.getUriForFile(this,"com.example.controlv2.fileprovider", fotoArchivo);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent,1);
                }



            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent media) {
            super.onActivityResult(requestCode, resultCode, media);
            if (requestCode ==1 && resultCode == RESULT_OK){
                // Bundle extras = data.getExtras();
                // Bitmap imgBitmap =(Bitmap) extras.get("data");
                Bitmap imgBitmap = BitmapFactory.decodeFile(ruta);
                visor.setImageBitmap(imgBitmap);

            }
        }
        private File GuardarImagen() throws IOException{
            String nombreFoto= "foto_";
            File directorio= getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File foto = File.createTempFile(nombreFoto,".jpg", directorio);
            ruta=foto.getAbsolutePath();
            return foto;

        }

    }