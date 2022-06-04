package pruebas;

import modelo.Actividad;
import modelo.PaqueteDeTrabajo;
import modelo.Proyecto;
import modelo.Tarea;
import procesamiento.ArchivadorProyectos;

public class PruebaArchivador
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		
		System.out.println("---------------------------");
		System.out.println("ProyectoPrueba1");
		System.out.println("---------------------------");
		Proyecto p1 = a.getProyecto("ProyectoPrueba1");
		PaqueteDeTrabajo mainWBS1 = p1.getWBS().getPaquete(0);
		Tarea tarea1P1 = mainWBS1.getTarea("Tarea1P1");
		
		System.out.println(tarea1P1.getNombreTarea() +  " - " + tarea1P1.esBorrable());
		
		for (Actividad act : tarea1P1.getActividades())
		{
			System.out.println(act.getDescripcion());
		}
		
		
		PaqueteDeTrabajo pqt = p1.getWBS().getPaquete(2);
		Tarea tarea2P1 = pqt.getTarea("Tarea2P1");
		
		System.out.println("\n" + tarea2P1.getNombreTarea() +  " - " + tarea2P1.esBorrable());
	
		for (Actividad act : tarea2P1.getActividades())
		{
			System.out.println(act.getDescripcion());
		}
		
		
		System.out.println("\n\n---------------------------");
		System.out.println("ProyectoPrueba2");
		System.out.println("---------------------------");
		Proyecto p2 = a.getProyecto("ProyectoPrueba2");
		PaqueteDeTrabajo mainWBS2 = p2.getWBS().getPaquete(0);
		Tarea tarea1P2 = mainWBS2.getTarea("Tarea1P2");
		
		System.out.println(tarea1P2.getNombreTarea() +  " - " + tarea1P2.esBorrable());
		
		for (Actividad act : tarea1P2.getActividades())
		{
			System.out.println(act.getDescripcion());
		}
		
		Tarea tarea2P2 = mainWBS2.getTarea("Tarea2P2");
		System.out.println("\n" + tarea2P2.getNombreTarea() +  " - " + tarea2P2.esBorrable());
		
		
		System.out.println("\n\n---------------------------");
		System.out.println("Paquetes Borrables");
		System.out.println("---------------------------");
		
		System.out.println("P1 - 0 " + p1.getWBS().paqueteEsBorrable(0));
		System.out.println("P1 - 1 " + p1.getWBS().paqueteEsBorrable(1));
		System.out.println("P1 - 2 " + p1.getWBS().paqueteEsBorrable(2));
		System.out.println("P1 - 3 " + p1.getWBS().paqueteEsBorrable(3));
		
		System.out.println("\nP2 - 0 " + p2.getWBS().paqueteEsBorrable(0));
		
		a.guardarInfoProyecto(p1);
	}
}
