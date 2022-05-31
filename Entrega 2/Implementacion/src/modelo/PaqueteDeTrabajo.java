package modelo;

import java.util.HashMap;

public class PaqueteDeTrabajo
{
	private String titulo;
	private String descripcion;
	private HashMap<String, PaqueteDeTrabajo> subPaquetes;
	private HashMap<String, Tarea> tareas;
	
	
	public PaqueteDeTrabajo(String titulo, String descripcion)
	{
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.subPaquetes  = new HashMap<String, PaqueteDeTrabajo>();
		this.tareas  = new HashMap<String, Tarea>();
	}
	
	public void agregarSubPaquete(PaqueteDeTrabajo paquete)
	{
		subPaquetes.put(paquete.getTitulo(), paquete);
	}
	
	public void agregarTarea(Tarea tarea)
	{
		tareas.put(tarea.getNombreTarea(), tarea);
	}
	
	
	//GETTERS
	public String getTitulo()
	{
		return titulo;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public PaqueteDeTrabajo getSubPaquete(String tituloSubPaquete)
	{
		return subPaquetes.get(tituloSubPaquete);
	}
	
	public Tarea getTarea(String tituloTarea)
	{
		return tareas.get(tituloTarea);
	}
}
