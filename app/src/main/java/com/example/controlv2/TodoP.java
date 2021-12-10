package com.example.controlv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.controlv2.dbFireBase.Producto;

import java.util.ArrayList;
import java.util.List;

public class TodoP extends AppCompatActivity {
EditText Producto,Cantidad, Precio;
ListView lProductos;
    private List<Producto> listaProductos = new ArrayList<Producto>();
    public ArrayAdapter<Producto> adapterProducto;
    Producto productoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_p);
        Producto = findViewById(R.id.ProductoName);
        Cantidad = findViewById(R.id.CantidadP);
        Precio = findViewById(R.id.PrecioP);




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



}