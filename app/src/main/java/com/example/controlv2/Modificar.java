package com.example.controlv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.controlv2.dbFireBase.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Modificar extends AppCompatActivity {
Button button;
    EditText Producto, Cantidad, Precio;

    com.example.controlv2.dbFireBase.Producto productoSelected;
    FirebaseDatabase fb;
    DatabaseReference dbReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        button= findViewById(R.id.btnModifica2) ;
        Producto = findViewById(R.id.ProductoM);
        Cantidad = findViewById(R.id.CantidadM);
        Precio = findViewById(R.id.PrecioM);
        iniciaFirebase();

        button=findViewById(R.id.btnModifica2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String producto = Producto.getText().toString();
                String cantidad = Cantidad.getText().toString();
                String precio = Precio.getText().toString();

                if (producto.equals("") || cantidad.equals("") || precio.equals("")) {
                    validarCampos();
                } else {
                    Producto p = new Producto();
                    p.setuID(productoSelected.getuID());
                    p.setProducto(Producto.getText().toString().trim());
                    p.setCantidad(Cantidad.getText().toString().trim());
                    p.setPrecio (Precio.getText().toString().trim());

                    dbReference.child("Producto").child(p.getuID()).setValue(p);
                    //  Toast.makeText(this, "Producto registrado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

    }
    private void limpiar () {
        Producto.setText("");
        Cantidad.setText("");
        Precio.setText("");

    }
    private void iniciaFirebase(){
        FirebaseApp.initializeApp(this);
        fb = FirebaseDatabase.getInstance();
        dbReference = fb.getReference();

    }
    private void validarCampos () {
        String producto = Producto.getText().toString();
        String cantidad = Cantidad.getText().toString();
        String precio = Precio.getText().toString();


        if (producto.equals("")) {
            Producto.setError("Campo o Nombre obligatorio");
        } else if (cantidad.equals("")) {
            Cantidad.setError("Campo o Nombre obligatorio");
        } else if (precio.equals("")) {
            Precio.setError("Campo o Nombre obligatorio");

        }
    }

}