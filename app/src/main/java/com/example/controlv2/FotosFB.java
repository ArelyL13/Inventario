package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.controlv2.databinding.ActivityFotosFbBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FotosFB extends AppCompatActivity {
  ActivityFotosFbBinding binding;
  Uri imageUri;
  StorageReference storageReference;
  ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFotosFbBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.SelectImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        binding.Subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploatImage();

            }
        });
    }

    private void UploatImage() {
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Uploading file...");
        progressDialog.show();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String filename= formatter.format(now);
        storageReference= FirebaseStorage.getInstance().getReference("image/"+filename);

        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        binding.firebaseImage.setImageURI(null);
                        Toast.makeText(FotosFB.this,"imagen subida",Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(FotosFB.this,"fallo subida",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100 && data != null && data.getData() != null){
            imageUri= data.getData();
            binding.firebaseImage.setImageURI(imageUri);

        }
    }
}