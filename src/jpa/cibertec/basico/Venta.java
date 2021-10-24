package jpa.cibertec.basico;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="venta")
public class Venta {
//creamos sus  respectivos atributos...
	//creamos la llave primaria auto incrementable....
	@Id
	@Column(name="idventa")
	private int idventa;
	@Column(name="importe") 
	private double importe;
	@Column(name="fecha")
	private Date fecha;
	
	@JoinTable(
			name="rel_venta_producto",
		joinColumns=@JoinColumn(name="FK_VENTA"),
		inverseJoinColumns=@JoinColumn(name="FK_PRODUCTO")
			)
			@ManyToMany(cascade=CascadeType.ALL)
	private List<Producto> productos;	
}  //fin de la clase venta.....
