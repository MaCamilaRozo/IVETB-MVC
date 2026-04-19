package model;

public class Producto {
    private int idProducto;
    private int idUnidad;
    private String nombre;
    private String tipoProducto;
    private String descripcion;

    public Producto() {
    }

    public Producto(int idProducto, int idUnidad, String nombre, String tipoProducto, String descripcion) {
        this.idProducto = idProducto;
        this.idUnidad = idUnidad;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.descripcion = descripcion;
    }

    public Producto(int idUnidad, String nombre, String tipoProducto, String descripcion) {
        this.idUnidad = idUnidad;
        this.nombre = nombre;
        this.tipoProducto = tipoProducto;
        this.descripcion = descripcion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
