package jpa.cibertec.basico;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPA_Test {
	private EntityManager  manager;
	

	public JPA_Test(EntityManager manager) {
	//	super();
		this.manager = manager;
	}  //fin del constructor.....


	public static void main(String[] args){
		//comenzamos  a testear  los respectivos  metodos.....
		EntityManagerFactory factory=Persistence.createEntityManagerFactory("jpa_2021_cibertec_III");
		EntityManager manager=factory.createEntityManager();
		//instanciamos la misma clase  JPA_Test
		JPA_Test jpatest=new JPA_Test(manager);
		EntityTransaction tx=manager.getTransaction();
		//iniciamos la transaccion....
		tx.begin();
		//llamamos al metodo creear  empleado....
		jpatest.CrearEmpleado();
		//llamamos al metodo listar empleados.....
		//declaramos una variables de tipo list empleado....
		List<Empleado> verlistado=jpatest.MostrarEmpleado();
		//creamos un bucle para realizar la respectiva impresion de los empleados...
		
		System.out.println("*********************Listado de empleados con JPQL**************");
		for(Empleado emp:verlistado){
		 //imprimimos por pantalla... con system.....
			System.out.println("NOMBRE: "+emp.getNombre()+" APELLIDO: "+
			emp.getApellido()+" SEXO: "+emp.getSexo()+" DNI: "+emp.getDni()+" EMAIL: "+emp.getEmail());		
		}  //fin del bucle for......,
		
		//imprimimos el empleado por codigo...***************************************************
		System.out.println("***************mostramos el empleado por codigo*****************");
		List<Empleado> buscarEmp=jpatest.BuscarEmpleado(4);
		//aplicamos un bucle**********
		for(Empleado busc:buscarEmp){
			System.out.println("codigo "+busc.getIdempleado()+   " NOMBRE: "+busc.getNombre()+" APELLIDO: "+
					busc.getApellido()+" SEXO: "+busc.getSexo()+" DNI: "+busc.getDni()+" EMAIL: "+busc.getEmail());		
		}   //fin del bucle for....
		
		//*******************		 lllamamos  al metodo actualizar dentro del metodo principal(main)......
		
		jpatest.ActualizarEmpleado(4,"Abby","Gonzales","F","104055897","abby@hotmail.com","3814950",3500, 4);
	
		//******************  llamamos al metodo eliminar  ***********************
		jpatest.EliminarEmpleado(4);
		
		//****************  llamamos al metodo insertar cliente *******************
		jpatest.InsertarCliente();
		
		//************** imprimimos por pantalla todo  el  listado de clientes *******
		List<Cliente> implista=jpatest.ListadoClientes();
		//imprimimos mensaje por pantalla....
		System.out.println("********* listado de cliente en la base de datos***************");
		//aplicamos un bucle for....
		System.out.println("Nombre "+"  Apellido      "+"  Sexo "+" Telefono "+" Email  "+"Dni");
		for(Cliente list:implista){
			//imprimimos listado de clientes por pantalla........
			System.out.println(list.getNombre()+" "+list.getApellido()+"   "+list.getSexo()+
					" "+list.getTelefono()+" "+list.getEmail()+" "+list.getDni());
			
		}  //fin del bucle for.....
		
		//llamamos al  metodo actualizar cliente.....
		jpatest.ActualizarCliente(5,"luis alberto","calderon","M","992243768","luis@gmail.comm","10405085");
		
		//	tx.commit();
		
	}    //fin del metodo principal....
	
	//crear un metodo para generar empleados....
	
	public void CrearEmpleado(){
		//instanciar la clase departamento.....
		Departamento departamento=new Departamento("Administracion","parte fundamental de la empresa");
		Departamento departamento2=new  Departamento("Sistemas","parte tecnologica de la empresa");
		Departamento departamento3=new Departamento("Contabilidad","parte de  la sunat con  la  empresa");
		Departamento departamento4=new  Departamento("Almacen","partet logistica de la empresa");
		//el metodo persist permite almacenar valores en la BD mysql...
		manager.persist(departamento);
        manager.persist(departamento2);
        manager.persist(departamento3);
        manager.persist(departamento4);
        //instanciar la clase empleado
        Empleado empleado=new Empleado("miguel","perez","M","10352240","miguel@hotmail.com",
        		"3814950",1600,departamento);
        Empleado empleado1=new Empleado("rosa maria","chavez","F","10354488","rosamaria@hotmail.com",
        		"989411223",3000, departamento2);
        
        Empleado empleado2=new Empleado("alberto","sanchez","M","103544778","alberto@gmail.com",
        		"989322448",4000,departamento3);
        Empleado empleado3=new Empleado("nora","gomez","F","40116978","nora@gmai.com","3810941",
        		2500,departamento4);
        //seguimos con la persistencia....
        manager.persist(empleado);
        manager.persist(empleado1);
        manager.persist(empleado2);
        manager.persist(empleado3);
		
		
	}   //fin del metodo crear empleado.....
	
	
	//******************************** consultas dinamicas JPQL **************************
	//creamos un metodo para listar empleados.......
	
	public List<Empleado> MostrarEmpleado(){
		//declaramos una variable de tipo cadena......
		//recuperar datos de la base de datos.........
		String consulta="select e from Empleado e";
		//aplicamos la interfaz query.....
		Query listado=manager.createQuery(consulta,Empleado.class);
		
		//almacenado los respectivos valores en la variable listadoempleado
		List<Empleado> listadoempleado=listado.getResultList();
		//retornomas listado de empleados......
		return listadoempleado;
	}   //fin del metodo listar empleados......
	
	
	//crear metodo buscar empleado por codigo....
	public List<Empleado> BuscarEmpleado(int cod){
		//declaramos una variable de tipo cadena....
		//parametros dinamicos por nombre........ sintaxis :nombre........
		String buscar="select e from Empleado e where e.idempleado=:codigo";
		//aplicamos la interfaz query.....
		Query buscarempl=manager.createQuery(buscar,Empleado.class).setParameter("codigo",cod);
		//declaramos una variable de tipo list empleado....
		List<Empleado> mostraemp=buscarempl.getResultList();
		//retornamos el valor busscado...
		return mostraemp;		
	}   //fin  del metodo buscar por empleado....
	
	//crear metodo actualizar empleado.....
	public void ActualizarEmpleado(int cod,String nom,String ape,String sex,String dn,
			String em,String tel,double suel,int coddepart){
		//declaramos una variable de tipo cadena....
		String cadena="UPDATE Empleado e SET e.nombre=:nom,e.apellido=:ape,"
				+ "sexo=:sex,dni=:dn,email=:em,telefono=:tel,"
				+ "sueldo=:suel,e.departamento.iddepartamento=:coddepart  where e.idempleado=:cod";
		//aplicamos la interfaz query......
		Query cons=manager.createQuery(cadena);
		//creamos los parametros dinamicos por nombre
		cons.setParameter("cod",cod);
		cons.setParameter("nom",nom);
		cons.setParameter("ape",ape);
		cons.setParameter("sex",sex);
		cons.setParameter("dn", dn);
		cons.setParameter("em",em);
		cons.setParameter("tel",tel);
		cons.setParameter("suel",suel);
		cons.setParameter("coddepart",coddepart);
	int num=cons.executeUpdate();
	if(num>0){
		System.out.println("Empleado actualizado correctamente con JPQL.....");
	}else {
		System.out.println("Empleado  NO actualizado");
	}
		
		
		
	}  //fin del metodo actualizar empleado.....
	
	//Creamos el metodo eliminar*************************
	public void EliminarEmpleado(int cod){
		//declaramos una variable de tipo cadena....
		String cadena="delete from Empleado e where e.idempleado=:cod";
		//aplicamos la interfaz query.....
		Query eliminarempl=manager.createQuery(cadena);
		//asignamos parametros...
		eliminarempl.setParameter("cod",cod);
		//ejecutamos las instrucciones....
		int n=eliminarempl.executeUpdate();
		if(n>0){
			
			System.out.println("Empleado eliminado de la BD...");
		}else {
			System.out.println("No se puede eliminar el empleado de la BD");
		}
		
	}  //fin del metodo eliminar empleado.....
	
	//creamos  un metodo para insertar datos en la tabla cliente....
	public void InsertarCliente(){
		//instanciamos la respectiva clase cliente....
		Cliente cliente1=new  Cliente("Rodrigo","Minaya","M","3814945","rodrigo@gmail.com","10352240");
		Cliente cliente2=new Cliente("Melany","Chuquimbalqui","F","381948","melani@gmail.com","10345588");
		Cliente cliente3=new Cliente("Juan	","Polo","M","3720524","juan@hotmail.com","10445589");
		Cliente cliente4=new Cliente("Bruno","Quispe","M","4720345","bruno@gmail.com","10559977");	
		Cliente cliente5=new Cliente("Santiago","Rodriguez","M","5244367","santiago@hotmail.com","10557755");
		//aplicamos el metodo persist (este metodo permite almacenar datos en la BD.)
		manager.persist(cliente1);
		manager.persist(cliente2);
		manager.persist(cliente3);
		manager.persist(cliente4);
		manager.persist(cliente5);
	}   //fin del metodo insertar cliente......
	
	//creamos un metodo para listar cllientes aplicando consultas dinamicas JPQL....
	public List<Cliente> ListadoClientes(){
		//declaramos una variable de tipo string.
		//recuperamos datos de la BD.
		String consulta="select c from Cliente c";
		//aplicamos la interfaz  query...
		Query queryconsulta=manager.createQuery(consulta,Cliente.class);
		//almacenando los respectivos valores recuperados de la BD en una variable..
		List<Cliente> listado=queryconsulta.getResultList();
		//retornamos los valores
		return listado;
		
	}   //fin del  metodo listar clientes.....
	
	//creamos el metodo actuallizar...*******
	public void ActualizarCliente(int cod,String nom,String ape,String sex,
			String tel,String  em,String dn){
		//declaramos una variable de tipo cadena....
		//aplicamos los parametros  dinamicos por nombre....
		String actuacliente="UPDATE  Cliente c SET  c.nombre=:nom,"
				+ "c.apellido=:ape,c.sexo=:sex,c.telefono=:tel,c.email=:em,c.dni=:dn "
				+ "where c.idcliente=:cod ";
		//aplicamos la interfaz query...
		Query queryactu=manager.createQuery(actuacliente);
		//aplicamos los parametros dinamicos 
		queryactu.setParameter("cod",cod);
		queryactu.setParameter("nom",nom);
		queryactu.setParameter("ape",ape);
		queryactu.setParameter("sex",sex);
		queryactu.setParameter("tel", tel);
		queryactu.setParameter("em",em);
		queryactu.setParameter("dn",dn);
		int  x=queryactu.executeUpdate();
		if(x>0){
			System.out.println("Cliente  actualizado correctamente**********");
		} else{	
			System.out.println("Cliente NO actualizado en la BD............");
		}
		
	}   //fin  del  metodo actualizar cliente........
	
}    //fin de la clase test.....
