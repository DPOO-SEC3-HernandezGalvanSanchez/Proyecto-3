package modelo;

import java.util.ArrayList;

public class Tarea
{
	private String nombreTarea;
	private String descripcion;
	private String tipoTarea;
	private String fechaEstimadaFin;
	private String ultimaFechaProgreso;
	private int tiempoEstimado;
	private int indexPadre;
	private boolean finalizada;
	private ArrayList<Actividad> actividades;
	private ArrayList<Participante> responsables;
	
	
	public Tarea(String nombreTarea, String descripcion, String tipoTarea,
				 String fechaEstimadaFin, int tiempoEstimado, int indexPadre,
				 ArrayList<Participante> responsables)
	{
		this.nombreTarea = nombreTarea;
		this.descripcion = descripcion;
		this.tipoTarea = tipoTarea;
		this.fechaEstimadaFin = fechaEstimadaFin;
		this.tiempoEstimado = tiempoEstimado;
		this.indexPadre = indexPadre;
		this.finalizada = false;
		this.actividades = new ArrayList<Actividad>();
		this.responsables = responsables;
	}

	public void agregarActividad(Actividad actividad) throws Exception
	{
		if (finalizada)
		{
			throw new Exception("La tarea ya fue finalizada");
		}
		
		else
		{
			actividades.add(actividad);
			this.ultimaFechaProgreso = actividad.getFecha();
			this.finalizada = actividad.cierraTarea();
		}
	}
	
	
	public int calcularTiempoReal()
	{
		int tiempo = 0;
		
		for (Actividad act : actividades)
		{
			tiempo += act.getTiempo();
		}
		
		return tiempo;
	}
	
	
	public boolean esBorrable()
	{
		return actividades.size() == 0;
	}
	
	
	//GETTERS
	public ArrayList<Actividad> getActividades()
	{
		ArrayList<Actividad> copia = new ArrayList<Actividad>(actividades);
		return copia;
	}
	
	public String getNombreTarea()
	{
		return nombreTarea;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public String getTipoTarea()
	{
		return tipoTarea;
	}

	public String getFechaEstimadaFin()
	{
		return fechaEstimadaFin;
	}

	public String getUltimaFechaProgreso()
	{
		return ultimaFechaProgreso;
	}

	public Integer getTiempoEstimado()
	{
		return tiempoEstimado;
	}
	
	public Integer getIndexPadre()
	{
		return indexPadre;
	}
	
	public ArrayList<Participante> getResponsables()
	{
		return new ArrayList<Participante>(responsables);
	}
	
	public boolean isFinalizada()
	{
		return finalizada;
	}
}
