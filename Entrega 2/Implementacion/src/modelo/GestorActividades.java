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
	public void registrarActividad(Actividad nuevaActividad)
	{
		String titulo = nuevaActividad.getTitulo();
		ArrayList<Actividad> homonimas = actividades.get(titulo);
		
		if (homonimas == null)
		{
			homonimas = new ArrayList<Actividad>();
		}
		
		homonimas.add(nuevaActividad);
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
