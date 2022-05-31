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
	
	public PaqueteDeTrabajo getPaquete(int index)
	{
		return listaPaquetes.get(index);
	}
	
}
