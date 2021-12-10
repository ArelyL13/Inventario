package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrar extends AppCompatActivity {
EditText inputEmail, inputPassword, inputConfirmPass;
Button btnRegistra;
Button btnInicia;
String emailPattern= "^(.+)@(.+)$";
ProgressDialog progressDialog;
FirebaseAuth mAuth;
FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        inputEmail=findViewById(R.id.CorreoR);
        inputPassword=findViewById(R.id.PassR);
        inputConfirmPass=findViewById(R.id.ConfirmR);
        btnRegistra=findViewById(R.id.btnRegistrar);
        progressDialog= new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        btnInicia=findViewById(R.id.btnIniciarR);
        btnInicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        btnRegistra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
            }
        });

    }

    private void PerforAuth() {
        String email= inputEmail.getText().toString();
        String pass= inputPassword.getText().toString();
        String comf=inputConfirmPass.getText().toString();

        if (!email.matches((emailPattern))){
            inputEmail.setError("Enter Conenext Email");
        }else if(pass.isEmpty()|| pass.length()<6){
            inputPassword.setError("Enter Proper Password");
        }else if(pass.equals(comf)){
            inputConfirmPass.setError("La contraseña no coincide");

        }else{
            progressDialog.setMessage("Registrando..");
            progressDialog.setTitle("Registrado");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        
                        Toast.makeText(Registrar.this, "Exito", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(Registrar.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(Registrar.this,MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}