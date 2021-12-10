package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    String emailPattern = "^(.+)@(.+)$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    Button btnIngresar;
    Button btnSalir;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputEmail = findViewById(R.id.CorreoL);
        inputPassword = findViewById(R.id.PassL);
        btnIngresar = findViewById(R.id.btnMaIngresar);
        btnReg = findViewById(R.id.btnRegitraT);
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Registrar.class);
                startActivity(intent);
            }
        });
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
                perforLogin();
            }
        });


        btnSalir = findViewById(R.id.btnMaSalirM);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this, "hasta pronto", Toast.LENGTH_LONG).show();
                finish();

            }
        });

    }

    private void perforLogin() {
        String email = inputEmail.getText().toString();
        String pass = inputPassword.getText().toString();

        if (!email.matches((emailPattern))) {
            inputEmail.setError("Enter Conenext Email");
        } else if (pass.isEmpty() || pass.length() < 6) {
            inputPassword.setError("Enter Proper Password");
        } else {
            progressDialog.setMessage("Ingresando..");
            progressDialog.setTitle("Ingreso");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        sendUserToNextActivity();

                        Toast.makeText(MainActivity.this, "Login", Toast.LENGTH_SHORT).show();

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}