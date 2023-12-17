package com.clearminds.maquina;

import com.clearminds.componentes.Celda;
import com.clearminds.componentes.Producto;

public class MaquinaDulces {

	private Celda celda1;
	private Celda celda2;
	private Celda celda3;
	private Celda celda4;
	private double saldo;

	public Celda getCelda1() {
		return celda1;
	}

	public void setCelda1(Celda celda1) {
		this.celda1 = celda1;
	}

	public Celda getCelda2() {
		return celda2;
	}

	public void setCelda2(Celda celda2) {
		this.celda2 = celda2;
	}

	public Celda getCelda3() {
		return celda3;
	}

	public void setCelda3(Celda celda3) {
		this.celda3 = celda3;
	}

	public Celda getCelda4() {
		return celda4;
	}

	public void setCelda4(Celda celda4) {
		this.celda4 = celda4;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void configurarMaquina(String codigoCelda1, String codigoCelda2, String codigoCelda3, String codigoCelda4) {
		celda1 = new Celda(codigoCelda1);
		celda2 = new Celda(codigoCelda2);
		celda3 = new Celda(codigoCelda3);
		celda4 = new Celda(codigoCelda4);
	}

	public void mostrarConfiguracion() {
		System.out.println("Configuración de la Máquina:");
		System.out.println("Celda 1: " + celda1.getCodigo());
		System.out.println("Celda 2: " + celda2.getCodigo());
		System.out.println("Celda 3: " + celda3.getCodigo());
		System.out.println("Celda 4: " + celda4.getCodigo());
		System.out.println("Saldo actual: $" + saldo);
	}

	public Celda buscarCelda(String codigoCelda) {
		if (celda1.getCodigo() == codigoCelda) {
			return celda1;
		} else if (celda2.getCodigo() == codigoCelda) {
			return celda2;
		} else if (celda3.getCodigo() == codigoCelda) {
			return celda3;
		} else if (celda4.getCodigo() == codigoCelda) {
			return celda4;
		}
		return null;
	}

	public void cargarProducto(Producto producto, String codigoCelda, int cantidadInicial) {
		Celda celdaRecuperada = buscarCelda(codigoCelda);
		if (celdaRecuperada != null) {
			celdaRecuperada.ingresarProducto(producto, cantidadInicial);
		}
	}

	public void mostrarProductos() {
		System.out.println("Productos en la Máquina:");
		mostrarProductosEnCelda(celda1);
		mostrarProductosEnCelda(celda2);
		mostrarProductosEnCelda(celda3);
		mostrarProductosEnCelda(celda4);
	}

	private void mostrarProductosEnCelda(Celda celda) {
		System.out.println("*****************Celda " + celda.getCodigo() + ":");
		System.out.println("Stock: " + celda.getStock());
		if (celda.getProducto() != null) {
			System.out.println("Nombre del Producto: " + celda.getProducto().getNombre());
			System.out.println("Precio del Producto: $" + celda.getProducto().getPrecio());
			System.out.println("Código del Producto: " + celda.getProducto().getCodigo());
		} else {
			System.out.println("No tiene producto!");
		}
	}

	public Producto buscarProductoEnCelda(String codigoCelda) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null) {
			return celda.getProducto();
		}
		return null;
	}

	public double consultarPrecio(String codigoCelda) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null && celda.getProducto() != null) {
			return celda.getProducto().getPrecio();
		}
		return -1;
	}

	public Celda buscarCeldaProducto(String codigoProducto) {
		if (celda1.getProducto() != null && celda1.getProducto().getCodigo() == codigoProducto) {
			return celda1;
		} else if (celda2.getProducto() != null && celda2.getProducto().getCodigo() == codigoProducto) {
			return celda2;
		} else if (celda3.getProducto() != null && celda3.getProducto().getCodigo() == codigoProducto) {
			return celda3;
		} else if (celda4.getProducto() != null && celda4.getProducto().getCodigo() == codigoProducto) {
			return celda4;
		}
		return null;
	}

	public void incrementarProductos(String codigoProducto, int cantidadIncrementar) {
		Celda celdaEncontrada = buscarCeldaProducto(codigoProducto);
		if (celdaEncontrada != null) {
			celdaEncontrada.incrementarStock(cantidadIncrementar);
		}
	}

	public void vender(String codigoCelda) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null && celda.getStock() > 0) {
			double precio = celda.getProducto().getPrecio();
			saldo += precio;
			celda.disminuirStock();
			mostrarProductos();
			System.out.println("Saldo actual: $" + saldo);
		} else {
			System.out.println("Error: Producto no disponible en la celda.");
		}
	}

	public double venderConCambio(String codigoCelda, double valorIngresado) {
		Celda celda = buscarCelda(codigoCelda);
		if (celda != null && celda.getStock() > 0) {
			double precio = celda.getProducto().getPrecio();
			if (valorIngresado >= precio) {
				double cambio = valorIngresado - precio;
				saldo += precio;
				celda.disminuirStock();
				mostrarProductos();
				System.out.println("Saldo actual: $" + saldo);
				return cambio;
			} else {
				System.out.println("Error: Dinero insuficiente.");
			}
		} else {
			System.out.println("Error: Producto no disponible en la celda.");
		}
		return -1;
	}
}
