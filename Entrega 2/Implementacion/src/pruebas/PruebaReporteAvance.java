package pruebas;

import modelo.Proyecto;
import modelo.ReporteAvance;
import modelo.WBS;
import procesamiento.ArchivadorProyectos;

public class PruebaReporteAvance
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		int numPaquetes = wbs.getNumPaquetes();
		
		for (int i=0; i<numPaquetes; i++)
		{
			ReporteAvance avance = wbs.calcularAvancePaquete(i);
			System.out.println("------------------------------");
			System.out.println("Reporte Avance Paquete " + i);
			System.out.println("------------------------------");
			System.out.println("Tareas terminadas: " + avance.tareasTerminadas);
			System.out.println("Tareas terminadas a tiempo: " + avance.tareasTerminadasATiempo);
			System.out.println("Total de tareas: " + avance.totalTareas);
			System.out.println("Tiempo planeado terminadas: " + avance.tiempoPlaneadoTerminadas);
			System.out.println("Tareas planeado total: " + avance.tiempoPlaneadoTotal);
			System.out.println("\n");
		}
	}
}
