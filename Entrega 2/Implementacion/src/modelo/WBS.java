package modelo;

import java.util.ArrayList;

public abstract class WBS
{
	private int numPaquetes = 1;
	protected Proyecto elProyecto;
	protected ArrayList<PaqueteDeTrabajo> listaPaquetes;
	
	//CONSTRUCTOR
	public WBS(Proyecto elProyecto)
	{
		String nombreProyecto = elProyecto.getNombre();
		PaqueteDeTrabajo mainWBS = new PaqueteDeTrabajo("WBS_" + nombreProyecto,
		   	   	  "Work Breakdown Structure del proyecto " + nombreProyecto, -1);
		
		this.elProyecto = elProyecto;
		this.listaPaquetes = new ArrayList<PaqueteDeTrabajo>();
		
		listaPaquetes.add(mainWBS);
	}
	
	
	//FUNCIONALIDADES
	public void agregarPaquete(PaqueteDeTrabajo paquete)
	{
		if (!paquete.getIndexPadre().equals(-2)) //-2 es para paquetes vacios
		{
			PaqueteDeTrabajo padre = listaPaquetes.get(paquete.getIndexPadre());
			padre.agregarSubPaquete(numPaquetes);
		}
		
		listaPaquetes.add(paquete);
		numPaquetes++;
	
	}
	
	
	public void agregarTarea(Tarea tarea)
	{
		PaqueteDeTrabajo padre = listaPaquetes.get(tarea.getIndexPadre());
		padre.agregarTarea(tarea);
	}
	
	
	public void borrarPaquete(int indexPaquete) throws Exception
	{	
		/*
		 * Reemplaza el paquete por un paquete vacio.
		 * No se borra del todo para conservar orden de los indices
		 */
		
		if (paqueteEsBorrable(indexPaquete))
		{
			PaqueteDeTrabajo paqueteVacio = new PaqueteDeTrabajo("PaqueteVacio", "", -2);
			listaPaquetes.set(indexPaquete, paqueteVacio);
		}
		
		else
		{
			throw new Exception("El paquete no es borrable");
		}
	}
	
	
	public void borrarTarea(int indexPaquete, String nombreTarea) throws Exception
	{
		PaqueteDeTrabajo elPaquete = listaPaquetes.get(indexPaquete);
		elPaquete.borrarTarea(nombreTarea);
	}
	
	
	public abstract ReporteAvance calcularAvancePaquete(int indexPaquete);
	public abstract ReporteCalidadPlaneacion calcularCalidadPlaneacion();
	public abstract ReporteDesempenoEquipo calcularDesempenoEquipo();
	public abstract ReporteResumenProyecto calcularResumenProyecto();
	
	
	//AUXILIAR
	private boolean paqueteEsBorrable(int indexPaquete)
	{
		PaqueteDeTrabajo paquete = listaPaquetes.get(indexPaquete);
		
		for (Tarea tarea : paquete.getTareas())
		{
			if (!tarea.esBorrable())
			{
				return false;
			}
		}
		
		for (int indexSubPaquete : paquete.getSubPaquetes())
		{
			return paqueteEsBorrable(indexSubPaquete);
		}
		
		return true;
	}
	
	
	//GETTERS
	public PaqueteDeTrabajo getPaquete(int index)
	{
		return listaPaquetes.get(index);
	}
	
	public int getNumPaquetes()
	{
		return listaPaquetes.size();
	}
	
	public ArrayList<PaqueteDeTrabajo> getListaPaquetes()
	{
		ArrayList<PaqueteDeTrabajo> copia = new ArrayList<PaqueteDeTrabajo>(listaPaquetes);
		return copia;
	}
	
}
