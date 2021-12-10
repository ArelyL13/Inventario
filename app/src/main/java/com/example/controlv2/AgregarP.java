package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.controlv2.dbFireBase.Producto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AgregarP extends AppCompatActivity {
    EditText Producto, Cantidad, Precio;
    ListView lProductos;
    Producto productoSelected;
    FirebaseDatabase fb;
    DatabaseReference dbReference;
    Button buttonA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_p);
        Producto = findViewById(R.id.ProductoName);
        Cantidad = findViewById(R.id.CantidadP);
        Precio = findViewById(R.id.PrecioP);

        iniciaFirebase();
        buttonA=findViewById(R.id.btnGuardar);
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String producto = Producto.getText().toString();
                String cantidad = Cantidad.getText().toString();
                String precio = Precio.getText().toString();

                if (producto.equals("") || cantidad.equals("") || precio.equals("")) {
                    validarCampos();
                } else {
                    Producto p = new Producto();
                    p.setuID(UUID.randomUUID().toString());
                    p.setProducto(producto);
                    p.setCantidad(cantidad);
                    p.setPrecio(precio);

                    dbReference.child("Producto").child(p.getuID()).setValue(p);
                     // Toast.makeText(this,"Producto registrado",Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });
    }

    private void iniciaFirebase() {
        FirebaseApp.initializeApp(this);
        fb = FirebaseDatabase.getInstance();
        dbReference = fb.getReference();

    }




        private void limpiar () {
            Producto.setText("");
            Cantidad.setText("");
            Precio.setText("");

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