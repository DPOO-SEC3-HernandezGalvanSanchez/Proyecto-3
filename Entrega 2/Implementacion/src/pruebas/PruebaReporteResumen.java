package pruebas;

import modelo.Proyecto;
import modelo.ReporteResumenProyecto;
import modelo.WBS;
import procesamiento.ArchivadorProyectos;

public class PruebaReporteResumen
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		ReporteResumenProyecto resumen = wbs.calcularResumenProyecto();
		
		System.out.println("------------------------------");
		System.out.println("Resumen del Proyecto ");
		System.out.println("------------------------------");

		for (String tipoTarea : resumen.tiempoPorTipo.keySet())
		{
			int tiempo = resumen.tiempoPorTipo.get(tipoTarea);
			int numPendientes = resumen.pendientesPorTipo.get(tipoTarea);
			
			System.out.println("\nTipo: " + tipoTarea);
			System.out.println("Tiempo invertido: " + tiempo + " minutos");
			System.out.println("Tareas pendientes: " + numPendientes);
		}
	}
}
