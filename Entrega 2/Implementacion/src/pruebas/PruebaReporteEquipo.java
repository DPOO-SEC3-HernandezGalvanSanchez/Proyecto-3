package pruebas;

import modelo.Participante;
import modelo.Proyecto;
import modelo.ReporteDesempenoEquipo;
import modelo.WBS;
import procesamiento.ArchivadorProyectos;

public class PruebaReporteEquipo
{
	public static void main(String[] args)
	{
		ArchivadorProyectos a = new ArchivadorProyectos();
		Proyecto p = a.getProyecto("ProyectoPrueba1");
		WBS wbs = p.getWBS();
		ReporteDesempenoEquipo equipo = wbs.calcularDesempenoEquipo();
		
		for (Participante participante : p.getParticipantes().values())
		{
			String nombre = participante.getNombre();
			
			int tiempoInvertido = equipo.tiempoInvertido.get(nombre);
			int tareasTerminadas = equipo.tareasTerminadas.get(nombre);
			int tareasPendientes = equipo.tareasPendientes.get(nombre);
			int tiempoInvertidoPendientes = equipo.tiempoInvertidoPendientes.get(nombre);
			int tiempoPlaneadoPendientes = equipo.tiempoPlaneadoPendientes.get(nombre);
			
			System.out.println("----------------------------------------------");
			System.out.println("Reporte Desempeno de " + nombre);
			System.out.println("----------------------------------------------");
			System.out.println("Tiempo total invertido: " + tiempoInvertido + " minutos");
			System.out.println("# de tareas terminadas: " + tareasTerminadas);
			System.out.println("# de tareas pendientes: " + tareasPendientes);
			System.out.println("Tiempo invertido en tareas pendientes: " + tiempoInvertidoPendientes + " minutos");
			System.out.println("Tiempo planeado en tareas pendientes: " + tiempoPlaneadoPendientes + " minutos");
			System.out.println("\n");
		}
	}
}
