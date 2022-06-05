package modelo;

import java.util.HashMap;

public class ReporteDesempenoEquipo
{
	public HashMap<String, Integer> tiempoInvertido = new HashMap<String, Integer>();
	public HashMap<String, Integer> tareasTerminadas = new HashMap<String, Integer>();
	public HashMap<String, Integer> tareasPendientes = new HashMap<String, Integer>();
	public HashMap<String, Integer> tiempoInvertidoPendientes = new HashMap<String, Integer>();
	public HashMap<String, Integer> tiempoPlaneadoPendientes = new HashMap<String, Integer>();
	
	public ReporteDesempenoEquipo(HashMap<String, Participante> miembrosEquipo)
	{
		for (Participante miembro : miembrosEquipo.values())
		{
			String nombre = miembro.getNombre();
			tiempoInvertido.put(nombre, 0);
			tareasTerminadas.put(nombre, 0);
			tareasPendientes.put(nombre, 0);
			tiempoInvertidoPendientes.put(nombre, 0);
			tiempoPlaneadoPendientes.put(nombre, 0);
		}
	}
}
