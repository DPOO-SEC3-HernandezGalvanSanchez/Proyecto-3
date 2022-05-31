package modelo;

import java.util.ArrayList;

public class GestorActividades extends Proyecto
{

	// CONSTRUCTOR
	public GestorActividades(String nombreProyecto, String descripcion, String fechaInicio,
							 String fechaFin, ArrayList<String> tiposActividades, Participante autor)
	{
		super(nombreProyecto, descripcion, fechaInicio, fechaFin, tiposActividades, autor);
	}
	
	
	// METODOS
	public void registrarActividad(Actividad proxy1, Actividad proxy2, Tarea tarea) throws Exception
	{
		tarea.agregarActividad(proxy1);
		
		String titulo = proxy2.getTitulo();
		ArrayList<Actividad> homonimas = actividades.get(titulo);
		
		if (homonimas == null)
		{
			homonimas = new ArrayList<Actividad>();
		}
		
		homonimas.add(proxy2);
		actividades.put(titulo, homonimas);		
	}


	public void modificarFechaActividad(String titulo, int index,
							   String nuevaFecha)
	{
		Actividad registro = getRegistroActividad(titulo, index);
		registro.setFecha(nuevaFecha);
	}
	
	
	public void modificarHoraInicio(String titulo, int index,
									String nuevaHoraInicio)
	{
		Actividad registro = getRegistroActividad(titulo, index);
		registro.setHoraInicio(nuevaHoraInicio);
	}
	
	
	public void modificarHoraFin(String titulo, int index,
								 String nuevaHoraFin)
	{
		Actividad registro = getRegistroActividad(titulo, index);
		registro.setHoraFin(nuevaHoraFin);
	}
	
	
	private Actividad getRegistroActividad(String titulo, int index)
	{
		ArrayList<Actividad> homonimas = actividades.get(titulo);
		return homonimas.get(index);
	}

}
