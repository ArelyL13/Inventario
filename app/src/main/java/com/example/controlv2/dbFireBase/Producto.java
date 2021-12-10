package com.example.controlv2.dbFireBase;

public class Producto {
    private String uID;
    private String producto;
    private String cantidad;
    private String precio;


    public Producto(){

    }
    public String getuID(){return uID;}
    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCantidad(){return cantidad = cantidad;}
    public void setCantidad(String cantidad){this.cantidad= cantidad;}
    public String getPrecio(){return precio = precio;}
    public void setPrecio(String cantidad){this.precio= precio;}
}
