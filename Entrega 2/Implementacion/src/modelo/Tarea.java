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
	private boolean finalizada;
	private ArrayList<Actividad> actividades;
	private ArrayList<Participante> responsables;
	
	
	public Tarea(String nombreTarea, String descripcion, String tipoTarea,
				 String fechaEstimadaFin, int tiempoEstimado,
				 ArrayList<Participante> responsables)
	{
		this.nombreTarea = nombreTarea;
		this.descripcion = descripcion;
		this.tipoTarea = tipoTarea;
		this.fechaEstimadaFin = fechaEstimadaFin;
		this.tiempoEstimado = tiempoEstimado;
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
			this.finalizada = actividad.getCierraTarea();
		}
	}
	
	
	//GETTERS
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

	public int getTiempoEstimado()
	{
		return tiempoEstimado;
	}
	
}
