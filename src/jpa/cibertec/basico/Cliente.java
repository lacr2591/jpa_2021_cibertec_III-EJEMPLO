package jpa.cibertec.basico;

import javax.persistence.*;

//realizamos el respectivo mapeo...

//realizamos  el respectivo mapeo....
@Entity
@Table(name="cliente")
public class Cliente {
//declaramos sus atributos....
	//generamos la llave primaria auto incrementable....
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idcliente")
	private int idcliente;
	//la  anotacion @column define el nombre del campo de la tabla  en la BD.
  @Column(name="nombre")
	private String nombre;
  @Column(name="apellido")
	private String apellido;
  @Column(name="sexo")
	private String sexo;
  @Column(name="telefono")
	private String telefono;
  @Column(name="email")
	private String email;
  @Column(name="dni")
	private String dni;
	//constructores
	//constructor con parametros...
	public Cliente(String nombre, String apellido, String sexo, String telefono, String email, String dni) {
	//	super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.telefono = telefono;
		this.email = email;
		this.dni = dni;
	}
	//constructor vacio....
	public Cliente() {
		//super();
	}  //fin del constructor vacio......
	
	//metodos getter y  setter.............
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
	
	
}   //fin de la clase cliente....
