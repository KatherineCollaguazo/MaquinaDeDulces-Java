package com.clearminds.componentes;

public class Producto {
	private String codigo;
	private String nombre;
	private double precio;
	
	public Producto( String codigo, String nombre, double precio) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void incrementarPrecio(double incremento) {
		this.precio = this.precio + (this.precio * (incremento/100));
	}
	
	public void disminuirPrecio(double disminuir) {
		this.precio = this.precio - (this.precio * (disminuir/100));
	}

}
