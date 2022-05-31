package pruebas;

import modelo.Actividad;
import modelo.AlmacenActividades;
import modelo.Participante;
import modelo.ProxyRegistro;

public class PruebaProxyRegistro
{
	public static void main(String[] args)
	{
		//Actividades de prueba
		Participante autor1 = new Participante("login1", "nombre1");
		Actividad a1 = new ProxyRegistro("tipo1", "A1", "descA1", "30/05/2022",
					       			"08:00", "09:00", autor1, "tarea1", false);
	
		Participante autor2 = new Participante("login2", "nombre2");
		Actividad a2 = new ProxyRegistro("tipo2", "A2", "descA2", "31/05/2022",
					       			"08:00", "11:00", autor2, "tarea2", false);
	
		Actividad a1Copy = new ProxyRegistro("tipo3", "A1", "descA1", "30/05/2022",
       									"08:00", "09:00", autor1, "tarea1", false);
	
	
		//Probar
		System.out.println(a1Copy.getFecha());
		a1.setFecha("nueva fecha");
		System.out.println(a1.getFecha());
		System.out.println(a1Copy.getFecha());
		
	}
}
