package modelo;

import java.util.HashMap;

public class AlmacenActividades
{
	private static AlmacenActividades singleton = null;
	private HashMap<String, Registro> actividades = new HashMap<String, Registro>();
	
	
	public static AlmacenActividades getInstance()
	{
		if (singleton == null)
		{
			singleton = new AlmacenActividades();
		}
		
		return singleton;
	}
	
	
	public void guardarRegistro(String idActividad, Registro registro)
	{
		actividades.put(idActividad, registro);
	}
	
	
	public Registro getRegistro(String idActividad)
	{
		return actividades.get(idActividad);
	}
}
