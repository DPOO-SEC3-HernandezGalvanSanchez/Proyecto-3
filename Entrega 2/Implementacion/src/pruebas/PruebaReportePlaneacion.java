package pruebas;

import modelo.Proyecto;
import modelo.ReporteCalidadPlaneacion;
import modelo.WBS;
import procesamiento.ArchivadorProyectos;

public class PruebaReportePlaneacion
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		
		ReporteCalidadPlaneacion calidad = wbs.calcularCalidadPlaneacion();
		
		System.out.println("------------------------------");
		System.out.println("Reporte Calidad Planeacion ");
		System.out.println("------------------------------");
		System.out.println("Tiempo planeado: " + calidad.tiempoPlaneado + " minutos");
		System.out.println("Tiempo real: " + calidad.tiempoReal + " minutos");
		System.out.println("# de tareas que cumplen planeacion: " + calidad.tareasCumplenTiempo);
		System.out.println("# total de tareas: " + calidad.totalTareas);
	}
}
