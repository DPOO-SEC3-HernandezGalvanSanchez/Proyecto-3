package pruebas;

import modelo.PaqueteDeTrabajo;
import modelo.Proyecto;
import modelo.Tarea;
import procesamiento.ArchivadorProyectos;

public class PruebaArchivador
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		PaqueteDeTrabajo wbs = p.getWBS();
		Tarea tarea = wbs.getTarea("Tarea1P1");
		
		System.out.println(tarea.getTiempoEstimado());
	}
}
