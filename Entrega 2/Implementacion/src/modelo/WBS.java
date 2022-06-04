package modelo;

import java.util.ArrayList;

public class WBS
{
	private int numPaquetes = 1;
	private ArrayList<PaqueteDeTrabajo> listaPaquetes;
	
	public WBS(String nombreProyecto)
	{
		listaPaquetes = new ArrayList<PaqueteDeTrabajo>();
		
		PaqueteDeTrabajo mainWBS = new PaqueteDeTrabajo("WBS_" + nombreProyecto,
		   	   	  "Work Breakdown Structure del proyecto " + nombreProyecto, -1);
		
		listaPaquetes.add(mainWBS);
	}
	
	public void agregarPaquete(PaqueteDeTrabajo paquete)
	{
		PaqueteDeTrabajo padre = listaPaquetes.get(paquete.getIndexPadre());
		padre.agregarSubPaquete(numPaquetes);
		
		listaPaquetes.add(paquete);
		numPaquetes++;
	}
	
	public void agregarTarea(Tarea tarea)
	{
		PaqueteDeTrabajo padre = listaPaquetes.get(tarea.getIndexPadre());
		padre.agregarTarea(tarea);
	}
	
	public boolean paqueteEsBorrable(int indexPaquete)
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
	
	public ArrayList<PaqueteDeTrabajo> getListaPaquetes()
	{
		ArrayList<PaqueteDeTrabajo> copia = new ArrayList<PaqueteDeTrabajo>(listaPaquetes);
		return copia;
	}
	
}
