package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteParticipante
{
		
	public ArrayList<Actividad> actividadesMiembro(String loginParticipante,
						  HashMap<String, ArrayList<Actividad>> actividades)
	{
		ArrayList<Actividad> actividadesPorMiembro = new ArrayList<Actividad>();

		for (String tituloActividad : actividades.keySet())
		{
			ArrayList<Actividad> listaReportes = actividades.get(tituloActividad);
			
			for (Actividad actividad : listaReportes)
			{
				if (loginParticipante.equals(actividad.getAutor().getLogin()))
				{
					actividadesPorMiembro.add(actividad);
				}
			}
		}
		return actividadesPorMiembro;
	}

	
	public int tiempoTotal(ArrayList<Actividad> actividadesPorMiembro)
	{
		int total = 0;
		for (Actividad actividad : actividadesPorMiembro)
		{
			total += actividad.getTiempo();
		}
		return total;
	}

	
	public HashMap<String, Double> tiempoPorActividad(ArrayList<Actividad> actividadesDelMiembro,
			ArrayList<String> tiposDeActividades)
	{
		HashMap<String, Double> promedios = new HashMap<String, Double>();
		
		for (String tipo : tiposDeActividades)
		{
			double promedio = 0.0;
			int cantidadRegistros = 0;
			int acumulado = 0;
			
			for (Actividad actividad : actividadesDelMiembro)
			{
				if (tipo.equals(actividad.getTipoActividad()))
				{
					cantidadRegistros++;
					acumulado += actividad.getTiempo();
				}
			}
			
			if (cantidadRegistros != 0)
			{
				promedio = acumulado / cantidadRegistros;
			}
			
			promedios.put(tipo, promedio);
		}
		return promedios;
	}


}
