package pruebas;

import modelo.Actividad;
import modelo.PaqueteDeTrabajo;
import modelo.Proyecto;
import modelo.Tarea;
import modelo.WBS;
import procesamiento.ArchivadorProyectos;

public class PruebaArchivador
{	
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p1 = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p1.getWBS();
		
		
		String fechaReal = "31/12/2022";
		String fechaEstimada = "04/06/2022";
	
	}
}
