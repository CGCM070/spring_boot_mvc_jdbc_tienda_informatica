package org.iesvegademijas.tienda_informatica.modelo;

public class Producto {

    private int codigo;
    private String nombre;
    private Double precio;
    private int id_fabricante;

    public Producto() {
    }

    public Producto(int codigo,String nombre , Double precio ,int id_fabricante) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.id_fabricante = id_fabricante;
    }

    public Double getPrecio() {
        return precio;
    }

    public Producto setPrecio(Double precio) {
        this.precio = precio;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Producto setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getId_fabricante() {
        return id_fabricante;
    }

    public Producto setId_fabricante(int id_fabricante) {
        this.id_fabricante = id_fabricante;
        return this;
    }

    public int getCodigo() {
        return codigo;
    }

    public Producto setCodigo(int codigo) {
        this.codigo = codigo;
        return this;
    }

    @Override
    public String toString() {
        return "Producto{" +
               "codigo=" + codigo +
               ", nombre='" + nombre + '\'' +
               ", precio=" + precio +
               ", id_fabricante=" + id_fabricante +
               '}';
    }
}
