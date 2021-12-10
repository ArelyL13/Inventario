package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class MostrarTodo extends AppCompatActivity {
    EditText Producto, Cantidad, Precio;
    ListView lProductos;
    Button botonModificar;
    Button botonEliminar;
    private List<Producto> listaProductos = new ArrayList<Producto>();
    public ArrayAdapter<Producto> adapterProducto;

    Producto productoSelected;
    FirebaseDatabase fb;
    DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_todo);
        Producto = findViewById(R.id.etNombreP);
        Cantidad = findViewById(R.id.etCantidadP);
        Precio = findViewById(R.id.etPrecioP);
        lProductos = findViewById(R.id.lvListaElementos);
        botonModificar=findViewById(R.id.btnModificaT);
        botonEliminar=findViewById(R.id.btnEliminaT);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String producto = Producto.getText().toString();
                String cantidad = Cantidad.getText().toString();
                String precio = Precio.getText().toString();
                Producto p = new Producto();

                if (producto.equals("") || cantidad.equals("") || precio.equals("")) {
                    validarCampos();
                } else {

                    p = new Producto();
                    p.setuID(productoSelected.getuID());
                    dbReference.child("Producto").child(p.getuID()).removeValue();
                   // Toast.makeText(this, "Usuario Eliminado", Toast.LENGTH_LONG).show();
                    limpiar();
                }

            }
        });
        iniciaFirebase();
        listarDatos();
        botonModificar.setOnClickListener(new View.OnClickListener() {
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
        lProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                productoSelected = (Producto) parent.getItemAtPosition(position);
                Producto.setText(productoSelected.getProducto());
                Cantidad.setText(productoSelected.getCantidad());
                Precio.setText(productoSelected.getPrecio());
            }
        });

    }

    private void iniciaFirebase() {
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

    public void listarDatos() {
        dbReference.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listaProductos.clear();
                for (DataSnapshot objectSnapshot : snapshot.getChildren()) {
                    Producto p = objectSnapshot.getValue(Producto.class);
                    listaProductos.add(p);
                    adapterProducto = new ArrayAdapter<Producto>(
                            MostrarTodo.this
                            , android.R.layout.simple_list_item_1
                            , listaProductos
                    );
                    lProductos.setAdapter(adapterProducto);
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void limpiar () {
        Producto.setText("");
        Cantidad.setText("");
        Precio.setText("");

    }

}


