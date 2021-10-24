package jpa.cibertec.basico;

import javax.persistence.*;

@Entity
@Table(name="producto")
public class Producto {
	//creamos sus respectivos atributos....
	//declaramos la variable idproducto llave primaria... y auto incrementable...
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idproducto;
	@Column(name="precioCompra")
	private double precioCompra;
	@Column(name="precioVenta")
	private double precioVenta;
	// @ManyToMany(mappedBy = "productos")
	public Producto() {
		//super();
	}  //fin  del constructor vacio....
	
	public Producto(double precioCompra, double precioVenta) {
	//	super();
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	

}   //fin de la clase producto....
