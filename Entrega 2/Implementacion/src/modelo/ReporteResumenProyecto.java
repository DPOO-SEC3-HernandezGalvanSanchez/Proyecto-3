package modelo;

import java.util.ArrayList;
import java.util.HashMap;

public class ReporteResumenProyecto
{
	public ReporteAvance avance;
	public HashMap<String, Integer> tiempoPorTipo = new HashMap<String, Integer>();
	public HashMap<String, Integer> pendientesPorTipo = new HashMap<String, Integer>();;
	
	public ReporteResumenProyecto(ArrayList<String >tipos)
	{
		for (String tipo : tipos)
		{
			tiempoPorTipo.put(tipo, 0);
			pendientesPorTipo.put(tipo, 0);
		}
	}
}
